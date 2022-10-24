package gui;

import javax.swing.JFrame;

import app.Menager;

public class GameFrame extends JFrame{
    private GameFramePanel gameFramePanel;

    public GameFrame(Menager menager) {

        this.setSize(720,720);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Monopoli");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        gameFramePanel = new GameFramePanel(menager);

        this.add(gameFramePanel);
    }
}
