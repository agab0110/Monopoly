package gui;

import java.io.IOException;
import java.util.List;

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
        passStartButton = new JButton("Passa dal via");
        goToPrisonButton = new JButton("Vai in prigione");
        
        buyContractsButton.setBounds(540, 390, 150, 30);
        payRentButton.setBounds(540, 430, 150, 30);
        payTaxButton.setBounds(540, 470, 150, 30);
        passStartButton.setBounds(540, 510, 150, 30);
        goToPrisonButton.setBounds(540, 550, 150, 30);

        this.add(buyContractsButton);
        this.add(payRentButton);
        this.add(payTaxButton);
        this.add(passStartButton);
        this.add(goToPrisonButton);

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

        passStartButton.addActionListener(
            e -> {
                player.addMoney(200);
            }
        );

        goToPrisonButton.addActionListener(
            e -> {
                prisonAction();
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
        } catch (MoneyExeption e1) {
            JOptionPane.showMessageDialog(null,
            "Errore: " + e1.getMessage(), 
            "Errore",
            JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void prisonAction() {
        players.get(GameFrame.i).setStatus(true);

        try {
            menager.saveMenager();
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(
            null, 
            "Errore salvataggio",
            "Errore",
            JOptionPane.ERROR_MESSAGE);
        }

        GameFrame.i++;

        if (GameFrame.i == players.size()) {
            GameFrame.i = 0;
        }

        showpanel();
    }
    
    private void showpanel() {
        JPanel panel;

        if(players.get(GameFrame.i).getStatus() == true) {
            panel = new PrisonPanel(menager);
        } else {
            panel = new NormalGamePanel(menager);
        }

        GameFrame.getInstance().remove(this);     
        GameFrame.getInstance().add(panel);
        panel.setVisible(true);

        GameFrame.getInstance().throwDice();
    }
}
