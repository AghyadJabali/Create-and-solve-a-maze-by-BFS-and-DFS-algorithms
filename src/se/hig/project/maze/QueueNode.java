package se.hig.project.maze;

import java.awt.Point;

/**
 * @author aghyad jabali The class will find the point position and the distance
 *         of from the first point
 */

public class QueueNode {
	Point point;
	int dist;

	public QueueNode(Point point, int dist) {
		this.point = point;
		this.dist = dist;
	}
};