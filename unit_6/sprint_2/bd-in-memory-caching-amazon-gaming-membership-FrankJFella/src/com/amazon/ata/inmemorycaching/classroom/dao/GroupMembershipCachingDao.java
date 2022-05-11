package com.amazon.ata.inmemorycaching.classroom.dao;
import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupMembershipCacheKey;
//import com.amazonaws.cache.CacheLoader;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheLoader;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

// This example is using the Google Guava caching framework
// Use this DAO in place of the the GroupMembershipDao to implement caching for the application
// This DAO will cause the GroupMemberchipDao is an entry is not found in the cache
//
// So when the application wants to look at Membership data:
//
//    1. Look in the cache to see if saved
//    2. If not in cache, call the GroupMembershipDao to get it from the database
//
public class GroupMembershipCachingDao {

    // Define a LoadingCache object for the cache
    //                                                   value
    //                          cache-key-data-type    , data-type
    private final LoadingCache<GroupMembershipCacheKey, Boolean>   cache;

    @Inject
    // ctor will instantiate the cache and assign it to the reference for the cache in the object
    // delegate DAO is DAO to be used if an entry is not in the cache
    public GroupMembershipCachingDao(final GroupMembershipDao delegateDao) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(20000)      // Max number of entries in the cache
                .expireAfterWrite(3, TimeUnit.HOURS) // Evict entries this long after they have been written to cache
                .build(CacheLoader.from(delegateDao::isUserInGroup)); // Go build the cache using the specified delegate
    }
    // Method to search the cache for the entry we want
    public boolean isUserInGroup(final String userId, final String groupId) {
        return cache.getUnchecked(new GroupMembershipCacheKey(userId, groupId));
    }
}
