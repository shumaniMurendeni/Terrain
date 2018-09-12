import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class ParallelTerrain extends RecursiveTask<Float>{
	
	private static final long serialVersionUID = 1L;
	private int start;
	private int end;
	final static int THRESHHOLD = 350;
	ArrayList<Tree> trees;
	String str;
	float sunlight;
	
	public ParallelTerrain(int start, int end, ArrayList<Tree> trees) {
		super();
		this.start = start;
		this.end = end;
		this.trees = trees;
		str = "";
		sunlight = 0;
	}
	
	@Override
	protected Float compute() {
		if (end - start <1) {return (float) 0;}
		else if (end - start <= THRESHHOLD) {
			for (int i=start;i<end;i++) {
				float t = trees.get(i).sunlight();
				sunlight += t;
				//str = str + t  +"\n";
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
		//str = str + left.str;
		sunlight = left.sunlight + right.sunlight;
	}
	
	@Override
	public String toString() {
		return str;
	}

}
