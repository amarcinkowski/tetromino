package pl.siata83.tetromino.math;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import pl.siata83.tetromino.algorithm.CubeVolume;

/**
 * The Class Conversion.
 */
public class Conversion {

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
	static Integer[] convertXYZtoN(double[][] array) {
		Integer ret[] = { -1, -1, -1, -1 };
		for (int i = 0; i < 4; i++) {
			int x[] = new int[3];
			for (int j = 0; j < 3; j++) {
				x[j] = (int) Math.round(array[i][j]);
			}
			ret[i] = convertXYZtoN(x[0], x[1], x[2]);
		}
		return ret;
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
	public static int convertXYZtoN(int x, int y, int z) {
		if (CubeVolume.exists(x, y, z))
			return x + y * CubeVolume.SIZE_X + z * CubeVolume.SIZE_X
					* CubeVolume.SIZE_Y;
		else
			return -1;
	}

	/**
	 * Convert nto xyz.
	 * 
	 * @param number
	 *            the number
	 * @return the int[]
	 */
	public static int[] convertNtoXYZ(int number) {
		int xyz[] = new int[3];
		xyz[0] = number % CubeVolume.SIZE_X;
		xyz[1] = number % (CubeVolume.SIZE_X * CubeVolume.SIZE_Y)
				/ CubeVolume.SIZE_X;
		xyz[2] = number / (CubeVolume.SIZE_X * CubeVolume.SIZE_Y);
		return xyz;
	}

	private static HashMap<Integer, Vector<Integer>> map;

	private static void addToMap(int index, int blockNumber) {
		Vector<Integer> v = map.get(index);
		if (v == null) {
			v = new Vector<Integer>();
		}
		v.add(blockNumber);
		map.put(index, v);
	}

	public static String convertFilledTo2D(int n[]) {
		map = new HashMap<Integer, Vector<Integer>>();
		String s = " ";
		for (int i = 0; i < n.length; i++) {
			addToMap(n[i], i);
		}
		for (Integer key : map.keySet()) {
			Vector<Integer> v = map.get(key);
			Collections.sort(v);
			s += " " + Arrays.toString(Conversion.convertNtoXYZ(v.get(0))) ;
			if (v.get(0) + 1 == v.get(1) && v.get(0) + 2 == v.get(2)) {
				if (v.get(1) == v.get(3) - 36) {
					s += "horizU";
				}
				if (v.get(1) == v.get(3) - 6) {
					s += "horizS";
				}
			}
			if ((v.get(1) + 1 == v.get(2) && v.get(1) + 2 == v.get(3))) {
				if (v.get(0) == v.get(2) - 36) {
					s += "horizD";
				}
				if (v.get(0) == v.get(2) - 6) {
					s += "horizN";
				}
			}
			if (v.get(0) + 6 == v.get(1) && v.get(0) + 12 == v.get(2)) {
				if (v.get(1) == v.get(3) - 36) {
					s += "vertU";
				}
			}
			if (v.get(1) + 6 == v.get(2) && v.get(1) + 12 == v.get(3)) {
				if (v.get(0) == v.get(2) - 36) {
					s += "vertD";
				}
			}
			if (v.get(0) + 6 == v.get(2) && v.get(0) + 12 == v.get(3)) {
				if (v.get(1) == v.get(2) - 1) {
					s += "vertW";
				}
			}
			if (v.get(0) + 6 == v.get(1) && v.get(0) + 12 == v.get(3)) {
				if (v.get(1) == v.get(2) - 1) {
					s += "vertE";
				}
			}
			s += ";";
		}
		return s;
	}
}
