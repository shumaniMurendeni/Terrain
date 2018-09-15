
public class Tree {
	protected int xStart;
	protected int yStart;
	protected int size;
	protected float sunlight;
	float[][] arr;
	public Tree(int xStart, int yStart, int size,float[][] arr) {
		super();
		this.xStart = xStart;
		this.yStart = yStart;
		this.size = size;
		sunlight = 0;
		this.arr = arr;
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
