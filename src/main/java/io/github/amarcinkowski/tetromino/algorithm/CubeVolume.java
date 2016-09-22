package io.github.amarcinkowski.tetromino.algorithm;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.tetromino.math.Conversion;
import io.github.amarcinkowski.tetromino.math.Transformations3D;

public class CubeVolume {

	private static Logger logger = LoggerFactory.getLogger(CubeVolume.class);

	public final static int SIZE_X = 6;
	public final static int SIZE_Y = 6;
	public final static int SIZE_Z = 2;

	public final static int VOLUME = SIZE_X * SIZE_Y * SIZE_Z;

	/** CubeVolume inserted block count */
	private int blockcount = 0;
	private boolean empty[] = new boolean[VOLUME];
	private int filled[] = new int[VOLUME];
	
	int insertTries = 0;
	private int possibilities[] = new int[VOLUME];
	int removeTries = 0;

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
			possibilities[x] = possibileInserts(x);
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

	public int getBlockCount() {
		return blockcount;
	}

	public int[] getFilled() {
		return filled;
	}

	// int na byte
	public boolean insert(int block[]) {

		insertTries++;
		if (!insertPossibile(block)) {
			return false;
		}

		setBlockCount(getBlockCount() + 1);
		for (int i = 0; i < 4; i++) {
			empty[block[i]] = false;
			filled[block[i]] = getBlockCount();
		}
		return true;
	}

	public boolean insertPossibile(int block[]) {
		for (int i = 0; i < 4; i++)
			if (!isEmpty(block[i]))
				return false;
		return true;
	}

	public boolean isEmpty(int n) {
		int dim[] = Conversion.n2XYZ(n);
		return isEmpty(dim[0], dim[1], dim[2]);
	}

	public boolean isEmpty(int x, int y, int z) {
		if (exists(x, y, z) && empty[Conversion.xyz2N(x, y, z)])
			return true;
		else
			return false;
	}

	public byte possibileInserts(int space) {
		byte count = 0;
		Vector<int[]> v = Block.getAllPossibilities(space);
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
		if (!isEmpty(space))
			return result;

		Vector<int[]> v = Block.getAllPossibilities(space);
		for (int i = 0; i < v.size(); i++) {
			int block[] = v.get(i);
			if (insertPossibile(block))
				result.add(i);
		}
		return result;
	}

	public boolean remove(int block[]) {

		removeTries++;
		if (!removePossibile(block))
			return false;

		setBlockCount(getBlockCount() - 1);
		for (int i = 0; i < 4; i++) {
			empty[block[i]] = true;
			filled[block[i]] = 0;
		}
		return true;
	}

	public boolean removePossibile(int block[]) {
		for (int i = 0; i < 4; i++)
			if (isEmpty(block[i]))
				return false;
		return true;
	}

	public void setBlockCount(int blockcount) {
		this.blockcount = blockcount;
	}

	/**
	 * Gets the all possibile blocks.
	 * 
	 * @return the all possibile blocks
	 */
	@SuppressWarnings("unchecked")
	public static Vector<int[]>[] getAllPossibileBlocks() {
		Vector<int[]>[] cubeVector = new Vector[VOLUME];
		for (int k = 0; k < VOLUME; k++) {
			cubeVector[k] = new Vector<int[]>();
			for (int i = 1; i <= Transformations3D.MAX_BLOCK_TYPE; i++) {
				int block[] = Transformations3D.block(i, k);
				if (Transformations3D.isInsertionPossibile(block)) {
					cubeVector[k].add(block);
				}
			}
		}
		return cubeVector;
	}

	/** Creates a new instance of CubeVolume */
	public CubeVolume() {
		for (int x = 0; x < VOLUME; x++) {
			empty[x] = true;
			filled[x] = 0;
		}
		for (int x = 0; x < VOLUME; x++)
			possibilities[x] = possibileInserts(x);
	}

}