package com.amazon.ata.inmemorycaching.classroom.dao.models;

import java.util.Objects;

// Due to our requiring multiple values to identify a cache entry
//     we use a class to encapsulate the values in a single entity/object
//
// A cache key is POJO with ctor, getters, equals(), hashCode()
//         remember: equals() and hashCode() should only use immutable values

public final class GroupMembershipCacheKey {

    private final String userId;   // an individual
    private final String groupId;  // a Group they belong to

    public GroupMembershipCacheKey(final String userId, final String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        GroupMembershipCacheKey request = (GroupMembershipCacheKey) obj;

        return userId.equals(request.userId) && groupId.equals(request.groupId);
    }
}
