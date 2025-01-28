package fr.crabbe.restaurant.exosTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FakeControllerTest {

    @InjectMocks
    private FakeController fakeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fake() {
        String expected = "Fake";
        String result = fakeController.fake();
        assertEquals(expected, result);
    }

    @Test
    void fakeParams_noParams() {
        String expected = "No name";
        String result = fakeController.fakeParams("");
        assertEquals(expected, result);
    }

    @Test
    void fakeParams() {
        String expected = "toto";
        String result = fakeController.fakeParams("toto");
        assertEquals(expected, result);
    }

    @Test
    void fakeCondition() {
        String expected = "YES";
        String result = fakeController.fakeCondition(true);
        assertEquals(expected, result);
    }

    @Test
    void fakeCondition_no() {
        String expected = "NO";
        String result = fakeController.fakeCondition(false);
        assertEquals(expected, result);
    }

    @Test
    void fakePost() {
        String expected = "Fake";
        String result = fakeController.fakePost(new FakeObject("Fake",0));
        assertEquals(expected, result);
    }

}