import java.util.Random;

public class Submarine {

	int x1, y1, x2, y2;

	public Submarine(Grid[][] gridler) {

		Random rnd = new Random();
		int ax1 = 110;
		int ay1 = 110;

		int x = rnd.nextInt(2);
		// Aşagı yada yana dogru olması için random deger x

		ax1 = rnd.nextInt(8);
		ay1 = rnd.nextInt(8);
		if (x == 1) {
			// o noktada baska gemi varsa tekrar random deger al
			while (ax1 > 8 || ay1 > 8 || gridler[ax1][ay1].getDeger() == 's'
					|| gridler[ax1][ay1 + 1].getDeger() == 's') {
				ax1 = rnd.nextInt(8);
				ay1 = rnd.nextInt(8);
			}

			this.x1 = ax1;
			this.y1 = ay1;
			this.x2 = ax1;
			this.y2 = ay1 + 1;
		} else {
			while (ax1 > 8 || ay1 > 8 || gridler[ax1][ay1].getDeger() == 's'
					|| gridler[ax1 + 1][ay1].getDeger() == 's') {
				ax1 = rnd.nextInt(8);
				ay1 = rnd.nextInt(8);

			}

			this.x1 = ax1;
			this.y1 = ay1;
			this.x2 = ax1 + 1;
			this.y2 = ay1;
		}

		gridler[x1][y1].setDeger('s');
		gridler[x2][y2].setDeger('s');

	}

	/*
	 * public Submarine(int x1, int y1, int x2, int y2) { this.x1 = x1; this.y1 =
	 * y1; this.x2 = x2; this.y2 = y2;
	 * 
	 * 
	 * gridler[x1][y1].setDeger('s'); gridler[x2][y2].setDeger('s');
	 * 
	 * }
	 */
	public boolean hepsi(Grid[][] gridler) {
		if (gridler[x1][y1].isVuruldu() == true && gridler[x2][y2].isVuruldu() == true) {
			gridler[x1][y1].setDeger('x');
			gridler[x2][y2].setDeger('x');
			System.out.println("You just sank a Submarine");
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

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

}
