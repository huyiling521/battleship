package battleship.client;

import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.Test;

public class BattleshipClientTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;

    @Override
    protected void onSetUp() {
        BattleshipClient.main(null);
        window = new FrameFixture(robot(), "BattleshipFrame");
        window.show();
    }

    @Test
    public void testBattleshipFrameIsVisible() {
        window.requireVisible();
    }

    @Override
    protected void onTearDown() {
        window.cleanUp();
    }
}
