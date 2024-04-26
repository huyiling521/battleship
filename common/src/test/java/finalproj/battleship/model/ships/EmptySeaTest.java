package finalproj.battleship.model.ships;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmptySeaTest {
    @Test
    public void testGetShotAt() {
        EmptySea emptySea = new EmptySea();
        assertFalse(emptySea.getShotAt(0, 0)); // Tests that shooting at the empty sea always fails
    }

    @Test
    public void testIsSunk() {
        EmptySea emptySea = new EmptySea();
        assertFalse(emptySea.isSunk()); // The empty sea never sinks
    }
}
