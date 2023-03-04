package com.searchai.towerssearchai;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Algorithms {

	private final static int goal = 9;
	private static final int[][] fullMap = { { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0, 0, 1, 0 },
			{ 1, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1, 1 }, { 0, 1, 0, 0, 0, 0, 1, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0 } };

	// Basiclally algorithm searches the matrix startes from first row representing
	// the first state and in that it finds the 1
	// and gets their position as in col number and uses the col number of the last
	// 1 that it chose from first row to get the new row that it will
	// operate on and adds that last 1 to a path list so that if it comes again to
	// it it skips it and takes another element
	public static ArrayList<Integer> DepthSearch(int startAt) {
		ArrayList<Integer> path = new ArrayList<>();
		
		if (startAt >= 1 && startAt <= 9) {

			if (startAt == goal) {
				path.add(startAt);
				return path;
			} else {
				startAt -= 1;
			}
			while (!path.contains(goal)) {
				Stack<Integer> temp_knowLast1_inArray = new Stack<>();
				// int j = 0;
				for (int i = 0; i < 9; i++) { // here you meant 9 as in size of array not goal so let's not confuse it I
												// wrote it back as "9"
					if (fullMap[startAt][i] == 1) {
						temp_knowLast1_inArray.push(i + 1);
						// System.out.println(temp_knowLast1_inArray.get(j));
						// j++;

					}
				}
				System.out.println("\n");

				if (path.size() > 0) {

					int newStartPoint = temp_knowLast1_inArray.pop();
					if (!path.contains(newStartPoint)) {
						if (!path.contains(newStartPoint)) {
							path.add(newStartPoint);
						}
						startAt = newStartPoint - 1;
					} else {
						int newStartPointV2 = temp_knowLast1_inArray.pop();
						if (!path.contains(newStartPointV2)) {
							path.add(newStartPointV2);
						}
						startAt = newStartPointV2 - 1;
					}
				} else {
					int newStartPoint = temp_knowLast1_inArray.pop();
					path.add(startAt + 1);// The starting point
					path.add(newStartPoint);
					startAt = newStartPoint - 1;
				}
			}

		} else {

		}
		return path;
	}

	public static ArrayList<Integer> BreadthFirst(int startAt) {
		ArrayList<Integer> path = new ArrayList<>();
		int head;
		if (startAt <= 0 || startAt >= 10) { // the better way to do it :)
			return path; // so the path is returned empty, we can use path.isEmpty() and if its true it prints an error
		}

		if (startAt == goal) { // if the goal is where we are starting than just add it to the path and return it
			path.add(startAt);
			return path;
		} else {
			startAt -= 1; // otherwise start at the start location -1 because the map is a 2D array.
		}
		
		Queue<Integer> queue = new ArrayDeque<>(); // we will use ArrayDeque because it implements the FIFO.
		queue.add(startAt + 1); // add the first element to the queue.

		while (!path.contains(goal)) { // now we iterate.
			head = queue.remove(); // dequeue the first element from the queue.
			if (path.contains(head)) {
				continue;
			} // if it's in the path then just skip ( no need to get its successors ).
			path.add(head); // otherwise add it to the path.
			
			for (int i = 0; i < 9; i++) { // and get it's successors.
				if (fullMap[head - 1][i] == 1)
					queue.add(i + 1);
			}
		}
		return path; // now return the path.
	}

}
