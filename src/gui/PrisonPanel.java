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

    public PrisonPanel(List<Player> players, Menager menager) {
        this.setLayout(null);
        this.players = players;
        this.menager = menager;

        exitPrisonButton = new JButton("<html>Esci di prigione<br> gratutitamente</html>");
        payExitPrisonButton = new JButton("<html>Esci di prigione<br>pagando 50€</html>");
        
        exitPrisonButton.setBounds(540, 390, 150, 70);
        payExitPrisonButton.setBounds(540, 470, 150, 70);

        this.add(exitPrisonButton);
        this.add(payExitPrisonButton);

        addAction(players.get(GameFrame.i));
    }

    private void addAction(Player player) {
        exitPrisonButton.addActionListener(
            e -> {
                player.setStatus(false);

                JPanel panel = new NormalGamePanel(players, menager);

                GameFrame.getInstance().remove(this);
                GameFrame.getInstance().add(panel);
                panel.setVisible(true);
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
                
                JPanel panel = new NormalGamePanel(players, menager);

                GameFrame.getInstance().remove(this);
                GameFrame.getInstance().add(panel);
                panel.setVisible(true);
            }
        );
    }
}
