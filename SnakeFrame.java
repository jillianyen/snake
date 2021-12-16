package snake2;
import java.awt.event.*;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame
{
	SnakePanel panel;
	SnakeModel model;
	
	SnakeFrame()
	{
		panel = new SnakePanel(this);
		model = new SnakeModel(); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		pack();//autosize the frame depending on component sizes
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public SnakeModel getModel()
	{
		return model;
	}
}

