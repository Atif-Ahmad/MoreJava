package MowingFinal;

import java.util.ArrayList;
import java.util.Scanner;

public class Mowing_Final {

	public static void main(String[] args) {
		System.out.println("Enter how many houses you have (this is the same number as the number of lawns that I will mow).");
		Scanner sc = new Scanner(System.in);
		int nums = sc.nextInt();
		sc.nextLine();
		for (int xyz = 0; xyz < nums; xyz++) {
			System.out.println("Enter dimensions");
			int e = sc.nextInt();
			int f = sc.nextInt();
			sc.nextLine();
			Lawn l = new Lawn(e, f);
//ASSUMES THE STARTING POS HAS NO TREES
			if (e > 11 && f > 12) {
				l.placeTree(1, 3);
				l.placeTree(1, 9);
				l.placeTree(4, 1);
				l.placeTree(5, 3);
				l.placeTree(6, 1);
				l.placeTree(8, 2);
				l.placeTree(9, 6);
				l.placeTree(11, 6);
				l.placeTree(8, 10);
				l.placeTree(7, 9);
				l.placeTree(1, 9);
			}else {
				for (int i = 0; i < 11; i++) {
					int x = (int) (Math.random() * (f-1));
					int y = (int) (Math.random() * (e-1));
					l.placeTree(x, y);
				}
			}

			l.printLawn();

			System.out.println("Enter starting coordinates");
			int x = sc.nextInt();
			int y = sc.nextInt();

			ArrayList<Point> q = new ArrayList<Point>();
			Point p = new Point(x, y);
			q.add(p);
			l.getLawn()[x][y] = " C ";
			l.getLawn()[x][y + 1] = " C ";
			l.getLawn()[x][y + 2] = " C ";
			l.getLawn()[x + 1][y] = " C ";
			l.getLawn()[x + 1][y + 1] = " C ";
			l.getLawn()[x + 1][y + 2] = " C ";
			l.getLawn()[x + 2][y] = " C ";
			l.getLawn()[x + 2][y + 1] = " C ";
			l.getLawn()[x + 2][y + 2] = " C ";

			while (!q.isEmpty()) {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					Point p1 = q.remove(q.size() - 1);
					// up
					if (p1.getX() > 0 && !l.getLawn()[p1.getX() - 1][p1.getY()].equals(" T ")
							&& !l.getLawn()[p1.getX() - 1][p1.getY() + 1].equals(" T ")
							&& !l.getLawn()[p1.getX() - 1][p1.getY() + 2].equals(" T ")) {
						if (p1.getX() > 0 && (l.getLawn()[p1.getX() - 1][p1.getY()].equals(" * ")
								|| l.getLawn()[p1.getX() - 1][p1.getY() + 1].equals(" * ")
								|| l.getLawn()[p1.getX() - 1][p1.getY() + 2].equals(" * "))) {
							q.add(new Point(p1.getX() - 1, p1.getY()));

							l.getLawn()[p1.getX() - 1][p1.getY()] = " C ";
							l.getLawn()[p1.getX() - 1][p1.getY() + 1] = " C ";
							l.getLawn()[p1.getX() - 1][p1.getY() + 2] = " C ";
						}
					}
					// down
					if (p1.getX() < l.getLength() - 3 && !l.getLawn()[p1.getX() + 3][p1.getY()].equals(" T ")
							&& !l.getLawn()[p1.getX() + 3][p1.getY() + 1].equals(" T ")
							&& !l.getLawn()[p1.getX() + 3][p1.getY() + 2].equals(" T ")) {
						if (p1.getX() < l.getLength() - 3 && (l.getLawn()[p1.getX() + 3][p1.getY()].equals(" * ")
								|| l.getLawn()[p1.getX() + 3][p1.getY() + 1].equals(" * ")
								|| l.getLawn()[p1.getX() + 3][p1.getY() + 2].equals(" * "))) {
							q.add(new Point(p1.getX() + 1, p1.getY()));

							l.getLawn()[p1.getX() + 3][p1.getY()] = " C ";
							l.getLawn()[p1.getX() + 3][p1.getY() + 1] = " C ";
							l.getLawn()[p1.getX() + 3][p1.getY() + 2] = " C ";
						}
					}
					// left
					if (p1.getY() > 0 && !l.getLawn()[p1.getX()][p1.getY() - 1].equals(" T ")
							&& !l.getLawn()[p1.getX() + 1][p1.getY() - 1].equals(" T ")
							&& !l.getLawn()[p1.getX() + 2][p1.getY() - 1].equals(" T ")) {

						if (p1.getY() > 0 && (l.getLawn()[p1.getX()][p1.getY() - 1].equals(" * ")
								|| l.getLawn()[p1.getX() + 1][p1.getY() - 1].equals(" * ")
								|| l.getLawn()[p1.getX() + 2][p1.getY() - 1].equals(" * "))) {
							q.add(new Point(p1.getX(), p1.getY() - 1));

							l.getLawn()[p1.getX()][p1.getY() - 1] = " C ";
							l.getLawn()[p1.getX() + 1][p1.getY() - 1] = " C ";
							l.getLawn()[p1.getX() + 2][p1.getY() - 1] = " C ";
						}
					}
					// right
					if (p1.getY() < l.getWidth() - 3 && !l.getLawn()[p1.getX()][p1.getY() + 3].equals(" T ")
							&& !l.getLawn()[p1.getX() + 1][p1.getY() + 3].equals(" T ")
							&& !l.getLawn()[p1.getX() + 2][p1.getY() + 3].equals(" T ")) {
						if (p1.getY() < l.getWidth() - 3 && (l.getLawn()[p1.getX()][p1.getY() + 3].equals(" * ")
								|| l.getLawn()[p1.getX() + 1][p1.getY() + 3].equals(" * ")
								|| l.getLawn()[p1.getX() + 2][p1.getY() + 3].equals(" * "))) {
							q.add(new Point(p1.getX(), p1.getY() + 1));

							l.getLawn()[p1.getX()][p1.getY() + 3] = " C ";
							l.getLawn()[p1.getX() + 1][p1.getY() + 3] = " C ";
							l.getLawn()[p1.getX() + 2][p1.getY() + 3] = " C ";
						}
					}
				}
			}
			l.printLawn();
		}
	}
}

class Point {
	private int x;
	private int y;

	public Point(int xcoor, int ycoor) {
		x = xcoor;
		y = ycoor;
	}

	public void setX(int e) {
		x = e;
	}

	public void setY(int e) {
		y = e;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
