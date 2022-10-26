package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoardPanel extends JPanel{
    private JLabel label;


    public GameBoardPanel() {
        this.setLayout(null);

        label = new JLabel(new ImageIcon("src\\gui\\images\\tabellone.jpg"));

        label.setBounds(0, 30, 512, 512);
        label.setOpaque(true);

        this.add(label);
    }
    
}