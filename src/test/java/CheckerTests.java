import com.tplaymeow.itmoweblab3.Checker.CoordinatesChecker;
import com.tplaymeow.itmoweblab3.Checker.CoordinatesCheckerImpl;
import com.tplaymeow.itmoweblab3.Models.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckerTests {
    @Test
    public void testFirstQuoter() {
        CoordinatesChecker checker = new CoordinatesCheckerImpl();

        Coordinates coordinates1 = new Coordinates(1.0, 1.0, 2);
        Assertions.assertTrue(checker.check(coordinates1));

        Coordinates coordinates2 = new Coordinates(0.0, 1.0, 2);
        Assertions.assertTrue(checker.check(coordinates2));

        Coordinates coordinates3 = new Coordinates(1.0, 0.0, 2);
        Assertions.assertTrue(checker.check(coordinates3));

        Coordinates coordinates4 = new Coordinates(0.0, 0.0, 2);
        Assertions.assertTrue(checker.check(coordinates4));

        Coordinates coordinates5 = new Coordinates(2.0, 2.0, 2);
        Assertions.assertTrue(checker.check(coordinates5));

        Coordinates coordinates6 = new Coordinates(3.0, 2.0, 2);
        Assertions.assertFalse(checker.check(coordinates6));

        Coordinates coordinates7 = new Coordinates(1.0, 3.0, 2);
        Assertions.assertFalse(checker.check(coordinates7));
    }

    @Test
    public void testSecondQuoter() {
        CoordinatesChecker checker = new CoordinatesCheckerImpl();

        Coordinates coordinates1 = new Coordinates(1.0, 0.0, 4);
        Assertions.assertTrue(checker.check(coordinates1));

        Coordinates coordinates2 = new Coordinates(0.0, -1.0, 4);
        Assertions.assertTrue(checker.check(coordinates2));

        Coordinates coordinates3 = new Coordinates(2.0, 0.0, 4);
        Assertions.assertTrue(checker.check(coordinates3));

        Coordinates coordinates4 = new Coordinates(0.0, -2.0, 4);
        Assertions.assertTrue(checker.check(coordinates4));

        Coordinates coordinates5 = new Coordinates(3.0, -1.0, 4);
        Assertions.assertFalse(checker.check(coordinates5));

        Coordinates coordinates6 = new Coordinates(0.0, -3.0, 4);
        Assertions.assertFalse(checker.check(coordinates6));

        Coordinates coordinates7 = new Coordinates(1.0, -1.0, 4);
        Assertions.assertTrue(checker.check(coordinates7));

        Coordinates coordinates8 = new Coordinates(1.2, -1.2, 5);
        Assertions.assertTrue(checker.check(coordinates8));
    }

    @Test
    public void testThirdQuoter() {
        CoordinatesChecker checker = new CoordinatesCheckerImpl();

        Coordinates coordinates1 = new Coordinates(-1.0, -1.0, 4);
        Assertions.assertFalse(checker.check(coordinates1));

        Coordinates coordinates2 = new Coordinates(-1.0, -2.0, 4);
        Assertions.assertFalse(checker.check(coordinates2));

        Coordinates coordinates3 = new Coordinates(-1.0, -2.0, 4);
        Assertions.assertFalse(checker.check(coordinates3));

        Coordinates coordinates4 = new Coordinates(-1.0, -0.2, 2);
        Assertions.assertFalse(checker.check(coordinates4));
    }

    @Test
    public void testFourthQuoter() {
        CoordinatesChecker checker = new CoordinatesCheckerImpl();

        Coordinates coordinates1 = new Coordinates(-1.0, 0.0, 4);
        Assertions.assertTrue(checker.check(coordinates1));

        Coordinates coordinates2 = new Coordinates(0.0, 1.0, 4);
        Assertions.assertTrue(checker.check(coordinates2));

        Coordinates coordinates3 = new Coordinates(-2.0, 0.0, 4);
        Assertions.assertTrue(checker.check(coordinates3));

        Coordinates coordinates4 = new Coordinates(0.0, 2.0, 4);
        Assertions.assertTrue(checker.check(coordinates4));

        Coordinates coordinates5 = new Coordinates(-3.0, 0.0, 4);
        Assertions.assertFalse(checker.check(coordinates5));

        Coordinates coordinates7 = new Coordinates(-1.0, 1.0, 4);
        Assertions.assertTrue(checker.check(coordinates7));

        Coordinates coordinates8 = new Coordinates(-1.2, 1.2, 4);
        Assertions.assertTrue(checker.check(coordinates8));
    }
}
