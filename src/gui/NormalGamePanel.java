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

public class NormalGamePanel extends JPanel {
    private JButton buyContractsButton;
    private JButton payRentButton;
    private JButton payTaxButton;
    private JButton passStartButton;
    private JButton goToPrisonButton;
    private JButton turnOverButton;

    private Menager menager;
    private List<Player> players;

    private int index = GameFramePanel.i;

    public NormalGamePanel(List<Player> players, Menager menager) {
        this.menager = menager;
        this.players = players;

        buyContractsButton = new JButton("Acquista contratto");
        payRentButton = new JButton("Paga affitto");
        payTaxButton = new JButton("Paga tassa");
        passStartButton = new JButton("Passa dal via");
        goToPrisonButton = new JButton("Vai in prigione");
        turnOverButton = new JButton("Termina turno");
        
        buyContractsButton.setBounds(540, 390, 150, 30);
        payRentButton.setBounds(540, 430, 150, 30);
        payTaxButton.setBounds(540, 470, 150, 30);
        passStartButton.setBounds(540, 510, 150, 30);
        goToPrisonButton.setBounds(540, 550, 150, 30);
        turnOverButton.setBounds(540, 590, 150, 30);

        this.add(buyContractsButton);
        this.add(payRentButton);
        this.add(payTaxButton);
        this.add(passStartButton);
        this.add(goToPrisonButton);
        this.add(turnOverButton);

        addAction(players.get(index));
    }

    private void addAction(Player player) {
        buyContractsButton.addActionListener(
            e -> {
                buyContract(player);
            }
        );

        payRentButton.addActionListener(
            e -> {
                payRent(player);
            }
        );

        payTaxButton.addActionListener(
            e -> {
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
                } catch (MoneyExeption e1) {
                    JOptionPane.showMessageDialog(null,
                    "Errore: " + e1.getMessage(), 
                    "Errore",
                    JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        );

        passStartButton.addActionListener(
            e -> {
                player.addMoney(200);
                passStartButton.setVisible(false);
            }
        );

        goToPrisonButton.addActionListener(
            e -> {
                player.setStatus(true);
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

    private void payRent(Player player) {
        PayRentFrame payRentFrame = new PayRentFrame(player, menager);
        payRentFrame.setVisible(true);
    }

    private void buyContract(Player player){
        BuyContractFrame buyContractFrame = new BuyContractFrame(player, menager);
        buyContractFrame.setVisible(true);   
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
