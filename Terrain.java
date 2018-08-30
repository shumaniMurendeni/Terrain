import java.util.ArrayList;

public class Terrain {
	private int width;
	private int height;
	private double[][] terrain;
	private int tree;
	private ArrayList<Integer[]> trees;
	public Terrain(int width, int height) {
		this.width = width;
		this.height = height;
		this.terrain = new double[this.height][this.width];
		this.tree = 0;
		this.trees = new ArrayList<>();
	}
	
	public void addEntries(double[] arr) {
		for (int i=0; i<height;i++) {
			for (int j=0;j<width;j++) {
				terrain[i][j] = arr[i*width + j];
			}
		}
	}

	public int getTree() {
		return tree;
	}

	public void setTree(int tree) {
		this.tree = tree;
	}
	
	public void addTree(Integer[] coord) {
		trees.add(coord);
	}
		
	public String toString() {
		StringBuilder strb = new StringBuilder();
		float sum = 0;
		strb.append(tree + "\n");
		for (Integer[] tre:trees) {
			double sunlight = getSunlight(tre[0],tre[1],tre[2]);
			sum+= sunlight;
			strb.append(String.format("%s\n", sunlight));
		}
		
		return String.format("%.1f\n"+strb.toString(), sum/trees.size());
	}

	private float getSunlight(Integer xStart, Integer yStart, Integer size) {
			float sum = 0;
			for (int i=yStart;i < yStart + size; i++ ) { 
				for (int j=xStart; j < xStart + size; j++ ) {
					if (j<width && i<height){sum+= terrain[i][j];}
					else {break;}
				}
				if (i>=height) {break;}
			}
			
		return sum;
	}
}
