package se.hig.project.maze;

import java.awt.Point;
import java.util.List;

public interface SolveTheMaze<T> {

	public boolean checkPoint(int x, int y);
	
	public int BFSCorrectPath(T[][] t, Point src, Point dest);

	public void printPaths(List<List<QueueNode>> paths, T[][] t);

	public void printRightPath(List<QueueNode> path, T[][] t);
}
