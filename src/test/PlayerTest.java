package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Test;

import app.Contract;
import app.MoneyException;
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

    @Test
    public void testAddContract() {
        Player player = new Player("Nome", Color.GREEN);

        Contract contract = new Contract("prova", 100, 10);
        player.addContract(contract);

        assertEquals(contract, player.getContract().get(0));
    }

    @Test
    public void testSetStatus() {
        Player player = new Player("Nome", Color.GREEN);

        player.setStatus(true);
        assertEquals(true, player.getStatus());
    }

    @Test
    public void testAddMoney() {
        Player player = new Player("Nome", Color.GREEN);

        player.setMoney(100);
        player.addMoney(200);
        assertEquals(300, player.getMoney());
    }

    @Test
    public void testSubMoney() {
        Player player = new Player("Nome", Color.GREEN);

        player.setMoney(200);

        try {
            player.subMoney(100);
        } catch (MoneyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        assertEquals(100, player.getMoney());
    }

    @Test
    public void testAdvanceBox() {
        Player player = new Player("Nome", Color.GREEN);

        for (int i = 0; i < 4; i++) {
            player.advanceBox();
        }

        assertEquals(4, player.getBox());
    }
}
