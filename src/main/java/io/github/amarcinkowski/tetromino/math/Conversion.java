package io.github.amarcinkowski.tetromino.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.amarcinkowski.tetromino.algorithm.Block;
import io.github.amarcinkowski.tetromino.algorithm.BlockBuilder;
import io.github.amarcinkowski.tetromino.algorithm.BlockType;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;

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
	static int[] vectorToNBlock(double[][] array) {
		int result[] = { -1, -1, -1, -1 };
		for (int i = 0; i < 4; i++) {
			int x[] = new int[3];
			for (int j = 0; j < 3; j++) {
				x[j] = (int) Math.round(array[i][j]);
			}
			result[i] = xyz2N(x[0], x[1], x[2]);
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
	public static int xyz2N(int x, int y, int z) {
		if (CubeVolume.exists(x, y, z)) {
			return x + y * CubeVolume.SIZE_X + z * CubeVolume.SIZE_X * CubeVolume.SIZE_Y;
		} else {
			return -1;
		}
	}

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

	public static Block n2Block(int[] n) {
		BlockType type = null;
		if (n[0] + 1 == n[1] && n[0] + 2 == n[2]) {
			if (n[1] == n[3] - 36) {
				type = BlockType.HORIZONTAL_UP;
			}
			if (n[1] == n[3] - 6) {
				type = BlockType.HORIZONTAL_SOUTH;
			}
		}
		if ((n[1] + 1 == n[2] && n[1] + 2 == n[3])) {
			if (n[0] == n[2] - 36) {
				type = BlockType.HORIZONTAL_DOWN;
			}
			if (n[0] == n[2] - 6) {
				type = BlockType.HORIZONTAL_NORTH;
			}
		}
		if (n[0] + 6 == n[1] && n[0] + 12 == n[2]) {
			if (n[1] == n[3] - 36) {
				type = BlockType.VERTICAL_UP;
			}
		}
		if (n[1] + 6 == n[2] && n[1] + 12 == n[3]) {
			if (n[0] == n[2] - 36) {
				type = BlockType.VERTICAL_DOWN;
			}
		}
		if (n[0] + 6 == n[2] && n[0] + 12 == n[3]) {
			if (n[1] == n[2] - 1) {
				type = BlockType.VERTICAL_WEST;
			}
		}
		if (n[0] + 6 == n[1] && n[0] + 12 == n[3]) {
			if (n[1] == n[2] - 1) {
				type = BlockType.VERTICAL_EAST;
			}
		}
		int xyz[] = Conversion.n2XYZ(n[0]);
		return new BlockBuilder().xyz(xyz).type(type).build();
	}

	public static List<Block> cvToBlockList(int n[]) {
		List<Block> blocks = new ArrayList<>();
		Multimap<Integer, Integer> map = ArrayListMultimap.create();
		for (int i = 0; i < n.length; i++) {
			map.put(n[i], i);
		}
		for (Integer key : map.keySet()) {
			ArrayList<Integer> v = new ArrayList<>(map.get(key));
			Collections.sort(v);
			// XXX TODO xXXXXXX XXX n4
//			Block block = new BlockBuilder().n4(v.toArray()).build();
			int n4[] = v.stream().mapToInt(Integer::intValue).toArray();
			blocks.add(n2Block(n4));
		}
		return blocks;
	}

}
