# Cache
Java implementation of TTL Cache
This project contain thread safe implementation of in-memory generic cahce using different eviction strategy.
# Eviction Stratagey
1. TTL Cache
  TimeToLive cache works by evicting those data whose time to live has expired after regular interval of time. 
2. LRU Cache
  LRU cache works by evicting the data using Least Recently Used algorithm.

# Usage
Usage of the code can be seen in Cache class which is creating a Singleton wrapper cache class using TTL eviction strategy by creating TTLCache class instance.

    public class Cache<K, V> {
            private static Cache mInstance = new Cache();

            private ICache<K,V> mCacheController;

            private Cache() {
                mCacheController = CacheFactory.<K, V>createCache(CacheType.TTLCache);
                mCacheController.init();
                mCacheController.setEvictionPeriod(5000);
                mCacheController.start();
            }
            ......
    }

    String key = "name";
    String value = "Nikhil";
    long timeToLive = 10000;
    Cache<String, String> instance = Cache.getInstance();
  
# Put
    instance.put(key, value, timeToLive);

# Get
    assertEquals(value, instance.get(key));
# Delete
    instance.delete(key);
    assertNotEquals(instance.get(key), value);
# DeleteCache
    instance.deleteCache();
    assertNotEquals(instance.get(key), value);

# Licence 
check licence https://github.com/nikhilkarnwal/cache/blob/master/LICENSE  file

For any question related to usage or feedback.
Please write to nikhilkarnwal93@gmail.com
