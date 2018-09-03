
public class Tree {
	protected int xStart;
	protected int yStart;
	protected int size;
	
	public Tree(int xStart, int yStart, int size) {
		super();
		this.xStart = xStart;
		this.yStart = yStart;
		this.size = size;
		checkAll();
	}
	
	public Tree(String [] info) {
		super();
		this.xStart = Integer.parseInt(info[0]);
		this.yStart = Integer.parseInt(info[1]);
		this.size = Integer.parseInt(info[2]);
		checkAll();
	}

	private void checkAll() {
		if (xStart < 0) { xStart = 0; }
		if (yStart < 0) { yStart = 0; }		
	}
	
	public String toString() {
		return xStart +" " + yStart + " " + size;
	}
	
	public double sunlight() {
		double sum = 0;
		double [][] arr = Terrain.terrain;
		int height = Terrain.height; 
		int width = Terrain.width;
		
		for (int i=yStart;i < yStart + size; i++ ) { 
			for (int j=xStart; j < xStart + size; j++ ) {
				if (j<width && i<height){sum+= arr[i][j];}
				else {break;}
			}
			if (i>=height) {break;}
		}
		return sum;
	}
}
