package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoardPanel extends JPanel{
    private JLabel label;
    private ImageIcon img;


    public GameBoardPanel() {
        this.setLayout(null);

        img = new ImageIcon("src/gui/images/tabellone.jpg");
        label = new JLabel();

        label.setBounds(0, 0, 512, 512);
        label.setIcon(img);
        label.setOpaque(true);
    }
    
}
