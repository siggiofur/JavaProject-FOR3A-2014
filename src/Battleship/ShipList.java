
public class ShipList {
	private Ship patrol_boat;
	private boolean lost = false;
	
	public ShipList() 
	{
		patrol_boat = new Ship(2);
		bigger_boat = new Ship(3);
	}
	
	public void shipHit(int a)
	{
		patrol_boat.hits();
	}
	
	public boolean isLost()
	{
		if (patrol_boat.isSunk())
		{
			lost = true;
		}
		return lost;
	}

}
