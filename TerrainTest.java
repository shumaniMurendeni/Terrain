

public class TerrainTest {
	public static void main(String[] args) throws InterruptedException {
		Terrain a = new Terrain();System.out.println("...");
		a.readInData("sample_input.txt");
		if (args.length>0) {
			for (String arg:args) {
				switch(arg) {
				case "test times":
					testall(a);
					break;
					
				case "print output":
					toString(a);
					break;
				default:
					System.out.println("Welcome to Parallel Terrain Test");
					break;
				}
			}
		}else {testall(a);}

		
		/**
		  * Without System.gc() reducing the likelihood of garbage collection  
		  * full size with threshold of 250 stabilizes around 0.27392 seconds
 		  * full size with threshold of 300 stabilizes around 0.25611007 seconds
		  * full size with threshold of 350 stabilizes around 0.26951998 seconds
		  * full size with threshold of 400 stabilizes around 0.26556006 seconds
		  * 
		  * With System.gc() reducing the likelihood of garbage collection  
		  * full size with threshold of 250 stabilizes around 0.112480015 seconds
 		  * full size with threshold of 300 stabilizes around 0.11073999 seconds
		  * full size with threshold of 350 stabilizes around 0.10878001 seconds
		  * full size with threshold of 400 stabilizes around 0.11118001 seconds
		  * 
		  * full size with sequential stabilizes around  seconds
		  **/
	}

	private static void toString(Terrain a) {
		ParallelTerrain b;
		b = new ParallelTerrain(0,a.trees.size(),a.trees);
		b.compute();
		System.out.printf("%s\n%s\n", b.sunlight/a.trees.size(),a.trees.size());
		System.out.println(b.toString());
		
			
	}

	private static void testall(Terrain a) {
		System.out.println("size,Sequential,parallel");
		for (int size=1;size<=100;size++) {
			ParallelTerrain b;
			float time = 0;
			for (int i=0; i<25;i++) {
				System.gc();
				b = new ParallelTerrain(0,size*10000,a.trees);
				long s =System.currentTimeMillis();
				b.compute();
				long e = System.currentTimeMillis();
				time += ((e-s)/10.0f);
				
			}
			float time2 = 0;
			//String str = "";
			for (int i=0; i<25;i++) {
				System.gc();
				@SuppressWarnings("unused")
				float sunlight=0;
				long s =System.currentTimeMillis();
				for (int j=0;j<size*10000;j++) {
					sunlight += a.trees.get(j).sunlight();
					//str = str + a.trees.get(j).sunlight + "\n";
				}
				long e = System.currentTimeMillis();
				time2 += ((e-s)/10.0f);
				
			}
			System.out.printf("%s,%.4f,%.4f\n", size*10000,time2/25,time/25);
		}
		
	}
}
