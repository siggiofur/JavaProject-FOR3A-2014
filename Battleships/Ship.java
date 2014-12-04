public class Ship {
	private int length;
	private int hits;
	private boolean sunk = false;
	
	public Ship(int initLength)
	{
		length = initLength;
		hits = 0;
	}
	
	public void hits()
	{
		hits++;
	}
	
	public boolean isSunk()
	{
		if(hits == length)
		{
			sunk = true;
		}
		
		return sunk;
	}


}
