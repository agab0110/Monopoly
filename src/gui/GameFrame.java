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
    private GameBoardPanel gameBoardPanel;

    private JTextField textField;
    private JTextArea textArea;

    private JButton turnOverButton;

    private List<Player> players;
    private List<Contract> contracts;
    private Menager menager;
    private static GameFrame frame;

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

                showPanel();
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
        JOptionPane.showMessageDialog(
        null,
        random.nextInt(2,12),
        "Dadi",
        JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static GameFrame getInstance() {
        return frame;
    }

    private void addGameBoard() {
        gameBoardPanel.setBounds(0, 60, 512, 512);
        this.add(gameBoardPanel);
        gameBoardPanel.setVisible(true);
    }
}