import java.util.ArrayList;
import java.util.List;

class Square implements Figure {
		public static final double THRESHOLD = 0.75;
		private List<Point> points = new ArrayList<>();
		private Point c;
		private int r;

		public Square(Point c, int r) {
			super();
			this.c = c;
			this.r = r;
		}

		public Square(int x, int y, int r) {
			this.c = new Point(x, y);
			this.r = r;
		}

		public Point getC() {
			return c;
		}

		public void setC(Point c) {
			this.c = c;
		}

		public int getR() {
			return r;
		}

		public void setR(int r) {
			this.r = r;
		}

		public void removePoint(Point p) {
			points.add(p);
		}
		
		public void removePoints(List<Point> p) {
			points = p;
		}

		public String toString() {
			String cmd = "PAINT_SQUARE " + c.getX() + " " + c.getY() + " " + r;
			for (Point p : points) {
				cmd += "\nERASE_CELL " + p.getX() + " " + p.getY();
			}

			return cmd + "\n";
		}

		@Override
		public void erasePoints(char[][] matrix) {
			for(Point p: points) {
				matrix[p.getX()][p.getY()] = '~';
			}
		}
		
		public boolean collidesWith(Figure base) {
			Square s = (Square) base;
			int lx = getC().getY() - r;
			int ux = getC().getY() + r;
			int uy = getC().getX() - r;
			int ly = getC().getX() + r;
			
			int bux = s.getC().getY() + s.getR();
			int blx = s.getC().getY() - s.getR();
			int buy = s.getC().getX() - s.getR();
			int bly = s.getC().getX() + s.getR();
			
			
			return (bux <= ux && bux >= lx && bly >= uy && bly <= ly ||
			        blx <= ux && blx >= lx && bly >= uy && bly <= ly);
		}
	}
