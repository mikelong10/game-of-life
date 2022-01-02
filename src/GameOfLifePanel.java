package GameOfLife;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameOfLifePanel extends JPanel {

    private LifeBoard lifeBoard;
    private Timer timer;

    public GameOfLifePanel(int width, int height) {
        setSize(width, height);

        lifeBoard = new LifeBoard();
        timer = new Timer(20, e -> {
            lifeBoard.nextGen();
            repaint();
        });

        setupMouse();
        setupKeyBoard();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        lifeBoard.draw(g2);
    }

    public void setupMouse(){
        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int r = y/LifeBoard.SIZE;
                int c = x/LifeBoard.SIZE;

                lifeBoard.toggleCell(r,c);
                repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void setupKeyBoard(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_SPACE)
                    lifeBoard.nextGen();
                if(keyCode == KeyEvent.VK_1)
                    lifeBoard.insertPattern(10, 10, "glider_gun");
                if(keyCode == KeyEvent.VK_2)
                    lifeBoard.insertPattern(10, 100, "spaghetti_monster");
                if(keyCode == KeyEvent.VK_3)
                    lifeBoard.insertPattern(20, 50, "gliders");
                if (keyCode == KeyEvent.VK_P) {
                    if(timer.isRunning())
                        timer.stop();
                    else
                        timer.start();
                }
                if (keyCode == KeyEvent.VK_C)
                    lifeBoard = new LifeBoard();
                repaint();
            }
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}