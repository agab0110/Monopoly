package gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.Menager;
import app.MoneyException;
import app.Player;

public class PrisonPanel extends JPanel{
    private JButton exitPrisonButton;
    private JButton payExitPrisonButton;
    private List<Player> players;
    private Menager menager;

    public PrisonPanel(Menager menager) {
        this.setLayout(null);
        this.players = menager.getPlayers();
        this.menager = menager;
        this.setBounds(520, 0, 200, 720);

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

                JPanel panel = new NormalGamePanel(menager);

                GameFrame.getInstance().remove(this);
                GameFrame.getInstance().add(panel);
                GameFrame.getInstance().throwDice();
                panel.setVisible(true);
            }
        );

        payExitPrisonButton.addActionListener(
            e -> {
                try {
                    player.subMoney(50);
                } catch (MoneyException e1) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Errore: " + e1.getMessage(),
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                player.setStatus(false);
                
                JPanel panel = new NormalGamePanel(menager);

                GameFrame.getInstance().remove(this);
                GameFrame.getInstance().add(panel);
                GameFrame.getInstance().throwDice();
                panel.setVisible(true);
            }
        );
    }
}
