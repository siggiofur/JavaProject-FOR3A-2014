
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class Player {
	private String playerName;
	private Board board;
	public boolean loss;
	private ShipList fleet;
	private AttackRecord record;
	private boolean human;

	
	public Player(String aname, boolean h)
	{
		playerName = aname;
		board = new Board();
		fleet = new ShipList();
		record = new AttackRecord();
		human = h;

	}
	
	public void placeShip(int m, int n)
	{
		int x = 0;
		int y = 0;
		boolean valid = false;
		boolean alignment = false;
		while(valid == false)
		{
			if(human)
			{
				String ship = "";
				if(n==1)
				{
					ship = "patrolboat";
				}
				if(n==2)
				{
				ship="patrolboat2";
				}
				try
				{
					System.out.println("Please place your " + ship + ". This gamepiece is " + m + " squares long.");
					System.out.println("Should the orientation be: 1 - horizontal, or 2 - vertical?");
					Scanner orientation = new Scanner(System.in);
					int a = orientation.nextInt();
					if(a != 1 && a != 2)
					{
						InputMismatchException e = new InputMismatchException();
						throw e;
					}
					else if(a == 1)
					{
						alignment = true;
					}
					else if(a == 2)
					{
						alignment = false;
					}
					System.out.println("Please name a starting point on the x axis.");
					Scanner vertical = new Scanner(System.in);
					x = vertical.nextInt();
					System.out.println("Please name a starting point on the y axis.");
					Scanner horizontal = new Scanner(System.in);
					y = horizontal.nextInt();
					valid = board.checkAvailibility(alignment, m, x, y);
					if (valid == false)
					{
						System.out.println("There's no place on the board for this ship. Please put it somewhere else.");
					}
				}
				catch (InputMismatchException e)
				{
					System.out.println("The information you have input is not valid. Please try again. :(");
					valid = false;
				}
				
			}
			else
			{
				Random random = new Random();
				alignment = random.nextBoolean();
			    x = (int) (Math.random()*10);
				y = (int) (Math.random()*10);
				valid = board.checkAvailibility(alignment, m, x, y);
			}
		}
		board.addShip(alignment, m, x, y, n);
	}
	
	public void displaytoMe()
	{
		board.displaytoMe();
	}
	
	public void displaytoEnemy()
	{
		board.displaytoEnemy();
	}
	
	public Coordinates attack()
	{
		System.out.println("It is " + playerName + "'s turn to attack.");
		int x = 0;
		int y = 0;
		boolean redundant = true;

		
		if(human)
		{
			while(redundant)
			{
				try
				{
					System.out.println("Please select a target on the x axis to attack.");
					Scanner targetx = new Scanner(System.in);
					x = targetx.nextInt();
					if (x < 0 || x > 9)
					{
						InputMismatchException f = new InputMismatchException();
						throw f;
					}
					System.out.println("Please select a target on the y axis to attack.");
					Scanner targety = new Scanner(System.in);
					y = targety.nextInt();
					if (y < 0 || y > 9)
					{
						InputMismatchException f = new InputMismatchException();
						throw f;
					}
					else
					{
						redundant = false;
					}
				}
				catch (InputMismatchException f)
				{
					System.out.println("This is not a valid target.");
					redundant = true;
				}
			}
			
		}
		else
		{
			while (redundant)
			{
				x = (int) (Math.random()*10);
				y = (int) (Math.random()*10);
				if(record.isRedundant(x, y) == false)
				{
					redundant = false;
				}
			}
		}
		Coordinates attackCoordinates = new Coordinates(x, y);
		record.addAttack(attackCoordinates);
		return attackCoordinates;
	}
	
	public void receiveAttack(Coordinates c)
	{
		int status = board.checkHit(c);
		if (status == 0)
		{
			System.out.println("The attack has missed.");
			board.receiveMiss(c);
		}
		else
		{
			System.out.println(playerName + "'s ship has been hit.");
			board.receiveHit(c);
			fleet.shipHit(status);
		}
	}
	
	public boolean hasLost()
	{
		if (fleet.isLost())
		{
			loss = true;
		}
		return loss;
		
	}
}
