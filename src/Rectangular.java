public class Rectangular {
	public static void main(String args[]) {
		int length = Integer.parseInt(args[0]);
		int width = Integer.parseInt(args[1]);

	Rectangle rect = new Rectangle(length,width);
	String output = String.format ("***Your rectangle***\nArea= %d\nPerimeter= %d", rect.Area(),rect.Perimeter());
	System.out.println(output);
	}
}
