
import java.lang.ArrayIndexOutOfBoundsException;

public class Board {
	private int[][] board;
	
	public Board()
	{
		board = new int[10][10];
	}
	
	public void displaytoMe()
	{
		System.out.println();
		System.out.println("This is your board. O: your ships / X: where ships are hit / -: enemy misses");
		System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
		System.out.println("--+---+---+---+---+---+---+---+---+---+---|");
		for(int x=0; x<board.length; x++)
		{
			System.out.print(x + " |");
			
			for(int y = 0; y<board.length; y++)
			{
				System.out.print(" ");
				
				if(board[x][y] == 1 || board[x][y] == 2 || board[x][y] == 3 || board[x][y] == 4 | board[x][y] == 5)
				{
					System.out.print("O");
				}
				
				if(board[x][y] == 6)
				{
					System.out.print("X");
				}
				
				if(board[x][y] == 11)
				{
					System.out.print("-");
				}
				
				if(board[x][y] == 0)
				{
					System.out.print(" ");
				}
				
				System.out.print(" ");
				System.out.print("|");
			}
			System.out.println();
			System.out.println("--+---+---+---+---+---+---+---+---+---+---|");		
		}
	}
	
	public void displaytoEnemy()
	{
		System.out.println("This is the enemy board. X: hits / -: misses");
		System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
		System.out.println("--+---+---+---+---+---+---+---+---+---+---|");
		
		for(int x=0; x<board.length; x++)
		{
			System.out.print(x + " |");
			
			for(int y = 0; y<board.length; y++)
			{
				System.out.print(" ");
				
				if(board[x][y] == 1 || board[x][y] == 2 || board[x][y] == 3 || board[x][y] ==4 | board[x][y] == 5)
				{
					System.out.print(" ");
				}
				
				if(board[x][y] == 6)
				{
					System.out.print("X");
				}
				
				if(board[x][y] == 11)
				{
					System.out.print("-");
				}
				
				if(board[x][y] == 0)
				{
					System.out.print(" ");
				}
				
				System.out.print(" ");
				System.out.print("|");
			}
			System.out.println();
			System.out.println("--+---+---+---+---+---+---+---+---+---+---|");	
		}
	}
	
	public boolean checkAvailibility(boolean placement, int len, int x, int y)
	{//checks if a spot is available
		boolean valid = false;
			if (placement == true)
			{
				for (int n=0; n<len; n++)
				{
					if (board[x][y+n] == 0)
					{
						valid = true;
					}
					else
					{
						valid = false;
						break;
					}
				}
			}
			if (placement == false)
			{
				for (int n=0; n<len; n++)
				{
					if (board[x+n][y] == 0)
					{
						valid = true;
					}
					else
					{
						valid = false;
						break;
					}
				}
			}
			
		return valid;
	}
	
	public void addShip(boolean placement, int len, int x, int y, int type)
	{
		if (placement == true)
		{
			for(int n = 0; n<len; n++)
			{
				board[x][y+n] = type;
			}
		}
		if (placement == false)
			for(int n = 0; n<len; n++)
			{
				board[x+n][y] = type;
			}
	}
	
	public int checkHit(Coordinates c)
	{
		int x = c.getX();
		int y = c.getY();
		int status = 0;

		if (board[x][y] == 0)
		{
			board[x][y] = 7;
			status = 0;
		}
		
		if (board[x][y] > 0 && board[x][y] < 6)
		{
			status = board[x][y];
		}

		return status;
	}
	
	public void receiveHit(Coordinates c)
	{
		board[c.getX()][c.getY()] = 6;
	}	
	
	public void receiveMiss(Coordinates c)
	{
		board[c.getX()][c.getY()] = 11;
	}
}

