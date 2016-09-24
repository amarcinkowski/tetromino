package io.github.amarcinkowski.tetromino.math;

import io.github.amarcinkowski.tetromino.algorithm.Block;
import io.github.amarcinkowski.tetromino.algorithm.BlockBuilder;
import io.github.amarcinkowski.tetromino.algorithm.BlockDirection;

public class BlockConverter {

	/**
	 * Convert xyz to n.
	 * 
	 * Eg. [0,0,0]
	 * 
	 * @param array
	 *            the array
	 * @return n is index of field numbered by rows (x), then columns (y) and
	 *         then levels (z)
	 */
	public static int[] vectorToNBlock(double[][] array) {
		int result[] = { -1, -1, -1, -1 };
		for (int i = 0; i < 4; i++) {
			int x[] = new int[3];
			for (int j = 0; j < 3; j++) {
				x[j] = (int) Math.round(array[i][j]);
			}
			result[i] = CoordinateConverter.xyz2N(x[0], x[1], x[2]);
		}
		return result;
	}

	public static Block n4ToBlock(int[] n4) {
		BlockDirection type = null;
		if (n4[0] + 1 == n4[1] && n4[0] + 2 == n4[2]) {
			if (n4[1] == n4[3] - 36) {
				type = BlockDirection.HORIZONTAL_UP;
			}
			if (n4[1] == n4[3] - 6) {
				type = BlockDirection.HORIZONTAL_SOUTH;
			}
		}
		if ((n4[1] + 1 == n4[2] && n4[1] + 2 == n4[3])) {
			if (n4[0] == n4[2] - 36) {
				type = BlockDirection.HORIZONTAL_DOWN;
			}
			if (n4[0] == n4[2] - 6) {
				type = BlockDirection.HORIZONTAL_NORTH;
			}
		}
		if (n4[0] + 6 == n4[1] && n4[0] + 12 == n4[2]) {
			if (n4[1] == n4[3] - 36) {
				type = BlockDirection.VERTICAL_UP;
			}
		}
		if (n4[1] + 6 == n4[2] && n4[1] + 12 == n4[3]) {
			if (n4[0] == n4[2] - 36) {
				type = BlockDirection.VERTICAL_DOWN;
			}
		}
		if (n4[0] + 6 == n4[2] && n4[0] + 12 == n4[3]) {
			if (n4[1] == n4[2] - 1) {
				type = BlockDirection.VERTICAL_WEST;
			}
		}
		if (n4[0] + 6 == n4[1] && n4[0] + 12 == n4[3]) {
			if (n4[1] == n4[2] - 1) {
				type = BlockDirection.VERTICAL_EAST;
			}
		}
		int xyz[] = CoordinateConverter.n2XYZ(n4[0]);
		return new BlockBuilder(). xyz(xyz).type(type).build();
	}

}
