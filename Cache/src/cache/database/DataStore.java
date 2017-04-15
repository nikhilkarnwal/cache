/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.database;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Nikhil Karnwal
 */
public class DataStore<K, V> {
    
    class ValueNode{
        public V Value;
        public long TimeStamp;
        public ValueNode(V value, long timeStamp){
            Value = value;
            TimeStamp = timeStamp;
        }
    }
    
    private final ConcurrentHashMap<K, ValueNode> mKeyValueMap;
    
    public DataStore(){
        this.mKeyValueMap = new ConcurrentHashMap<>();
    }
    
    public void put(K key, V value, long timeStamp){
        mKeyValueMap.put(key, new ValueNode(value, timeStamp));
    }
    
    public void put(K key, V value){
        put(key, value, 0);
    }
    
    public void remove(K Key){
        mKeyValueMap.remove(Key);
    }
    
    public void removeAll(){
        mKeyValueMap.clear();
    }

    public V get(K key) {
        return mKeyValueMap.containsKey(key)?mKeyValueMap.get(key).Value:null;
    }
    
    public long getExpireTime(K key){
        return mKeyValueMap.containsKey(key)?mKeyValueMap.get(key).TimeStamp:Long.MAX_VALUE;
    }
}
