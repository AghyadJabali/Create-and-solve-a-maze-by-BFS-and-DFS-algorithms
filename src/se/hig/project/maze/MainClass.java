package se.hig.project.maze;

import java.awt.Point;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {

		System.out.print("Set maze size: ");
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		
		CreatMaze<Integer[][]> mazeGenerator = new CreatMazeClass<Integer[][]>(size);
		Object[][] maze = mazeGenerator.creatTheMaze();
		scan.close();
		System.out.println("The Maze is:\n" + mazeGenerator.getTheMaze());

		Point source = new Point(0, 0);
		Point dest = new Point(size - 1, size - 1);
		
		SolveMazeClass<Integer> solver = new SolveMazeClass<Integer>(size);
		int dist = solver.BFSCorrectPath((Integer[][]) maze, source, dest);

			System.out.println("Shortest way is: " + dist);

	}
}