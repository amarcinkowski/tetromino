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
			int xyz[] = CoordinateConverter.vector2XYZ(array[i]);
			result[i] = CoordinateConverter.xyz2N(xyz);
		}
		return result;
	}

	public static Block n4ToBlock(int[] n4) {
		BlockDirection direction = BlockDirection.getDirection(n4);
		int xyz[] = CoordinateConverter.n2XYZ(n4[0]);
		return new BlockBuilder().xyz(xyz).type(direction).build();
	}

}
