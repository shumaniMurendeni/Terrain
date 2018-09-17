import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class ParallelTerrain extends RecursiveTask<Float>{
	
	private static final long serialVersionUID = 1L;
	private int start;
	private int end;
	static int THRESHHOLD = 350;
	static boolean strr = false;
	ArrayList<Tree> trees;
	final StringBuilder str = new StringBuilder();
	float sunlight;
	
	public ParallelTerrain(int start, int end, ArrayList<Tree> trees) {
		super();
		this.start = start;
		this.end = end;
		this.trees = trees;
		sunlight = 0;
	}
	public ParallelTerrain(int start, int end, ArrayList<Tree> trees,int size) {
	  this(start,end,trees);
	  threshhold(size);
	}
	public static void threshhold(int size) {
		THRESHHOLD = (int) Math.max(250, size*0.22);
	}
	
	@Override
	protected Float compute() {
		if (end - start <1) {return (float) 0;}
		else if (end - start <= THRESHHOLD) {
			for (int i=start;i<end;i++) {
				float t = trees.get(i).sunlight();
				sunlight += t;
			}
			if(strr) {
				for (int i=start;i<end;i++) 
					str.append(trees.get(i).sunlight + "\n");
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
		if (strr) {
			str.append(left.str);
			str.append(right.str);
		}
		sunlight = left.sunlight + right.sunlight;
	}
	
	@Override
	public String toString() {
		return str.toString();
	}

}
