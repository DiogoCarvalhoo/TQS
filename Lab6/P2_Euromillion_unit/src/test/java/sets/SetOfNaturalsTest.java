/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddDuplicatedElement() {
        assertThrows(IllegalArgumentException.class, () -> {setB.add(10);}, "add: should not add duplicated elements");
    }

    @Test
    public void testAddInvalidElement() {
        assertThrows(IllegalArgumentException.class, () -> {setA.add(0);}, "add: should not add <=0 elements");
        assertThrows(IllegalArgumentException.class, () -> {setA.add(-1);}, "add: should not add <=0 elements");

    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    public void testFromArrayWithBadValues() {
        int[] invalidelems = new int[]{10, 20, -30};
        int[] validelems = new int[]{10, 20, 30, 40, 50, 60};

        assertThrows(IllegalArgumentException.class, () -> {setA.fromArray(invalidelems);});
        setA = setA.fromArray(validelems);
        assertEquals(setA, setB);
    }

    @Test
    public void testContainsValidation() {
        assertTrue(setB.contains(10), "contains: contains element added before");
        assertFalse(setB.contains(11), "contains: elements not added are not contained");
        assertFalse(setB.contains(0), "contains: elements not added are not contained");
        assertFalse(setB.contains(-1), "contains: elements not added are not contained");

    }

    @Test
    public void testSizeBehaviour() {
        assertEquals(6, setB.size(), "size: showing wrong size of set");
    }


    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");

    }

    @Test
    public void testIntersectWithTrueIntersection() {
        assertTrue(setB.intersects(setD), "intersection but was reported as non-existing");
    }

}
