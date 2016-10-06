package io.github.amarcinkowski.tetromino.math;

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

}
