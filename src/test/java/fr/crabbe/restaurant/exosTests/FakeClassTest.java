package fr.crabbe.restaurant.exosTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FakeClassTest {

    @Test
    void test_isEven() {
        int a = 2;
        boolean result = FakeClass.isEven(a);

        Assertions.assertTrue(result);
    }

    @Test
    void test_isEven_NotTrue() {
        int a = 3;
        boolean result = FakeClass.isEven(a);

        Assertions.assertFalse(result);
    }

    @Test
    void test_isPalindrome() {
        String test = "kayak";
        boolean result = FakeClass.isPalindrome(test);

        Assertions.assertTrue(result);
    }

    @Test
    void test_isPalindrome_NotTrue() {
        String test = "Plop";
        boolean result = FakeClass.isPalindrome(test);

        Assertions.assertFalse(result);
    }

    @Test
    void test_Average() {
        int[] test = {1, 2, 3, 4, 5};
        double expected = 3;

        double result = FakeClass.average(test);

        Assertions.assertEquals(expected, result);
    }
}
