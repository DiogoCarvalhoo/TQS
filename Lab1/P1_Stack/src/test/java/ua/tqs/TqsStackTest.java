import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.util.NoSuchElementException;

import java.util.Random;


class CalculatorTests {

    private TqsStack<Integer> newStack;
    private TqsStack<Integer> stack3elements;
    private TqsStack<Integer> stackbounded1element;

    @BeforeEach
    void setUp() {
        this.newStack = new TqsStack<Integer>();

        this.stack3elements = new TqsStack<Integer>();
        stack3elements.push(1);
        stack3elements.push(2);
        stack3elements.push(3);

        this.stackbounded1element = new TqsStack<Integer>(1);
        stackbounded1element.push(1);
    }

    @AfterEach
    void tearDown() {

    }

	@Test
	@DisplayName("a) A stack is empty on construction.")
	void emptyOnConstruction() {
		assertTrue(this.newStack.isEmpty());
	}

    @Test
	@DisplayName("b) A stack has size 0 on construction.")
	void sizeOnConstruction() {
		assertEquals(0, this.newStack.size(), "size on construction should be 0");
	}

    @Test
	@DisplayName("c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
	void sizeAfterNPushes() {
        Random rand = new Random(); 
        int size = rand.nextInt(30); 
        for (int i = 0; i<size; i++) {
            this.newStack.push(i);
        }
		assertEquals(size, this.newStack.size(), "size after n pushes should be n");
	}

    @Test
	@DisplayName("d) If one pushes x then pops, the value popped is x.")
	void popAfterPushValue() {
        Random rand = new Random(); 
        int value = rand.nextInt(30); 
        this.newStack.push(value);
		assertEquals(value, this.newStack.pop(), "value of pop after pushes X should be X");
	}

    @Test
	@DisplayName("e) If one pushes x then peeks, the value returned is x, but the size stays the same")
	void peekAfterPushValue() {
        Random rand = new Random(); 
        int value = rand.nextInt(30); 
        this.stack3elements.push(value);
		assertEquals(value, this.stack3elements.peek(), "value of peek after pushes X should be X");
        assertEquals(4, this.stack3elements.size(), "size after peek should be the same");
	}

    @Test
	@DisplayName("f) If the size is n, then after n pops, the stack is empty and has a size 0")
	void sizeAfterPopEveryElement() {
        this.stack3elements.pop();
        this.stack3elements.pop();
        this.stack3elements.pop();
        assertEquals(0, this.stack3elements.size(), "size after every element is poped should be 0");
	}

    @Test
	@DisplayName("g) Popping from an empty stack does throw a NoSuchElementException")
	void popFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> { this.newStack.pop(); });
	}

    @Test
	@DisplayName("h) Peeking into an empty stack does throw a NoSuchElementException")
	void peekFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> { this.newStack.peek(); });
	}

    @Test
	@DisplayName("i) For bounded stacks only, pushing onto a full stack does throw an IllegalStateException")
	void pushingIntoFullBoundedStack() {
        assertThrows(IllegalStateException.class, () -> { this.stackbounded1element.push(2); });
	}

}