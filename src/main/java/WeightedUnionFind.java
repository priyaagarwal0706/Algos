

public class WeightedUnionFind {
	int[] rootArray;
	int[] sizeArray;

	public WeightedUnionFind(int size) {
		rootArray=new int[size];
		sizeArray=new int[size];
		for(int i=0;i<size;i++) {
			rootArray[i]=i;
			sizeArray[i]=1;
		}
	}
	public boolean isConnected(int index1,int index2) {
		return root(index1)==root(index2);
	}
	
	public int root(int index) {
		while(index!=rootArray[index]) {
			index =rootArray[index];
		}
		return index;
	}

	public void union(int index1,int index2) {
		int rootI1=root(index1);
		int rootI2=root(index2);
		if(sizeArray[rootI1]<=sizeArray[rootI2]) {
			rootArray[rootI1]=rootI2;
			sizeArray[rootI2]+=sizeArray[rootI1];
		}else{
			rootArray[rootI2]=rootI1;
			sizeArray[rootI1]+=sizeArray[rootI2];
		}
	}
	
	public static void main(String [] args) {
		WeightedUnionFind  wf= new WeightedUnionFind(10);
		if(!wf.isConnected(2, 3)) {
			wf.union(2, 3);
		}
		if(!wf.isConnected(2, 7)) {
			wf.union(2, 7);
		}
		if(!wf.isConnected(5, 4)) {
			wf.union(5, 4);
		}
		if(!wf.isConnected(2, 5)) {
			wf.union(2, 5);
		}
		
		for(int i=0;i<10;i++) {
			System.out.println(wf.rootArray[i]);
		}
		
	}
}
