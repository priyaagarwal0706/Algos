

import java.util.Arrays;

public class BinarySearch {
	public static int binarySearch(int keyToSearch,int[] inputArray) {
		Arrays.sort(inputArray);
		int lowInd=0;
		int highInd=inputArray.length-1;
		while(highInd>=lowInd) {
			int midIndex = lowInd+(highInd-lowInd)/2;
			int midTerm=inputArray[midIndex];
			if(keyToSearch<midTerm) {
				highInd = midIndex-1;
			}
			if(keyToSearch>midTerm) {
				lowInd = midIndex+1;
			}
			if(keyToSearch==midTerm) {
				return midIndex;
			}
		}
		
		return -1;
	}
	public static void main(String [] args) {
		int [] numArray= {1,6,2,8,3,8,10,11,56};
		int index=binarySearch(6,numArray);
		if(index>-1) {
			System.out.println("Item Found at index="+index);
		}else {
			System.out.println("Item Not Found");
		}
	}
}
