import java.util.ArrayList;
import java.util.List;

class Parser {
		private static List<Figure> figures = new ArrayList<>();

		private static int findSquares(char[][] matrix) {
			int squares = 0;
			
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[0].length; j++) { 
					int radius = 1;
					Square current = validSquare(matrix, new Point(i, j), radius);
					Square previous = null;
					
					while(current != null) {
						previous = current;
						current = validSquare(matrix, new Point(i, j), ++radius);
					}
					
					if(previous != null) {
						figures.add(previous);
						squares++;
					}
				}
			}
			
			return squares;
		}

		private static Square validSquare(char[][] matrix, Point c, int r) {
			
			if(c.getY() - r < 0 || c.getY() + r >= matrix[0].length ||
			   c.getX() - r < 0 || c.getX() + r >= matrix.length) {
				return null;
			}
			
			int count = 0;
			List<Point> points = new ArrayList<>();
			
			for(int i = c.getX() - r; i <= c.getX() + r; i++) {
				for(int j = c.getY() - r; j <= c.getY() + r; j++) { 
					if(matrix[i][j] == '#') {
						count += 1;
					} else if(matrix[i][j] == '.'){
						points.add(new Point(i,j));
					}
				}
			}
			
			if(r >= 2) {
				List<Figure> collisions = new ArrayList<>();
				Square s = new Square(c, r);
				
				for(Figure f: figures) {
					if(s.collidesWith(f)) {
						collisions.add(f);
					}
				}
				
				if(collisions.size() >= 2) {
					for(Figure f: collisions) {
						figures.remove(f);
					}
					
					for(int i = c.getX() - r; i <= c.getX() + r; i++) {
						for(int j = c.getY() - r; j <= c.getY() + r; j++) { 
							matrix[i][j] = '*';
						}
					}
					
					return s;
				}
			}
			
			if(count/Math.pow(r*2+1, 2) >= Square.THRESHOLD) {
				Square s = new Square(c, r);
				s.removePoints(points);
				
				// Paint square
				for(int i = c.getX() - r; i <= c.getX() + r; i++) {
					for(int j = c.getY() - r; j <= c.getY() + r; j++) { 
						matrix[i][j] = '+';
					}
				}
				return s;
			}
			
			return null;
		}

		private static int findLines(char[][] matrix) {
			int lines = 0;
			
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[0].length; j++) { 
					Line l = validLineY(matrix, new Point(i,j));
					if(l != null) {
						figures.add(l);
						j = l.getDst().getY();
						lines++;
					}
					
					l = validLineX(matrix, new Point(i,j));
					if(l != null) {
						figures.add(l);
						lines++;
					}
					
				}
			}
			return lines;
		}
		
		private static Line validLineX(char[][] matrix, Point c) {
			
			if(matrix[c.getX()][c.getY()] != '#') return null;
			
			int count = 1;
			int pos = c.getX() + ((c.getX() != matrix.length - 1)?1:0);
			matrix[c.getX()][c.getY()] = 'ç';
			
			while(pos < matrix.length && matrix[pos][c.getY()] == '#' || matrix[pos][c.getY()] == '~') {
				if(matrix[pos][c.getY()] == '#' || matrix[pos][c.getY()] == '~') matrix[pos][c.getY()] = 'ç';
				count++;
			}
			
			return new Line(c, new Point(c.getX() + count - 1, c.getY()));
		}

		private static Line validLineY(char[][] matrix, Point c) {
			
			if(matrix[c.getX()][c.getY()] != '#') return null;
			
			int count = 1;
			int pos = c.getY() + ((c.getY() != matrix[0].length - 1)?1:0);
			matrix[c.getX()][c.getY()] = 'ç';
			
			while(pos < matrix[0].length && matrix[c.getX()][pos] == '#' || matrix[c.getX()][pos] == '~') {
				if(matrix[c.getX()][pos] == '#' || matrix[c.getX()][pos] == '~') matrix[c.getX()][pos] = 'ç';
				count++;
			}
			
			return new Line(c, new Point(c.getX(), c.getY() + count - 1));
		}
		
		public static String parse(char[][] matrix) {
			while (findSquares(matrix) > 0);
			while (findLines(matrix) > 0);

			String res = "";
			for (Figure f : figures) {
				res += f.toString();
				f.erasePoints(matrix); // Erase points
			}
			
			
			

			// Post-proccess (rearrange commands)
			return res;
		}

	}