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

public class BuyContractFrame extends JFrame{
    private JPanel panel;

    public BuyContractFrame(Player player, Menager menager) {
        this.setSize(450, 500);
        this.setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Acquista contratto");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        panel = new JPanel(); 

        for (Contract contract : menager.getContracts()) {
            if (contract.getOwner() == null) {
                JLabel label = new JLabel(contract.getName() + ", Prezzo: " + contract.getPrice() + " Affitto: " + contract.getRent());
                JButton button = new JButton("Acquista");

                panel.add(label);
                panel.add(button);

                button.addActionListener(
                    e -> {
                        try {
                            menager.buyContract(player, contract);
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
