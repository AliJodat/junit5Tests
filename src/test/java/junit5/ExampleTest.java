package junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {

    @Test
    @Disabled("Not implemented yet") //غیر فعال کردن تست
    @DisplayName("Should show simple assert")
    void shouldShowSimpleAssertion() {
        assertEquals(1, 1);
    }

    @Test
    @DisplayName("Should Check All Items In The List")
    void shouldCheckAllItemsInTheList() {
        final List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(7);
        Assertions.assertAll(
                () -> assertEquals(2, list.get(0)),
                () -> assertEquals(3, list.get(1)),
                () -> assertEquals(5, list.get(2)),
                () -> assertEquals(7, list.get(3)));
    }

    @Test
    @DisplayName("Should Only Run The Test If Some Criteria Are Met")
    void shouldOnlyRunTheTestIfSomeCriteriaAreMet() {
        Assumptions.assumeTrue(Fixture.apiVersion() < 10);
        assertEquals(1 ,1);
    }

    @ParameterizedTest(name = "{0}") // فقط نمایش ولویی که باهاش تست انجام شده
    @ValueSource(ints = {3, 4, 5, 8, 14})
    @DisplayName("Should Create Shapes With Different Number Of Sides")
    void shouldCreateShapesWithDifferentNumberOfSides(int value) {
        Shape shape = new Shape(value);
        assertEquals(value , shape.getNumberOfSides());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, Integer.MAX_VALUE})
    @DisplayName("Should Not Create Shapes With Invalid Numbers Of Sides")
    void shouldNotCreateShapesWithInvalidNumbersOfSides(int value) {
        assertThrows(IllegalArgumentException.class , () -> new Shape(value));
    }

    @Nested // تست گروهی در کلاسهای بزرگ .
    @DisplayName("When a shape has been created")
    class WhenShapeExist{
        private final Shape shape = new Shape(4);

        @Nested
        @DisplayName("Should Allow")
        class ShouldAllow{
            @Test
            @DisplayName("seeing The Number Of Sides")
            void seeingTheNumberOfSides() {
                assertEquals(4 , shape.getNumberOfSides());
            }

            @Test
            @DisplayName("seeing The Description")
            void seeingTheDescription() {
                assertEquals("Square" , shape.getDescription());
            }
        }

        @Nested
        @DisplayName("Should not")
        class ShouldNot{
            @Test
            @DisplayName("be Equal To Another Shape With The Same Number Of Sides")
            void beEqualToAnotherShapeWithTheSameNumberOfSides() {
                assertNotEquals(shape, new Shape(4));
            }
        }
    }
}
