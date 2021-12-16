package snake2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakePanel extends JPanel implements KeyListener
{
	final int PANEL_SIZE = 500;
	final int UNIT_SIZE = PANEL_SIZE/20; //unitsize = panelsize/boardsize
	private SnakeFrame frame;
	int foodX;
	int foodY;
	
	SnakePanel(SnakeFrame frame)
	{
		this.frame = frame;
		this.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		this.setBackground(Color.gray);
		this.addKeyListener(this);
		this.setFocusable(true);//needed so keylistener works in panel
		this.setFocusTraversalKeysEnabled(false);//needed to keylistener works in panel
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawFood(g);	
		drawSnake(g);
	//	drawGrid(g);
		repaint();
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
		
		g.setColor(Color.YELLOW);
		g.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
	}
	public void drawSnake(Graphics g)
	{
		for(int i = 0; i < frame.getModel().getBoard().length; i++) {
			for(int j = 0; j < frame.getModel().getBoard().length; j++) {
				if(frame.getModel().getBoard()[i][j] == 'x') {
					g.setColor(Color.red);
					g.fillRect(j*UNIT_SIZE, i*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				} 
				else if (frame.getModel().getBoard()[i][j] == 's') {
					g.setColor(Color.pink);
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
			frame.getModel().left(frame.getModel().getBoard());
		}
		if(arrowpressed == 38) {
			frame.getModel().up(frame.getModel().getBoard());
		}
		if(arrowpressed == 39) {
			frame.getModel().right(frame.getModel().getBoard());
		}
		if(arrowpressed == 40) {
			frame.getModel().down(frame.getModel().getBoard());
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
}
