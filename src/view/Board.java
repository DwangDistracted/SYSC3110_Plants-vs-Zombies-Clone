package view;

import java.util.*;
import java.util.Map.Entry;
import assets.Flower;
import assets.Peashooter;
import assets.Zombie;



public class Board {
	
	private Object[][] board;
	private int row;
	private int column;
	private LinkedHashMap<Object,int[]> experimental;  //trying something out... first value holds unit instance and second value holds coordinates
	private LinkedHashMap<Integer,Queue> rowQ;         //linkedhashmap of row number and queue of zombies in that row
	private int SFCounter;
	
	public Board(int row, int column)
	{
		experimental = new LinkedHashMap<>();
		rowQ = new LinkedHashMap<>();
		this.row = row;
		this.column = column;
		board = new Object[row][column];
		
		//Initialize board
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c <board[0].length; c++)
			{
				board[r][c] = " ";
			}
		}
		
	}
	
	public void addUnit(Object unit, int row , int column)
	{
		int[] coordinates = {row,column};    //for experimental
		experimental.put(unit,coordinates);  //unitlist
		
		
		board[row][column] = unit.toString();
		
		if(unit instanceof Zombie)
		{
			addToQ(unit, row);
		}
		if(unit instanceof Flower)
		{
			SFCounter++;
		}
	}
	
	public LinkedHashMap<Object,int[]> getExperimental()
	{
		return experimental;
	}
	
	public LinkedHashMap<Integer, Queue> getRowQ()
	{
		return rowQ;
	}
	
	/**
	 * 
	 * @param row where zombie is located
	 * @return returns the zombie that was removed
	 */
	public Object removeZom(Integer row)
	{
		Queue oldQ = rowQ.get(row);
		Queue newQ = rowQ.get(row);
		Object unit = newQ.remove();
		rowQ.replace(row,oldQ,newQ);
		
		int[] cord = experimental.get(unit);
		clearUnit(cord[0],cord[1]);
		
		//experimental.remove(unit); //remove it from the unit linkedhashmap   ---  causes issues in Combat - computePlantAttacks due to iterator
		return unit;
	}
	
	public void clearUnit(int row,int column)
	{
		board[row][column] = " ";
		//experimental.remove(findUnit(row,column));    //causes an exception error message
	}
	
	/**
	 * looks within the experiment hashmap and returns the key corrlating to the given value
	 * @param coordinate
	 * @return key corolating to value. otherwise return null
	 */
	public Object findKey(int row, int column)
	{
		for(Entry<Object, int[]> entry : experimental.entrySet()) 
		{
			int[] cord = entry.getValue();
			int entryRow = cord[0];
			int entryColumn = cord[1];
			
			if(entryRow == row && entryColumn == column)
			{
				return entry.getKey();
			}
		}
		return null;
	}
	
	/**
	 * looks within the experiment hashmap and returns the key corrlating to the given value
	 * @param coordinate
	 * @return key corolating to value. otherwise return null
	 */
	public Object findKey(int[] coordinate)
	{
		for(Entry<Object, int[]> entry : experimental.entrySet()) {
			if(coordinate == entry.getValue())
			{
				return entry.getKey();
			}
		}
		return null;
	}
	
	
	/**
	 * moves unit based on speed and current coordinates
	 * Checks to see if the calculated new coordinate is occupied by a plant, if so
	 * decrease the speed
	 * @param speed
	 * @param row
	 * @param column
	 * @return  the new coordinates if the spot is not occupied, otherwise return the same coordinate or null if zombies win
	 */
	public int[] moveUnit(int speed, int row, int column)  //where row and column is where they currently are
	{	
		int modifer = 0;
		for(int i = 1; i <= speed; i++)
		{
			if(column - i < 0)
			{
				return null;
			}
			if(board[row][column - i] == " ")
			{
				modifer++;
			}
			else
				break;
		}
		
		int[] newCoordinate = {row, column - modifer};
		
		if(!isOccupied(newCoordinate))
		{
			board[row][column - modifer] = board[row][column];
			clearUnit(row,column);
			return newCoordinate;                    //I believe this value always gets returned
		}
		else
		{
			speed--;
			if(speed > 0)
			{
				return moveUnit(speed, row, column); 
			}
		}
		
		int[] sameCoordinate = {row, column};          
		return sameCoordinate;                     //i believe there is never a situation where this gets returned
	}
	

	public void displayBoard()
	{
		for(Object[] a : board)
		{
			int columnCount = 0;
			for(Object i : a)
			{
				System.out.print(i + "   |    ");
				
			}	
			System.out.println();
			for(Object i : a)
			{
				System.out.print(columnCount + "   |    ");
				columnCount++;
			}
			System.out.print("\n");
		}
		
	}
	
	/**
	 * determines if a coordinate is occupied by a plant
	 * @return boolean - true if occupied, false otherwise
	 */
	public boolean isOccupied(int[] coordinate)
	{
		
		for (Entry<Object, int[]> entry : experimental.entrySet())
		{
			int[] test = entry.getValue();
			//System.out.println((test[0] == coordinate[0] && test[1] == coordinate[1]) + " coordinate: " + coordinate[0] + coordinate[1] + " value " + test[0] + test[1]); //testing purposes
			//System.out.println((entry.getKey() instanceof Flower) + "instance of flower");  //testing purposes
			
		    if((entry.getKey() instanceof Flower || entry.getKey() instanceof Peashooter)&& test[0] == coordinate[0] && test[1] == coordinate[1])
		    	return true;
		}
		
		return false;
	}
	
	public void addToQ(Object unit, int row)
	{
		Queue newQ = new LinkedList();
		Queue oldQ = new LinkedList();
		boolean found = false;
		for(Entry<Integer, Queue> entry : rowQ.entrySet()) 
		{
			if(entry.getKey() == row)
			{
				oldQ = entry.getValue();
				newQ = entry.getValue();
				newQ.add(unit);
				found = true;
			}
		}
		if(found == true)
		{
			rowQ.replace(row, oldQ, newQ);
		}
		else //if there hasn't been any zombies in the row yet
		{
			newQ.add(unit);
			rowQ.put(row, newQ);
		}
		
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getColumn()
	{
		return this.column;
	}
	
	public int getNumberOfSF()
	{
		return SFCounter;
	}

	public int getNumberOfZombies() {
		return 0;
	}
	
	

	
	
	

}