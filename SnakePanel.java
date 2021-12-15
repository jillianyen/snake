package snake2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SnakePanel extends JPanel implements KeyListener, ActionListener
{
	final int PANEL_SIZE = 500;//panel will be square, so height and width both are same size
	final int UNIT_SIZE = 20; //panel size / model board size for unit size
	
	int xHead, yHead, xO, yO; 
	
	Timer timer;
	int xVel = UNIT_SIZE;
	int yVel = UNIT_SIZE; 
	int speed = 200;//bigger num = slower speed
	
	private SnakeFrame frame;
	
	SnakePanel(SnakeFrame frame)
	{
		this.frame = frame;
		this.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		this.setBackground(Color.gray);
		this.addKeyListener(this);
		this.setFocusable(true);//needed so keylistener works in panel
		this.setFocusTraversalKeysEnabled(false);//needed to keylistener works in panel
		timer = new Timer(speed, this);
		timer.start();
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawGrid(g);
		drawHead(g);
		drawO(g);
		repaint();
	}
	
	public void drawGrid(Graphics g)
	{
		for(int i = 0; i < PANEL_SIZE/UNIT_SIZE; i++) {
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, PANEL_SIZE);
			g.drawLine(0, i*UNIT_SIZE, PANEL_SIZE,i*UNIT_SIZE);
		}
	}
	public void drawHead(Graphics g)
	{
		xHead = frame.getModel().getHeadY(frame.getModel().getBoard());
		yHead = frame.getModel().getHeadX(frame.getModel().getBoard());
		
		//drawing it on panel
		g.setColor(Color.RED);
		g.fillRect(xHead*UNIT_SIZE, yHead*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
	}
	public void drawO(Graphics g)
	{
		//getting x and y coor of o from model
		xO = frame.getModel().getOy(frame.getModel().getBoard());
		yO = frame.getModel().getOx(frame.getModel().getBoard());
		
		//drawing it on panel
		g.setColor(Color.YELLOW);
		g.fillRect(xO*UNIT_SIZE, yO*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
	}
	
	

	@Override
	public void keyPressed(KeyEvent e)
	{
		int arrowpressed = e.getKeyCode();
		if(arrowpressed == 37) {
			frame.getModel().moveLeft(frame.getModel().getBoard());
		}
		if(arrowpressed == 38) {
			frame.getModel().moveUp(frame.getModel().getBoard());
		}
		if(arrowpressed == 39) {
			frame.getModel().moveRight(frame.getModel().getBoard());
		}
		if(arrowpressed == 40) {
			frame.getModel().moveDown(frame.getModel().getBoard());
		}
	}
	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyReleased(KeyEvent e){}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		repaint();
		xHead = frame.getModel().getHeadY(frame.getModel().getBoard());
		yHead = frame.getModel().getHeadX(frame.getModel().getBoard());
		xHead += xVel;
		yHead += yVel;
	}
}
