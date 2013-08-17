//********************************************************************
//  Maze2.java       Author: Lewis and Chase
//
//  Represents a maze of characters. The goal is to get from the
//  top left corner to the bottom right, following a path of 1s.
//********************************************************************
import jss2.*;
public class Maze2
{




		


   //-----------------------------------------------------------------
   //  Attempts to iteratively traverse the maze.  It inserts special
   //  characters indicating locations that have been tried and that
   //  eventually become part of the solution.
   //-----------------------------------------------------------------
   public boolean traverse ()
   {
      boolean done = false;
      Position pos = new Position();
      Object dispose;
      StackADT stack = new ArrayStack();
      StackADT pathstack = new ArrayStack();
      stack.push(pos);

      while (!(done))
	{
		pos = (Position)stack.pop();
         	grid[pos.getx()][pos.gety()] = TRIED;  // this cell has been tried
         	if (pos.getx() == grid.length-1 && pos.gety() == grid[0].length-1)
          		done = true;  // the maze is solved
		else
		{
			stack = push_new_pos(pos.getx(),pos.gety() - 1, stack);
			stack = push_new_pos(pos.getx(),pos.gety() + 1, stack); 
			stack = push_new_pos(pos.getx() - 1,pos.gety(), stack); 
			stack = push_new_pos(pos.getx() + 1,pos.gety(), stack); 
		}//else
	}//while

      return done;
   }//method traverse
   













}//class Maze2



