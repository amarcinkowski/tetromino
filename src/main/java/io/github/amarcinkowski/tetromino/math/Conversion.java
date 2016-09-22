package io.github.amarcinkowski.tetromino.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

import io.github.amarcinkowski.tetromino.algorithm.BlockType;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;
import io.github.amarcinkowski.tetromino.algorithm.XYZTBlock;

public class Conversion {

	private static HashMap<Integer, Vector<Integer>> map;

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
	static int[] convertXYZtoN(double[][] array) {
		int result[] = { -1, -1, -1, -1 };
		for (int i = 0; i < 4; i++) {
			int x[] = new int[3];
			for (int j = 0; j < 3; j++) {
				x[j] = (int) Math.round(array[i][j]);
			}
			result[i] = convertXYZtoN(x[0], x[1], x[2]);
		}
		return result;
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
		if (number < 0) {
			throw new NumberIsTooSmallException(number, 0, true);
		}
		int xyz[] = new int[3];
		xyz[0] = number % CubeVolume.SIZE_X;
		xyz[1] = number % (CubeVolume.SIZE_X * CubeVolume.SIZE_Y)
				/ CubeVolume.SIZE_X;
		xyz[2] = number / (CubeVolume.SIZE_X * CubeVolume.SIZE_Y);
		return xyz;
	}

	private static void addToMap(int index, int blockNumber) {
		Vector<Integer> v = map.get(index);
		if (v == null) {
			v = new Vector<Integer>();
		}
		v.add(blockNumber);
		map.put(index, v);
	}
	
	private boolean isOneBlock() {
		return false;
	}
	
	public static List<XYZTBlock> convertFilledToBlocks(int n[]) {
		List<XYZTBlock> xyztBlocks = new ArrayList<>(); 
		map = new HashMap<Integer, Vector<Integer>>();
		for (int i = 0; i < n.length; i++) {
			addToMap(n[i], i);
		}
		for (Integer key : map.keySet()) {
			Vector<Integer> v = map.get(key);
			Collections.sort(v);
			int xyz[] = Conversion.convertNtoXYZ(v.get(0));
			BlockType type = null;
			if (v.get(0) + 1 == v.get(1) && v.get(0) + 2 == v.get(2)) {
				if (v.get(1) == v.get(3) - 36) {
					type = BlockType.HORIZONTAL_UP;
				}
				if (v.get(1) == v.get(3) - 6) {
					type = BlockType.HORIZONTAL_SOUTH;
				}
			}
			if ((v.get(1) + 1 == v.get(2) && v.get(1) + 2 == v.get(3))) {
				if (v.get(0) == v.get(2) - 36) {
					type = BlockType.HORIZONTAL_DOWN;
				}
				if (v.get(0) == v.get(2) - 6) {
					type = BlockType.HORIZONTAL_NORTH;
				}
			}
			if (v.get(0) + 6 == v.get(1) && v.get(0) + 12 == v.get(2)) {
				if (v.get(1) == v.get(3) - 36) {
					type = BlockType.VERTICAL_UP;
				}
			}
			if (v.get(1) + 6 == v.get(2) && v.get(1) + 12 == v.get(3)) {
				if (v.get(0) == v.get(2) - 36) {
					type = BlockType.VERTICAL_DOWN;
				}
			}
			if (v.get(0) + 6 == v.get(2) && v.get(0) + 12 == v.get(3)) {
				if (v.get(1) == v.get(2) - 1) {
					type = BlockType.VERTICAL_WEST;
				}
			}
			if (v.get(0) + 6 == v.get(1) && v.get(0) + 12 == v.get(3)) {
				if (v.get(1) == v.get(2) - 1) {
					type = BlockType.VERTICAL_EAST;
				}
			}
			xyztBlocks.add(new XYZTBlock(xyz, type));
		}
		return xyztBlocks;
	}
	
	
}
