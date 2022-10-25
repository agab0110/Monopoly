package gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.Menager;
import app.Player;
import app.PlayerException;

public class MainFrame extends JFrame{   
    private JPanel panel;
    private JLabel label;
    private ImageIcon img;   
    private JButton insertPlayerButton;
    private JButton startGameButton;
    private JButton exitButton;

    private List<Player> players;
    private Menager menager;

    public MainFrame(){
        players = new ArrayList<>();

        this.setTitle("Monopoly");
        setResizable(false);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setTitle("Monopoli");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        img = new ImageIcon("src/gui/images/iniziale.png"); 
        label = new JLabel();

        insertPlayerButton = new JButton("Inserisci giocatore");
        startGameButton = new JButton("Avvia partita");
        exitButton = new JButton("Esci");

        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        label.setBounds(0, 0, 500, 500);
        label.setIcon(img);
        label.setOpaque(true);
        insertPlayerButton.setBounds(175,100,150,30);
        startGameButton.setBounds(175, 150, 150, 30);
        exitButton.setBounds(175, 200, 150, 30);

        this.buildFrame();

        try {
            if(Menager.searchMenager() == true) {
                int answer = JOptionPane.showConfirmDialog(
                    null,
                    "Partita trovata. Continuare?",
                    "Partita in corso",
                    JOptionPane.YES_NO_OPTION
                    );

                if (answer == 0) {
                    this.dispose();
                    menager = Menager.loadMenager();
                    GameFrame gameFrame = new GameFrame(menager);
                    gameFrame.setVisible(true);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                null,
                "Errore ricerca menager",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buildFrame() {

        this.add(panel);
        panel.add(label);
        panel.add(insertPlayerButton);
        panel.add(startGameButton);
        panel.add(exitButton);

        addAction();
    }

    private void addAction() {
        insertPlayerButton.addActionListener(
            actionEvent -> {
                if (players.size() < 6) {
                    try {
                        addNewPlayer();
                    } catch (PlayerException e) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Errore: " + e.getMessage(),
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Limite giocatori raggiunto",
                        "ERRORE", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        );

        startGameButton.addActionListener(
            actionEvent -> {
                if (players.size() >= 2) {
                    startGame();
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Non ci sono abbastanza giocatori",
                        "ERRORE",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        );

        exitButton.addActionListener(
            actionEvent -> {
                System.exit(0);
            }
        );
    }

    private void addNewPlayer() throws PlayerException{        
        String name = JOptionPane.showInputDialog(
            null,
            "Inserire un nuovo giocatore");
        for (Player player : players) {
            if (name.equals(player.getName())) {
                throw new PlayerException("Nome duplicato");
            }
        }
        Player player = new Player(name);
        this.players.add(player);
    }

    private void startGame() {
        menager = new Menager();
        menager.constructor(players);

        GameFrame gameFrame = new GameFrame(menager);
        gameFrame.setVisible(true);

        this.dispose();         
    }
}
