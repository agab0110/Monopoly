package gui;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.Contract;
import app.Menager;
import app.Player;

public class GameFramePanel extends JPanel{
    public static int i = 0;

    private JTextField textField;
    private JTextArea textArea;

    private List<Player> players;
    private List<Contract> contracts;
    private Menager menager;
    
    public GameFramePanel(Menager menager) { 
        this.setLayout(null);
        this.setSize(720, 720);
        this.players = menager.getPlayers();
        this.contracts = menager.getContracts();
        this.menager = menager;
        this.removeAll();

        textField = new JTextField();
        textArea = new JTextArea();

        textField.setBounds(530, 30, 170, 30);       
        textArea.setBounds(540, 70, 150, 300);

        this.add(textField);
        this.add(textArea);

        showPanel();

        update();
    }

    private void showPanel() {
        if(players.get(i).getStatus() == false) {
            NormalGamePanel normalGamePanel = new NormalGamePanel(players, menager);
            this.add(normalGamePanel);
        } else {
            PrisonPanel prisonPanel = new PrisonPanel(players, menager);
            this.add(prisonPanel);
        }
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

}