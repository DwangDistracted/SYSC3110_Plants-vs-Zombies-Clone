import java.util.*;

public class Board {
	
	private Object[][] board;
	private int row;
	private int column;
	private HashMap unitList;  //this is useless atm
	private Queue badGuys; //there should be one of these per row. idk how to do that
	private ArrayList<Regular_Zombie> badGuysList; //planed to store instances of zombies in here
	private int SFCounter;
	
	public Board(int row, int column)
	{
		unitList = new HashMap();
		badGuys = new LinkedList();
		badGuysList = new ArrayList();
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
		int[][] placement = {{row,column}}; //useless atm
		unitList.put(unit, placement);      //useless atm
		

		board[row][column] = unit.toString();
		
		
		if(unit instanceof Regular_Zombie)
		{
			badGuys.add(unit);
			badGuysList.add((Regular_Zombie) unit);
		}
		else if(unit instanceof Flower)
		{
			SFCounter++;
		}
	}
	
	public void clearUnit(int row,int column)
	{
		board[row][column] = " ";
	}
	
	public void moveUnit(int speed, int row, int column)  //where row and column is where they currently are
	{
		int modifer = 0;
		for(int i = 1; i <= speed; i++)
		{
			if(board[row][column - i] == " ")
			{
				modifer++;
			}
			else
				break;
		}
		
		board[row][column - modifer] = board[row][column];
		clearUnit(row,column);
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
		/*
		int numberOfSF = 0;
		for (Object key : unitList.keySet())  //can't have the keyset.. need to find something else 
		{
		    if(key instanceof Flower)
		    {
		    	numberOfSF++;
		    }
		}
		*/
		return SFCounter;
	}
	
	
	
	public void computeAttacks() //under construction
	{
		for(Object i : badGuys)
		{
			for(Object key : unitList.keySet())  //fix
			{
				if(key instanceof Regular_Zombie)
				{
					unitList.get(key);
				}
			}
		}
	}
	
	
	

}
