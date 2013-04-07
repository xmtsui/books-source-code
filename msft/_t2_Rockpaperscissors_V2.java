import java.util.Scanner;
class _t2_Rockpaperscissors_V2{
  public static void main(String args[])
  {
    Scanner sin = new Scanner(System.in);

    String s_group = sin.nextLine();

    int group = new Integer(s_group);
    
    int p1 = 0; 
    int p2 = 0;
    int p3 = 0;
    String[][] paramsa=new String[group][];
    String[][] paramsb=new String[group][];
    for(int group_index=0; group_index < group; group_index++)
    {
      String s_params = sin.nextLine();
      String[] params = s_params.split(" ");
      p1 = new Integer(params[0]);
      p2 = new Integer(params[1]);
      p3 = new Integer(params[2]);

      String s_paramsa = sin.nextLine();
      paramsa[group_index] = new String[p2];
      paramsa[group_index] = s_paramsa.split(" ");

      String s_paramsb = sin.nextLine();
      paramsb[group_index] = new String[p3];
      paramsb[group_index] = s_paramsb.split(" ");
    }//end of for
    
    for(int gi=0; gi < group; gi++)
    {
      int round = 0;
      int ia = 0;
      int ib = 0;
    
      int[] a = new int[p1];
      int[] b = new int[p1];

      while( round < p1)
      {
        ia = 0;
        while( ia < p2)
	{
	  a[round] = new Integer(paramsa[gi][ia]);
	  ia++;
	  round++;
	  if(round >= p1)
	    break;
	}
	if( round % p2 == 0)
	  continue;
        round++;
      }

      round = 0;
      while( round < p1)
      {
        ib = 0;
        while( ib < p3)
	{
	  b[round] = new Integer(paramsb[gi][ib]);
	  ib++;
	  round++;
	  if(round >= p1)
	    break;
	}
	if( round % p3 == 0)
	  continue;
        round++;
      }
      
      round = 0;
      int aw = 0;
      int bw = 0;
      int ab = 0;
      while( round < p1)
      {
        if(a[round] == 0)
	{
	  if(b[round] == 0)
	  {
	    ab++;
	  }
	  else if(b[round] == 2)
	  {
	    aw++;
	  }
	  else if(b[round] == 5)
	  {
	    bw++;
	  }
	}
	else if(a[round] == 2)
	{
	  if(b[round] == 0)
	  {
	    bw++;
	  }
	  else if(b[round] == 2)
	  {
	    ab++;
	  }
	  else if(b[round] == 5)
	  {
	    aw++;
	  }
	}
	else if(a[round] == 5)
	{
	  if(b[round] == 0)
	  {
	    aw++;
	  }
	  else if(b[round] == 2)
	  {
	    bw++;
	  }
	  else if(b[round] == 5)
	  {
	    ab++;
	  }
	}
	round++;
      }//end of while

      if(aw > bw)
      {
        System.out.println("A");
      }
      else if(aw < bw)
      {
        System.out.println("B");
      }
      else
      {
        System.out.println("draw");
      }
    }//end of for
  }//end of main
}//end of class
