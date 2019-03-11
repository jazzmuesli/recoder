public class VarArgs {
	public static int max(int... intArray) {
		int maxSoFar = Integer.MIN_VALUE;
		for (int i : intArray) {
			if (i > maxSoFar) maxSoFar = i;
		}
		return maxSoFar;
	}
	
	public static int minOrMax(boolean min, int... intArray) {
		int result;
		if (min) {
			result = Integer.MAX_VALUE;
			for (int i : intArray) {
				if (i < result) result = i;
			}
		}
		else {
			result = Integer.MIN_VALUE;
			for (int i : intArray) {
				if (i > result) result = i;
			}
		}
		return result;
	}
	
	public static int sumArrays(int[]... intArrays) {
        int sum, i, j;

        sum=0;
        for(i=0; i<intArrays.length; i++) {
            for(j=0; j<intArrays[i].length; j++) {
                sum += intArrays[i][j];
            }
        }
        return(sum);
    }
	
	public static void main(String[] args) {
		int max = max(4,9,6,27,0,3);
		int[] array = {5,3,7,4,9,1};
		int[] array2 = {8,7,11,38,6,8};
		max = max(new int[] {5,3,7,4,9,1});
		max = max(array);
		int min = minOrMax(true, 4,9,6,27,0,3);
		int sum = sumArrays(array,array2);
//		System.out.printf("max = %d %s", max, "blubb");
	}
}