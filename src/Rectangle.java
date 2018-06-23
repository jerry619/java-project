public class Rectangle {
	public int length, width;
 	
	public Rectangle(int l, int w) {
		this.length = l;
		this.width = w;
	}
	public int Area() {
		return length * width;
	}
	public int Perimeter() {
		return 2 *(length + width);
	}

}
