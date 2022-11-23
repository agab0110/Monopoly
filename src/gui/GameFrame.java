package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
    private GameBoardPanel gameBoardPanel;

    private JTextField textField;
    private JTextArea textArea;

    private JButton turnOverButton;

    private JLabel label;

    private List<Player> players;
    private List<Contract> contracts;
    private Menager menager;
    private static GameFrame frame;
    private int dice;

    public GameFrame(Menager menager) {
        GameFrame.frame = this;

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

        label = new JLabel();
        label.setBounds(10, 10, 100, 30);

        textField.setBounds(530, 30, 170, 30);       
        textArea.setBounds(540, 70, 150, 300);

        this.add(textField);
        this.add(textArea);
        this.add(turnOverButton);
        this.add(label);

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

                showPanel();
                throwDice();
            }
        );

        gameBoardPanel = new GameBoardPanel(players);
        
        addGameBoard();
        showPanel();
        throwDice();
        updateThread();
    }

    private void showPanel() {
        if(players.get(i).getStatus() == true) {
            panel = new PrisonPanel(menager);
        } else {
            panel = new NormalGamePanel(menager);
        }

        this.add(panel);
        panel.setVisible(true);
    }

    public void updateThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
                
                textField.setText("Turno di " + players.get(i).getName() + ", soldi: " + players.get(i).getMoney());
                textArea.setText(setContracts());
                label.setText("Tempo rimasto: " + "...");
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

    public void throwDice(){
        Random random = new Random();
        dice = random.nextInt(2,12);

        JOptionPane.showMessageDialog(
        null,
        dice,
        "Dadi",
        JOptionPane.INFORMATION_MESSAGE
        );

        checkAndMovePlayer();
        
    }

    public static GameFrame getInstance() {
        return frame;
    }

    private void addGameBoard() {
        gameBoardPanel.setBounds(0, 60, 512, 512);
        this.add(gameBoardPanel);
        gameBoardPanel.setVisible(true);
    }

    private void checkAndMovePlayer() {
        for (int i = 0; i < dice; i++) {
            gameBoardPanel.movePlayer();
        }

        if (players.get(i).getBox() == 30) {
            for (int i = 0; i <= 20; i++) {
                gameBoardPanel.movePlayer();
            }

            players.get(i).setStatus(true);
        }
    }
}