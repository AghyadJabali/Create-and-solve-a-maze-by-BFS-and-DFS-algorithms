package se.hig.project.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SolveMazeClass<T> implements SolveTheMaze<T> {

	private static final int ROW_MAZE[] = { -1, 0, 0, 1 };
	private static final int COLUM_MAZE[] = { 0, -1, 1, 0 };
	private final int mazeSize;
	QueueNode node;
	StringBuilder sb;

	public SolveMazeClass(int mazeSize) {
		this.mazeSize = mazeSize;
	}

	@Override
	public int BFSCorrectPath(T[][] maze, Point firstPoint, Point lastPoint) {

		Integer startPoint = (Integer) maze[firstPoint.x][firstPoint.y];
		Integer endPoint = (Integer) maze[lastPoint.x][lastPoint.y];

		if (startPoint != 1 || endPoint != 1)
			return -1;

		boolean[][] visited = new boolean[mazeSize][mazeSize];

		// Mark first point as visited
		visited[firstPoint.x][firstPoint.y] = true;

		// to save all the ways
		List<List<QueueNode>> paths = new ArrayList<>();

		// push and pop the way
		Queue<List<QueueNode>> queue = new LinkedList<>();

		// the final way
		List<QueueNode> rightPath = new ArrayList<>();

		// this the first point
		QueueNode node = new QueueNode(firstPoint, 0);
		rightPath.add(node);
		queue.add(rightPath);

		while (!queue.isEmpty()) {

			rightPath = queue.peek();

			paths.add(rightPath);
			
			QueueNode curr = rightPath.get(rightPath.size() - 1);
			Point pt = curr.point;
			if (pt.x == lastPoint.x && pt.y == lastPoint.y) {

				printPaths(paths, maze);
				printRightPath(rightPath, maze);

				return curr.dist;
			}

			queue.remove();

			for (int i = 0; i < 4; i++) {
				int row = pt.x + ROW_MAZE[i];
				int col = pt.y + COLUM_MAZE[i];
				
				if (checkPoint(row, col)) {

					// bring the value of point
					Integer pos = (Integer) maze[row][col];

					if (pos == 1 && !visited[row][col]) {
						visited[row][col] = true;

						QueueNode neighbor = new QueueNode(new Point(row, col), curr.dist + 1);

						// create a new route based on the previous rout and add the new nighb.
						List<QueueNode> partly = new ArrayList<>(rightPath);
						partly.add(neighbor);
						queue.add(partly);
					}
				}
			}
		}
		return -1;
	}

	@Override
	public void printPaths(List<List<QueueNode>> paths, T[][] maze) {
		
		String[][] board = new String[mazeSize][mazeSize];

		for (int i = 0; i < mazeSize; i++) {
			for (int j = 0; j < mazeSize; j++) {
				board[i][j] = String.valueOf(maze[i][j]);
			}
		}

		sb = new StringBuilder();

		for (List<QueueNode> partpath : paths) {
			for (QueueNode node : partpath) {
				int x = node.point.x;
				int y = node.point.y;
				board[x][y] = "*";
			}
		}
		for (String[] row : board) {
			sb.append(Arrays.toString(row) + "\n");
		}
		System.out.println(sb.toString());

	}

	@Override
	public void printRightPath(List<QueueNode> path, T[][] maze) {

		String[][] board = new String[mazeSize][mazeSize];

		for (int i = 0; i < mazeSize; i++) {
			for (int j = 0; j < mazeSize; j++) {
				board[i][j] = String.valueOf(maze[i][j]);
			}
		}

		sb = new StringBuilder();
		for (QueueNode node : path) {
			int x = node.point.x;
			int y = node.point.y;
			board[x][y] = "*";
		}
		for (String[] row : board) {
			sb.append(Arrays.toString(row) + "\n");
		}
		System.out.println(sb.toString());
	}

	@Override
	public boolean checkPoint(int row, int col) {
		return (row >= 0) && (row < mazeSize) && (col >= 0) && (col < mazeSize);
	}

}