package com.searchai.towerssearchai;
import java.util.ArrayList;
import java.util.Stack;
public class Algorithms {

	private final static int goal = 9;
	private static final int[][] fullMap = {{0, 1, 1, 0, 0, 0, 0, 0, 0}, {1, 0, 1, 0, 0, 0, 0, 1, 0}, {1, 1, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 0, 0},
			{0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1, 1}, {0, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 0}};

	//Basiclally algorithm searches the matrix startes from first row representing the first state and in that it finds the 1
	//and gets their position as in col number and uses the col number of the last 1 that it chose from first row to get the new row that it will
	//operate on and adds that last 1 to a path list so that if it comes again to it it skips it and takes another element
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
				//int j = 0;
				for (int i = 0; i < goal; i++) {
					if (fullMap[startAt][i] == 1) {
						temp_knowLast1_inArray.push(i + 1);
						//System.out.println(temp_knowLast1_inArray.get(j));
						//j++;

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
					path.add(startAt + 1);//The starting point
					path.add(newStartPoint);
					startAt = newStartPoint - 1;
				}
			}

		} else {

		}
		return path;
	}

}
