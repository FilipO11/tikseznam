/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import fri.tik.seznam.Sklad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author Filip
 */
public class SkladTest {
    
    public SkladTest() {
    }

    @Test
    public void testPush() {
        Sklad instance = new Sklad();
        String a = "Test";
        instance.add(a);
    }

    @Test
    public void testPop() {
        Sklad<String> instance = new Sklad<>();
        String a = "Test";
        instance.add(a);
        String b = instance.removeFirst();
        assertEquals("Test", b);
    }
    
    @Test
    public void testPopWithTwoElements(){
        Sklad<String> instance = new Sklad<>();
        String a = "Prvi test";
        String b = "Drugi test";
        instance.add(a);
        instance.add(b);
        assertEquals(b, instance.removeFirst());
        assertEquals(a, instance.removeFirst());
    }
    
    @Test
    public void testPopOnEmptyStack(){
        Sklad<String> instance = new Sklad<>();
        assertThrows(java.util.NoSuchElementException.class,
                                        ()-> {instance.removeFirst();});
    }
    
    @Test
    public void testIsEmptyOnEmpty() {
        Sklad<String> instance = new Sklad<>();
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyOnFull() {
        Sklad<String> instance = new Sklad<>();
        instance.add("Test");
        assertFalse(instance.isEmpty());
    }
    
    @Test
    public void testTopOnEmpty() {
        Sklad<String> instance = new Sklad<>();
        assertThrows(java.util.NoSuchElementException.class,
                                        ()-> {instance.getFirst();});
    }
    
    @Test
    public void testTopWithOneElement() {
        Sklad<String> instance = new Sklad<>();
        instance.add("Test");
        assertEquals("Test", instance.getFirst());
    }
    
    @Test
    public void testTopWithNElements() {
        Sklad<String> instance = new Sklad<>();
        String a = "Prvi test";
        String b = "Drugi test";
        instance.add(a);
        instance.add(b);
        assertEquals(b, instance.getFirst());
    }
    
    @Test
    public void testSizeOnEmpty() {
        Sklad<String> instance = new Sklad<>();
        assertEquals(0, instance.size());
    }
    
    @Test
    public void testSizeWithOneElement() {
        Sklad<String> instance = new Sklad<>();
        instance.add("Test");
        assertEquals(1, instance.size());
    }
    
    @Test
    public void testSizeWithNElements() {
        Sklad<String> instance = new Sklad<>();
        String a = "Prvi test";
        String b = "Drugi test";
        instance.add(a);
        instance.add(b);
        assertEquals(2, instance.size());
    }
    
    @Test
    public void testSearchOnEmpty() {
        Sklad<String> instance = new Sklad<>();
        assertEquals(-1, instance.search(""));
    }
    
    @Test
    public void testSearchWithOneElement() {
        Sklad<String> instance = new Sklad<>();
        instance.add("test");
        assertEquals(0, instance.search("test"));
    }
    
    @Test
    public void testSearchWithNElements() {
        Sklad<String> instance = new Sklad<>();
        instance.add("test1");
        instance.add("test2");
        instance.add("test3");
        assertEquals(1, instance.search("test2"));
    }

    @Test
    public void testIsTopOnEmpty() {
        Sklad<String> instance = new Sklad<>();
        assertThrows(java.util.NoSuchElementException.class,
                ()-> {instance.isTop("test");});
    }

    @Test
    public void testIsTopOnFull() {
        Sklad<String> instance = new Sklad<>();
        instance.add("test1");
        assertTrue(instance.isTop("test1"));
        assertFalse(instance.isTop("test2"));
    }
    
    @Test
    public void testRemoveOnEmpty() {
        Sklad<String> instance = new Sklad<>();
        assertThrows(java.util.NoSuchElementException.class,
                                        ()-> {instance.remove("test");});
    }
    
    @Test
    public void testRemoveMissing() {
        Sklad<String> instance = new Sklad<>();
        instance.add("test1");
        assertThrows(java.util.NoSuchElementException.class,
                                        ()-> {instance.remove("test");});
    }
    
    @Test
    public void testRemoveTop() {
        Sklad<String> instance = new Sklad<>();
        instance.add("test1");
        assertEquals("test1", instance.remove("test1"));
        assertTrue(instance.isEmpty());
    }
    
    @Test
    public void testRemoveOnFull() {
        Sklad<String> instance = new Sklad<>();
        instance.add("test1");
        instance.add("test2");
        assertEquals("test1", instance.remove("test1"));
        assertFalse(instance.exists("test1"));
        assertTrue(instance.exists("test2"));
    }
    
    @Test
    public void testExistsOnEmpty() {
        Sklad<String> instance = new Sklad<>();
        assertFalse(instance.exists("test"));
    }
    
    @Test
    public void testExistsOnFull() {
        Sklad<String> instance = new Sklad<>();
        instance.add("test1");
        assertTrue(instance.exists("test1"));
        assertFalse(instance.exists("test"));
    }

}
