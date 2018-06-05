import java.util.Random;

public class Battleship {

	private int xKoordinati1, yKoordinati1, xKoordinati2, yKoordinati2, xKoordinati3, yKoordinati3, xKoordinati4,
			yKoordinati4, xKoordinati1Random, yKoordinati1Random, maxGridSayisi;

	Random rnd = new Random();

	public Battleship(Grid[][] gridler, int mode) {

		if (mode == 1) {
			maxGridSayisi = 7;
		} else if (mode == 2) {
			maxGridSayisi = 12;
		} else if (mode == 3) {
			maxGridSayisi = 17;
		}
		xKoordinati1Random = rnd.nextInt(maxGridSayisi);// 0-6 arası rakam verir.
		yKoordinati1Random = rnd.nextInt(maxGridSayisi);

		int randomDeger = rnd.nextInt(2);
		// Aşagı yada yana dogru olması için random deger x
		if (randomDeger == 1) {
			// o noktada baska gemi varsa tekrar random deger al
			while (xKoordinati1Random > maxGridSayisi - 1 || yKoordinati1Random > maxGridSayisi - 1
					|| gridler[xKoordinati1Random][yKoordinati1Random].getDeger() == 's'
					|| gridler[xKoordinati1Random][yKoordinati1Random + 3].getDeger() == 's'
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
			this.yKoordinati3 = yKoordinati1Random + 2;
			this.xKoordinati4 = xKoordinati1Random;
			this.yKoordinati4 = yKoordinati1Random + 3;// 9 14 19
		} else {
			while (xKoordinati1Random > maxGridSayisi - 1 || yKoordinati1Random > maxGridSayisi - 1
					|| gridler[xKoordinati1Random][yKoordinati1Random].getDeger() == 's'
					|| gridler[xKoordinati1Random + 3][yKoordinati1Random].getDeger() == 's'
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
			this.xKoordinati4 = xKoordinati1Random + 3;
			this.yKoordinati4 = yKoordinati1Random;
		}

		gridler[xKoordinati1][yKoordinati1].setDeger('s');
		gridler[xKoordinati2][yKoordinati2].setDeger('s');
		gridler[xKoordinati3][yKoordinati3].setDeger('s');
		gridler[xKoordinati4][yKoordinati4].setDeger('s');
	}

	public boolean hepsiVuruldumu(Grid[][] gridler) { // ship in tamamı vuruldu ise x yap
		if (gridler[xKoordinati1][yKoordinati1].isVuruldu() == true
				&& gridler[xKoordinati2][yKoordinati2].isVuruldu() == true
				&& gridler[xKoordinati3][yKoordinati3].isVuruldu() == true
				&& gridler[xKoordinati4][yKoordinati4].isVuruldu() == true) {
			gridler[xKoordinati1][yKoordinati1].setDeger('x');
			gridler[xKoordinati2][yKoordinati2].setDeger('x');
			gridler[xKoordinati3][yKoordinati3].setDeger('x');
			gridler[xKoordinati4][yKoordinati4].setDeger('x');
			System.out.println("You just sank a BattleShip");
			return true;
		}

		return false;
	}

}
