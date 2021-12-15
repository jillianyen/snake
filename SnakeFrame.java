package snake2;

import java.awt.event.*;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame //implements KeyListener
{
	SnakePanel panel;
	SnakeModel model; 
	SnakeFrame()
	{
		panel = new SnakePanel(this);
		model = new SnakeModel(25);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		pack();//autosize the frame depending on component sizes
		setLocationRelativeTo(null);
		//addKeyListener(this);
		setVisible(true);
	}

	public SnakeModel getModel()
	{
		return model;
	}
	
	
	/*
	@Override
	public void keyPressed(KeyEvent e)
	{
		int arrowpressed = e.getKeyCode();
		if(arrowpressed == 37) {
			model.moveLeft(model.getBoard());
		}
		if(arrowpressed == 38) {
			model.moveUp(model.getBoard());
		}
		if(arrowpressed == 39) {
			model.moveRight(model.getBoard());
		}
		if(arrowpressed == 40) {
			model.moveDown(model.getBoard());
		}
	}

	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyReleased(KeyEvent e){}
*/
}
