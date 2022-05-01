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
    @CsvSource(value = {"' pv'; OK", "' sk'; OK", "' bst'; OK", "' bk'; OK",
            "''; Error: please specify a data structure type (pv, sk, bst, bk)",
            "' aaa'; Error: please specify a correct data structure type (pv, sk, bst, bk)"},
                delimiterString = ";")
    public void testUse(ArgumentsAccessor arg) {
        assertEquals(arg.getString(1), uv.processInput(String.format("use%s", arg.getString(0))));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testAddNothing(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: please specify a string", uv.processInput("add"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testAdd(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testAddMultipleWords(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test One"));
        assertEquals("OK", uv.processInput("add Test Two"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testRemoveFirstOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: structure is empty", uv.processInput("remove_first"));
    }
    
    @ParameterizedTest
    @CsvSource({"pv, Test", "sk, Test", "bst, Test", "bk, 'Unsupported operation on BinomskaKopica'"})
    public void testRemoveFirst(ArgumentsAccessor arg) {
        assertEquals("OK", uv.processInput(String.format("use %s", arg.get(0))));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals(arg.get(1), uv.processInput("remove_first"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testGetFirstOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: structure is empty", uv.processInput("get_first"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testGetFirst(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("get_first"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testSizeOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("0", uv.processInput("size"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testSize(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test5"));
        assertEquals("3", uv.processInput("size"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testDepthOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("0", uv.processInput("depth"));
    }
    
    @ParameterizedTest
    @CsvSource({"pv, 3", "sk, 1", "bst, 4", "bk, 3"})
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
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testIsEmptyOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Structure is empty", uv.processInput("is_empty"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testIsEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Structure is not empty", uv.processInput("is_empty"));
    }
    
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testRemoveNothing(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: please specify a string", uv.processInput("remove"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testRemoveOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: structure is empty", uv.processInput("remove Test"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testRemoveEltNotFound(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Element not found", uv.processInput("remove Test1"));
    }
    
    @ParameterizedTest
    @CsvSource({"pv, Test3, Test2, Test5", "sk, Test3, Test2, Test5", "bst, Test3, Test2, Test5",
            "bk, 'Unsupported operation on BinomskaKopica', 'Unsupported operation on BinomskaKopica', 'Unsupported operation on BinomskaKopica'"})
    public void testRemove(ArgumentsAccessor arg) {
        assertEquals("OK", uv.processInput(String.format("use %s", arg.get(0))));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test5"));
        assertEquals(arg.get(1), uv.processInput("remove Test3"));
        assertEquals(arg.get(2), uv.processInput("remove Test2"));
        assertEquals(arg.get(3), uv.processInput("remove Test5"));
    }

    @ParameterizedTest
    @CsvSource({"pv, 'Test One'", "sk, 'Test One'", "bst, 'Test One'", "bk, 'Unsupported operation on BinomskaKopica'"})
    public void testRemoveMultipleWords(ArgumentsAccessor arg) {
        assertEquals("OK", uv.processInput(String.format("use %s", arg.get(0))));
        assertEquals("OK", uv.processInput("add Test One"));
        assertEquals(arg.get(1), uv.processInput("remove Test One"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testExistsOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Element is not in the structure", uv.processInput("exists Test"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testExistsNothing(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("Error: please specify a string", uv.processInput("exists"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testExists(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Element is in the structure", uv.processInput("exists Test"));
        assertEquals("Element is not in the structure", uv.processInput("exists Test1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testExistsMultipleWords(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("add Test One"));
        assertEquals("Element is in the structure", uv.processInput("exists Test One"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testAsListOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("[]", uv.processInput("as_list"));
    }

    @ParameterizedTest
    @CsvSource(value = {"pv; [4, 3, 1, 2]", "sk; [4, 1, 2, 3]", "bst; [1, 2, 3, 4]", "bk; [4, 3, 2, 1]"},
                delimiterString = ";")
    public void testAsList(ArgumentsAccessor arg) {
        assertEquals("OK", uv.processInput(String.format("use %s", arg.get(0))));
        assertEquals("OK", uv.processInput("add 3"));
        assertEquals("OK", uv.processInput("add 2"));
        assertEquals("OK", uv.processInput("add 1"));
        assertEquals("OK", uv.processInput("add 4"));
        assertEquals(arg.get(1), uv.processInput("as_list"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pv", "sk", "bst", "bk"})
    public void testResetOnEmpty(String structure) {
        assertEquals("OK", uv.processInput(String.format("use %s", structure)));
        assertEquals("OK", uv.processInput("reset"));
    }

    @ParameterizedTest
    @CsvSource({"pv, OK", "sk, OK", "bst, OK", "bk, 'Unsupported operation on BinomskaKopica'"})
    public void testReset(ArgumentsAccessor arg) {
        assertEquals("OK", uv.processInput(String.format("use %s", arg.get(0))));
        assertEquals("OK", uv.processInput("add 1"));
        assertEquals(arg.get(1), uv.processInput("reset"));
    }
}
