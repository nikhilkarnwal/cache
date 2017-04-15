/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import cache.controller.ICache;
import cache.utility.Utility;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikhil Karnwal
 */
public class TestThread implements Runnable {

    private Cache<String, String> mCache;
    private String mName;
    private boolean mIsPut;

    public TestThread(Cache cache, String name, boolean isput) {
        mCache = cache;
        mName = name;
        mIsPut = isput;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                if (!mIsPut) {
                    String key = mName + String.valueOf(new Random().nextInt(i));
                    System.out.println("Get for :" + key + " = " + mCache.get(key) + " at " + String.valueOf(mCache.getExpireTime(key) - Utility.getTimeStamp()));
                } else {
                    String key = mName + String.valueOf(i);
                    long ttl = new Random().nextInt(20) * 1000;
                    System.out.println("Put at " + Utility.getTimeStamp() + ": " + key + " for eviction at - " + Utility.getTimeStamp(ttl));
                    mCache.put(key, key, ttl);
                }
                Thread.sleep(new Random().nextInt(5) * 100);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //mCache.deleteCache();
    }

}
