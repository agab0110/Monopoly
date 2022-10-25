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

    private int index = GameFrame.i;

    public PrisonPanel(List<Player> players, Menager menager) {
        this.setLayout(null);

        exitPrisonButton = new JButton("Esci di prigione gratutitamente");
        payExitPrisonButton = new JButton("Esci di prigione pagando 50€");
        
        exitPrisonButton.setBounds(540, 390, 200, 30);
        payExitPrisonButton.setBounds(540, 430, 200, 30);

        this.add(exitPrisonButton);
        this.add(payExitPrisonButton);

        addPrisonAction(players.get(index));
    }

    private void addPrisonAction(Player player) {
        exitPrisonButton.addActionListener(
            e -> {
                player.setStatus(false);
                this.removeAll();
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
                this.removeAll();           
            }
        );
    }
}
