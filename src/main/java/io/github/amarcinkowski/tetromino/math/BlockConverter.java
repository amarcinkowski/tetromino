package io.github.amarcinkowski.tetromino.math;

import io.github.amarcinkowski.tetromino.algorithm.Block;
import io.github.amarcinkowski.tetromino.algorithm.BlockBuilder;
import io.github.amarcinkowski.tetromino.algorithm.BlockDirection;

public class BlockConverter {

	private static final int _Y = 6;
	private static final int _2Y = 12;
	private static final int _XY = 36;

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
			int xyz[] = CoordinateConverter.vector2XYZ(array[i]);
			result[i] = CoordinateConverter.xyz2N(xyz);
		}
		return result;
	}

	private static boolean horizontal(int[] n4) {
		return n4[0] + 1 == n4[1] && n4[0] + 2 == n4[2];
	}

	public static Block n4ToBlock(int[] n4) {
		BlockDirection direction = getDirection(n4);
		int xyz[] = CoordinateConverter.n2XYZ(n4[0]);
		return new BlockBuilder().xyz(xyz).type(direction).build();
	}

	private static BlockDirection getDirection(int[] n4) {
		BlockDirection direction = null;
		if (n4[0] + 1 == n4[1] && n4[0] + 2 == n4[2]) {
			if (n4[1] == n4[3] - 36) {
				return BlockDirection.HORIZONTAL_UP;
			}
			if (n4[1] == n4[3] - 6) {
				return BlockDirection.HORIZONTAL_SOUTH;
			}
		}
		if ((n4[1] + 1 == n4[2] && n4[1] + 2 == n4[3])) {
			if (n4[0] == n4[2] - 36) {
				return BlockDirection.HORIZONTAL_DOWN;
			}
			if (n4[0] == n4[2] - 6) {
				return BlockDirection.HORIZONTAL_NORTH;
			}
		}
		if (n4[0] + 6 == n4[1] && n4[0] + 12 == n4[2]) {
			if (n4[1] == n4[3] - 36) {
				return BlockDirection.VERTICAL_UP;
			}
		}
		if (n4[1] + 6 == n4[2] && n4[1] + 12 == n4[3]) {
			if (n4[0] == n4[2] - 36) {
				return BlockDirection.VERTICAL_DOWN;
			}
		}
		if (n4[0] + 6 == n4[2] && n4[0] + 12 == n4[3]) {
			if (n4[1] == n4[2] - 1) {
				return BlockDirection.VERTICAL_WEST;
			}
		}
		if (n4[0] + 6 == n4[1] && n4[0] + 12 == n4[3]) {
			if (n4[1] == n4[2] - 1) {
				return BlockDirection.VERTICAL_EAST;
			}
		}
		return direction;
	}

}
