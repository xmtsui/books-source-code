/**
 * @author Lewis and Chase
 *
 *  Represents a maze of characters. The goal is to get from the
 *  top left corner to the bottom right, following a path of 1's.
 */
import jss2.*;

public class Maze
{
  /**
   * constant to represent tried paths
   */
  private final int TRIED = 3;
  /**
   * constant to represent the final path
   */
  private final int PATH = 7;
  /**
   * two dimensional array representing the grid
   */
  private int [][] grid = { {1,1,1,0,1,1,0,0,0,1,1,1,1},
                            {1,0,0,1,1,0,1,1,1,1,0,0,1},
                            {1,1,1,1,1,0,1,0,1,0,1,0,0},
                            {0,0,0,0,1,1,1,0,1,0,1,1,1},
                            {1,1,1,0,1,1,1,0,1,0,1,1,1},
                            {1,0,1,0,0,0,0,1,1,1,0,0,1},
                            {1,0,1,1,1,1,1,1,0,1,1,1,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0},
                            {1,1,1,1,1,1,1,1,1,1,1,1,1} };
  /**
   * push a new attempted move onto the stack
   * @param x represents x coordinate
   * @param y represents y coordinate
   * @param stack the working stack of moves within the grid
   * @return StackADT<Position> stack of moves within the grid
   */
  private StackADT<Position> push_new_pos(int x, int y, 
                                          StackADT<Position> stack)
  {
    Position npos = new Position();
    npos.setx(x);
    npos.sety(y);
    if (valid(npos.getx(),npos.gety()))
      stack.push(npos);

    return stack;
  }
  
  /**
   * Attempts to iteratively traverse the maze.  It inserts special
   * characters indicating locations that have been tried and that
   * eventually become part of the solution.  This method uses a 
   * stack to keep track of the possible moves that could be made.
   * @return boolean returns true if the maze is successfully traversed
   */
  public boolean traverse ()
  {
    boolean done = false;
    Position pos = new Position();
    Object dispose;
    StackADT<Position> stack = new LinkedStack<Position>();
    stack.push(pos);
    
    while (!(done))
    {
      pos = stack.pop();
      grid[pos.getx()][pos.gety()] = TRIED;  // this cell has been tried
      if (pos.getx() == grid.length-1 && pos.gety() == grid[0].length-1)
        done = true;  // the maze is solved
      else
      {
        stack = push_new_pos(pos.getx(),pos.gety() - 1, stack);
        stack = push_new_pos(pos.getx(),pos.gety() + 1, stack); 
        stack = push_new_pos(pos.getx() - 1,pos.gety(), stack); 
        stack = push_new_pos(pos.getx() + 1,pos.gety(), stack);
      }
    }
    
    return done;
  }
  
  /**
   * Determines if a specific location is valid.
   * @param row int representing y coordinate
   * @param column int representing x coordinate
   * @return boolean true if the given coordinate is a valid move
   */
  private boolean valid (int row, int column)
  {
    boolean result = false;
    
    /** Check if cell is in the bounds of the matrix */
    if (row >= 0 && row < grid.length &&
        column >= 0 && column < grid[row].length)
      
      /**  Check if cell is not blocked and not previously tried */
      if (grid[row][column] == 1)
        result = true;
    
    return result;
  }
  
  /**
   * Returns the maze as a string.
   * @return String representation of the maze grid
   */
  public String toString ()
  {
    String result = "\n";
    
    for (int row=0; row < grid.length; row++)
    {
      for (int column=0; column < grid[row].length; column++)
        result += grid[row][column] + "";
      
      result += "\n";
    }
    
    return result;
  }
}
