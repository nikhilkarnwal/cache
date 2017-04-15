/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controller;

/**
 *
 * @author Nikhil Karnwal
 */
public interface ICache<K, V> {
    public void init();
    public void start();
    public void stop();
    public void put(K key, V value);
    public void put(K key, V value, long timeToLive);
    public V get(K key);
    public long getExpireTime(K key);
    public void setEvictionPeriod(long period);

    public void delete(K key);

    public void deleteCache();
}
