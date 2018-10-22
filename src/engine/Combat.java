package engine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import assets.*;

import java.util.Queue;

import view.*;

public class Combat 
{
	Board board;
	
	public Combat(Board board)
	{
		this.board = board;
	}
	
	public void computeZombieAttacks()
	{
		ArrayList<Object> removeBin = new ArrayList(); // for storing keys to be removed
		for(Object zombieKey : board.getExperimental().keySet()) //find attacker
		{
			if(zombieKey instanceof Regular_Zombie)
			{
				int[] zombieCord = board.getExperimental().get(zombieKey);
				int[] refZC = new int[] {zombieCord[0],zombieCord[1]};     //had to create a reference to zombieCord because it affected the actual value
				refZC[1] -= 1;                                            //find the unit infront of the zombie
				if(board.isOccupied(refZC)) 
				{
					for(Entry<Object, int[]> plantEntry : board.getExperimental().entrySet()) //find victim
					{  
						int[] plantCord = plantEntry.getValue();
						if(plantCord[0] == refZC[0] && plantCord[1] == refZC[1] && plantEntry.getKey() instanceof Plant)         //only checks for an instance of a flower now... have to modify this later
						{
							((Plant) plantEntry.getKey()).takeDamage(((Zombie) zombieKey).getDamage());
							if(((Plant) plantEntry.getKey()).getHP() <= 0)
							{
								int row = plantCord[0];
								int column = plantCord[1];
								board.clearUnit(row, column);
								removeBin.add(plantEntry.getKey());
								System.out.println("TEST: Removed unit");
							}
						}
					}
				}
				
			}
		}
		for(Object plant : removeBin)
		{
			board.getExperimental().remove(plant);
		}
	}
	
	public void computePlantAttacks()
	{
		ArrayList<Object> removeBin = new ArrayList(); // for storing keys to be removed
		
		for(Entry<Object,int[]> unit : board.getExperimental().entrySet())
		{
			System.out.println("TEST: Looking for ps" + unit.getKey().toString());
			if(unit.getKey() instanceof pDmgDealer)
			{
				System.out.println("TEST: Damage dealer found");
				int[] cord = unit.getValue();
				Integer rowOfP = cord[0];
				
				System.out.println("TEST: " + board.getRowQ().containsKey(rowOfP));
				System.out.println("TEST: " + board.getRowQ().isEmpty());
				for(Integer rowKey : board.getRowQ().keySet()) //TEST
				{
					System.out.println(rowKey);    //TEST
				}
				if(board.getRowQ().containsKey(rowOfP))
				{
					//assuming the value correlating to the key is never null
					Queue tempQ = board.getRowQ().get(rowOfP);
					Zombie zombie = (Zombie) tempQ.peek();
					zombie.takeDamage(((pDmgDealer) unit.getKey()).getDamage());
					System.out.println("TEST: Damage dealer did damage");
		
					if(zombie.getHP() <= 0)
					{
						board.removeZom(rowOfP);  //remove unit from rowQ
						removeBin.add(zombie);
						System.out.println("TEST: zombie added to remove bin");
					}
				}
			}	
		}
		
		for(Object zombie : removeBin)
		{
			board.getExperimental().remove(zombie);
			System.out.println("TEST: Damage dealer removed a unit");
		}
	}
	
	

}
