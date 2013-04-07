import java.util.Scanner;
class _t2_Rockpaperscissors_V1{
  public static void main(String args[])
  {
    Scanner sin = new Scanner(System.in);

    String s_group = sin.nextLine();
    System.out.println(s_group);

    int group = new Integer(s_group);
    for(int group_index=0; group_index < group; group_index++)
    {
      String s_params = sin.nextLine();
      String[] params = s_params.split(" ");
      System.out.printf("params %d, %s \n", 1, params[0]);
      System.out.printf("params %d, %s \n", 2, params[1]);
      System.out.printf("params %d, %s \n", 3, params[2]);
      int p1 = new Integer(params[0]);
      int p2 = new Integer(params[1]);
      int p3 = new Integer(params[2]);

      String s_paramsa = sin.nextLine();
      String[] paramsa = s_paramsa.split(" ");
      if(paramsa.length != p2)
      {
        System.out.println("cycle a error");
	break;
      }
      for(int i=0; i < p2; i++)
      {
        System.out.printf("paramsa %d, %s \n", i, paramsa[i]);
      }

      String s_paramsb = sin.nextLine();
      String[] paramsb = s_paramsb.split(" ");
      if(paramsb.length != p3)
      {
        System.out.println("cycle b error");
	break;
      }
      for(int i=0; i < p3; i++)
      {
        System.out.printf("paramsb %d, %s \n", i, paramsb[i]);
      }

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
	  a[round] = new Integer(paramsa[ia]);
	  ia++;
	  round++;
	  if(round >= p1)
	    break;
	}
	if( round % p2 == 0)
	  continue;
        round++;
      }
      for(int i=0; i < p1; i++)
      {
        System.out.println("a" + i + ":" + a[i]);
      }

      round = 0;
      while( round < p1)
      {
        ib = 0;
        while( ib < p3)
	{
	  b[round] = new Integer(paramsb[ib]);
	  ib++;
	  round++;
	  if(round >= p1)
	    break;
	}
	if( round % p3 == 0)
	  continue;
        round++;
      }
      for(int i=0; i < p1; i++)
      {
        System.out.println("b" + i + ":" + b[i]);
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
      
    }
  }
}
