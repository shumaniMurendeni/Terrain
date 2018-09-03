
public class TerrainTest {
	public static void main(String[] args) throws InterruptedException {
		Terrain a = new Terrain();
		System.out.println("Reading in data");
		a.readInData("sample_input.txt");System.out.println("Reading in data Complete");
		//System.out.println(a.toString());
		/*for (Tree t:a.trees) {
			System.out.println(t.sunlight());
		}*/
		ParallelTerrain[] b = new ParallelTerrain[100];
		
		for (int i=0; i<100;i++) {
			b[i] = new ParallelTerrain(i*(a.trees.size()/100),a.trees.size()/100,a.trees);
			b[i].start();
		}
		//System.out.println("Average sunlight per tree is: " + Terrain.totalSunlight/a.trees.size());
	}
}
