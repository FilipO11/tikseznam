import fri.tik.seznam.BinomskaKopica;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinomskaKopicaTest {

    private BinomskaKopica<String> bk;

    public BinomskaKopicaTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        bk = new BinomskaKopica<>();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testInsertOnEmpty() {
        bk.add("test");
    }

    /**
     * Test of add method
     */
    @ParameterizedTest
    @ValueSource(strings = {"10", "90"})
    public void testInsertOnOne(String x) {
        bk.add("50");
        bk.add(x);
    }

    @Test
    public void testInsertOnTwo() {
        bk.add("10");
        bk.add("20");
        bk.add("30");
    }

    @ParameterizedTest
    @CsvSource({"20, 70", "20, 10", "20, 50", "70, 30"})
    public void testInsertOnThree(ArgumentsAccessor arg) {
        bk.add("60");
        bk.add("40");
        bk.add(arg.get(0).toString());
        bk.add(arg.get(1).toString());
    }

    /**
     * Test of getFirst method
     */
    @Test
    public void testGetFirstOnEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> bk.getFirst());
    }

    @Test
    public void testGetFirstOnOne() {
        bk.add("10");
        assertEquals("10", bk.getFirst());
    }

    @Test
    public void testGetFirstOnTwo() {
        bk.add("10");
        bk.add("20");
        assertEquals("20", bk.getFirst());
    }

    @Test
    public void testGetFirstOnThree() {
        bk.add("10");
        bk.add("20");
        bk.add("15");
        assertEquals("20", bk.getFirst());
    }

    /**
     * Test of size method
     */
    @Test
    public void testSizeOnEmpty() {
        assertEquals(0, bk.size());
    }

    @Test
    public void testSizeOnOne() {
        bk.add("10");
        assertEquals(0, bk.size());
    }

    @Test
    public void testSizeOnTwo() {
        bk.add("10");
        bk.add("20");
        assertEquals(2, bk.size());
    }

    @Test
    public void testSizeOnThree() {
        bk.add("10");
        bk.add("20");
        bk.add("30");
        assertEquals(3, bk.size());
    }

    @Test
    public void testSizeOnFour() {
        bk.add("10");
        bk.add("20");
        bk.add("30");
        bk.add("40");
        assertEquals(4, bk.size());
    }

    /**
     * Test of depth method
     */
    @Test
    public void testDepthOnEmpty() {
        assertEquals(0, bk.depth());
    }

    @Test
    public void testDepthOnOne() {
        bk.add("10");
        assertEquals(1, bk.depth());
    }

    @Test
    public void testDepthOnTwo() {
        bk.add("10");
        bk.add("20");
        assertEquals(2, bk.depth());
    }

    @Test
    public void testDepthOnThree() {
        bk.add("10");
        bk.add("20");
        bk.add("30");
        assertEquals(2, bk.depth());
    }

    /**
     * Test of isEmpty method
     */
    @Test
    public void testIsEmptyOnEmpty() {
        assertTrue(bk.isEmpty());
    }

    @Test
    public void testIsEmptyOnFull() {
        bk.add("10");
        assertFalse(bk.isEmpty());
    }

    /**
     * Test of exists method
     */
    @Test
    public void testExistsOnEmpty() {
        assertFalse(bk.exists("10"));
    }

    @Test
    public void testExistsOnOne() {
        bk.add("10");
        assertTrue(bk.exists("10"));
        assertFalse(bk.exists("20"));
    }

    @Test
    public void testExistsOnTwo() {
        bk.add("10");
        bk.add("20");
        assertTrue(bk.exists("10"));
        assertTrue(bk.exists("20"));
        assertFalse(bk.exists("30"));
    }

    @Test
    public void testExistsOnSix() {
        bk.add("60");
        bk.add("50");
        bk.add("40");
        bk.add("30");
        bk.add("20");
        bk.add("10");
        assertTrue(bk.exists("30"));
        assertFalse(bk.exists("15"));
        assertFalse(bk.exists("70"));
    }

    /**
     * Test of asList method
     */
    @Test
    public void testAsListOnEmpty() {
        assertEquals(new ArrayList<String>(), bk.asList());
    }

    @Test
    public void testAsListOnOne() {
        bk.add("10");
        assertEquals(new ArrayList<>(Arrays.asList("10")), bk.asList());
    }

    @Test
    public void testAsListOnTwo() {
        bk.add("10");
        bk.add("20");
        assertEquals(new ArrayList<>(Arrays.asList("10", "20")), bk.asList());
    }

    @Test
    public void testAsListOnThree() {
        bk.add("10");
        bk.add("20");
        bk.add("30");
        assertEquals(new ArrayList<>(Arrays.asList("30", "10", "20")), bk.asList());
    }

    @Test
    public void testAsListOnFour() {
        bk.add("10");
        bk.add("20");
        bk.add("30");
        bk.add("40");
        assertEquals(new ArrayList<>(Arrays.asList("40", "20", "10", "30")), bk.asList());
    }
}