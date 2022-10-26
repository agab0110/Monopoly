package gui;

import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import app.Player;

public class NewPlayerFrame extends JFrame{
    public enum Pawns {ditale, carriola, scarpa, cane, macchina, ferro, cappello, nave};

    private JTextField textField;
    private JCheckBox checkBox;

    public NewPlayerFrame(List<Player> players) {
        this.setSize(400,200);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Monopoli");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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