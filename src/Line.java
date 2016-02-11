class Line implements Figure {
		private Point src, dst;

		public Line(Point src, Point dst) {
			super();
			this.src = src;
			this.dst = dst;
		}

		public Line(int sx, int sy, int dx, int dy) {
			this.src = new Point(sx, sy);
			this.dst = new Point(dx, dy);
		}

		@Override
		public String toString() {
			return "PAINT_LINE " + src.getX() + " " + src.getY() + " " + dst.getX() + " " + dst.getY() + "\n";
		}

		@Override
		public void erasePoints(char[][] matrix) {
			// nothing
		}

		@Override
		public boolean collidesWith(Figure f) {
			return false;
		}

		public Point getSrc() {
			return src;
		}

		public void setSrc(Point src) {
			this.src = src;
		}

		public Point getDst() {
			return dst;
		}

		public void setDst(Point dst) {
			this.dst = dst;
		}
		
		
	}