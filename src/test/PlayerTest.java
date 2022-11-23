package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Test;

import app.Player;

public class PlayerTest {
    @Test
    public void testConstructor() {
        Player player = new Player("Nome", Color.GREEN);
        assertEquals("Nome", player.getName());
        assertEquals(Color.GREEN, player.getColor());
        assertEquals(0, player.getBox());
        assertNotNull(player.getContract());
        assertEquals(false, player.getStatus());
    }   

    @Test
    public void testSetMoney() {
        Player player = new Player("Nome", Color.GREEN);
        player.setMoney(200);
        assertEquals(200, player.getMoney());
    }
}
