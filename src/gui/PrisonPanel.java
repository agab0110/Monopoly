package gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.Menager;
import app.MoneyExeption;
import app.Player;

public class PrisonPanel extends JPanel{
    private JButton exitPrisonButton;
    private JButton payExitPrisonButton;
    private List<Player> players;
    private Menager menager;

    private int index = GameFrame.i;

    public PrisonPanel(List<Player> players, Menager menager) {
        this.setLayout(null);
        this.players = players;
        this.menager = menager;

        exitPrisonButton = new JButton("Esci di prigione gratutitamente");
        payExitPrisonButton = new JButton("Esci di prigione pagando 50€");
        
        exitPrisonButton.setBounds(490, 390, 210, 30);
        payExitPrisonButton.setBounds(490, 430, 210, 30);

        this.add(exitPrisonButton);
        this.add(payExitPrisonButton);

        addAction(players.get(index));
    }

    private void addAction(Player player) {
        exitPrisonButton.addActionListener(
            e -> {
                player.setStatus(false);

                GameFrame.getInstance().remove(this);
                GameFrame.getInstance().add(new NormalGamePanel(players, menager));
            }
        );

        payExitPrisonButton.addActionListener(
            e -> {
                try {
                    player.subMoney(50);
                } catch (MoneyExeption e1) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Errore: " + e1.getMessage(),
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                player.setStatus(false);
                
                GameFrame.getInstance().remove(this);
                GameFrame.getInstance().add(new NormalGamePanel(players, menager));
            }
        );
    }
}
