import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RectangleTest {
	Rectangle myrectangle = Rectangle(4, 6);

	@Test
	public void testArea() {
		assertEquals(myrectangle.Area(), 20);
	}
	
	@Test
	public void testPerimeter() {
		assertEquals(myrectangle.Perimeter(), 20);
	}
	
	@Test
	public void Testlength() {
		assertEquals(myrectangle.length, 4)
	}

	@Test
	public void Testwidth() {
		assertEquals(myrectangle.width, 6)
	}
}
