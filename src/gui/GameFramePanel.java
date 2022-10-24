package gui;

import java.util.List;
import java.util.Random;

import java.io.IOException;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.Contract;
import app.Menager;
import app.MoneyExeption;
import app.Player;

public class GameFramePanel extends JPanel{
    public static int i = 0;

    private JPanel panel;

    private JTextField textField;
    private JTextArea textArea;

    private JButton buyContractsButton;
    private JButton payRentButton;
    private JButton payTaxButton;
    private JButton passStartButton;
    private JButton goToPrisonButton;
    private JButton turnOverButton;
    private JButton exitPrisonButton;
    private JButton payExitPrisonButton;

    private List<Player> players;
    private List<Contract> contracts;
    private Menager menager;
    
    
    public GameFramePanel(Menager menager) { 
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.players = menager.getPlayers();
        this.contracts = menager.getContracts();
        this.menager = menager;

        panel = new JPanel();
        textField = new JTextField();
        textArea = new JTextArea();

        textField.setBounds(530, 30, 170, 30);       
        textArea.setBounds(540, 70, 150, 300);

        this.add(textField);
        this.add(textArea);

        showButtons();

        panel.setSize(720, 720);
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        
        this.add(panel);
        panel.setVisible(true);

        update();
    }

    private void showMenu() {
        
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

        panel.add(buyContractsButton);
        panel.add(payRentButton);
        panel.add(payTaxButton);
        panel.add(passStartButton);
        panel.add(goToPrisonButton);
        panel.add(turnOverButton);

        throwDice();

        addAction(players.get(i));
    }

    private void showPrisonMenu() {
        exitPrisonButton = new JButton("Esci di prigione gratutitamente");
        payExitPrisonButton = new JButton("Esci di prigione pagando 50€");
        turnOverButton = new JButton("Termina turno");
        
        exitPrisonButton.setBounds(540, 390, 200, 30);
        payExitPrisonButton.setBounds(540, 430, 200, 30);
        turnOverButton.setBounds(540, 470, 200, 30);

        panel.add(exitPrisonButton);
        panel.add(payExitPrisonButton);
        panel.add(turnOverButton);

        addPrisonAction(players.get(i));
    }

    private void showButtons() {
        if(players.get(i).getStatus() == false) {
            showMenu();
        } else {
            showPrisonMenu();
        }
    }

    private void buyContract(Player player){
        BuyContractFrame buyContractFrame = new BuyContractFrame(player, menager);
        buyContractFrame.setVisible(true);   
    }

    private void update() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}

                textField.setText("Turno di " + players.get(i).getName() + ", soldi: " + players.get(i).getMoney());
                textArea.setText(setContracts());
            }
        });
        thread.start();
    }

    private String setContracts() {
        String contractName = "";

        contracts = players.get(i).getContract();

        for (Contract contract : contracts) {
            contractName += contract.getName() + "\n";
        }
        return contractName;
    }

    private void payRent(Player player) {
        PayRentFrame payRentFrame = new PayRentFrame(player, menager);
        payRentFrame.setVisible(true);
    }

    private void throwDice(){
        Random random = new Random();
        JOptionPane.showMessageDialog(null,
        random.nextInt(2,12),
        "Dadi",
        JOptionPane.INFORMATION_MESSAGE
        );
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
                i++;

                if (i == players.size()) {
                    i = 0;
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

                panel.removeAll();
                showButtons();
            }
        );
    }

    private void addPrisonAction(Player player) {
        exitPrisonButton.addActionListener(
            e -> {
                player.setStatus(false);
                panel.removeAll();
                showButtons();
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
                panel.removeAll();
                showButtons();               
            }
        );
    }
}