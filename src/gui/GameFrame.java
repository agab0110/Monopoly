package gui;

import javax.swing.JFrame;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.Contract;
import app.Menager;
import app.Player;


public class GameFrame extends JFrame{
    public static int i = 0;

    private JPanel panel;

    private JTextField textField;
    private JTextArea textArea;

    private JButton turnOverButton;

    private List<Player> players;
    private List<Contract> contracts;
    private Menager menager;

    public GameFrame(Menager menager) {

        this.setSize(720,720);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Monopoli");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.players = menager.getPlayers();
        this.contracts = menager.getContracts();
        this.menager = menager;

        turnOverButton = new JButton("Termina turno");
        turnOverButton.setBounds(540, 590, 150, 30);

        textField = new JTextField();
        textArea = new JTextArea();

        textField.setBounds(530, 30, 170, 30);       
        textArea.setBounds(540, 70, 150, 300);

        this.add(textField);
        this.add(textArea);
        this.add(turnOverButton);

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
                this.remove(panel);
            }
        );
        
        throwDice();
        showPanel();

        update();
    }

    private void showPanel() {
        if(players.get(i).getStatus() == true) {
            panel = new PrisonPanel(players, menager);
        } else {
            panel = new NormalGamePanel(players, menager);
        }

        this.add(panel);
        panel.setVisible(true);
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