import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class SnakePanel extends JPanel implements KeyListener, ActionListener
{
	final int PANEL_SIZE = 700;
	final int UNIT_SIZE = PANEL_SIZE/20; //unitsize = panelsize/boardsize, this is the length in pixels of how long 1 grid unit is
	private SnakeFrame frame;
	int foodX;
	int foodY;
	int dir = 8;
	int speed = 200;//smaller number = faster snake movement
	Timer t = new Timer(speed, this);
	Random rand = new Random();
	
	SnakePanel(SnakeFrame frame)
	{
		this.frame = frame;
		this.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		this.setBackground(Color.gray);
		this.addKeyListener(this);
		this.setFocusable(true);//needed so keylistener works in panel
		this.setFocusTraversalKeysEnabled(false);//needed to keylistener works in panel
		t.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(frame.getModel().getGameState() == true) {
			drawFood(g);	
			drawSnake(g);
			drawGrid(g);
		} else {
			t.stop();
			drawFood(g);
			drawSnake(g);
			gameOverScreen(g);
		}
	}
	public void gameOverScreen(Graphics g)
	{
		Font myFont = new Font("Helvetica", Font.PLAIN, 50);
		g.setFont(myFont);
		g.setColor(Color.black);
		g.drawString("Game Over", 100, 100);
		g.drawString("Score:", 105, 160);
		g.setColor(Color.green);
		g.drawString(" " +frame.getModel().getScore(), 280, 160);
	}
	public void drawGrid(Graphics g)
	{
		for(int i = 0; i < PANEL_SIZE/UNIT_SIZE; i++) {
			g.setColor(Color.black);
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, PANEL_SIZE);
			g.drawLine(0, i*UNIT_SIZE, PANEL_SIZE,i*UNIT_SIZE);
		}
	}
	public void drawFood(Graphics g)
	{
		foodX = frame.getModel().getFoodY(frame.getModel().getBoard());
		foodY = frame.getModel().getFoodX(frame.getModel().getBoard());
		foodX *= UNIT_SIZE;
		foodY *= UNIT_SIZE;

		g.setColor(Color.red);
		g.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
	}
	public void drawSnake(Graphics g)
	{
		for(int i = 0; i < frame.getModel().getBoard().length; i++) {
			for(int j = 0; j < frame.getModel().getBoard().length; j++) {
				if(frame.getModel().getBoard()[i][j] == 'x') {
					g.setColor(Color.orange);
					g.fillRect(j*UNIT_SIZE, i*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				} 
				else if (frame.getModel().getBoard()[i][j] == 's') {
					g.setColor(new Color(180,90,255));
					g.fillRect(j*UNIT_SIZE, i*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				}
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int arrowpressed = e.getKeyCode();
		if(arrowpressed == 37) {
			dir = 4;
		}
		if(arrowpressed == 38) {
			dir = 8;
		}
		if(arrowpressed == 39) {
			dir = 6;
		}
		if(arrowpressed == 40) {
			dir = 2;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		repaint();
		if(dir == 4) {
			frame.getModel().left(frame.getModel().getBoard());
		}
		if(dir == 8) {
			frame.getModel().up(frame.getModel().getBoard());
		}
		if(dir == 6) {
			frame.getModel().right(frame.getModel().getBoard());
		}
		if(dir == 2) {
			frame.getModel().down(frame.getModel().getBoard());
		}
	}
}
