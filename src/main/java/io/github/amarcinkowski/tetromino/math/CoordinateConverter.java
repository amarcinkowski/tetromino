package io.github.amarcinkowski.tetromino.math;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;

public class CoordinateConverter {

	/**
	 * Convert nto xyz.
	 * 
	 * @param number
	 *            the number
	 * @return the int[]
	 */
	public static int[] n2XYZ(int number) {
		if (number < 0) {
			throw new NumberIsTooSmallException(number, 0, true);
		}
		int xyz[] = new int[3];
		xyz[0] = number % CubeVolume.SIZE_X;
		xyz[1] = number % (CubeVolume.SIZE_X * CubeVolume.SIZE_Y) / CubeVolume.SIZE_X;
		xyz[2] = number / (CubeVolume.SIZE_X * CubeVolume.SIZE_Y);
		return xyz;
	}

	public static int xyz2N(int x, int y, int z) {
		return xyz2N(new int[] { x, y, z });
	}

	/**
	 * Field number.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @return the int
	 */
	public static int xyz2N(int[] xyz) {
		int x = xyz[0];
		int y = xyz[1];
		int z = xyz[2];
		if (CubeVolume.exists(x, y, z)) {
			return x + y * CubeVolume.SIZE_X + z * CubeVolume.SIZE_X * CubeVolume.SIZE_Y;
		} else {
			return -1;
		}
	}

	public static int[] vector2XYZ(double[] vector) {
		int[] x = new int[3];
		for (int j = 0; j < 3; j++) {
			x[j] = (int) Math.round(vector[j]);
		}
		return x;
	}

	public static double[] xyz2ScreenCoordinates(int x, int y, int z) {
		return new double[] { (6 * x - y * 2.95), (y * 2.65 + x * 1.25 - 5.9 * z) };
	}

}
