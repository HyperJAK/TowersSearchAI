package com.searchai.towerssearchai;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Algorithms {

	private static final int[][] fullMap = {{0, 1, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 1, 0, 0, 0, 0, 1, 0}, {1, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0},
			{0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1, 1}, {0, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 0}};

	// Basiclally algorithm searches the matrix startes from first row representing
	// the first state and in that it finds the 1
	// and gets their position as in col number and uses the col number of the last
	// 1 that it chose from first row to get the new row that it will
	// operate on and adds that last 1 to a path list so that if it comes again to
	// it it skips it and takes another element
	public static ArrayList<Integer> DepthSearch(int startAt, int goal) {
		ArrayList<Integer> path = new ArrayList<>();

		if (startAt == goal) {
			path.add(startAt);
			return path;
		} else {
			//Adds starting point then removes a value from it for the array fullMap to work
			path.add(startAt);
			startAt -= 1;
		}
		while (!path.contains(goal)) {
			Stack<Integer> stateChildren = new Stack<>();

			//If children in path it skips adding them
			for (int i = 0; i < 9; i++) {
				if (fullMap[startAt][i] == 1) {
					if (!path.contains(i + 1)) {
						stateChildren.push(i + 1);
					}
				}
			}

			//Adds newwest starting point
			int newStartPoint = stateChildren.pop();
			path.add(newStartPoint);
			startAt = newStartPoint - 1;

		}
		return path;

	}

	public static ArrayList<Integer> BreadthFirst(int startAt, int goal) {
		ArrayList<Integer> path = new ArrayList<>();
		int queueHead;

		if (startAt == goal) {
			path.add(startAt);
			return path;
		} else {
			startAt -= 1;
		}

		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(startAt + 1);

		while (!path.contains(goal)) {
			queueHead = queue.remove();
			if (path.contains(queueHead)) {
				continue;
			}
			path.add(queueHead);

			for (int i = 0; i < 9; i++) {
				if (fullMap[queueHead - 1][i] == 1)
					queue.add(i + 1);
			}
		}
		return path; // now return the path.
	}

}



