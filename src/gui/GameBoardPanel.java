package gui;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Player;

public class GameBoardPanel extends JPanel{
    private JLabel imageLabel;
    private JLabel nameLabel;
    private List<Player> players;


    public GameBoardPanel(List<Player>players) {
        this.setLayout(null);

        this.players = players;

        imageLabel = new JLabel(new ImageIcon("src\\gui\\images\\tabellone.jpg"));

        imageLabel.setBounds(0, 0, 512, 512);
        imageLabel.setOpaque(true);

        this.add(imageLabel);

        createPawn();
    }
    
    private void createPawn() {
        for (Player player : players) {
            JLabel nameLabel = new JLabel();
            nameLabel.setText(player.getName());
            nameLabel.setForeground(player.getColor());
            setCoordinates();

            this.add(nameLabel);

            setCoordinates();
        }
    }

    private void setCoordinates() {
        int y = 435;
        nameLabel.setBounds(435, y, 50, 12);
        y += 12;
    }
}