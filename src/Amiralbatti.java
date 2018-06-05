import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Amiralbatti {

	public static Grid[][] gridler = new Grid[20][20];

	public static void gridOlustur(int gridSayisi) { // baslangıcta tamamı - olan grid olusturulur.
		for (int i = 0; i < gridSayisi; i++) {
			for (int j = 0; j < gridSayisi; j++) {
				gridler[i][j] = new Grid('-');
			}
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int level = -1;

		boolean girisMenusu = true;
		boolean devam = true;
		String username = "";

		while (girisMenusu) { // login
			System.out.println("GAME MENU");
			System.out.println("1.Login \n2.Create new user\n3.Exit");
			int usertype = scanner.nextInt();

			if (usertype == 1) {
				System.out.print("Enter Username:");
				username = scanner.next();
				level = dosyadanOkuma(username);
				if (level >= 0) {
					girisMenusu = false;// menüden cık
				} else {
					System.out.println("Wrong username.Try again.");
				}

			} else if (usertype == 2) {
				System.out.print("New Username:");
				username = scanner.next();
				boolean kayitBasarili = dosyayaKullaniciKaydetme(username);
				level = 0;// yeni kullanıcının baslangic leveli
				if (kayitBasarili == true) {
					girisMenusu = false;
				}

			} else {// exit
				break;
			}
		}

		while (level >= 0 && level < 2) {// game easy mode
			gridOlustur(10);
			Boat boat = new Boat(gridler, 1);// 1=easy mode
			Destroyer des = new Destroyer(gridler, 1);
			Submarine sub = new Submarine(gridler, 1);

			int leveldekiToplamGunSayisi = 7, leveldekiToplamRocketSayisi = 1, leveldekiToplambombSayisi = 2;
			int kullanilanGunSayisi = 0, kullanilanRocketSayisi = 0, kullanilanBombSayisi = 0;
			int jBirEksi, iBirEksi, jBirArti, iBirArti;

			boolean boatHepsiVuruldu = false;
			boolean submarineHepsiVuruldu = false;
			boolean destroyerHepsiVuruldu = false;

			while (devam) {// level

				oyunTahtasi(10);

				if (kullanilanRocketSayisi >= leveldekiToplamRocketSayisi
						&& kullanilanGunSayisi >= leveldekiToplamGunSayisi
						&& kullanilanBombSayisi >= leveldekiToplambombSayisi) {// oyun biter.

					if (boatHepsiVuruldu == false || submarineHepsiVuruldu == false || destroyerHepsiVuruldu == false) {
						devam = false;

						System.out.println("No shot left. Game over");

						dosyayaKayıtEtme(username, level);
						level = -1;

						break;
					}
				}
				System.out.println("0.Gun Shot: " + (leveldekiToplamGunSayisi - kullanilanGunSayisi));
				System.out.println("1.Hand Bomb: " + (leveldekiToplambombSayisi - kullanilanBombSayisi));
				System.out.println("2.Rocket: " + (leveldekiToplamRocketSayisi - kullanilanRocketSayisi));
				System.out.print("Weapon and x,y coordinate:");
				int weaponName = scanner.nextInt();
				int iKoordinati = scanner.nextInt();
				int jKoordinati = scanner.nextInt();

				if (jKoordinati > 0) {
					jBirEksi = jKoordinati - 1;
				} else {
					jBirEksi = jKoordinati;
				}
				if (jKoordinati < 9) {
					jBirArti = jKoordinati + 1;
				} else {
					jBirArti = jKoordinati;
				}
				if (iKoordinati > 0) {
					iBirEksi = iKoordinati - 1;
				} else {
					iBirEksi = iKoordinati;
				}
				if (iKoordinati < 9) {
					iBirArti = iKoordinati + 1;
				} else {
					iBirArti = iKoordinati;
				}

				if (weaponName == 0) {// gun

					if (kullanilanGunSayisi < leveldekiToplamGunSayisi) {// gun kaldımı
						gridler[iKoordinati][jKoordinati].setVuruldu(true);
						kullanilanGunSayisi = kullanilanGunSayisi + 1;
						// System.out.println(gun);
						if (gridler[iKoordinati][jKoordinati].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("Gun is over.");
					}
				} else if (weaponName == 1) {// bomb
					if (kullanilanBombSayisi < leveldekiToplambombSayisi) {
						gridler[iKoordinati][jBirEksi].setVuruldu(true);
						gridler[iKoordinati][jKoordinati].setVuruldu(true);

						gridler[iKoordinati][jBirArti].setVuruldu(true);
						kullanilanBombSayisi = kullanilanBombSayisi + 1;
						if (gridler[iKoordinati][jKoordinati].getDeger() == 's'
								|| gridler[iKoordinati][jBirEksi].getDeger() == 's'
								|| gridler[iKoordinati][jBirArti].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("The bomb is over.");
					}

				} else if (weaponName == 2) {// rocket
					if (kullanilanRocketSayisi < leveldekiToplamRocketSayisi) {
						gridler[iKoordinati][jKoordinati].setVuruldu(true);
						gridler[iKoordinati][jBirEksi].setVuruldu(true);
						gridler[iKoordinati][jBirArti].setVuruldu(true);
						gridler[iBirArti][jKoordinati].setVuruldu(true);
						gridler[iBirEksi][jKoordinati].setVuruldu(true);

						kullanilanRocketSayisi = kullanilanRocketSayisi + 1;

						if (gridler[iKoordinati][jKoordinati].getDeger() == 's'
								|| gridler[iKoordinati][jBirEksi].getDeger() == 's'
								|| gridler[iKoordinati][jBirArti].getDeger() == 's'
								|| gridler[iBirArti][jKoordinati].getDeger() == 's'
								|| gridler[iBirEksi][jKoordinati].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("The Rocket is over.");
					}

				}

				if (boatHepsiVuruldu == false) {
					boatHepsiVuruldu = boat.hepsiVuruldumu(gridler);// boat vuruldu ise x yapar.
				}
				if (submarineHepsiVuruldu == false) {
					submarineHepsiVuruldu = sub.hepsiVuruldumu(gridler);
				}
				if (destroyerHepsiVuruldu == false) {
					destroyerHepsiVuruldu = des.hepsiVuruldumu(gridler);
				}
				if (boatHepsiVuruldu == true && submarineHepsiVuruldu == true && destroyerHepsiVuruldu == true) {
					System.out.println("Congratulations. the level has increased.");
					oyunTahtasi(10);
					level = level + 1;
					dosyayaKayıtEtme(username, level);
					System.out.println("c.Continue level \nq.exit :");
					String acontinue = scanner.next();
					if (acontinue.equals("q")) {
						level = -1;
					}
					break;
				}

			}

		}
		while (level >= 2 && level < 5) {// normal mode
			gridOlustur(15);
			Boat boat = new Boat(gridler, 2);
			Boat boat2 = new Boat(gridler, 2);
			Destroyer des = new Destroyer(gridler, 2);
			Submarine sub = new Submarine(gridler, 2);
			Battleship bir = new Battleship(gridler, 2);
			int leveldekiToplamGunSayisi = 10, leveldekiToplamRocketSayisi = 1, leveldekiToplambombSayisi = 3;
			int kullanilanGunSayisi = 0, kullanilanRocketSayisi = 0, kullanilanBombSayisi = 0;

			int jBirEksi, iBirEksi, jBirArti, iBirArti;

			boolean boatHepsiVuruldu = false;
			boolean boat2HepsiVuruldu = false;
			boolean submarineHepsiVuruldu = false;
			boolean destroyerHepsiVuruldu = false;
			boolean battleshipHepsiVuruldu = false;
			while (devam) {

				oyunTahtasi(15);
				// atış kalmadı ve hedeflerın tamamı vurulmadı ise
				if (kullanilanRocketSayisi >= leveldekiToplamRocketSayisi
						&& kullanilanGunSayisi >= leveldekiToplamGunSayisi
						&& kullanilanBombSayisi >= leveldekiToplambombSayisi) {// oyun biter.

					if (boatHepsiVuruldu == false || boat2HepsiVuruldu == false || submarineHepsiVuruldu == false
							|| destroyerHepsiVuruldu == false || battleshipHepsiVuruldu == false) {

						devam = false;
						System.out.println("No shot left. Game over");

						dosyayaKayıtEtme(username, level);// dosyaya kaydet
						level = -1;// oyun biter
						break;
					}
				}
				System.out.println("0.Gun Shot: " + (leveldekiToplamGunSayisi - kullanilanGunSayisi));
				System.out.println("1.Hand Bomb: " + (leveldekiToplambombSayisi - kullanilanBombSayisi));
				System.out.println("2.Rocket: " + (leveldekiToplamRocketSayisi - kullanilanRocketSayisi));
				System.out.print("Weapon and x,y coordinate:");
				int weaponName = scanner.nextInt();
				int iKoordinati = scanner.nextInt();
				int jKoordinati = scanner.nextInt();

				if (jKoordinati > 0) {
					jBirEksi = jKoordinati - 1;
				} else {// 0 ise bir eksigi olamaz
					jBirEksi = jKoordinati;
				}
				if (jKoordinati < 9) {
					jBirArti = jKoordinati + 1;
				} else {
					jBirArti = jKoordinati;
				}
				if (iKoordinati > 0) {
					iBirEksi = iKoordinati - 1;
				} else {
					iBirEksi = iKoordinati;
				}
				if (iKoordinati < 9) {
					iBirArti = iKoordinati + 1;
				} else {
					iBirArti = iKoordinati;
				}

				if (weaponName == 0) {
					if (kullanilanGunSayisi < leveldekiToplamGunSayisi) {// gun varmı
						gridler[iKoordinati][jKoordinati].setVuruldu(true);
						kullanilanGunSayisi = kullanilanGunSayisi + 1;
						// System.out.println(gun);
						if (gridler[iKoordinati][jKoordinati].getDeger() == 's') {
							System.out.println("Hit!");
						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("The Gun is over..");
					}
				} else if (weaponName == 1) {
					if (kullanilanBombSayisi < leveldekiToplambombSayisi) {
						gridler[iKoordinati][jBirEksi].setVuruldu(true);
						gridler[iKoordinati][jKoordinati].setVuruldu(true);

						gridler[iKoordinati][jBirArti].setVuruldu(true);
						kullanilanBombSayisi = kullanilanBombSayisi + 1;
						if (gridler[iKoordinati][jKoordinati].getDeger() == 's'
								|| gridler[iKoordinati][jBirEksi].getDeger() == 's'
								|| gridler[iKoordinati][jBirArti].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("The bomb is over.");
					}

				} else if (weaponName == 2) {
					if (kullanilanRocketSayisi < leveldekiToplamRocketSayisi) {
						gridler[iKoordinati][jKoordinati].setVuruldu(true);
						gridler[iKoordinati][jBirEksi].setVuruldu(true);
						gridler[iKoordinati][jBirArti].setVuruldu(true);
						gridler[iBirArti][jKoordinati].setVuruldu(true);
						gridler[iBirEksi][jKoordinati].setVuruldu(true);

						kullanilanRocketSayisi = kullanilanRocketSayisi + 1;

						if (gridler[iKoordinati][jKoordinati].getDeger() == 's'
								|| gridler[iKoordinati][jBirEksi].getDeger() == 's'
								|| gridler[iKoordinati][jBirArti].getDeger() == 's'
								|| gridler[iBirArti][jKoordinati].getDeger() == 's'
								|| gridler[iBirEksi][jKoordinati].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("The Rocket is over.");
					}
				}

				if (boatHepsiVuruldu == false) {
					boatHepsiVuruldu = boat.hepsiVuruldumu(gridler);// boat vuruldu ise x yapar.
				}
				if (boat2HepsiVuruldu == false) {
					boat2HepsiVuruldu = boat2.hepsiVuruldumu(gridler);// boat vuruldu ise x yapar.
				}
				if (submarineHepsiVuruldu == false) {
					submarineHepsiVuruldu = sub.hepsiVuruldumu(gridler);
				}
				if (destroyerHepsiVuruldu == false) {
					destroyerHepsiVuruldu = des.hepsiVuruldumu(gridler);
				}
				if (battleshipHepsiVuruldu == false) {
					battleshipHepsiVuruldu = bir.hepsiVuruldumu(gridler);
				}
				if (boatHepsiVuruldu == true && boat2HepsiVuruldu == true && submarineHepsiVuruldu == true
						&& destroyerHepsiVuruldu == true && battleshipHepsiVuruldu == true) {

					System.out.println("Congratulations. the level has increased.");
					oyunTahtasi(15);
					level = level + 1;

					dosyayaKayıtEtme(username, level);// kaydet
					System.out.println("c.Continue level \nq.exit :");
					String acontinue = scanner.next();
					if (acontinue.equals("q")) {
						level = -1;
					}
					break;
				}

			}

		}
		while (level >= 5) { // hard level
			gridOlustur(20);
			Boat boat = new Boat(gridler, 3);
			Boat boat2 = new Boat(gridler, 3);
			Destroyer des = new Destroyer(gridler, 3);
			Submarine sub = new Submarine(gridler, 3);
			Battleship bir = new Battleship(gridler, 3);

			int leveldekiToplamGunSayisi = 12, leveldekiToplamRocketSayisi = 1, leveldekiToplambombSayisi = 4;
			int kullanilanGunSayisi = 0, kullanilanRocketSayisi = 0, kullanilanBombSayisi = 0;
			int jBirEksi, iBirEksi, jBirArti, iBirArti;

			boolean boatHepsiVuruldu = false;
			boolean boat2HepsiVuruldu = false;
			boolean submarineHepsiVuruldu = false;
			boolean destroyerHepsiVuruldu = false;
			boolean battleshipHepsiVuruldu = false;

			while (devam) {//////////////

				oyunTahtasi(20);

				if (kullanilanRocketSayisi >= leveldekiToplamRocketSayisi
						&& kullanilanGunSayisi >= leveldekiToplamGunSayisi
						&& kullanilanBombSayisi >= leveldekiToplambombSayisi) {// oyun biter.

					if (boatHepsiVuruldu == false || boat2HepsiVuruldu == false || submarineHepsiVuruldu == false
							|| destroyerHepsiVuruldu == false || battleshipHepsiVuruldu == false) {

						devam = false;
						System.out.println("No shot left. Game over");

						dosyayaKayıtEtme(username, level);// dosyaya kaydet
						level = -1;// oyun biter
						break;
					}
				}

				System.out.println("0.Gun Shot: " + (leveldekiToplamGunSayisi - kullanilanGunSayisi));
				System.out.println("1.Hand Bomb: " + (leveldekiToplambombSayisi - kullanilanBombSayisi));
				System.out.println("2.Rocket: " + (leveldekiToplamRocketSayisi - kullanilanRocketSayisi));
				System.out.print("Weapon and x,y coordinate:");
				int weaponName = scanner.nextInt();
				int iKoordinati = scanner.nextInt();
				int jKoordinati = scanner.nextInt();
				if (jKoordinati > 0) {
					jBirEksi = jKoordinati - 1;
				} else {
					jBirEksi = jKoordinati;
				}
				if (jKoordinati < 9) {
					jBirArti = jKoordinati + 1;
				} else {
					jBirArti = jKoordinati;
				}
				if (iKoordinati > 0) {
					iBirEksi = iKoordinati - 1;
				} else {
					iBirEksi = iKoordinati;
				}
				if (iKoordinati < 9) {
					iBirArti = iKoordinati + 1;
				} else {
					iBirArti = iKoordinati;
				}

				if (weaponName == 0) {
					if (kullanilanGunSayisi < leveldekiToplamGunSayisi) {// gun varmı
						gridler[iKoordinati][jKoordinati].setVuruldu(true);
						kullanilanGunSayisi = kullanilanGunSayisi + 1;
						if (gridler[iKoordinati][jKoordinati].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("The Gun is over..");
					}
				} else if (weaponName == 1) {
					if (kullanilanBombSayisi < leveldekiToplambombSayisi) {
						gridler[iKoordinati][jBirEksi].setVuruldu(true);
						gridler[iKoordinati][jKoordinati].setVuruldu(true);

						gridler[iKoordinati][jBirArti].setVuruldu(true);
						kullanilanBombSayisi = kullanilanBombSayisi + 1;
						if (gridler[iKoordinati][jKoordinati].getDeger() == 's'
								|| gridler[iKoordinati][jBirEksi].getDeger() == 's'
								|| gridler[iKoordinati][jBirArti].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("no bomb left");
					}
				} else if (weaponName == 2) {
					if (kullanilanRocketSayisi < leveldekiToplamRocketSayisi) {
						gridler[iKoordinati][jKoordinati].setVuruldu(true);
						gridler[iKoordinati][jBirEksi].setVuruldu(true);
						gridler[iKoordinati][jBirArti].setVuruldu(true);
						gridler[iBirArti][jKoordinati].setVuruldu(true);
						gridler[iBirEksi][jKoordinati].setVuruldu(true);

						kullanilanRocketSayisi = kullanilanRocketSayisi + 1;

						if (gridler[iKoordinati][jKoordinati].getDeger() == 's'
								|| gridler[iKoordinati][jBirEksi].getDeger() == 's'
								|| gridler[iKoordinati][jBirArti].getDeger() == 's'
								|| gridler[iBirArti][jKoordinati].getDeger() == 's'
								|| gridler[iBirEksi][jKoordinati].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("The Rocket is over.");
					}

				}
				if (boatHepsiVuruldu == false) {
					boatHepsiVuruldu = boat.hepsiVuruldumu(gridler);// boat vuruldu ise x yapar.
				}
				if (boat2HepsiVuruldu == false) {
					boat2HepsiVuruldu = boat2.hepsiVuruldumu(gridler);// boat vuruldu ise x yapar.
				}
				if (submarineHepsiVuruldu == false) {
					submarineHepsiVuruldu = sub.hepsiVuruldumu(gridler);
				}
				if (destroyerHepsiVuruldu == false) {
					destroyerHepsiVuruldu = des.hepsiVuruldumu(gridler);
				}
				if (battleshipHepsiVuruldu == false) {
					battleshipHepsiVuruldu = bir.hepsiVuruldumu(gridler);
				}
				if (boatHepsiVuruldu == true && boat2HepsiVuruldu == true && submarineHepsiVuruldu == true
						&& destroyerHepsiVuruldu == true && battleshipHepsiVuruldu == true) {

					System.out.println("Congratulations. the level has increased.");
					oyunTahtasi(20);
					level = level + 1;

					dosyayaKayıtEtme(username, level);
					System.out.println("c.Continue level \nq.exit :");
					String acontinue = scanner.next();
					if (acontinue.equals("q")) {
						level = -1;
					}
					break;
				}

			}
		}

	}

	public static void dosyayaKayıtEtme(String username, int level) {// kayıt etme

		int levelYazma = -1;

		String mode = "Hata";

		Path path = Paths.get("", "AmiralBattiInfo.txt");

		Charset charset = Charset.forName("UTF-8");
		try {
			List<String> lines = Files.readAllLines(path, charset);

			/*
			 * for (String line : lines) { System.out.println(line);
			 * 
			 * }
			 */
			if (level >= 0 && level < 2) {
				mode = "Easy";
				levelYazma = level;
			} else if (level >= 2 && level < 5) {
				mode = "Normal";
				levelYazma = (level % 5) - 2;
			} else if (level >= 5) {
				mode = "Hard";
				levelYazma = (level % 5);
			}

			for (int i = 0; i < lines.size(); i++) {
				String[] array = lines.get(i).split(" ");
				if (array[0].equals(username)) {

					System.out.println("Saving...");

					lines.set(i, username + " " + mode + "(" + levelYazma + ")");
					break;
				}
			}
			Path file = Paths.get("AmiralBattiInfo.txt");

			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public static int dosyadanOkuma(String username) {// dosyadan okuma

		int level = -1;
		try (Scanner scanner = new Scanner(new FileReader("AmiralBattiInfo.txt"))) {

			while (scanner.hasNextLine()) {

				String user_bilgisi = scanner.nextLine();

				String[] array = user_bilgisi.split(" ");

				if (array[0].equals(username)) {

					String modeokuma = array[1].substring(0, array[1].length() - 3);
					String[] parts = user_bilgisi.split("\\(");

					String part2 = parts[1].substring(0, 1);// cıktısı 1
					int foo = Integer.parseInt(part2);

					// System.out.println(modeokuma);

					if (modeokuma.equals("Easy")) {
						level = foo;
					} else if (modeokuma.equals("Normal")) {
						level = foo + 2;
					} else if (modeokuma.equals("Hard")) {
						level = foo + 5;
					}

					// System.out.println(level);
					return level;

				}

			}

		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {
			System.out.println("Dosya açılırken bir hata oluştu....");
		}

		return -1;
	}

	public static boolean dosyayaKullaniciKaydetme(String newusername) {// new user olusturma

		if (dosyadanOkuma(newusername) != -1) {
			System.out.println("Already exists");
			return false;
		}
		int level = 0;
		String mode = "Easy";
		FileWriter writer = null;

		try {
			writer = new FileWriter("AmiralBattiInfo.txt", true);

			writer.write(newusername + " " + mode + "(");
			if (level > 9) {
				System.out.println("level 9 dan fazla olamaz.");
			}

			writer.write(Integer.toString(level) + ")\n");

		} catch (IOException ex) {
			System.out.println("Dosya açılırken IOException oluştu...");
		} finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					System.out.println("Dosya Kapatılırken bir hata oluştu...");
				}

			}
		}
		return true;
	}

	public static void oyunTahtasi(int boyut) {// ekrana yazdırma
		System.out.print("   ");
		for (int i = 0; i < boyut; i++) {
			if (i < 9) {
				System.out.print(i + "  ");
			} else {
				System.out.print(i + " ");
			}
		}
		System.out.println("");
		for (int i = 0; i < boyut; i++) {
			if (i < 10) {
				System.out.print(i + "  ");
			} else {
				System.out.print(i + " ");
			}

			for (int j = 0; j < boyut; j++) {
				// gridler[i][j].isVuruldu()==
				if (gridler[i][j].isVuruldu() == true) {

					System.out.print(gridler[i][j].getDeger() + "  ");

				} else {
					System.out.print(".  ");

				}
			}
			System.out.println("");
		}

	}

}
