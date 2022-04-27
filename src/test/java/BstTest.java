/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import fri.tik.seznam.Bst;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Filip
 */
public class BstTest {
    
    static Bst<String> bst;
    
    public BstTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        bst = new Bst<>();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of add method, of class Bst.
     */
    @Test
    public void testAddOne() {
        bst.add("Test");
        assertEquals(1, bst.size());
    }
    
    @Test
    public void testAddMultiple() {
        bst.add("Test1");
        bst.add("Test2");
        assertEquals(2, bst.size());
    }
    
    @Test
    public void testAddDuplicate() {
        bst.add("Test");
        assertThrows(java.lang.IllegalArgumentException.class, () -> bst.add("Test"));
    }

    /**
     * Test of removeFirst method, of class Bst.
     */
    @Test
    public void testRemoveFirstOnEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> bst.removeFirst());
    }
    
    @Test
    public void testRemoveFirstOnOne() {
        bst.add("Test");
        assertEquals("Test", bst.removeFirst());
        assertTrue(bst.isEmpty());
    }
    
    @Test
    public void testRemoveFirstOnTwo() {
        bst.add("Test2");
        bst.add("Test1");
        assertEquals("Test2", bst.removeFirst());
        assertEquals("Test1", bst.getFirst());
    }
    
    @Test
    public void testRemoveFirstOnThree() {
        bst.add("Test2");
        bst.add("Test1");
        bst.add("Test3");
        assertEquals("Test2", bst.removeFirst());
        assertEquals("Test3", bst.getFirst());
    }
    
    @Test
    public void testRemoveFirstOnFull() {
        bst.add("Test3");
        bst.add("Test2");
        bst.add("Test5");
        bst.add("Test1");
        bst.add("Test4");
        bst.add("Test6");
        assertEquals("Test3", bst.removeFirst());
        assertEquals("Test4", bst.getFirst());
    }

    /**
     * Test of getFirst method, of class Bst.
     */
    @Test
    public void testGetFirstOnEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> bst.getFirst());
    }
    
    @Test
    public void testGetFirstOnOne() {
        bst.add("Test");
        assertEquals("Test", bst.getFirst());
    }

    /**
     * Test of size method, of class Bst.
     */
    @Test
    public void testSizeOnEmpty() {
        assertEquals(0, bst.size());
    }
    
    @Test
    public void testSizeOnOne() {
        bst.add("Test");
        assertEquals(1, bst.size());
    }
    
    @Test
    public void testSizeOnFull() {
        bst.add("Test3");
        bst.add("Test2");
        bst.add("Test5");
        bst.add("Test1");
        bst.add("Test4");
        bst.add("Test6");
        assertEquals(6, bst.size());
    }

    /**
     * Test of depth method, of class Bst.
     */
    @Test
    public void testDepthOnEmpty() {
        assertEquals(0, bst.depth());
    }
    
    @Test
    public void testDepthOnOne() {
        bst.add("Test");
        assertEquals(1, bst.depth());
    }
    
    @Test
    public void testDepthOnFull() {
        bst.add("Test3");
        bst.add("Test2");
        bst.add("Test5");
        bst.add("Test1");
        bst.add("Test4");
        bst.add("Test6");
        bst.add("Test7");
        assertEquals(4, bst.depth());
    }

    /**
     * Test of isEmpty method, of class Bst.
     */
    @Test
    public void testIsEmptyOnEmpty() {
        assertTrue(bst.isEmpty());
    }
    
    @Test
    public void testIsEmptyOnFull() {
        bst.add("Test");
        assertFalse(bst.isEmpty());
    }

    /**
     * Test of remove method, of class Bst.
     */
    @Test
    public void testRemoveOnEmpty() {
        assertThrows(java.util.NoSuchElementException.class, ()-> {bst.remove("test");});
    }

    @Test
    public void testRemoveOnOne() {
        bst.add("Test");
        assertEquals("Test", bst.remove("Test"));
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testRemoveOnTwoRightIsNull() {
        bst.add("Test2");
        bst.add("Test1");
        assertEquals("Test2", bst.remove("Test2"));
        assertEquals("Test1", bst.getFirst());
    }

    @Test
    public void testRemoveOnTwoLeftIsNull() {
        bst.add("Test2");
        bst.add("Test3");
        assertEquals("Test2", bst.remove("Test2"));
        assertEquals("Test3", bst.getFirst());
    }
    
    @Test
    public void testRemoveOnFull() {
        bst.add("Test3");
        bst.add("Test2");
        bst.add("Test5");
        bst.add("Test1");
        bst.add("Test4");
        bst.add("Test6");
        bst.add("Test7");
        bst.remove("Test5");
        assertFalse(bst.exists("Test5"));
        assertTrue(bst.exists("Test4"));
        assertEquals(3, bst.depth());
    }

    /**
     * Test of exists method, of class Bst.
     */
    @Test
    public void testExistsOnEmpty() {
        assertFalse(bst.exists("Test"));
    }
    
    @Test
    public void testExistsOnOne() {
        bst.add("Test");
        assertTrue(bst.exists("Test"));
        assertFalse(bst.exists("Test1"));
    }
    
    @Test
    public void testExistsOnFull() {
        bst.add("Test3");
        bst.add("Test2");
        bst.add("Test5");
        bst.add("Test1");
        bst.add("Test4");
        bst.add("Test6");
        bst.add("Test7");
        assertTrue(bst.exists("Test4"));
    }

    @Test
    public void testAsListOnEmpty() {
        assertEquals(new ArrayList<String>(), bst.asList());
    }

    @Test
    public void testAsListOnFull() {
        bst.add("07");
        bst.add("04");
        bst.add("03");
        bst.add("15");
        bst.add("12");
        bst.add("08");
        bst.add("13");
        assertEquals(new ArrayList<>(Arrays.asList("03", "04", "07", "08", "12", "13", "15")), bst.asList());
    }
    
}
