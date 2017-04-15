/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.utility;

/**
 *
 * @author Nikhil Karnwal
 */
public class Utility {
    public static long getTimeStamp(){
        return System.currentTimeMillis();
    }

    public static long getTimeStamp(long timeToLive) {
        return getTimeStamp() + timeToLive;
    }
}
