
public class TerrainTest {
	public static void main(String[] args) {
		Terrain a = new Terrain(5,5);
		System.out.println(a.toString());
		a.addEntries(new double[]{4.5,5.5,4,3.5,4,4.5,5,4.5,4,4,5,5,5.5,4.5,5,4,4.5,5,5,4,4,4,4.5,4,3.5});
		a.setTree(2);
		a.addTree(new Integer[] {1,1,3});
		a.addTree(new Integer[] {2,3,4});
		System.out.println(a.toString());
	}
}
