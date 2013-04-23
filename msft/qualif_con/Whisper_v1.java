import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
public class Whisper_V1{
  public static void main(String args[])
  {
    Scanner sin = new Scanner(System.in);

    String s_group = sin.nextLine();

    int group = new Integer(s_group);
    
    //p1:people number
    //p2:word pair number
    int[] p1 = new int[group]; 
    int[] p2 = new int[group];

    //wp:word pair
    List<Map<String,String>> wpl = new ArrayList<Map<String,String>>();
    int tmp = 0;
    while( tmp < group )
    {
      Map<String, String> wp = new HashMap<String, String>();
      wpl.add(wp);
      tmp++;
    }

    //os:original sentence
    //ts:transformed sentence
    String[] os = new String[group];
    String[] ts = new String[group];
    for(int gi=0; gi < group; gi++)
    {
      String s_params = sin.nextLine();
      String[] params = s_params.split(" ");
      p1[gi] = new Integer(params[0]);
      p2[gi] = new Integer(params[1]);

      int i = 0;
      while( i < p2[gi] )
      {
        String wpline = new String();
	wpline = sin.nextLine();
	String[] wpline_ = wpline.split(" ");
        wpl.get(gi).put(wpline_[0], wpline_[1]);
	i++;
      }
      os[gi] = sin.nextLine();
    }//end of for

    for(int gi=0; gi < group; gi++)
    {
      int pi=0;//people index
      String tmps = os[gi];
      while( pi < p1[gi]-1 )
      {
        Set<String> key = wpl.get(gi).keySet();
        for (Iterator<String> it = key.iterator(); it.hasNext();)
        {
          String s = (String) it.next();
	  ts[gi] = tmps.replace(s, wpl.get(gi).get(s));
        } 
          System.out.println("step #" + (pi+1) + ", ts: " + ts[gi]);
          System.out.println("step #" + (pi+1) + ", tmps: " + ts[gi]);
	  tmps = ts[gi];
        pi++;
      }
    }

    for(int gi=0; gi < group; gi++)
    {
      System.out.println("Case #" + (gi+1) + ": " + ts[gi]);
    }
    
    /*
    for(int gi=0; gi < group; gi++)
    {
      Set<String> key = wpl.get(gi).keySet();
      for (Iterator<String> it = key.iterator(); it.hasNext();)
      {
        String s = (String) it.next();
        System.out.println(s + "--" + wpl.get(gi).get(s));
      }

      System.out.println("os:"+os[gi]);
    }//end of for
   */ 
  }//end of main
}//end of class
