package gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.Player;

public class NewPlayerFrame extends JFrame{
    private String[] pawns = {"ditale", "carriola", "scarpa", "cane", "macchina", "ferro", "cappello", "nave"};
    
    private JLabel nameLabel;
    private JLabel pawnLabel;
    private JTextField textField;
    private JComboBox comboBox;
    private JButton sendButton;

    private JPanel panel;

    public NewPlayerFrame(List<Player> players) {
        this.setSize(400,200);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Nuovo giocatore");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        nameLabel = new JLabel("Nome");
        pawnLabel = new JLabel("Pedina");
        textField = new JTextField();
        comboBox = new JComboBox(pawns);
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
    }
    
}