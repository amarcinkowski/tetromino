package io.github.amarcinkowski.tetromino.algorithm;

import java.util.Arrays;

/**
 * The Class Conversion.
 */

public enum BlockDirection {
	HORIZONTAL_UP, HORIZONTAL_NORTH, HORIZONTAL_DOWN, HORIZONTAL_SOUTH, VERTICAL_UP, VERTICAL_EAST, VERTICAL_DOWN, VERTICAL_WEST,;

	@Override
	public String toString() {
		return name().toLowerCase().replace("_", "-");
	}

	private static boolean horizontal(int n1, int n2, int n3) {
		return n1 + 1 == n2 && n1 + 2 == n3;
	}

	private static boolean vertical(int n1, int n2, int n3) {
		return n1 + CubeVolume.SIZE_X == n2 && n1 + CubeVolume.SIZE_X * 2 == n3;
	}

	private static boolean beside(int n1, int n2) {
		return n1 == n2 - 1;
	}

	private static boolean under(int n1, int n2) {
		return n1 == n2 - CubeVolume.SIZE_X * CubeVolume.SIZE_Y;
	}

	private static boolean on(int n1, int n2) {
		return n1 == n2 - CubeVolume.SIZE_X;
	}

	public static BlockDirection getDirection(int[] n4) {
		Arrays.sort(n4);
		BlockDirection direction = null;
		if (horizontal(n4[0], n4[1], n4[2])) {
			if (under(n4[1], n4[3])) {
				return BlockDirection.HORIZONTAL_UP;
			}
			if (on(n4[1], n4[3])) {
				return BlockDirection.HORIZONTAL_SOUTH;
			}
		}
		if (horizontal(n4[1], n4[2], n4[3])) {
			if (under(n4[0], n4[2])) {
				return BlockDirection.HORIZONTAL_DOWN;
			}
			if (on(n4[0], n4[2])) {
				return BlockDirection.HORIZONTAL_NORTH;
			}
		}
		if (vertical(n4[0], n4[1], n4[2])) {
			if (under(n4[1], n4[3])) {
				return BlockDirection.VERTICAL_UP;
			}
		}
		if (vertical(n4[1], n4[2], n4[3])) {
			if (under(n4[0], n4[2])) {
				return BlockDirection.VERTICAL_DOWN;
			}
		}
		if (vertical(n4[0], n4[2], n4[3])) {
			if (beside(n4[1], n4[2])) {
				return BlockDirection.VERTICAL_WEST;
			}
		}
		if (vertical(n4[0], n4[1], n4[3])) {
			if (beside(n4[1], n4[2])) {
				return BlockDirection.VERTICAL_EAST;
			}
		}
		return direction;
	}
}