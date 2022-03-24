package com.amazon.ata.inmemorycaching.classroom.dao;

import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupMembershipCacheKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GroupMembershipCachingDaoTest {
    @Mock
    private GroupMembershipDao membershipDao;

    // The unit under test
    @InjectMocks
    private GroupMembershipCachingDao cachingMembershipDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void isUserInGroup_userNotInCache_delegateDaoCalled() {
        // GIVEN
        // A cache key with a known user and group
        String userId  = "userId";
        String groupId = "groupId";
        GroupMembershipCacheKey key = new GroupMembershipCacheKey(userId, groupId);

        // The delegate DAO will return true when asked if the user is in the group
        when(membershipDao.isUserInGroup((key))).thenReturn(true);

        // WHEN
        // We ask the caching DAO if the user is in the group
        boolean result = cachingMembershipDao.isUserInGroup(userId, groupId);

        // THEN
        // The user was in the group
        assertTrue(result,"expected true");
        // The delegate DAO was called
        verify(membershipDao).isUserInGroup(key);
        // There were no other calls to the delegate DAO
        verifyNoMoreInteractions(membershipDao);
    }

    @Test
    public void isUserInGroup_userInCache_delegateDaoNotCalled() {
        // GIVEN
        // A cache key with a known user and group
        String userId = "userId";
        String groupId = "groupId";
        GroupMembershipCacheKey key = new GroupMembershipCacheKey(userId, groupId);

        // The delegate DAO will return true when asked if the user is in the group
        when(membershipDao.isUserInGroup(eq(key))).thenReturn(true);

        // The caching DAO has been "primed" by asking if the user is in the group
        cachingMembershipDao.isUserInGroup(userId, groupId);

        // WHEN
        // We ask the caching DAO if the user is in the group
        boolean result = cachingMembershipDao.isUserInGroup(userId, groupId);

        // THEN
        // The user was in the group
        assertTrue(result, "Expected result to be consistent with the cached GroupMembershipDao's response");

        // The delegate DAO was called exactly once
        verify(membershipDao, times(1)).isUserInGroup(eq(key));
        // There were no other calls to the delegate DAO
        verifyNoMoreInteractions(membershipDao);
    }
}
