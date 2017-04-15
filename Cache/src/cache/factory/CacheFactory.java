/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.factory;

import cache.controller.TTLCache;
import cache.controller.ICache;

/**
 *
 * @author Nikhil Karnwal
 */
public class CacheFactory<K, V> {
    public static <K, V> ICache<K, V> createCache(CacheType cacheType){
        ICache<K, V> cache = null;
        switch(cacheType){
            case TTLCache:
                cache = new TTLCache<K, V>();
                break;
            default:
                cache = new TTLCache<K, V>();
        }
        return cache;
    }
}
