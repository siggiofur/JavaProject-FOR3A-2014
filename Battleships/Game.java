public class Game {
	Player Human;
	Player Computer;
	
	public Game()
	{
		Human = new Player("Player 1", true);
		Computer = new Player("Player 2", false);
	}
	
	public void play()
	{
		Human.displaytoMe();
		Human.placeShip(3, 4);
		Human.displaytoMe();
		Human.placeShip(2, 5);
		Human.displaytoMe();
		Computer.placeShip(3, 4);
		Computer.placeShip(2, 5);
		
		Computer.displaytoEnemy();
		
		while (!Human.hasLost() && !Computer.hasLost())
		{
			Computer.receiveAttack(Human.attack());
			Computer.displaytoEnemy();
			if (Computer.hasLost())
			{
				break;
			}
			Human.receiveAttack(Computer.attack());
			Human.displaytoMe();
		}
		
		if (Computer.hasLost())
		{
			System.out.println("Computer has lost.");
		}
		
		if (Human.hasLost())
		{
			System.out.println("Human has lost.");
		}	
	}
}
