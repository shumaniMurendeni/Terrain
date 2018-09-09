
public class Tree {
	protected int xStart;
	protected int yStart;
	protected int size;
	protected float sunlight;
	public Tree(int xStart, int yStart, int size) {
		super();
		this.xStart = xStart;
		this.yStart = yStart;
		this.size = size;
		checkAll();
	}

	private void checkAll() {
		if (xStart < 0) { xStart = 0; }
		if (yStart < 0) { yStart = 0; }		
	}
	
	public String toString() {
		return xStart +" " + yStart + " " + size;
	}
	
	public float sunlight() {
		sunlight = 0;
		float[][] arr = Terrain.terrain;
		int height = arr[0].length; 
		int width = arr[0].length;
		
		for (int i=yStart;i < yStart + size; i++ ) { 
			for (int j=xStart; j < xStart + size; j++ ) {
				if (j<width && i<height){sunlight+= arr[i][j];}
				else {break;}
			}
			if (i>=height) {break;}
		}
		return sunlight;
	}
}
