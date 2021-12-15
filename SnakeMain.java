package snake2;

public class SnakeMain
{

	public static void main(String[] args)
	{
		SnakeFrame frame = new SnakeFrame();
		frame.getModel().printBoard(frame.getModel().getBoard());
	}

}
