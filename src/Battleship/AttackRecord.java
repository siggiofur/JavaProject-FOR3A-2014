
public class AttackRecord {
	private boolean[][] record;
	
	public AttackRecord()
	{
		record = new boolean[10][10];
	}
	
	public void addAttack(Coordinates c)
	{
		record[c.getX()][c.getY()] = true;
	}
	
	public boolean isRedundant(int x, int y)
	{
		//checks if the attack has been made before
		return record[x][y];	
	}

}
