
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

	public static void Gridolustur(int gridx) { // baslangıcta tamamı - olan grid olusturulur.
		for (int i = 0; i < gridx; i++) {
			for (int j = 0; j < gridx; j++) {
				gridler[i][j] = new Grid('-');
			}
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int level = -1;

		boolean giris = true;
		boolean devam = true;
		String username = "";

		while (giris) {
			System.out.println("GAME MENU");
			System.out.println("1.Login \n2.Create new user\n3.Exit");
			int usertype = scanner.nextInt();

			if (usertype == 1) {
				System.out.print("Enter Username:");
				username = scanner.next();
				level = okuma(username);
				if (level >= 0) {
					giris = false;
				} else {
					System.out.println("Wrong username.Try again.");
				}

			} else if (usertype == 2) {
				System.out.print("New Username:");
				username = scanner.next();
				boolean s = kaydetme(username);
				level = 1;
				if (s == true) {
					giris = false;
				}

			} else {
				break;
			}
		}

		while (level >= 0 && level < 2) {
			Gridolustur(10);
			Boat boat = new Boat(gridler);
			Destroyer des = new Destroyer(gridler);
			Submarine sub = new Submarine(gridler);

			int gunsayisi = 7, rocketsayisi = 10, bombsayisi = 2;
			int gun = 0, rocket = 0, bomb = 0;
			int jeksi, ieksi, jarti, iarti;

			boolean b1 = false;
			boolean b2 = false;
			boolean su = false;
			boolean de = false;
			boolean battle = false; 
			

			while (devam) { 

				oyunTahtasi(10);

				if (rocket >= rocketsayisi && gun >= gunsayisi && bomb >= bombsayisi) {

					if (b2 == false || su == false || de == false) {
						devam = false;

						System.out.println("no shot left. Game over");

						uzerineyazma(username, level);
						level = -1;

						break;
					}
				}
				System.out.println("0.Gun Shot: " + (gunsayisi - gun));
				System.out.println("1.Hand Bomb: " + (bombsayisi - bomb));
				System.out.println("2.Rocket: " + (rocketsayisi - rocket));
				System.out.print("level: " + level + " Weapon and x,y coordinate:");
				int name = scanner.nextInt();
				int i1 = scanner.nextInt();
				int j1 = scanner.nextInt();

				if (j1 > 0) {
					jeksi = j1 - 1;
				} else {
					jeksi = j1;
				}
				if (j1 < 9) {
					jarti = j1 + 1;
				} else {
					jarti = j1;
				}
				if (i1 > 0) {
					ieksi = i1 - 1;
				} else {
					ieksi = i1;
				}
				if (i1 < 9) {
					iarti = i1 + 1;
				} else {
					iarti = i1;
				}

				if (name == 0) {

					if (gun < gunsayisi) {
						gridler[i1][j1].setVuruldu(true);
						gun = gun + 1;
						System.out.println(gun);
						if (gridler[i1][j1].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("Gun kalmadı.");
					}
				} else if (name == 1) {
					if (bomb < bombsayisi) {
						gridler[i1][jeksi].setVuruldu(true);
						gridler[i1][j1].setVuruldu(true);

						gridler[i1][jarti].setVuruldu(true);
						bomb = bomb + 1;
						if (gridler[i1][j1].getDeger() == 's' || gridler[i1][jeksi].getDeger() == 's'
								|| gridler[i1][jarti].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("bomb kalmadı");
					}

				} else if (name == 2) {
					if (rocket < rocketsayisi) {
						gridler[i1][j1].setVuruldu(true);
						gridler[i1][jeksi].setVuruldu(true);
						gridler[i1][jarti].setVuruldu(true);
						gridler[iarti][j1].setVuruldu(true);
						gridler[ieksi][j1].setVuruldu(true);

						rocket = rocket + 1;

						if (gridler[i1][j1].getDeger() == 's' || gridler[i1][jeksi].getDeger() == 's'
								|| gridler[i1][jarti].getDeger() == 's' || gridler[iarti][j1].getDeger() == 's'
								|| gridler[ieksi][j1].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("Rocket kalmadı");
					}

				}

				if (b2 == false) {

					b2 = boat.hepsi(gridler);
				}
				if (su == false) {
					su = sub.hepsi(gridler);
				}
				if (de == false) {
					de = des.hepsi(gridler);
				}

				if (b2 == true && su == true && de == true) {
					System.out.println("Congratulations. the level has increased.");
					oyunTahtasi(10);
					level = level + 1;

					uzerineyazma(username, level);

					System.out.println("c.Continue level \nq.exit :");
					String acontinue = scanner.next();
					if (acontinue.equals("q")) {
						level = -1;
					}

					break;
				}

			}

		}
		while (level >= 2 && level < 5) {
			Gridolustur(15);

			Boat boat = new Boat(gridler);
			Boat boat2 = new Boat(gridler);
			Destroyer des = new Destroyer(gridler);
			Submarine sub = new Submarine(gridler);

			Battleship bir = new Battleship(gridler);
			int gunsayisi = 10, rocketsayisi = 1, bombsayisi = 3;
			int gun = 0, rocket = 0, bomb = 0;
			int jeksi, ieksi, jarti, iarti;

			boolean b1 = false;
			boolean b2 = false;
			boolean su = false;
			boolean de = false;
			boolean battle = false;
			while (devam) {//////////////

				oyunTahtasi(15);

				if (rocket >= rocketsayisi && gun >= gunsayisi && bomb >= bombsayisi) {
					if (b2 == false || su == false || de == false || b1 == false || battle == false) {

						devam = false;
						System.out.println("no shot left. Game over");

						uzerineyazma(username, level);
						level = -1;
						break;
					}
				}
				System.out.println("0.Gun Shot: " + (gunsayisi - gun));
				System.out.println("1.Hand Bomb: " + (bombsayisi - bomb));
				System.out.println("2.Rocket: " + (rocketsayisi - rocket));
				System.out.print("level: " + level + " Weapon and x,y coordinate:");
				int name = scanner.nextInt();
				int i1 = scanner.nextInt();
				int j1 = scanner.nextInt();

				if (j1 > 0) {
					jeksi = j1 - 1;
				} else {
					jeksi = j1;
				}
				if (j1 < 9) {
					jarti = j1 + 1;
				} else {
					jarti = j1;
				}
				if (i1 > 0) {
					ieksi = i1 - 1;
				} else {
					ieksi = i1;
				}
				if (i1 < 9) {
					iarti = i1 + 1;
				} else {
					iarti = i1;
				}

				if (name == 0) {

					if (gun < gunsayisi) {
						gridler[i1][j1].setVuruldu(true);
						gun = gun + 1;
						System.out.println(gun);
						if (gridler[i1][j1].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("Gun kalmadı.");
					}
				} else if (name == 1) {

					if (bomb < bombsayisi) {
						gridler[i1][jeksi].setVuruldu(true);
						gridler[i1][j1].setVuruldu(true);

						gridler[i1][jarti].setVuruldu(true);
						bomb = bomb + 1;
						if (gridler[i1][j1].getDeger() == 's' || gridler[i1][jeksi].getDeger() == 's'
								|| gridler[i1][jarti].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("bomb kalmadı");
					}

				} else if (name == 2) {

					if (rocket < rocketsayisi) {
						gridler[i1][j1].setVuruldu(true);
						gridler[i1][jeksi].setVuruldu(true);
						gridler[i1][jarti].setVuruldu(true);
						gridler[iarti][j1].setVuruldu(true);
						gridler[ieksi][j1].setVuruldu(true);

						rocket = rocket + 1;

						if (gridler[i1][j1].getDeger() == 's' || gridler[i1][jeksi].getDeger() == 's'
								|| gridler[i1][jarti].getDeger() == 's' || gridler[iarti][j1].getDeger() == 's'
								|| gridler[ieksi][j1].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("Rocket kalmadı");
					}

				}

				if (battle == false) {
					battle = bir.hepsi(gridler);
				}
				if (b1 == false) {
					b1 = boat2.hepsi(gridler);
				}
				if (b2 == false) {

					b2 = boat.hepsi(gridler);
				}
				if (su == false) {
					su = sub.hepsi(gridler);
				}
				if (de == false) {
					de = des.hepsi(gridler);
				}

				if (battle == true && b1 == true && b2 == true && su == true && de == true) {
					System.out.println("Congratulations. the level has increased.");
					level = level + 1;

					uzerineyazma(username, level);
					System.out.println("c.Continue level \nq.exit :");
					String acontinue = scanner.next();
					if (acontinue.equals("c")) {

					} else {
						level = -1;
					}

					break;
				}

			}

		}
		if (level >= 5) { // hard

			Gridolustur(20);
			Boat boat = new Boat(gridler);
			Boat boat2 = new Boat(gridler);
			Destroyer des = new Destroyer(gridler);
			Submarine sub = new Submarine(gridler);

			Battleship bir = new Battleship(gridler);
			int gunsayisi = 12, rocketsayisi = 1, bombsayisi = 4;
			int gun = 0, rocket = 0, bomb = 0;
			int jeksi, ieksi, jarti, iarti;

			boolean b1 = false;
			boolean b2 = false;
			boolean su = false;
			boolean de = false;
			boolean battle = false;

			while (devam) {//////////////

				oyunTahtasi(20);

				if (rocket >= rocketsayisi && gun >= gunsayisi && bomb >= bombsayisi) {
					if (b2 == false || su == false || de == false || b1 == false || battle == false) {
						devam = false;
						System.out.println("no shot left. Game over");

						uzerineyazma(username, level);
						level = -1;
						break;
					}
				}

				System.out.println("0.Gun Shot: " + (gunsayisi - gun));
				System.out.println("1.Hand Bomb: " + (bombsayisi - bomb));
				System.out.println("2.Rocket: " + (rocketsayisi - rocket));
				System.out.print("level: " + level + " Weapon and x,y coordinate:");
				int name = scanner.nextInt();
				int i1 = scanner.nextInt();
				int j1 = scanner.nextInt();
				if (j1 > 0) {
					jeksi = j1 - 1;
				} else {
					jeksi = j1;
				}
				if (j1 < 9) {
					jarti = j1 + 1;
				} else {
					jarti = j1;
				}
				if (i1 > 0) {
					ieksi = i1 - 1;
				} else {
					ieksi = i1;
				}
				if (i1 < 9) {
					iarti = i1 + 1;
				} else {
					iarti = i1;
				}

				if (name == 0) {

					if (gun < gunsayisi) {
						gridler[i1][j1].setVuruldu(true);
						gun = gun + 1;
						System.out.println(gun);
						if (gridler[i1][j1].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("no gun left.");
					}
				} else if (name == 1) {

					if (bomb < bombsayisi) {
						gridler[i1][jeksi].setVuruldu(true);
						gridler[i1][j1].setVuruldu(true);

						gridler[i1][jarti].setVuruldu(true);
						bomb = bomb + 1;
						if (gridler[i1][j1].getDeger() == 's' || gridler[i1][jeksi].getDeger() == 's'
								|| gridler[i1][jarti].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("no bomb left");
					}

				} else if (name == 2) {

					if (rocket < rocketsayisi) {
						gridler[i1][j1].setVuruldu(true);
						gridler[i1][jeksi].setVuruldu(true);
						gridler[i1][jarti].setVuruldu(true);
						gridler[iarti][j1].setVuruldu(true);
						gridler[ieksi][j1].setVuruldu(true);

						rocket = rocket + 1;

						if (gridler[i1][j1].getDeger() == 's' || gridler[i1][jeksi].getDeger() == 's'
								|| gridler[i1][jarti].getDeger() == 's' || gridler[iarti][j1].getDeger() == 's'
								|| gridler[ieksi][j1].getDeger() == 's') {
							System.out.println("Hit!");

						} else {
							System.out.println("İnvalid Hit");
						}
					} else {
						System.out.println("Rocket kalmadı");
					}

				}
				if (battle == false) {
					battle = bir.hepsi(gridler);
				}
				if (b1 == false) {
					b1 = boat2.hepsi(gridler);
				}
				if (b2 == false) {

					b2 = boat.hepsi(gridler);
				}
				if (su == false) {
					su = sub.hepsi(gridler);
				}
				if (de == false) {
					de = des.hepsi(gridler);
				}

				if (battle == true && b1 == true && b2 == true && su == true && de == true) {
					System.out.print("Congratulations. the level has increased.");
					level = level + 1;

					uzerineyazma(username, level);
					System.out.println("c.Continue level \nq.exit :");
					String acontinue = scanner.next();
					if (acontinue.equals("c")) {

					} else {
						level = -1;
					}
					break;
				}

			}
		}

	}

	public static void uzerineyazma(String username, int level) {

		int levelyazma = -1;

		String mode = "Hata";

		Path wiki_path = Paths.get("", "AmiralBattiInfo.txt");

		Charset charset = Charset.forName("UTF-8");
		try {
			List<String> lines = Files.readAllLines(wiki_path, charset);

			/*
			 * for (String line : lines) { System.out.println(line);
			 * 
			 * }
			 */
			if (level >= 0 && level < 2) {
				mode = "Easy";

				levelyazma = level;
			} else if (level >= 2 && level < 5) {
				mode = "Normal";
				levelyazma = (level % 5) - 2;
			} else if (level >= 5) {
				mode = "Hard";
				levelyazma = (level % 5);
			}

			for (int i = 0; i < lines.size(); i++) {
				String[] array = lines.get(i).split(" ");
				if (array[0].equals(username)) {

					System.out.println("Saving...");

					lines.set(i, username + " " + mode + "(" + levelyazma + ")");
					break;
				}
			}
			Path file = Paths.get("AmiralBattiInfo.txt");

			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public static int okuma(String username) {

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

					System.out.println(modeokuma);

					if (modeokuma.equals("Easy")) {
						level = foo;
					} else if (modeokuma.equals("Normal")) {
						level = foo + 2;
					} else if (modeokuma.equals("Hard")) {
						level = foo + 5;
					}

					// System.out.println(array[0]);

					// System.out.println(" Bilgisi: " + user_bilgisi);

					// System.out.println(foo);
					System.out.println(level);
					return level;

				}

			}

		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {
			System.out.println("Dosya açılırken bir hata oluştu....");
		}

		return -1;
	}

	public static boolean kaydetme(String newusername) {

		if (okuma(newusername) != -1) {
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

	public static void oyunTahtasi(int boyut) {
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
				if (true) {

					System.out.print(gridler[i][j].getDeger() + "  ");

				} else {
					System.out.print(".  ");

				}
			}
			System.out.println("");
		}

	}

}
