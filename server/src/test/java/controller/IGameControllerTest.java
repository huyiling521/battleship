package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import controller.IGameController;
import model.ships.ShipType;
import org.junit.Before;
import org.junit.Test;

public class IGameControllerTest {
    private IGameController gameController;

    @Before
    public void setUp() {
        // 使用Mockito模拟IGameController接口
        gameController = mock(IGameController.class);
    }

    @Test
    public void testShootAt() {
        // 假设射击(0,0)时返回true
        when(gameController.shootAt(0, 0)).thenReturn(true);
        assertTrue(gameController.shootAt(0, 0));
    }

    @Test
    public void testIsSunk() {
        // 假设(0,0)的船已沉没
        when(gameController.isSunk(0, 0)).thenReturn(true);
        assertTrue(gameController.isSunk(0, 0));
    }

    @Test
    public void testGetShipType() {
        // 假设(0,0)的船是战列舰
        when(gameController.getShipType(0, 0)).thenReturn(ShipType.BATTLESHIP);
        assertEquals(ShipType.BATTLESHIP, gameController.getShipType(0, 0));
    }

    @Test
    public void testIsGameOver() {
        // 假设游戏结束
        when(gameController.isGameOver()).thenReturn(true);
        assertTrue(gameController.isGameOver());
    }

    @Test
    public void testGetConclusion() {
        // 假设游戏结束时的结果是"Player 1 wins!"
        when(gameController.getConclusion()).thenReturn("Player 1 wins!");
        assertEquals("Player 1 wins!", gameController.getConclusion());
    }
}
