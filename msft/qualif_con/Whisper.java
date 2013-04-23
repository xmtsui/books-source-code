//source here
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Whisper {
    public static void main(String[] args) {
    	Scanner sin = new Scanner(System.in);

		String s_group = sin.nextLine();

		int group = new Integer(s_group);

		// p1:people number
		// p2:word pair number
		int[] p1 = new int[group];
		int[] p2 = new int[group];

		// wp:word pair
		List<Map<String, String>> wpl = new ArrayList<Map<String, String>>();
		int tmp = 0;
		while (tmp < group) {
			Map<String, String> wp = new HashMap<String, String>();
			wpl.add(wp);
			tmp++;
		}

		// os:original sentence
		// ts:transformed sentence
		String[] os = new String[group];
		String[] ts = new String[group];
		for (int gi = 0; gi < group; gi++) {
			String s_params = sin.nextLine();
			String[] params = s_params.split(" ");
			p1[gi] = new Integer(params[0]);
			p2[gi] = new Integer(params[1]);

			int i = 0;
			while (i < p2[gi]) {
				String wpline = new String();
				wpline = sin.nextLine();
				String[] wpline_ = wpline.split(" ");
				wpl.get(gi).put(wpline_[0], wpline_[1]);
				i++;
			}
			os[gi] = sin.nextLine();
		}// end of for

		for (int gi = 0; gi < group; gi++) {
			int pi = 0;// people index
			int si = 0;// sentence word index

			String[] bef = os[gi].split(" ");
			int t_size = bef.length;
			String[] aft = new String[t_size];

			while (pi < p1[gi] - 1) {
				si = 0;
				while (si < t_size) {
					Set<String> key = wpl.get(gi).keySet();
					for (Iterator<String> it = key.iterator(); it.hasNext();) {
						String s = (String) it.next();
						if (bef[si].equals(s)) {
							aft[si] = wpl.get(gi).get(s);
							break;
						}// end of if
					}// end of for
							aft[si] = bef[si];
					si++;
				}
				pi++;
				bef = aft;
			}
			System.out.print("Case #" + (gi + 1) + ":");
			si = 0;
			while (si < t_size) {
				if (si != (t_size - 1)) {
					System.out.print(" " + aft[si]);
					si++;
				} else {

					System.out.println(" " + aft[si]);
					si++;

				}
			}
		}
        sin.close();
	}// end of main
}// end of class

