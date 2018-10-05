
public class Board {
	
	Object[][] board;
	
	public Board(int row, int column)
	{
		board = new Object[row][column];
	}
	
	public void initBoard()
	{
		for(int row = 0; row < board.length; row++)
		{
			for(int column = 0; column <board[0].length; column++)
			{
				board[row][column] = " ";
			}
		}
	}
	
	
	public void displayBoard()
	{
		board[0][0] = "*";
		for(Object[] a : board)
		{
			for(Object i : a)
			{
				System.out.print(i + "   |    ");
			}	
			System.out.println(" ---- ");
		}
		
	}
	

}
