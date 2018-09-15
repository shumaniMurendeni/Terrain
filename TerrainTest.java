import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class TerrainTest {
	public static void main(String[] args) throws InterruptedException {
		Terrain a = new Terrain();System.out.println("...");
		if(args.length==2) {
			a.readInData(args[0]);
			writeToFile(a,args[1]);
		}
		
		else {
			a.readInData("sample_input.txt");
			if (args.length>0) {
				for (String arg:args) {
					switch(arg) {
					case "test times":			//runs a time test with constant cut-off value
						testall(a,false);
						break;
					case "test times with VC":	//runs a time test with adjusting cut-off value
						testall(a,true);
						break;
					case "print output":
						System.out.println(toString(a));
						break;
					case "cutoff_Datasize":
						cutOffWithDifferentDataSize(a);
						break;
					case "Thread test":
						threadTest(a);
						break;
					default:
						System.out.println(toString(a));
						break;
					}
				}
			}else {System.out.println(toString(a));}
		}
		
		/**
		  * Without System.gc() reducing the likelihood of garbage collection  
		  * full size with threshold of 250 stabilizes around 2.7392 seconds
 		  * full size with threshold of 300 stabilizes around 2.5611007 seconds
		  * full size with threshold of 350 stabilizes around 2.6951998 seconds
		  * full size with threshold of 400 stabilizes around 2.6556006 seconds
		  * 
		  * With System.gc() reducing the likelihood of garbage collection  
		  * full size with threshold of 250 stabilizes around 1.12480015 seconds
 		  * full size with threshold of 300 stabilizes around 1.1073999 seconds
		  * full size with threshold of 350 stabilizes around 1.0878001 seconds
		  * full size with threshold of 400 stabilizes around 1.1118001 seconds
		  * 
		  * full size with sequential stabilizes around 1.5 seconds
		  **/
		
	}
	private static void cutOffValueTest(Terrain a) {
		System.out.println("Sequential cut-off value, Time");
		for (double per=1;per<101;per++ ) {
			ParallelTerrain b;
			float time = 0;
			ParallelTerrain.THRESHHOLD=  (int) (a.trees.size()*per/100);
			for (int i=0; i<5;i++) {
				System.gc();
				b = new ParallelTerrain(0,a.trees.size(),a.trees);
				long s =System.currentTimeMillis();
				b.compute();
				long e = System.currentTimeMillis();
				time += ((e-s)/10.0f);
				
			}
			System.out.printf("%d,%.4f\n",ParallelTerrain.THRESHHOLD,time/5);
		}
		
	}
	private static void writeToFile(Terrain a, String filename) {
		
		String output = toString(a);
		
		try {
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			writer.print(output);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private static String toString(Terrain a) {
		ParallelTerrain b;
		b = new ParallelTerrain(0,a.trees.size(),a.trees);
		ParallelTerrain.strr=true;
		b.compute();
		ParallelTerrain.strr=false;
		
		return String.format("%s\n%s\n", b.sunlight/a.trees.size(),a.trees.size()) + b.toString();	
	}

	
	private static void testall(Terrain a, boolean variableCutOff) {
		System.out.println("size,Sequential,parallel");
		for (int size=1;size<=100;size++) {
			ParallelTerrain b;
			if(variableCutOff) {ParallelTerrain.threshhold(size*10000);}
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
			for (int i=0; i<25;i++) {
				System.gc();
				@SuppressWarnings("unused")
				float sunlight=0;
				long s =System.currentTimeMillis();
				for (int j=0;j<size*10000;j++) {
					sunlight += a.trees.get(j).sunlight();
				}
				long e = System.currentTimeMillis();
				time2 += ((e-s)/10.0f);
				
			}
			System.out.printf("%s,%.4f,%.4f\n", size*10000,time2/25,time/25);
		}
		ParallelTerrain.threshhold(0);		
	}
	
	private static void cutOffWithDifferentDataSize(Terrain a) {
		System.out.print("Data size");
		for (int i=1;i<=51;i++) {System.out.print(","+i+" thread(s)");}
		System.out.println();
		for (int size=10000;size<=1000000;size += 10000 ) {
			ParallelTerrain b;
			System.out.print(size);
			for (double per=1;per<51;per++ ) {
				float time = 0;
				ParallelTerrain.THRESHHOLD=  (int) (size*per/100);
				for (int i=0; i<5;i++) {
					System.gc();
					b = new ParallelTerrain(0,size,a.trees);
					long s =System.currentTimeMillis();
					b.compute();
					long e = System.currentTimeMillis();
					time += ((e-s)/10.0f);
					
					}
				System.out.printf(",%.4f",time/5);
				}
			System.out.println();
			}
		ParallelTerrain.threshhold(0);
	}

	private static void threadTest(Terrain a) {
		System.out.println("Number of Threads, Time");
		for (int i = 1; i<=100; i++) {
			ParallelTerrain b;
			float time = 0;
			int size = a.trees.size();
			ParallelTerrain.THRESHHOLD = size/i;
			for (int j=0; j<5;j++) {
				System.gc();
				b = new ParallelTerrain(0,size,a.trees);
				long s =System.currentTimeMillis();
				b.compute();
				long e = System.currentTimeMillis();
				time += ((e-s)/10.0f);
				
			}
			System.out.printf("%s,%.4f\n", i,time/5);
		}
		ParallelTerrain.threshhold(0);
	}
}
