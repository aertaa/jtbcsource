package test;

/**
 * @(#)Print.java
 * 
 * 
 * @author
 * @version 1.00 2010/3/19
 */

public class testary {

	/**
	 * Creates a new instance of <code>Print</code>.
	 */
	public testary() {

		int n = 4;

		int a[][] = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++)
				a[i][j] = a[j][i] = i;

		}

		// 输出a
		print(a, n, n);

		int b[][] = new int[4][7];
		for (int i = n - 1; i >= 0; i--)
			for (int j = 3; j >= 3 - i; j--)
				b[i][j] = b[i][6 - j] = j + i - 2;
		// 输出b
		print(b, n, 7);
	}

	void print(int a[][], int n, int m) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				System.out.print(a[i][j] + " ");
			System.out.println("\n");
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here

		new testary();
	}
}
