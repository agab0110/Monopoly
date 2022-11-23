package gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.Menager;
import app.MoneyException;
import app.Player;

public class NormalGamePanel extends JPanel {
    private JButton buyContractsButton;
    private JButton payRentButton;
    private JButton payTaxButton;

    private List<Player> players;
    private Menager menager;

    public NormalGamePanel(Menager menager) {
        this.menager = menager;
        this.players = menager.getPlayers();
        this.setLayout(null);
        this.setBounds(520, 0, 200, 720);

        buyContractsButton = new JButton("Acquista contratto");
        payRentButton = new JButton("Paga affitto");
        payTaxButton = new JButton("Paga tassa");
        
        buyContractsButton.setBounds(540, 390, 150, 30);
        payRentButton.setBounds(540, 430, 150, 30);
        payTaxButton.setBounds(540, 470, 150, 30);

        this.add(buyContractsButton);
        this.add(payRentButton);
        this.add(payTaxButton);

        addAction(players.get(GameFrame.i));
    }

    private void addAction(Player player) {
        buyContractsButton.addActionListener(
            e -> {
                buyContractAction(player);
            }
        );

        payRentButton.addActionListener(
            e -> {
                payRentAction(player);
            }
        );

        payTaxButton.addActionListener(
            e -> {
                payTaxAction(player);
            }
        );

    }

    private void payRentAction(Player player) {
        PayRentFrame payRentFrame = new PayRentFrame(player, menager);
        payRentFrame.setVisible(true);
    }

    private void buyContractAction(Player player){
        BuyContractFrame buyContractFrame = new BuyContractFrame(player, menager);
        buyContractFrame.setVisible(true);   
    }

    private void payTaxAction(Player player) {
        int tax = 0;
        String s = JOptionPane.showInputDialog("Inserire l'importo ");
        try{
            tax = Integer.parseInt(s);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
                
        try {
            player.subMoney(tax);
        } catch (MoneyException e1) {
            JOptionPane.showMessageDialog(null,
            "Errore: " + e1.getMessage(), 
            "Errore",
            JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
}
