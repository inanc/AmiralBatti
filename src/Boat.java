import java.util.Random;

public class Boat {

	private int xKoordinati1, yKoordinati1, maxGridSayisi;

	Random rnd = new Random();

	public Boat(Grid[][] gridler, int mode) {
		if (mode == 1) {
			maxGridSayisi = 10;
		} else if (mode == 2) {
			maxGridSayisi = 15;
		} else if (mode == 3) {
			maxGridSayisi = 20;
		}

		this.xKoordinati1 = rnd.nextInt(maxGridSayisi);// 9 14 19
		this.yKoordinati1 = rnd.nextInt(maxGridSayisi);
		if (gridler[xKoordinati1][xKoordinati1].getDeger() == 's') {
			this.xKoordinati1 = rnd.nextInt(maxGridSayisi);
			this.yKoordinati1 = rnd.nextInt(maxGridSayisi);
		}

		gridler[xKoordinati1][yKoordinati1].setDeger('s');

	}

	public boolean hepsiVuruldumu(Grid[][] gridler) {
		if (gridler[xKoordinati1][yKoordinati1].isVuruldu() == true
				&& gridler[xKoordinati1][yKoordinati1].getDeger() == 's') {
			System.out.println("You just sank a Boat");

			gridler[xKoordinati1][yKoordinati1].setDeger('x');

			return true;
		}
		return false;
	}

}
