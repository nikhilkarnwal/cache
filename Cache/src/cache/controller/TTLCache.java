/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controller;

import cache.utility.Utility;
import cache.database.DataStore;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikhil Karnwal
 */
public class TTLCache<K, V> implements ICache<K, V> {

    class DataNode {

        public long mTimeStamp;
        public K mKey;

        public DataNode(long timeStamp, K key) {
            this.mTimeStamp = timeStamp;
            this.mKey = key;
        }
    }

    private PriorityQueue<DataNode> mTimeStampKeyList = new PriorityQueue<>(new Comparator<DataNode>() {
        @Override
        public int compare(DataNode o1, DataNode o2) {
            return (int) (o1.mTimeStamp - o2.mTimeStamp);
        }
    });
    private boolean mIsEviction = false;
    private long mEvictionPeriod = 1;
    private DataStore<K, V> mDataStore;

    public TTLCache() {
        this.mDataStore = new DataStore();
    }

    @Override
    public void init() {
    }

    @Override
    public synchronized void start() {
        if (!mIsEviction) {
            this.mIsEviction = true;
            startEvictionService();
        }
    }

    @Override
    public synchronized void stop() {
        this.mIsEviction = false;
    }

    @Override
    public void put(K key, V value) {
        put(key, value, 0);
    }

    @Override
    public void put(K key, V value, long timeToLive) {
        start();
        timeToLive = timeToLive!=0?timeToLive:((long)1)*(365*24*60*60*1000);
        long timeStamp = Utility.getTimeStamp(timeToLive);
        synchronized (mTimeStampKeyList) {
            mDataStore.put(key, value, timeStamp);
            mTimeStampKeyList.add(new DataNode(timeStamp, key));
        }
    }

    @Override
    public V get(K key) {
        return mDataStore.get(key);
    }
    
    @Override
    public long getExpireTime(K key) {
        return mDataStore.getExpireTime(key);
    }

    @Override
    public synchronized void setEvictionPeriod(long period) {
        mEvictionPeriod = period;
    }

    @Override
    public void delete(K key) {
        mDataStore.remove(key);
    }

    @Override
    public void deleteCache() {
        mDataStore.removeAll();
        stop();
        mTimeStampKeyList.clear();
    }

    private void startEvictionService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mIsEviction) {
                    synchronized (mTimeStampKeyList) {
                        evictData();
                    }
                    try {
                        Thread.sleep(mEvictionPeriod);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TTLCache.class.getName()).log(Level.SEVERE, null, ex);
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }

    private void evictData() {
        while (mTimeStampKeyList.isEmpty() == false
                && mTimeStampKeyList.peek().mTimeStamp <= Utility.getTimeStamp()) {
            DataNode currNode = mTimeStampKeyList.poll();
            mDataStore.remove(currNode.mKey);
        }
    }
}
