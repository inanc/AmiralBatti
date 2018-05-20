import java.util.Random;

public class Boat {

	int x1, y1;

	Random rnd = new Random();

	public Boat(Grid[][] gridler) {

		this.x1 = rnd.nextInt(10);
		this.y1 = rnd.nextInt(10);
		if (gridler[x1][x1].getDeger() == 's') {
			this.x1 = rnd.nextInt(10);
			this.y1 = rnd.nextInt(10);
		}

		gridler[x1][y1].setDeger('s');

	}/*
		 * public Boat(int x1, int y1) { this.x1 = x1; this.y1 = y1;
		 * 
		 * 
		 * gridler[x1][y1].setDeger('s');
		 * 
		 * }
		 */

	public boolean hepsi(Grid[][] gridler) {
		if (gridler[x1][y1].isVuruldu() == true && gridler[x1][y1].getDeger() == 's') {
			System.out.println("You just sank a Boat");

			gridler[x1][y1].setDeger('x');

			return true;
		}
		return false;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

}
