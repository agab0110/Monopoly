package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.MoneyExeption;
import app.Player;

public class PrisonPanel extends JPanel{
    private JButton turnOverButton;
    private JButton exitPrisonButton;
    private JButton payExitPrisonButton;
    
    public PrisonPanel(Player player) {

        exitPrisonButton = new JButton("Esci di prigione gratutitamente");
        payExitPrisonButton = new JButton("Esci di prigione pagando 50€");
        turnOverButton = new JButton("Termina turno");
        
        exitPrisonButton.setBounds(540, 390, 200, 30);
        payExitPrisonButton.setBounds(540, 430, 200, 30);
        turnOverButton.setBounds(540, 470, 200, 30);

        this.add(exitPrisonButton);
        this.add(payExitPrisonButton);
        this.add(turnOverButton);

        addPrisonAction(player);
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
