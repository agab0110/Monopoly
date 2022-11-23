package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Player;

public class GameBoardPanel extends JPanel{
    private JLabel imageLabel;
    private List<Player> players;
    private ArrayList<JLabel> nameLabels;

    public GameBoardPanel(List<Player>players) {
        this.setLayout(null);
        this.setBounds(0,0,512,512);

        this.players = players;
        nameLabels = new ArrayList<>();

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

            nameLabels.add(nameLabel);
            this.add(nameLabel);
        }
    }

    public void movePlayer() {
        if (players.get(GameFrame.i).getBox() < 10) {
            nameLabels.get(GameFrame.i).setLocation(nameLabels.get(GameFrame.i).getX() - 42, nameLabels.get(GameFrame.i).getY());
        } else if (players.get(GameFrame.i).getBox() >= 10 && players.get(GameFrame.i).getBox() < 20) {
            nameLabels.get(GameFrame.i).setLocation(nameLabels.get(GameFrame.i).getX(), nameLabels.get(GameFrame.i).getY() - 42);
        } else if (players.get(GameFrame.i).getBox() >= 20 && players.get(GameFrame.i).getBox() < 30) {
            nameLabels.get(GameFrame.i).setLocation(nameLabels.get(GameFrame.i).getX() + 42, nameLabels.get(GameFrame.i).getY());
        } else if (players.get(GameFrame.i).getBox() >= 30 && players.get(GameFrame.i).getBox() < 40) {
            nameLabels.get(GameFrame.i).setLocation(nameLabels.get(GameFrame.i).getX(), nameLabels.get(GameFrame.i).getY() + 42);
        }

        players.get(GameFrame.i).setBox();
        
    }

}