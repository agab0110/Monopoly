package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.Contract;
import app.Menager;
import app.MoneyException;
import app.Player;

public class PayRentFrame extends JFrame{
    private JPanel panel;

    public PayRentFrame(Player player, Menager menager) {
        this.setSize(350, 500);
        this.setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Paga affitto");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        panel = new JPanel();

        for (Contract contract : menager.getContracts()) {
            if (contract.getOwner() != null && contract.getOwner() != player) {
                JLabel label = new JLabel(contract.getName() + ", Affitto: " + contract.getRent());
                JButton button = new JButton("Paga affitto");

                panel.add(label);
                panel.add(button);

                button.addActionListener(
                    e -> {
                        try {
                            menager.payRent(player, contract);
                        } catch (MoneyException e1) {
                            JOptionPane.showMessageDialog(null, "Errore: " + e1.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                        this.dispose();
                    }
                );
            }
        }
        
        this.add(panel);
        panel.setVisible(true);        
    }    
}