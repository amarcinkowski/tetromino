package io.github.amarcinkowski.tetromino.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.amarcinkowski.tetromino.math.CoordinateConverter;

public class CubeVolume {

	public final static int SIZE_X = 6;
	public final static int SIZE_Y = 6;
	public final static int SIZE_Z = 2;

	public final static int VOLUME_SIZE = SIZE_X * SIZE_Y * SIZE_Z;
	public final static int MAX_BLOCK = VOLUME_SIZE / BlockHelper.BLOCK_VOLUME;

	protected int blockcount = 0;
	protected int cubeVolumePointer = 0;

	protected int filled[] = new int[VOLUME_SIZE];

	public static boolean exists(int x, int y, int z) {
		if (x >= 0 && x < SIZE_X && y >= 0 && y < SIZE_Y && z >= 0 && z < SIZE_Z) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasEmptySpaceThatCannotBeFilledWithABlock() {

		for (int n = 0; n < VOLUME_SIZE; n++) {
			if (isEmpty(n) && possibileInsertsCount(n) == 0) {
				return true;
			}
		}
		return false;
	}

	public int[] getFilled() {
		return filled;
	}

	// int na byte
	public boolean insert(int block[]) {

		if (!insertPossibile(block)) {
			return false;
		}

		blockcount++;
		for (int i = 0; i < 4; i++) {
			filled[block[i]] = blockcount;
		}
		return true;
	}

	public boolean insertPossibile(int block[]) {
		for (int i = 0; i < 4; i++) {
			if (!isEmpty(block[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return blockcount == 0;
	}

	public boolean isEmpty(int n) {
		int dim[] = CoordinateConverter.n2XYZ(n);
		return exists(dim[0], dim[1], dim[2]) && filled[n] == 0;
	}

	public byte possibileInsertsCount(int space) {
		byte count = 0;
		Vector<int[]> v = BlockHelper.getAllPossibilities(space);
		if (!isEmpty(space)) {
			return -1;
		}
		for (int i = 0; i < v.size(); i++) {
			int[] block = v.get(i);
			if (insertPossibile(block))
				count++;
		}
		return count;
	}

	public Vector<Integer> possibileInserts(int space) {
		Vector<Integer> result = new Vector<Integer>();
		if (!isEmpty(space)) {
			return result;
		}

		Vector<int[]> v = BlockHelper.getAllPossibilities(space);
		for (int i = 0; i < v.size(); i++) {
			int block[] = v.get(i);
			if (insertPossibile(block)) {
				result.add(i);
			}
		}
		return result;
	}

	public boolean remove(int block[]) {

		if (!removePossibile(block)) {
			return false;
		}

		blockcount--;
		for (int i = 0; i < 4; i++) {
			filled[block[i]] = 0;
		}
		return true;
	}

	public boolean removePossibile(int block[]) {
		for (int i = 0; i < 4; i++) {
			if (isEmpty(block[i])) {
				return false;
			}
		}
		return true;
	}

	public List<Block> getBlockList() {
		List<Block> blocks = new ArrayList<>();
		Multimap<Integer, Integer> map = ArrayListMultimap.create();
		for (int index = 0; index < filled.length; index++) {
			int blockNumber = filled[index];
			if (blockNumber > 0) {
				map.put(blockNumber, index);
			}
		}
		for (Integer key : map.keySet()) {
			Block block = new BlockBuilder().collection(map.get(key)).build();
			blocks.add(block);
		}
		return blocks;
	}

	public int getBlockCount() {
		return blockcount;
	}

	/** Creates a new instance of CubeVolume */
	private CubeVolume() {
	}

	public static CubeVolume newInstance() {
		return new CubeVolume();
	}

	public boolean isSolution() {
		return blockcount == CubeVolume.MAX_BLOCK;
	}

	public void findNextEmptySpace() {
		while (!isEmpty(cubeVolumePointer)) {
			cubeVolumePointer++;
		}
	}

	/**
	 * Possibile.
	 * 
	 * @param block
	 *            the block
	 * @return true, if successful
	 */
	static boolean isInsertionPossibile(int[] block) {
		for (int i = 0; i < 4; i++) {
			if (block[i] == -1) {
				return false;
			}
		}
		return true;
	}

}