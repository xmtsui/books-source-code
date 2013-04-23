import java.util.Scanner;

public class Similar {
	public static void insertSort(int[] elements) {
		for (int i = 1; i < elements.length; i++) {
			int j = -1;
			while (j < i && elements[i] > elements[++j])
				;
			if (j < i) {
				int temp = elements[i];
				for (int k = i - 1; k >= j; k--) {
					elements[k + 1] = elements[k];
				}
				elements[j] = temp;
			}
		}

		System.out.println(elements[0]);
	}

	public static int distanceCal(String sub_s1, String s2) {

		char[] s1c = stochar(sub_s1);
		char[] s2c = stochar(s2);
		int size = s2.length();
		int i = 0;
		int dis = size;
		while (i < size) {
			if (s1c[i] == s2c[i]) {
				dis--;
			}
			i++;
		}
		return dis;
	}

	public static char[] stochar(String s) {

		char[] c1 = new char[s.length()];
		s.getChars(0, s.length(), c1, 0);
		return c1;

	}

	public static void main(String[] args) {

		Scanner sin = new Scanner(System.in);
		String s_group = sin.nextLine();
		int group = new Integer(s_group);

		int gi = 0;

		String[] s1 = new String[group];
		String[] s2 = new String[group];

		while (gi < group) {
			s1[gi] = sin.nextLine();
			s2[gi] = sin.nextLine();
			gi++;
		}

		gi = 0;

		while (gi < group) {
			int s1i = 0;
			int s1len = s1[gi].length();
			int s2len = s2[gi].length();
			int dis_num = s1len - s2len + 1;
			int[] distance = new int[dis_num];
			while (s1i < dis_num) {
				distance[s1i] = distanceCal(s1[gi].substring(s1i, s1i + s2len),
						s2[gi]);
				s1i++;
			}

			System.out.print("Case #" + (gi + 1) + ": ");
			insertSort(distance);
			gi++;
		}
	}
}
