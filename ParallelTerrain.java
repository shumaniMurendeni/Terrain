import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelTerrain extends RecursiveTask<Float>{
	
	private int start;
	private int end;
	final static int THRESHHOLD = 400000000;
	ArrayList<Tree> trees;
	StringBuilder str;
	float sunlight;
	
	public ParallelTerrain(int start, int end, ArrayList<Tree> trees) {
		super();
		this.start = start;
		this.end = end;
		this.trees = trees;
		str = new StringBuilder();
		sunlight = 0;
	}
	
	@Override
	protected Float compute() {
		if (end - start <1) {return (float) 0;}
		else if (end - start <= THRESHHOLD) {
			//System.out.println("Compute method "+start + " " + end);
			for (int i=start;i<end;i++) {
				float t = trees.get(i).sunlight();
				sunlight += t;
				//System.out.println(t);
				str.append(t  +"\n");
			}
			return sunlight;
		}
		computeAll(start,end);
		return sunlight;
	}
	
	private void computeAll(int lo,int high) {
		ParallelTerrain left = new ParallelTerrain(lo,(lo+high)/2,trees);
		ParallelTerrain right = new ParallelTerrain((lo+high)/2,high,trees);
		
		left.fork();
		right.compute();
		left.quietlyJoin();
		str.append(left.str);str.append(right.str);
		sunlight = left.sunlight + right.sunlight;
	}
	

}
