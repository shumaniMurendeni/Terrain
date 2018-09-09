import java.util.concurrent.ForkJoinPool;

public class TerrainTest {
	public static void main(String[] args) throws InterruptedException {
		Terrain a = new Terrain();
		System.out.println("Reading in data");
		a.readInData("sample_input.txt");System.out.println("Reading in data Complete");
		//System.out.println(a.toString());
		/*for (Tree t:a.trees) {
			System.out.println(t.sunlight());
		}*/
		ParallelTerrain b;
		float time = 0;
		for (int i=0; i<100;i++) {
			System.gc();
			b = new ParallelTerrain(0,a.trees.size(),a.trees);
			long s =System.currentTimeMillis();
			b.compute();
			long e = System.currentTimeMillis();
			System.out.println((e-s)/1000.0f);
			time += ((e-s)/1000.0f);
			//System.out.println(b.sunlight/a.trees.size());
			
		}
		System.out.println(time/100);
		/**
		  * Without System.gc() reducing the likelihood of garbage collection  
		  * full size with threshold of 250 stabilizes around 0.27392 seconds
 		  * full size with threshold of 300 stabilizes around 0.25611007 seconds
		  * full size with threshold of 350 stabilizes around 0.26951998 seconds
		  * full size with threshold of 400 stabilizes around 0.26556006 seconds
		  * 
		  * With System.gc() reducing the likelihood of garbage collection  
		  * full size with threshold of 250 stabilizes around 0.24826004 seconds
 		  * full size with threshold of 300 stabilizes around 0.23368 seconds
		  * full size with threshold of 350 stabilizes around 0.23645993 seconds
		  * full size with threshold of 400 stabilizes around 0.23627996 seconds
		  * 
		  * full size with sequential stabilizes around 0.23627996 seconds
		  **/
	}
}
