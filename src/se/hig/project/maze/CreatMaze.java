package se.hig.project.maze;

import java.awt.Point;
import java.util.List;

public interface CreatMaze<T> {

	public T[][] creatTheMaze();

	public String getTheMaze();

	public boolean checkNextPoint(Point point);

	public void addNeighborsRandom(List<Point> point);

	public List<Point> findNeighbors(Point point);

	public boolean pointInMaze(int x, int y);

	public boolean pointNotCorner(Point point, int x, int y);

	public boolean notSamePoint(Point point, int x, int y);

}