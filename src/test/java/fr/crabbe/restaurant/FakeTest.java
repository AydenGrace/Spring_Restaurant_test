package fr.crabbe.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FakeTest {

    @Test
    void test1(){
        Assertions.assertTrue(true);
    }

    @Test
    void test2(){
        Assertions.assertFalse(false);
    }

    @Test
    void test3(){
        Assertions.assertEquals(1,1);
    }

    @Test
    void test4(){
        Assertions.assertNotEquals(1,2);
    }

    @Test
    void test5(){
        Assertions.assertNull(null);
    }
    @Test
    void test6(){
        Assertions.assertNotNull(new Object());
    }


}
