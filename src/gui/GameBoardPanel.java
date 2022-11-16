package gui;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Player;

public class GameBoardPanel extends JPanel{
    private JLabel imageLabel;
    private List<Player> players;


    public GameBoardPanel(List<Player>players) {
        this.setLayout(null);

        this.players = players;

        imageLabel = new JLabel(new ImageIcon("src\\gui\\images\\tabellone.jpg"));
        imageLabel.setBounds(0, 0, 512, 512);

        createPawn();
        this.add(imageLabel);
        
    }
    
    private void createPawn() {
        int y = 445;

        for (Player player : players) {
            JLabel nameLabel = new JLabel();
            nameLabel.setText(player.getName());
            nameLabel.setForeground(player.getColor());
            
            nameLabel.setBounds(450, y, 150, 12);
            y += 12;

            this.add(nameLabel);
        }
    }

}