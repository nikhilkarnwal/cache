/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikhil Karnwal
 */
public class CacheTest {
    
    public CacheTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class Cache.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Cache expResult = null;
        Cache result = Cache.getInstance();
        assertEquals(Cache.getInstance(), result);
    }

    /**
     * Test of put method, of class Cache.
     */
    @Test
    public void testPut() {
        try {
            System.out.println("put");
            String key = "name";
            String value = "Nikhil";
            long timeToLive = 10000;
            Cache<String, String> instance = Cache.getInstance();
            instance.put(key, value, timeToLive);
            assertEquals(value, instance.get(key));
            Thread.sleep(timeToLive+10000);
            System.out.println(instance.get(key));
            assertNotEquals(instance.get(key), value);
        } catch (InterruptedException ex) {
            Logger.getLogger(CacheTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of get method, of class Cache.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String key = "n";
        String value = "Nikhil";
        long timeToLive = 10000;
        Cache<String, String> instance = Cache.getInstance();
        instance.put(key, value, timeToLive);
        instance.put("dob", "24sep", timeToLive);
        assertEquals(value, instance.get(key));
        assertNotEquals(instance.get("dob"), value);
        assertEquals("24sep", instance.get("dob"));
    }

    /**
     * Test of delete method, of class Cache.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String key = "n";
        String value = "Nikhil";
        long timeToLive = 10000;
        Cache<String, String> instance = Cache.getInstance();
        instance.put(key, value, timeToLive);
        assertEquals(value, instance.get(key));
        instance.delete(key);
        assertNotEquals(instance.get(key), value);
    }

    /**
     * Test of deleteCache method, of class Cache.
     */
    @Test
    public void testDeleteCache() {
        System.out.println("deleteCache");
        String key = "n";
        String value = "Nikhil";
        long timeToLive = 10000;
        Cache<String, String> instance = Cache.getInstance();
        instance.put(key, value, timeToLive);
        assertEquals(value, instance.get(key));
        instance.deleteCache();
        assertNotEquals(instance.get(key), value);
    }
    
}
