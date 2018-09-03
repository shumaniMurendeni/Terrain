import java.util.ArrayList;

public class ParallelTerrain extends Thread {

	private int startPoint;
	private int Threshhold;
	private double sunlight;
	ArrayList<Tree> trees;
	
	public ParallelTerrain(int startPoint, int threshhold,ArrayList<Tree> trees) {
		super();
		this.startPoint = startPoint;
		Threshhold = threshhold;
		sunlight = 0;
		this.trees = trees;
	}
	
	public void run() {
		for (int i=startPoint; i< startPoint + Threshhold;i++) {
			sunlight += trees.get(i).sunlight();
		}
		System.out.println(sunlight);
		Terrain.totalSunlight += sunlight;
	}

}
