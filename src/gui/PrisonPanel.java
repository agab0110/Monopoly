package gui;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.Menager;
import app.MoneyExeption;
import app.Player;

public class PrisonPanel extends JPanel{
    private JButton turnOverButton;
    private JButton exitPrisonButton;
    private JButton payExitPrisonButton;
    private int index = GameFramePanel.i;
    private Menager menager;
    private List<Player> players;

    public PrisonPanel(List<Player> players, Menager menager) {
        this.menager = menager;
        this.players = players;

        exitPrisonButton = new JButton("Esci di prigione gratutitamente");
        payExitPrisonButton = new JButton("Esci di prigione pagando 50€");
        turnOverButton = new JButton("Termina turno");
        
        exitPrisonButton.setBounds(540, 390, 200, 30);
        payExitPrisonButton.setBounds(540, 430, 200, 30);
        turnOverButton.setBounds(540, 470, 200, 30);

        this.add(exitPrisonButton);
        this.add(payExitPrisonButton);
        this.add(turnOverButton);

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

        turnOverButton.addActionListener(
            e -> {
                index++;

                if (index == players.size()) {
                    index = 0;
                }
                try {
                    menager.saveMenager();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Errore salvataggio",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
                }

                this.removeAll();

                throwDice();
            }
        );
    }

    private void throwDice(){
        Random random = new Random();
        JOptionPane.showMessageDialog(
        null,
        random.nextInt(2,12),
        "Dadi",
        JOptionPane.INFORMATION_MESSAGE
        );
    }

}
