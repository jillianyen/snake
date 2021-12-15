package snake2;

import java.util.*;

public class SnakeModel
{

	private char[][] board; 
	public int osEaten = 0;
	
	public SnakeModel(int boardsize)
	{
		setBoard(new char[boardsize][boardsize]);
		initBoard(getBoard());
		placeO(getBoard());
		placeStart(getBoard());
	}
	
	public void initBoard(char[][] board)
	{
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = '.';
			}
		}
	}
	public void placeO(char[][] board)
	{
		Random rand = new Random();
		int x = rand.nextInt(board.length);
		int y = rand.nextInt(board.length);
		if(board[x][y] != '.')
		{
			x = rand.nextInt(board.length);
			y = rand.nextInt(board.length);
			board[x][y] = 'o';
		} else {
			board[x][y] = 'o';
		}
	}
	public void placeStart(char[][] board)
	{
		Random rand = new Random();
		int x = rand.nextInt(board.length);
		int y = rand.nextInt(board.length);
		board[x][y] = 'x';
	}
	public void printBoard(char[][] board)
	{
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	public int getHeadX(char[][] board)
	{
		int x = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == 'x') {
					x = i;
				}
			}
		}
		return x;
	}
	//get snake head y coor
	public int getHeadY(char[][] board)
	{
		int y = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == 'x') {
					y = j;
				}
			}
		}
		return y;
	}
	
	public int getOx(char[][] board)
	{
		int x = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == 'o') {
					x = i;
				}
			}
		}
		return x;
	}
	
	public int getOy(char[][] board)
	{
		int y = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == 'o') {
					y = j;
				}
			}
		}
		return y;
	}
	
	
	public void moveDown(char [][] board)
	{
		int x = getHeadX(board);
		int y = getHeadY(board);
		board[x][y] = '.';
		x = x+1;
		checkSpot(board, x, y);
		board[x][y] = 'x';
		printBoard(board);
	//	return board;
	}
	public void moveUp(char [][]board)
	{
		int x = getHeadX(board);
		int y = getHeadY(board);
		board[x][y] = '.';
		x = x-1;
		checkSpot(board, x, y);
		board[x][y] = 'x';
		printBoard(board);
		//return board; 
	}
	public void moveRight(char [][] board)
	{
		int x = getHeadX(board);
		int y = getHeadY(board);
		board[x][y] = '.';
		y = y+1;
		checkSpot(board, x, y);
		board[x][y] = 'x';
		printBoard(board);
		//return board; 
	}
	public void moveLeft(char [][] board)
	{
		int x = getHeadX(board);
		int y = getHeadY(board);
		board[x][y] = '.';
		y-=1;
		checkSpot(board, x, y);
	    board[x][y] = 'x';
	    printBoard(board);
	//	return board;
	}
	//after moving snake head, check curr spot to see if you at an o, if so, place a new random o on board
	public void checkSpot(char[][] board, int x, int y)
	{
		if(board[x][y] == 'o')
		{
			placeO(board);
		}
	}
	public char[][] getBoard()
	{
		return board;
	}

	//used in constructor so model can use board var outside of this class using getBoard()
	public void setBoard(char[][] board)
	{
		this.board = board;
	}
	
	public static void main(String[] args)
	{
		SnakeModel model = new SnakeModel(10);
		model.printBoard(model.getBoard());
	}


}
