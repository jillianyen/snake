package snake2;

import java.util.Random;
import java.util.Scanner;

public class SnakeModel
{
	public char[][] board;
	public int boardsize = 20;
	public int boardTiles = boardsize*boardsize;
	public int foodEaten = 0;
	public int xPosOfFood;
	public int yPosOfFood;
	public int snakeBodyX[] = new int[boardTiles];
	public int snakeBodyY[] = new int[boardTiles];
	public int snakeSize = 3;
	public int score = 0; 
	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	
	public SnakeModel()
	{
		setBoard(new char[boardsize][boardsize]);
		initBoard(getBoard());
		initSnake(getBoard());
		System.out.println("Score: " + score);
//		printBoard(getBoard());
	//	move(getBoard()); //use only when not playing with graphics
	}
	//fill board with '.'s
	public void initBoard(char[][] board)
	{
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = '.';
			}
		}
		placeFood(getBoard());
	}
	//initialize snake of size snakeSize. the head = 'x', the body = 's'. places head in center of board with body going downwards
	public void initSnake(char[][] board)
	{
		for(int i = 0; i < snakeSize; i++)
		{
			if(i==0)//head of snake
			{
				snakeBodyX[i] = boardsize/2;
				snakeBodyY[i] = boardsize/2;
				board[snakeBodyX[i]][snakeBodyY[i]] = 'x';
			}
			else //snake body
			{
				snakeBodyX[i] = getHeadX(board)+i; 
				snakeBodyY[i] = getHeadY(board);
				board[snakeBodyX[i]][snakeBodyY[i]] = 's';
			}
		}
		
	}
	public void right(char[][] board)
	{
		clearBody(board);
		for(int i = snakeSize; i>0; i--) {
			snakeBodyX[i] = snakeBodyX[i-1];
			snakeBodyY[i] = snakeBodyY[i-1];
			board[snakeBodyX[i]][snakeBodyY[i]] = 's';
		}
		snakeBodyY[0] += 1;
		checkSpot(board, snakeBodyX[0], snakeBodyY[0]);
		board[snakeBodyX[0]][snakeBodyY[0]] = 'x';
//		printBoard(board);
	}
	public void left(char[][] board)
	{
		clearBody(board);
		for(int i = snakeSize; i>0; i--) {
			snakeBodyX[i] = snakeBodyX[i-1];
			snakeBodyY[i] = snakeBodyY[i-1];
			board[snakeBodyX[i]][snakeBodyY[i]] = 's';
		}
		snakeBodyY[0] -= 1;
		checkSpot(board, snakeBodyX[0], snakeBodyY[0]);
		board[snakeBodyX[0]][snakeBodyY[0]] = 'x';
//		printBoard(board);
	}
	public void up(char[][] board)
	{
		clearBody(board);
		for(int i = snakeSize; i>0; i--) {
			snakeBodyX[i] = snakeBodyX[i-1];
			snakeBodyY[i] = snakeBodyY[i-1];
			board[snakeBodyX[i]][snakeBodyY[i]] = 's';
		}
		snakeBodyX[0] -= 1; 
		checkSpot(board, snakeBodyX[0], snakeBodyY[0]);
		board[snakeBodyX[0]][snakeBodyY[0]] = 'x';
//		printBoard(board);
	}
	public void down(char[][] board)
	{
		clearBody(board);
		for(int i = snakeSize; i>0; i--) {
			snakeBodyX[i] = snakeBodyX[i-1];
			snakeBodyY[i] = snakeBodyY[i-1];
			board[snakeBodyX[i]][snakeBodyY[i]] = 's';
		}
		snakeBodyX[0] += 1; 
		checkSpot(board, snakeBodyX[0], snakeBodyY[0]);
		board[snakeBodyX[0]][snakeBodyY[0]] = 'x';
//		printBoard(board);
	}
	//used when playing without graphics, need to edit
	public void move(char[][] board)
	{
		while(true)
		{
			clearBody(board);
			
			//fill in body of snake.
			//start with last element. the new location of each element is the previous element's old location
			for(int i = snakeSize; i>0; i--) {
				snakeBodyX[i] = snakeBodyX[i-1];
				snakeBodyY[i] = snakeBodyY[i-1];
				board[snakeBodyX[i]][snakeBodyY[i]] = 's';
			}
			
			//moving head of snake depending on input
			int input = scan.nextInt();
			if(input == 2) {
				snakeBodyX[0] += 1; 
				checkSpot(board, snakeBodyX[0], snakeBodyY[0]);
				board[snakeBodyX[0]][snakeBodyY[0]] = 'x';
				printBoard(board);
			}
			if(input == 4) {
				snakeBodyY[0] -= 1;
				checkSpot(board, snakeBodyX[0], snakeBodyY[0]);
				board[snakeBodyX[0]][snakeBodyY[0]] = 'x';
				printBoard(board);
			}
			if(input == 8) {
				snakeBodyX[0] -= 1; 
				checkSpot(board, snakeBodyX[0], snakeBodyY[0]);
				board[snakeBodyX[0]][snakeBodyY[0]] = 'x';
				printBoard(board);
			}
			if(input == 6) {
				snakeBodyY[0] += 1;
				checkSpot(board, snakeBodyX[0], snakeBodyY[0]);
				board[snakeBodyX[0]][snakeBodyY[0]] = 'x';
				printBoard(board);
			}
		}
	}
	//check spot that head lands on when moved
	public void checkSpot(char[][] board, int x, int y)
	{
		if(board[x][y] == 'o') //means you ate a food. increase score and place another food
		{
			placeFood(board);
			snakeSize++;
			score++;
			System.out.println("Score: " + score);
		}
		if(board[x][y] == 's')//means you ran into yourself. end game and display score
		{
			System.out.println("You lose");
			System.out.println("Score: " + score);
		}
		if(board[x][y] != '.' && board[x][y] != 'o' && board[x][y] != 's')//means you went offscreen
		{
			System.out.println("You lose");
			System.out.println("Score: "+score);
		}
	}
	//remove all 's's on board
	public void clearBody(char[][] board)
	{
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == 's') {
					board[i][j] = '.';
				}
			}
		}
	}
	//'o' on board represents a food. place one o on board in random spot
	public void placeFood(char[][] board)
	{
		xPosOfFood = rand.nextInt(board.length);
		yPosOfFood = rand.nextInt(board.length);
		if(board[xPosOfFood][yPosOfFood] != '.')
		{
			xPosOfFood = rand.nextInt(board.length);
			yPosOfFood = rand.nextInt(board.length);
			board[xPosOfFood][yPosOfFood] = 'o';
		} else {
			board[xPosOfFood][yPosOfFood] = 'o';
		}
	}
	//get x coor of snake head
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
	//get y coor of snake head
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
	//get x coor of food
	public int getFoodX(char[][] board)
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
	//get y coor of food
	public int getFoodY(char[][] board)
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
	public void printBoard(char[][] board)
	{
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	public char[][] getBoard()
	{
		return board;
	}
	public void setBoard(char[][] board)
	{
		this.board = board;
	}
	public static void main(String[] args)
	{
		SnakeModel model = new SnakeModel();
		//model.printBoard(model.board);
	}

}
