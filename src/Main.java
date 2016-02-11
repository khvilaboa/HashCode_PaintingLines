import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static char[][] getInput(String fileLocation) {
		char[][] toRet;

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileLocation));
			StringBuilder sb = new StringBuilder();

			String firstLine = br.readLine();
			int height = Integer.parseInt((firstLine.split(" "))[0]);
			int width = Integer.parseInt((firstLine.split(" "))[1]);
			//System.out.println(height);
			//System.out.println(width);
			toRet = new char[height][width];

			String line = br.readLine();
			int currentLine = 0;
			int currentIndex = 0;
			while (line != null) {
				currentIndex = 0;
				for (char c : line.toCharArray()) {
					// System.out.println(c);
					toRet[currentLine][currentIndex] = c;
					currentIndex++;
				}
				currentLine++;
				line = br.readLine();
			}
			br.close();
			return toRet;
		} catch (Exception e) {
			System.out.println("Error");
		}

		return null;

	}

	public static void printMatrix(char[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[row].length; column++) {
				System.out.print(matrix[row][column] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		char[][] input = { { '.', '.', '.', '.', '#', '.', '.' }, { '.', '.', '#', '#', '#', '.', '.' },
				{ '.', '.', '#', '.', '#', '.', '.' }, { '.', '.', '#', '#', '#', '.', '.' },
				{ '.', '.', '#', '.', '.', '.', '.' } };

		char[][] file = getInput("learn_and_teach.in"); 
		//printMatrix(file);
		
		String cmd = Parser.parse(file);
		System.out.println(cmd.split("\n").length);
		//System.out.println(cmd);
		System.out.println();
		printMatrix(file);
		
		/*Square tocho = new Square(new Point(6,6), 3);
		Square peq = new Square(new Point(2,2), 2);
		//Square peq = new Square(new Point(2,11), 2);
		
		System.out.println(tocho.collidesWith(peq));*/
	}
}
