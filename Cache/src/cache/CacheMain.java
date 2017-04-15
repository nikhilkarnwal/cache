/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikhil Karnwal
 */
public class CacheMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            // TODO code application logic here
            Cache<String, String> cache = Cache.getInstance();
            Thread T1 = new Thread(new TestThread(cache, "TH", true));
            Thread T2 = new Thread(new TestThread(cache, "TH", false));
            T1.start();
            T2.start();
            T1.join();
            T2.join();
            cache.deleteCache();
            //new Thread(new TestThread(cache, "TH_3")).start();
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//        while (true) {
//            System.out.println("Enter option");
//            String input = reader.readLine();
//            switch (Integer.parseInt(input)) {
//                case 1:
//                    System.out.println("Enter into cahce");
//                    String key = reader.readLine();
//                    String Value = reader.readLine();
//                    long ttl = Long.parseLong(reader.readLine());
//                    cache.put(key, Integer.parseInt(Value), ttl);
//                    break;
//                case 2:
//                    key = reader.readLine();
//                    System.out.println("Value for key -" + key + " = " + cache.get(key));
//                    break;
//                case 3:
//                    cache.deleteCache();
//                    return;
//            }
//        }
        } catch (InterruptedException ex) {
            Logger.getLogger(CacheMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
