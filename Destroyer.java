import java.util.Random;

public class Destroyer {
	private int x1, y1, x2, y2, x3, y3;
	Random rnd = new Random();
	public Destroyer(Grid[][] gridler) {

	
		int ax1 = 110;
		int ay1 = 110;
		int x = rnd.nextInt(2);

		ax1 = rnd.nextInt(8);
		ay1 = rnd.nextInt(8);

		// Aşagı yada yana dogru olması için random deger x

		if (x == 1) {
			// o noktada baska gemi varsa tekrar random deger al
			while (ax1 > 7 || ay1 > 7 || gridler[ax1][ay1].getDeger() == 's' || gridler[ax1][ay1 + 2].getDeger() == 's'
					|| gridler[ax1][ay1 + 1].getDeger() == 's') {
				ax1 = rnd.nextInt(7);
				ay1 = rnd.nextInt(7);
			}

			this.x1 = ax1;
			this.y1 = ay1;
			this.x2 = ax1;
			this.y2 = ay1 + 1;
			this.x3 = ax1;
			this.y3 = ay1 + 2;
		} else {
			while (ax1 > 7 || ay1 > 7 || gridler[ax1][ay1].getDeger() == 's' || gridler[ax1 + 2][ay1].getDeger() == 's'
					|| gridler[ax1 + 1][ay1].getDeger() == 's') {
				ax1 = rnd.nextInt(7);
				ay1 = rnd.nextInt(7);
			}
			this.x1 = ax1;
			this.y1 = ay1;
			this.x2 = ax1 + 1;
			this.y2 = ay1;
			this.x3 = ax1 + 2;
			this.y3 = ay1;
		}

		gridler[x1][y1].setDeger('s');
		gridler[x2][y2].setDeger('s');
		gridler[x3][y3].setDeger('s');

	}

	public boolean hepsi(Grid[][] gridler) {
		if (gridler[x1][y1].isVuruldu() == true && gridler[x2][y2].isVuruldu() == true
				&& gridler[x3][y3].isVuruldu() == true) {
			gridler[x1][y1].setDeger('x');
			gridler[x2][y2].setDeger('x');
			gridler[x3][y3].setDeger('x');
			System.out.println("You just sank a Destroyer");
			return true;
		}
		return false;
	}

	

}
