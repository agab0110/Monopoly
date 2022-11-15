package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;

import app.Menager;
import app.Player;
import app.PlayerException;

public class NewPlayerFrame extends JFrame {
    private String[] colors = {"rosso", "blu", "verde", "giallo", "arancione", "azzurro"};
    
    private JLabel nameLabel;
    private JLabel pawnLabel;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private JButton sendButton;

    private JPanel panel;

    private Menager menager;

    public NewPlayerFrame(Menager menager) throws PlayerException {
        this.menager = menager;

        this.setSize(400,200);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Nuovo giocatore");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        nameLabel = new JLabel("Nome");
        pawnLabel = new JLabel("Pedina");
        textField = new JTextField();
        comboBox = new JComboBox<>(colors);
        sendButton = new JButton("Inserisci");

        panel.setLayout(null);
        nameLabel.setBounds(10, 10, 200, 30);
        pawnLabel.setBounds(220, 10, 150, 30);
        textField.setBounds(10, 40, 200, 30);
        comboBox.setBounds(220, 40, 150, 30);
        sendButton.setBounds(125, 80, 150, 30);

        this.add(panel);
        panel.add(nameLabel);
        panel.add(pawnLabel);
        panel.add(textField);
        panel.add(comboBox);
        panel.add(sendButton);

        insertAction();
    }

    private void insertAction() throws PlayerException{
        sendButton.addActionListener(
            e -> {
                String name = new String();
                String temp = new String();
                Color color;

                name = textField.getText();
                temp = (String) comboBox.getSelectedItem();

                color = stringToColor(temp);

                Player player = new Player(name, color);

                try {
                    menager.addPlayer(player);
                } catch (PlayerException e1) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Errore " + e1.getMessage(),
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                        );
                    }
                    
                this.dispose();
            }
        );
    }

    private Color stringToColor(String temp) {
        switch (temp) {
            case "rosso":
                return Color.RED;
            case "blu":
                return Color.BLUE;
            case "verde":
                return Color.GREEN;
            case "giallo":
                return Color.YELLOW;
            case "arancione":
                return Color.ORANGE;
            case "azzurro":
                return Color.CYAN;
            default:
                return null;
        }
    }
    
}