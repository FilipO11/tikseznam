/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import fri.tik.seznam.SeznamiUV;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Filip
 */
public class SeznamiUVTest {
    
    private SeznamiUV uv;
    
    public SeznamiUVTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        uv = new SeznamiUV();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testUnrecognizedCommand() {
        assertEquals("Unrecognized command: abc", uv.processInput("abc test"));
    }
    
    @ParameterizedTest
    @CsvSource(value = {"' pv'; OK", "' sk'; OK", "' bst'; OK", "''; Error: please specify a data structure type (pv, sk, bst)", "' aaa'; Error: please specify a correct data structure type (pv, sk, bst)"},
                delimiterString = ";")
    public void testUse(ArgumentsAccessor arg) {
        assertEquals(arg.getString(1), uv.processInput(String.format("use%s", arg.getString(0))));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testAddNothing(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: please specify a string", uv.processInput("add"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testAdd(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testAddMultipleWords(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test One"));
        assertEquals("OK", uv.processInput("add Test Two"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testRemoveFirstOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: structure is empty", uv.processInput("remove_first"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testRemoveFirst(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("remove_first"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testGetFirstOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: structure is empty", uv.processInput("get_first"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testGetFirst(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("get_first"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testSizeOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("0", uv.processInput("size"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testSize(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test5"));
        assertEquals("3", uv.processInput("size"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testDepthOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("0", uv.processInput("depth"));
    }
    
    @ParameterizedTest
    @CsvSource({"pv, 3", "sk, 1", "bst, 4"})
    public void testDepth(ArgumentsAccessor arg) {
        assertEquals("OK", uv.processInput(String.format("use %s", arg.get(0))));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test5"));
        assertEquals("OK", uv.processInput("add Test8"));
        assertEquals("OK", uv.processInput("add Test9"));
        assertEquals(arg.get(1), uv.processInput("depth"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testIsEmptyOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Structure is empty", uv.processInput("is_empty"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testIsEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Structure is not empty", uv.processInput("is_empty"));
    }
    
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testRemoveNothing(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: please specify a string", uv.processInput("remove"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testRemoveOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: structure is empty", uv.processInput("remove Test"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testRemoveEltNotFound(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Element not found", uv.processInput("remove Test1"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testRemove(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test5"));
        assertEquals("Test3", uv.processInput("remove Test3"));
        assertEquals("Test2", uv.processInput("remove Test2"));
        assertEquals("Test5", uv.processInput("remove Test5"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testRemoveMultipleWords(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test One"));
        assertEquals("Test One", uv.processInput("remove Test One"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testExistsOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Element is not in the structure", uv.processInput("exists Test"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testExistsNothing(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: please specify a string", uv.processInput("exists"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testExists(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Element is in the structure", uv.processInput("exists Test"));
        assertEquals("Element is not in the structure", uv.processInput("exists Test1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst"})
    public void testExistsMultipleWords(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test One"));
        assertEquals("Element is in the structure", uv.processInput("exists Test One"));
    }
    
    /*
    @Test
    public void testPushBasic() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testPushBasic");
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
    }
    
    @Test
    public void testPushMultipleWords() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testPushMultipleWords");
        assertEquals("OK",
        uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("1", uv.processInput("s_size"));
        assertEquals("OK", uv.processInput("s_add \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("s_size"));
    }
    
    @Test
    public void testPushNothing() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testPushNothing");
        assertEquals("Error: please specify a string", uv.processInput("s_add"));
    }
    
    @Test
    public void testPopBasic() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testPopBasic");
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("Test2", uv.processInput("s_remove_first"));
        assertEquals("Test1", uv.processInput("s_remove_first"));
    }
    
    @Test
    public void testPopMultipleWords() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testPopMultipleWords");
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("OK", uv.processInput("s_add \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("s_size"));
        assertEquals("Another test with multiple words", uv.processInput("s_remove_first"));
        assertEquals("1", uv.processInput("s_size"));
        assertEquals("Test with multiple words", uv.processInput("s_remove_first"));
        assertEquals("0", uv.processInput("s_size"));
    }
    
    @Test
    public void testPopNothing() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testPopNothing");
        assertEquals("Error: stack is empty", uv.processInput("s_remove_first"));
        assertEquals("Error: please specify a string", uv.processInput("s_add"));
        assertEquals("Error: stack is empty", uv.processInput("s_remove_first"));
    }
    
    @Test
    public void testResetOnEmpty() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testResetOnEmpty");
        assertEquals("OK", uv.processInput("s_reset"));
    }
    
    @Test
    public void testResetOnFull() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testResetOnFull");
        assertEquals("OK", uv.processInput("s_add Test"));
        assertEquals("OK", uv.processInput("s_reset"));
        assertEquals("Error: stack is empty", uv.processInput("s_remove_first"));
        assertEquals("0", uv.processInput("s_size"));
    }
    
    @Test
    public void testCountOnEmpty() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testCountOnEmpty");
        assertEquals("0", uv.processInput("s_size"));
    }
    
    @Test
    public void testCountOne() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testCountOne");
        assertEquals("OK", uv.processInput("s_add Test"));
        assertEquals("1", uv.processInput("s_size"));
    }
    
    @Test
    public void testCountTwo() {
        //SeznamiUV uv = new SeznamiUV();
        System.out.println("testCountTwo");
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("2", uv.processInput("s_size"));
    }
    
    @Test
    public void testIsTopNoArgs() {
        System.out.println("testIsTopOnEmpty");
        assertEquals("Error: please specify a string", uv.processInput("s_get_first"));
    }
    
    @Test
    public void testIsTopOnEmpty() {
        System.out.println("testIsTopOnEmpty");
        assertEquals("Error: stack is empty", uv.processInput("s_get_first test"));
    }
    
    @Test
    public void testIsTopOnWrongElement() {
        System.out.println("testIsTopOnWrongElement");
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("Error: wrong element", uv.processInput("s_get_first test"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("Error: wrong element", uv.processInput("s_get_first Test1"));
    }
    
    //@Disabled("To be implemented at a later stage")
    @Test
    public void testIsTopMultipleWords() {
        System.out.println("testIsTopMultipleWords");
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("OK", uv.processInput("s_get_first \"Test with multiple words\""));
        assertEquals("OK", uv.processInput("s_add \"Another test with multiple words\""));
        assertEquals("Error: wrong element", uv.processInput("s_get_first \"Test with multiple words\""));
    }
    
    @Test
    public void testSearchNoArgs() {
        System.out.println("testSearchNoArgs");
        assertEquals("Error: please specify a string", uv.processInput("s_search"));
    }
    
    @Test
    public void testSearchOnEmpty() {
        System.out.println("testSearchOnEmpty");
        assertEquals("-1", uv.processInput("s_search test"));
    }
    
    @Test
    public void testSearchOnFull() {
        System.out.println("testSearchOnFull");
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("OK", uv.processInput("s_add Test3"));
        assertEquals("2", uv.processInput("s_search Test1"));
        assertEquals("1", uv.processInput("s_search Test2"));
        assertEquals("0", uv.processInput("s_search Test3"));
    }
    
    @Test
    public void testPQAdd() {
        System.out.println("testPQAdd");
        assertEquals("OK", uv.processInput("pq_add Test1"));
    }
    
    @Test
    public void testPQAddNone() {
        System.out.println("testPQAddNone");
        assertEquals("Error: please specify a string", uv.processInput("pq_add"));
    }
    
    @Test
    public void testPQRemoveFirst() {
        System.out.println("testPQRemoveFirst");
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("Test1", uv.processInput("pq_remove_first"));
    }
    
    @Test
    public void testPQRemoveFirstOnEmpty() {
        System.out.println("testPQRemoveFirstOnEmpty");
        assertEquals("Error: priority queue is empty", uv.processInput("pq_remove_first"));
    }
    
    @Test
    public void testPQGetFirst() {
        System.out.println("testPQGetFirst");
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("Test1", uv.processInput("pq_get_first"));
    }
    
    @Test
    public void testPQGetFirstOnEmpty() {
        System.out.println("testPQGetFirstOnEmpty");
        assertEquals("Error: priority queue is empty", uv.processInput("pq_get_first"));
    }
    
    @Test
    public void testPQDepthOnEmpty() {
        System.out.println("testPQDepthOnEmpty");
        assertEquals("0", uv.processInput("pq_depth"));
    }
    
    @Test
    public void testPQDepthOnFull() {
        System.out.println("testPQDepthOnFull");
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("OK", uv.processInput("pq_add Test2"));
        assertEquals("OK", uv.processInput("pq_add Test3"));
        assertEquals("OK", uv.processInput("pq_add Test4"));
        assertEquals("OK", uv.processInput("pq_add Test5"));
        assertEquals("OK", uv.processInput("pq_add Test6"));
        assertEquals("OK", uv.processInput("pq_add Test7"));
        assertEquals("OK", uv.processInput("pq_add Test8"));
        assertEquals("OK", uv.processInput("pq_add Test9"));
        assertEquals("4", uv.processInput("pq_depth"));
    }
    
    @Test
    public void testPQSizeOnEmpty() {
        System.out.println("testPQSizeOnEmpty");
        assertEquals("0", uv.processInput("pq_size"));
    }
    
    @Test
    public void testPQSizeOnFull() {
        System.out.println("testPQSizeOnFull");
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("OK", uv.processInput("pq_add Test2"));
        assertEquals("OK", uv.processInput("pq_add Test3"));
        assertEquals("OK", uv.processInput("pq_add Test4"));
        assertEquals("OK", uv.processInput("pq_add Test5"));
        assertEquals("OK", uv.processInput("pq_add Test6"));
        assertEquals("OK", uv.processInput("pq_add Test7"));
        assertEquals("OK", uv.processInput("pq_add Test8"));
        assertEquals("8", uv.processInput("pq_size"));
    }
    
    @Test
    public void testPQIsEmptyOnEmpty() {
        System.out.println("testPQIsEmptyOnEmpty");
        assertEquals("Priority queue is empty", uv.processInput("is_empty"));
    }
    
    @Test
    public void testPQIsEmptyOnFull() {
        System.out.println("testPQIsEmptyOnFull");
        assertEquals("OK", uv.processInput("pq_add Test1"));
        assertEquals("Priority queue is not empty", uv.processInput("is_empty"));
    }
    */
}
