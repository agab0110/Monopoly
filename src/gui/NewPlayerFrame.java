package gui;

import java.util.List;

import javax.swing.JFrame;

import app.Player;

public class NewPlayerFrame extends JFrame{

    public NewPlayerFrame(List<Player> players) {
        this.setSize(400,200);
    }
    
}

/*
String name = JOptionPane.showInputDialog(
            null,
            "Inserire un nuovo giocatore",
            "Nuovo giocatore",
            JOptionPane.OK_CANCEL_OPTION
            );

        for (Player player : players) {
            if (name.equals(player.getName())) {
                throw new PlayerException("Nome duplicato");
            }
        }
        Player player = new Player(name);
        this.players.add(player);
 */