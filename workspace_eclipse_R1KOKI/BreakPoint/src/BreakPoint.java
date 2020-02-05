
public class BreakPoint {
	private static int[][] ar = new int[4][4];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<ar.length;i++) {
			for(int j=0;j<ar[i].length;j++) {
				ar[i][j] = i+j;
			}
		}
		for(int i=0;i<ar.length;i++) {
			for(int j=0;j<ar[i].length;j++) {
				System.out.print(ar[i][j] + "\t");
			}
			System.out.println();
		}
	}
}