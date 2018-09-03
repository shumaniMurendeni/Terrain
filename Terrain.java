import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terrain {
	
	protected static int width;
	protected static int height;
	protected static double[][] terrain;
	protected static double totalSunlight;
	protected ArrayList<Tree> trees;
	
	public Terrain(int width, int height) {
		setDimensions(width,height);
		totalSunlight = 0;
		trees = new ArrayList<>();
	}
	public Terrain(String filename) {
		trees = new ArrayList<>();
		totalSunlight = 0;
		readInData(filename);
	}
	public Terrain() {
		trees = new ArrayList<>();
		totalSunlight = 0;
	}

	public void addEntries(Scanner in) {
		for (int i=0; i<height;i++) {
			for (int j=0;j<width;j++) {
				terrain[i][j] = in.nextDouble();
			}
		}
	}
	
	public void addTree(Tree tre) {
		trees.add(tre);	
	}
		
	public String toString() {
		System.out.println("toString Method");
		StringBuilder strb = new StringBuilder();
		float sum = 0;
		strb.append(trees.size() + "\n");
		for (Tree tre:trees) {
			double sunlight = tre.sunlight();
			sum+= sunlight;
			strb.append(String.format("%s\n", sunlight));
		}
		
		return String.format("%.1f\n"+strb.toString(), sum/trees.size());
	}
	
	public void readInData(String filename) {
		try {
			int count = 0;
			File file = new File(filename);
			Scanner reader = new Scanner(file);
			
			setDimensions(reader.nextInt(),reader.nextInt());	//reads in the size of the terrain and sets it up.
			addEntries(reader);									//read and add terrain sunlight values.
			reader.nextInt(); 									//reads in the number of trees.
			
			while (reader.hasNext() && count<1000000) {
				addTree(new Tree(reader.nextInt(),
						reader.nextInt(),reader.nextInt()));	//reads in each tree spec and add the tree to the List
				
				count++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setDimensions(int parseInt, int parseInt2) {
		width = parseInt;
		height = parseInt2;
		terrain = new double[height][width];
	}
}
