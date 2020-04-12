package se.hig.project.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class CreatMazeClass<T> implements CreatMaze<T> {

	private static final int ROW_MAZE[] = { -1, 0, 0, 1 };
	private static final int COLUM_MAZE[] = { 0, -1, 1, 0 };
	private Stack<Point> stack = new Stack<>();
	private Random rand = new Random();
	private Integer[][] maze;
	private StringBuilder sb;
	private int dimension;

	CreatMazeClass(int dim) {
		
		maze = new Integer[dim][dim];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				maze[i][j] = 0;
			}
		}
		dimension = dim;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[][] creatTheMaze() {
		// TODO Auto-generated method stub
		stack.push(new Point(0, 0));
		while (!stack.empty()) {
			Point next = stack.pop();
			if (checkNextPoint(next)) {
				
				maze[next.x][next.y] = 1;
				List<Point> neighbors = findNeighbors(next);
				addNeighborsRandom(neighbors);
			}
		}
		return (T[][]) maze;
	}

	@Override
	public String getTheMaze() {
		// TODO Auto-generated method stub

		sb = new StringBuilder();
		for (Integer[] row : maze) {
			sb.append(Arrays.toString(row) + "\n");
		}
		return sb.toString();
	}

	@Override
	public boolean checkNextPoint(Point point) {
		// TODO Auto-generated method stub
		int numNeighbor = 0;
		for (int i = 0; i < 4; i++) {
			int row = point.x + ROW_MAZE[i];
			int col = point.y + COLUM_MAZE[i];
			
			if (pointInMaze(row, col) && notSamePoint(point, row, col) && maze[row][col] == 1) {
				numNeighbor++;
			}
		}
		return (numNeighbor < 3) && maze[point.x][point.y] != 1;
	}

	@Override
	public void addNeighborsRandom(List<Point> point) {
		// TODO Auto-generated method stub
		int pointIndex;

		while (!point.isEmpty()) {
			pointIndex = rand.nextInt(point.size());
			stack.push(point.remove(pointIndex));
		}
	}

	@Override
	public List<Point> findNeighbors(Point pt) {
		// TODO Auto-generated method stub
		List<Point> neighbors = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			int row = pt.x + ROW_MAZE[i];
			int col = pt.y + COLUM_MAZE[i];
			if (pointInMaze(row, col) && pointNotCorner(pt, row, col) && notSamePoint(pt, row, col)) {
				neighbors.add(new Point(row, col));
			}
		}
		return neighbors;
	}

	@Override
	public boolean pointInMaze(int x, int y) {
		// TODO Auto-generated method stub
		
		return (x >= 0 && y >= 0 && x < dimension && y < dimension);
	}

	@Override
	public boolean pointNotCorner(Point point, int x, int y) {
		// TODO Auto-generated method stub

		return (x == point.x || y == point.y);
	}

	@Override
	public boolean notSamePoint(Point point, int x, int y) {
		// TODO Auto-generated method stub
		return !(x == point.x && y == point.y);
	}
}