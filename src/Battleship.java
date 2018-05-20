import java.util.Random;

public class Battleship {

	int x1, y1, x2, y2, x3, y3, x4, y4;

	public Battleship(Grid[][] gridler) {
		Random rnd = new Random();
		int ax1 = 110;
		int ay1 = 110;
		ax1 = rnd.nextInt(8);
		ay1 = rnd.nextInt(8);

		int x = rnd.nextInt(2);
		// Aşagı yada yana dogru olması için random deger x

		if (x == 1) {
			// o noktada baska gemi varsa tekrar random deger al

			while (ax1 > 6 || ay1 > 6 || gridler[ax1][ay1].getDeger() == 's' || gridler[ax1][ay1 + 3].getDeger() == 's'
					|| gridler[ax1][ay1 + 2].getDeger() == 's' || gridler[ax1][ay1 + 1].getDeger() == 's') {
				ax1 = rnd.nextInt(6);
				ay1 = rnd.nextInt(6);
			}

			this.x1 = ax1;
			this.y1 = ay1;
			this.x2 = ax1;
			this.y2 = ay1 + 1;
			this.x3 = ax1;
			this.y3 = ay1 + 2;
			this.x4 = ax1;
			this.y4 = ay1 + 3;
		} else {

			while (ax1 > 6 || ay1 > 6 || gridler[ax1][ay1].getDeger() == 's' || gridler[ax1 + 3][ay1].getDeger() == 's'
					|| gridler[ax1 + 2][ay1].getDeger() == 's' || gridler[ax1 + 1][ay1].getDeger() == 's') {
				ax1 = rnd.nextInt(6);
				ay1 = rnd.nextInt(6);
			}

			this.x1 = ax1;
			this.y1 = ay1;
			this.x2 = ax1 + 1;
			this.y2 = ay1;
			this.x3 = ax1 + 2;
			this.y3 = ay1;
			this.x4 = ax1 + 3;
			this.y4 = ay1;
		}

		gridler[x1][y1].setDeger('s');
		gridler[x2][y2].setDeger('s');
		gridler[x3][y3].setDeger('s');
		gridler[x4][y4].setDeger('s');
	}

	public boolean hepsi(Grid[][] gridler) {
		if (gridler[x1][y1].isVuruldu() == true && gridler[x2][y2].isVuruldu() == true
				&& gridler[x3][y3].isVuruldu() == true && gridler[x4][y4].isVuruldu() == true) {
			gridler[x1][y1].setDeger('x');
			gridler[x2][y2].setDeger('x');
			gridler[x3][y3].setDeger('x');
			gridler[x4][y4].setDeger('x');
			System.out.println("You just sank a BattleShip");
			return true;
		}

		return false;
	}

	public int getX4() {
		return x4;
	}

	public void setX4(int x4) {
		this.x4 = x4;
	}

	public int getY4() {
		return y4;
	}

	public void setY4(int y4) {
		this.y4 = y4;
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

	public int getX3() {
		return x3;
	}

	public void setX3(int x3) {
		this.x3 = x3;
	}

	public int getY3() {
		return y3;
	}

	public void setY3(int y3) {
		this.y3 = y3;
	}

}
