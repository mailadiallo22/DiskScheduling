package diskScheduler;


public class SSTF {
	String[] str;
	String[] cylNum;
	int x[];
	int nearest = -1;

//---------function to find the closest value from the head position------------// 
	
	public int searchForSmallestDis(int nSearchinFor) {
		int bestDisf = 1000;
		int d;
		int i;
		for (int k = 0; k < x.length; k++) {
			if (nearest == x[k])
				x[k] = 5000;
		}

		for (i = 1; i < x.length; i++) {
			d = Math.abs(nSearchinFor - x[i]);
			if (d < bestDisf ) {
				bestDisf  = d;
				nearest = x[i];
			}
		}
		return nearest;
	}
//--------------Once the closest position found, get the values make it the wanted value---------//
	
	public int[] getSftf(int[] x, int startP) {
		this.x = x;
		int[] sstf = new int[x.length + 1];
		sstf[0] = startP;

		int nSearchinFor = startP;
		
		for (int count = 0; count < x.length - 1; count++) {
			sstf[count + 1] = searchForSmallestDis(nSearchinFor);

			nSearchinFor = nearest;
		}
		return sstf;
	}
 
	public static void main(String[] args) {
		int[] x = { 89, 183, 37, 122, 14, 124, 65, 67 };
		SSTF sstf = new SSTF();
		int[] sst = sstf.getSftf(x, 53);
		for (int i = 0; i < sst.length; i++) {
		}
	}
}

