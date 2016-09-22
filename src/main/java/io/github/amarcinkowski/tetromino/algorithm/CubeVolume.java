package io.github.amarcinkowski.tetromino.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.amarcinkowski.tetromino.math.Conversion;

public class CubeVolume {

	private static Logger logger = LoggerFactory.getLogger(CubeVolume.class);

	public final static int SIZE_X = 6;
	public final static int SIZE_Y = 6;
	public final static int SIZE_Z = 2;

	public final static int VOLUME = SIZE_X * SIZE_Y * SIZE_Z;

	private int blockcount = 0;
	private int filled[] = new int[VOLUME];

	private int possibilities[] = new int[VOLUME];

	public final static int MAX_BLOCK = VOLUME / BlockHelper.BLOCK_VOLUME;

	static int cubeVolumePointer = 0;

	public static boolean exists(int x, int y, int z) {
		if (x >= 0 && x < SIZE_X && y >= 0 && y < SIZE_Y && z >= 0 && z < SIZE_Z) {
			return true;
		} else {
			return false;
		}
	}

	public int factor() {

		for (int x = 0; x < VOLUME; x++) {
			possibilities[x] = possibileInsertsCount(x);
		}

		int factor = 1;
		for (int i = 0; i < VOLUME; i++) {
			if (isEmpty(i)) {
				factor += possibilities[i];
				if (possibilities[i] == 0) {
					return 0;
				}
			}
			logger.trace(String.format("factor (%d/%d)=%d", i, VOLUME, factor));
		}
		return factor;
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

	public boolean isEmpty(int n) {
		int dim[] = Conversion.n2XYZ(n);
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

	public Vector<Integer> possibileInsertsVector(int space) {
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
		for (int i = 0; i < filled.length; i++) {
			map.put(filled[i], i);
		}
		for (Integer key : map.keySet()) {
			ArrayList<Integer> v = new ArrayList<>(map.get(key));
			Collections.sort(v);
			int n4[] = v.stream().mapToInt(Integer::intValue).toArray();
			Block block = new BlockBuilder().n4(n4).build();
			blocks.add(block);
		}
		return blocks;
	}
	
	public int getBlockCount() {
		return blockcount;
	}

	/** Creates a new instance of CubeVolume */
	public CubeVolume() {
		cubeVolumePointer = 0;
		for (int x = 0; x < VOLUME; x++) {
			filled[x] = 0;
		}
		for (int x = 0; x < VOLUME; x++)
			possibilities[x] = possibileInsertsCount(x);
	}

	public boolean isSolution() {
		return blockcount == CubeVolume.MAX_BLOCK;
	}

}