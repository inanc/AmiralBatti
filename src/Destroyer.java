import java.util.Random;

public class Destroyer {
	private int xKoordinati1, yKoordinati1, xKoordinati2, yKoordinati2, xKoordinati3, yKoordinati3, xKoordinati1Random,
			yKoordinati1Random, maxGridSayisi;
	Random rnd = new Random();

	public Destroyer(Grid[][] gridler, int mode) {

		if (mode == 1) {
			maxGridSayisi = 8;
		} else if (mode == 2) {
			maxGridSayisi = 13;
		} else if (mode == 3) {
			maxGridSayisi = 18;
		}

		int randomDeger = rnd.nextInt(2);

		xKoordinati1Random = rnd.nextInt(maxGridSayisi);
		yKoordinati1Random = rnd.nextInt(maxGridSayisi);

		// Aşagı yada yana dogru olması için random deger x

		if (randomDeger == 1) {
			// o noktada baska gemi varsa tekrar random deger al
			while (xKoordinati1Random > maxGridSayisi - 1 || yKoordinati1Random > maxGridSayisi - 1
					|| gridler[xKoordinati1Random][yKoordinati1Random].getDeger() == 's'
					|| gridler[xKoordinati1Random][yKoordinati1Random + 2].getDeger() == 's'
					|| gridler[xKoordinati1Random][yKoordinati1Random + 1].getDeger() == 's') {
				xKoordinati1Random = rnd.nextInt(maxGridSayisi);
				yKoordinati1Random = rnd.nextInt(maxGridSayisi);
			}

			this.xKoordinati1 = xKoordinati1Random;
			this.yKoordinati1 = yKoordinati1Random;
			this.xKoordinati2 = xKoordinati1Random;
			this.yKoordinati2 = yKoordinati1Random + 1;
			this.xKoordinati3 = xKoordinati1Random;
			this.yKoordinati3 = yKoordinati1Random + 2; // 19
		} else {
			while (xKoordinati1Random > maxGridSayisi - 1 || yKoordinati1Random > maxGridSayisi - 1
					|| gridler[xKoordinati1Random][yKoordinati1Random].getDeger() == 's'
					|| gridler[xKoordinati1Random + 2][yKoordinati1Random].getDeger() == 's'
					|| gridler[xKoordinati1Random + 1][yKoordinati1Random].getDeger() == 's') {
				xKoordinati1Random = rnd.nextInt(maxGridSayisi);
				yKoordinati1Random = rnd.nextInt(maxGridSayisi);
			}
			this.xKoordinati1 = xKoordinati1Random;
			this.yKoordinati1 = yKoordinati1Random;
			this.xKoordinati2 = xKoordinati1Random + 1;
			this.yKoordinati2 = yKoordinati1Random;
			this.xKoordinati3 = xKoordinati1Random + 2;
			this.yKoordinati3 = yKoordinati1Random;
		}

		gridler[xKoordinati1][yKoordinati1].setDeger('s');
		gridler[xKoordinati2][yKoordinati2].setDeger('s');
		gridler[xKoordinati3][yKoordinati3].setDeger('s');

	}

	public boolean hepsiVuruldumu(Grid[][] gridler) {
		if (gridler[xKoordinati1][yKoordinati1].isVuruldu() == true
				&& gridler[xKoordinati2][yKoordinati2].isVuruldu() == true
				&& gridler[xKoordinati3][yKoordinati3].isVuruldu() == true) {
			gridler[xKoordinati1][yKoordinati1].setDeger('x');
			gridler[xKoordinati2][yKoordinati2].setDeger('x');
			gridler[xKoordinati3][yKoordinati3].setDeger('x');
			System.out.println("You just sank a Destroyer");
			return true;
		}
		return false;
	}

}
