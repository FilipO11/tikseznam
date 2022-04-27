/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import fri.tik.seznam.PrioritetnaVrsta;
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
public class PrioritetnaVrstaTest {
    
    static PrioritetnaVrsta<String> pv;
    
    public PrioritetnaVrstaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        pv = new PrioritetnaVrsta<>(10);
    }
    
    @AfterEach
    public void tearDown() {
    }

     /** Test metod razreda <PrioritetnaVrsta> */
	
    // testi dodajanja

    @Test
    public void testAddOne() {
        pv.add("Test");
    }
    
    @Test
    public void testAddMultiple() {
        pv.add("Test1");
        pv.add("Test2");
    }
    
    @Test
    public void testAddOverflow() {
        pv = new PrioritetnaVrsta<>(2);
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
    }
    
    // testi brisanja
	
    @Test
    public void testRemoveFirstEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> pv.removeFirst());
    }
    
    @Test
    public void testRemoveFirstOne() {
        pv.add("Test");
        assertEquals("Test", pv.removeFirst());
    }
    
    @Test
    public void testRemoveFirstMultiple() {
        pv.add("Test9");
        pv.add("Test5");
        pv.add("Test8");
        pv.add("Test2");
        pv.add("Test4");
        pv.add("Test3");
        assertEquals("Test9", pv.removeFirst());
        assertEquals("Test8", pv.removeFirst());
        assertEquals("Test5", pv.removeFirst());
        assertEquals("Test4", pv.removeFirst());
        assertEquals("Test3", pv.removeFirst());
        assertEquals("Test2", pv.removeFirst());
    }
    
    // metoda get
	
    @Test
    public void testGetFirstEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> pv.getFirst());
    }
    
    @Test
    public void testGetFirstOne() {
        pv.add("Test");
        assertEquals("Test", pv.getFirst());
    }
    
    @Test
    public void testGetFirstMultiple() {
        pv.add("Test1");
        assertEquals("Test1", pv.getFirst());
        pv.add("Test3");
        pv.add("Test2");
        assertEquals("Test3", pv.getFirst());
        assertEquals("Test3", pv.getFirst());
    }
    
    // testiranje metode za globino
	
    @Test
    public void testDepthEmpty() {
        assertEquals(0, pv.depth());
    }
    
    @Test
    public void testDepthOne() {
        pv.add("Test1");
        assertEquals(1, pv.depth());
    }

    @Test
    public void testDepthMultiple() {
        pv.add("Test1");
        assertEquals(1, pv.depth());
        pv.add("Test5");
        assertEquals(2, pv.depth());
        pv.add("Test2");
        assertEquals(2, pv.depth());
        pv.add("Test4");
        assertEquals(3, pv.depth());
        pv.add("Test3");
        assertEquals(3, pv.depth());
        pv.add("Test6");
        assertEquals(3, pv.depth());
        pv.add("Test8");
        assertEquals(3, pv.depth());
        pv.add("Test7");
        assertEquals(4, pv.depth());
    }
    
    // test metode size
	
    @Test
    public void testSizeEmpty() {
        assertEquals(0, pv.size());
    }
    
    @Test
    public void testSizeOne() {
        pv.add("Test");
        assertEquals(1, pv.size());
    }
    
    @Test
    public void testSizeMultiple() {
        assertEquals(0, pv.size());
        pv.add("Test");
        assertEquals(1, pv.size());
        pv.add("Test1");
        assertEquals(2, pv.size());
        pv.add("Test2");
        assertEquals(3, pv.size());
    }
    
    // test metode isEmpty
	
    @Test
    public void testIsEmptyEmpty() {
        assertTrue(pv.isEmpty());
    }

    @Test
    public void testIsEmptyOne() {
        pv.add("Test");
        assertFalse(pv.isEmpty());
    }
    
    @Test
    public void testIsEmptyMultiple() {
        pv.add("Test");
        pv.add("Test1");
        pv.add("Test2");
        assertFalse(pv.isEmpty());
    }
    
    @Test
    public void testRemoveOnEmpty() {
        assertThrows(java.util.NoSuchElementException.class, ()-> {pv.remove("test");});
    }

    @Test
    public void testRemoveFirst() {
        pv.add("Test");
        assertEquals("Test", pv.remove("Test"));
        assertFalse(pv.exists("Test"));
    }

    @Test
    public void testRemoveLast() {
        pv.add("Test1");
        pv.add("Test2");
        assertEquals("Test1", pv.remove("Test1"));
        assertFalse(pv.exists("Test1"));
    }
    
    @Test
    public void testRemoveOnFull() {
        pv.add("Test90");
        pv.add("Test50");
        pv.add("Test80");
        pv.add("Test20");
        pv.add("Test40");
        pv.add("Test30");
        assertEquals("Test50", pv.remove("Test50"));
        assertFalse(pv.exists("Test50"));
        pv.add("Test50");
        assertEquals("Test20", pv.remove("Test20"));
        assertFalse(pv.exists("Test20"));
        pv.add("Test10");
        assertEquals("Test40", pv.remove("Test40"));
        assertFalse(pv.exists("Test40"));
        pv.add("Test70");
        assertEquals("Test50", pv.remove("Test50"));
        assertFalse(pv.exists("Test50"));
        pv.add("Test40");
        assertEquals("Test10", pv.remove("Test10"));
        assertFalse(pv.exists("Test10"));
        pv.add("Test50");
        assertEquals("Test70", pv.remove("Test70"));
        assertFalse(pv.exists("Test70"));
        assertEquals("Test50", pv.remove("Test50"));
        assertFalse(pv.exists("Test50"));
    }
    
    @Test
    public void testExistsOnEmpty() {
        assertFalse(pv.exists("Test"));
    }
    
    @Test
    public void testExistsOnFull() {
        pv.add("Test1");
        assertTrue(pv.exists("Test1"));
        assertFalse(pv.exists("Test"));
    }
    
}
