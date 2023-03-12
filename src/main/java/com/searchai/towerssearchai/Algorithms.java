package com.searchai.towerssearchai;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Algorithms {

	// Basiclally algorithm searches the matrix startes from first row representing
	// the first state and in that it finds the 1
	// and gets their position as in col number and uses the col number of the last
	// 1 that it chose from first row to get the new row that it will
	// operate on and adds that last 1 to a path list so that if it comes again to
	// it it skips it and takes another element
	private static final int[][] fullMap = { { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0, 0, 1, 0 },
			{ 1, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1, 1 }, { 0, 1, 0, 0, 0, 0, 1, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0 } };

	/*
	 * For thing u were saying for A*, I was doing this for best first but seems way
	 * it happened currently is simpler
	 */
	/*
	 * private static final int[][] fullMap_informed = { { 0, 3, 4, 0, 0, 0, 0, 0, 0
	 * }, { 3, 0, 3, 0, 0, 0, 0, 2, 0 }, { 4, 3, 0, 0, 0, 4, 0, 0, 0 }, { 0, 0, 0,
	 * 0, 3, 3, 2, 0, 0 }, { 0, 0, 0, 3, 0, 4, 0, 0, 0 }, { 0, 0, 4, 3, 4, 0, 0, 0,
	 * 0 }, { 0, 0, 0, 2, 0, 0, 0, 2, 1 }, { 0, 2, 0, 0, 0, 0, 2, 0, 1 }, { 0, 0, 0,
	 * 0, 0, 0, 1, 1, 0 } };
	 */

	private static final int totalStates = 9;

	// Uninformed searches
	public static ArrayList<Integer> DepthFirst(int startAt, int goal) {
		ArrayList<Integer> path = new ArrayList<>();

		if (startAt == goal) {
			path.add(startAt);
			return path;
		} else {
			// Adds starting point then removes a value from it for the array fullMap to
			// work
			path.add(startAt);
			startAt -= 1;
		}
		Stack<Integer> stateChildren = new Stack<>();
		while (!path.contains(goal)) {

			// If children in path it skips adding them
			for (int i = 0; i < totalStates; i++) {
				if (fullMap[startAt][i] == 1) {
					if (!path.contains(i + 1)) {
						stateChildren.push(i + 1);
					}
				}
			}

			// Adds newwest starting point
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

		// This is stateChildren
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(startAt + 1);

		while (!path.contains(goal)) {
			queueHead = queue.remove();
			if (path.contains(queueHead)) {
				continue;
			}
			path.add(queueHead);

			for (int i = 0; i < totalStates; i++) {
				if (fullMap[queueHead - 1][i] == 1)
					queue.add(i + 1);
			}
		}
		return path; // now return the path.
	}

	// Informed searches
	public static ArrayList<Integer> BestFirst(int startAt, int goal) {

		// From state 1 to 9 these are h functions
		int[] heuristics = new int[9];// {3, 2, 3, 2, 3, 3, 1, 1, 0};

		switch (goal) {
		case 1 -> { // if goal is node 1
			heuristics[0] = 0;
			heuristics[1] = 1;
			heuristics[2] = 1;
			heuristics[3] = 3;
			heuristics[4] = 3;
			heuristics[5] = 2;
			heuristics[6] = 3;
			heuristics[7] = 2;
			heuristics[8] = 3;
		}
		case 2 -> {
			heuristics[0] = 1;
			heuristics[1] = 0;
			heuristics[2] = 1;
			heuristics[3] = 3;
			heuristics[4] = 3;
			heuristics[5] = 2;
			heuristics[6] = 2;
			heuristics[7] = 1;
			heuristics[8] = 2;
		}
		case 3 -> {
			heuristics[0] = 1;
			heuristics[1] = 1;
			heuristics[2] = 0;
			heuristics[3] = 2;
			heuristics[4] = 2;
			heuristics[5] = 1;
			heuristics[6] = 3;
			heuristics[7] = 2;
			heuristics[8] = 3;
		}
		case 4 -> {
			heuristics[0] = 3;
			heuristics[1] = 3;
			heuristics[2] = 2;
			heuristics[3] = 0;
			heuristics[4] = 1;
			heuristics[5] = 1;
			heuristics[6] = 7;
			heuristics[7] = 2;
			heuristics[8] = 2;
		}
		case 5 -> {
			heuristics[0] = 3;
			heuristics[1] = 3;
			heuristics[2] = 2;
			heuristics[3] = 1;
			heuristics[4] = 0;
			heuristics[5] = 1;
			heuristics[6] = 2;
			heuristics[7] = 3;
			heuristics[8] = 3;
		}
		case 6 -> {
			heuristics[0] = 2;
			heuristics[1] = 2;
			heuristics[2] = 1;
			heuristics[3] = 1;
			heuristics[4] = 1;
			heuristics[5] = 0;
			heuristics[6] = 2;
			heuristics[7] = 3;
			heuristics[8] = 3;
		}
		case 7 -> {
			heuristics[0] = 3;
			heuristics[1] = 2;
			heuristics[2] = 3;
			heuristics[3] = 1;
			heuristics[4] = 2;
			heuristics[5] = 2;
			heuristics[6] = 0;
			heuristics[7] = 1;
			heuristics[8] = 1;
		}
		case 8 -> {
			heuristics[0] = 2;
			heuristics[1] = 1;
			heuristics[2] = 2;
			heuristics[3] = 2;
			heuristics[4] = 3;
			heuristics[5] = 3;
			heuristics[6] = 1;
			heuristics[7] = 0;
			heuristics[8] = 1;
		}
		case 9 -> {
			heuristics[0] = 3;
			heuristics[1] = 2;
			heuristics[2] = 3;
			heuristics[3] = 2;
			heuristics[4] = 3;
			heuristics[5] = 3;
			heuristics[6] = 1;
			heuristics[7] = 1;
			heuristics[8] = 0;
		}
		}

		ArrayList<Integer> path = new ArrayList<>();
		/*
		 * In this line of code, integer is a parameter name that represents each
		 * element of the PriorityQueue. Specifically, stateChildren is a priority queue
		 * that stores Integer objects, and the Comparator.comparingInt() method is used
		 * to specify that the priority of each element should be based on the
		 * corresponding heuristic value.
		 */

		PriorityQueue<Integer> stateChildren = new PriorityQueue<>(
				Comparator.comparingInt(integer -> heuristics[integer - 1]));

		if (startAt == goal) {
			path.add(startAt);
			return path;
		} else {
			// Adds starting point then removes a value from it for the array fullMap to
			// work
			path.add(startAt);
			startAt -= 1;
		}

		while (!path.contains(goal)) {
			stateChildren.clear();
			// If children in path it skips adding them
			for (int i = 0; i < totalStates; i++) {
				if (fullMap[startAt][i] == 1) {
					if (!path.contains(i + 1)) {
						stateChildren.add(i + 1);
					}
				}
			}

			// Adds newwest starting point
			int newStartPoint = stateChildren.poll();
			path.add(newStartPoint);
			startAt = newStartPoint - 1;

		}
		return path;

	}

	public static ArrayList<Integer> Astar(int startAt, int goal) {
		// From state 1 to 9 these are h functions
		// these are from node 1 to goal
		int[] heuristics = new int[9]; // {3, 2, 3, 2, 3, 3, 1, 1, 0};

		switch (goal) {
		case 1 -> { // if goal is node 1
			heuristics[0] = 0;
			heuristics[1] = 1;
			heuristics[2] = 1;
			heuristics[3] = 3;
			heuristics[4] = 3;
			heuristics[5] = 2;
			heuristics[6] = 3;
			heuristics[7] = 2;
			heuristics[8] = 3;
		}
		case 2 -> {
			heuristics[0] = 1;
			heuristics[1] = 0;
			heuristics[2] = 1;
			heuristics[3] = 3;
			heuristics[4] = 3;
			heuristics[5] = 2;
			heuristics[6] = 2;
			heuristics[7] = 1;
			heuristics[8] = 2;
		}
		case 3 -> {
			heuristics[0] = 1;
			heuristics[1] = 1;
			heuristics[2] = 0;
			heuristics[3] = 2;
			heuristics[4] = 2;
			heuristics[5] = 1;
			heuristics[6] = 3;
			heuristics[7] = 2;
			heuristics[8] = 3;
		}
		case 4 -> {
			heuristics[0] = 3;
			heuristics[1] = 3;
			heuristics[2] = 2;
			heuristics[3] = 0;
			heuristics[4] = 1;
			heuristics[5] = 1;
			heuristics[6] = 7;
			heuristics[7] = 2;
			heuristics[8] = 2;
		}
		case 5 -> {
			heuristics[0] = 3;
			heuristics[1] = 3;
			heuristics[2] = 2;
			heuristics[3] = 1;
			heuristics[4] = 0;
			heuristics[5] = 1;
			heuristics[6] = 2;
			heuristics[7] = 3;
			heuristics[8] = 3;
		}
		case 6 -> {
			heuristics[0] = 2;
			heuristics[1] = 2;
			heuristics[2] = 1;
			heuristics[3] = 1;
			heuristics[4] = 1;
			heuristics[5] = 0;
			heuristics[6] = 2;
			heuristics[7] = 3;
			heuristics[8] = 3;
		}
		case 7 -> {
			heuristics[0] = 3;
			heuristics[1] = 2;
			heuristics[2] = 3;
			heuristics[3] = 1;
			heuristics[4] = 2;
			heuristics[5] = 2;
			heuristics[6] = 0;
			heuristics[7] = 1;
			heuristics[8] = 1;
		}
		case 8 -> {
			heuristics[0] = 2;
			heuristics[1] = 1;
			heuristics[2] = 2;
			heuristics[3] = 2;
			heuristics[4] = 3;
			heuristics[5] = 3;
			heuristics[6] = 1;
			heuristics[7] = 0;
			heuristics[8] = 1;
		}
		case 9 -> {
			heuristics[0] = 3;
			heuristics[1] = 2;
			heuristics[2] = 3;
			heuristics[3] = 2;
			heuristics[4] = 3;
			heuristics[5] = 3;
			heuristics[6] = 1;
			heuristics[7] = 1;
			heuristics[8] = 0;
		}
		}

		// From state 1 to 9 these are the g costs for each node

		int[] gCost = new int[9];
		switch (startAt) {
		case 1 -> { // if starting node is 1
			gCost[0] = 0;
			gCost[1] = 1;
			gCost[2] = 1;
			gCost[3] = 3;
			gCost[4] = 3;
			gCost[5] = 2;
			gCost[6] = 3;
			gCost[7] = 2;
			gCost[8] = 3;
		}
		case 2 -> {
			gCost[0] = 1;
			gCost[1] = 0;
			gCost[2] = 1;
			gCost[3] = 3;
			gCost[4] = 3;
			gCost[5] = 2;
			gCost[6] = 2;
			gCost[7] = 1;
			gCost[8] = 2;
		}
		case 3 -> {
			gCost[0] = 1;
			gCost[1] = 1;
			gCost[2] = 0;
			gCost[3] = 2;
			gCost[4] = 2;
			gCost[5] = 1;
			gCost[6] = 3;
			gCost[7] = 2;
			gCost[8] = 3;
		}
		case 4 -> {
			gCost[0] = 3;
			gCost[1] = 3;
			gCost[2] = 2;
			gCost[3] = 0;
			gCost[4] = 1;
			gCost[5] = 1;
			gCost[6] = 7;
			gCost[7] = 2;
			gCost[8] = 2;
		}
		case 5 -> {
			gCost[0] = 3;
			gCost[1] = 3;
			gCost[2] = 2;
			gCost[3] = 1;
			gCost[4] = 0;
			gCost[5] = 1;
			gCost[6] = 2;
			gCost[7] = 3;
			gCost[8] = 3;
		}
		case 6 -> {
			gCost[0] = 2;
			gCost[1] = 2;
			gCost[2] = 1;
			gCost[3] = 1;
			gCost[4] = 1;
			gCost[5] = 0;
			gCost[6] = 2;
			gCost[7] = 3;
			gCost[8] = 3;
		}
		case 7 -> {
			gCost[0] = 3;
			gCost[1] = 2;
			gCost[2] = 3;
			gCost[3] = 1;
			gCost[4] = 2;
			gCost[5] = 2;
			gCost[6] = 0;
			gCost[7] = 1;
			gCost[8] = 1;
		}
		case 8 -> {
			gCost[0] = 2;
			gCost[1] = 1;
			gCost[2] = 2;
			gCost[3] = 2;
			gCost[4] = 3;
			gCost[5] = 3;
			gCost[6] = 1;
			gCost[7] = 0;
			gCost[8] = 1;
		}
		case 9 -> {
			gCost[0] = 3;
			gCost[1] = 2;
			gCost[2] = 3;
			gCost[3] = 2;
			gCost[4] = 3;
			gCost[5] = 3;
			gCost[6] = 1;
			gCost[7] = 1;
			gCost[8] = 0;
		}
		}

		int stateChildrenHead;

		ArrayList<Integer> path = new ArrayList<>();

		if (startAt == goal) {
			path.add(startAt);
			return path;
		} else {
			startAt -= 1;
		}

		// System.out.println(gCost[1]+" "+gCost[2]);
		PriorityQueue<Integer> stateChildren = new PriorityQueue<>(
				Comparator.comparingInt(integer -> (heuristics[integer - 1] + gCost[integer - 1])));

		stateChildren.add(startAt + 1);

		while (!path.contains(goal)) {

			System.out.println("path: " + path.toString());
			System.out.println("state: " + stateChildren.toString() + "\n");

			stateChildrenHead = stateChildren.poll();
			if (path.contains(stateChildrenHead)) {
				continue;
			}
			path.add(stateChildrenHead);

			stateChildren.clear();

			for (int i = 0; i < totalStates; i++) {
				if (fullMap[stateChildrenHead - 1][i] == 1)
					stateChildren.add(i + 1);
			}

		}
		return path; // now return the path.
	}

}
