/*
 * CubeVolume.java
 *
 * Created on 27 grudzie� 2007, 19:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.siata83.tetromino.algorithm;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.siata83.tetromino.math.Conversion;

/**
 * 
 * @author siatan
 */
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

	public static boolean exists(int x, int y, int z) {
		if (x >= 0 && x < SIZE_X && y >= 0 && y < SIZE_Y && z >= 0
				&& z < SIZE_Z) {
			return true;
		} else {
			return false;
		}
	}

	private void countPossiCube() {
		for (int x = 0; x < VOLUME; x++)
			possibilities[x] = possibileInserts(x);
	}

	public int factor() {
		countPossiCube();
		int factor = 1;
		for (int i = 0; i < VOLUME; i++) {
			if (isEmpty(i)) {
				factor += possibilities[i];
				if (possibilities[i] == 0)
					return 0;
			}
			logger.trace("factor (" + i + "/" + VOLUME + ") = " + factor);
		}
		return factor;
	}

	public int getBlockcount() {
		return blockcount;
	}

	public int[] getFilled() {
		return filled;
	}

	// int na byte
	public boolean insert(int block[]) {

		insertTries++;
		if (!insertPossibile(block))
			return false;

		setBlockcount(getBlockcount() + 1);
		for (int i = 0; i < 4; i++) {
			empty[block[i]] = false;
			filled[block[i]] = getBlockcount();
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
		int dim[] = Conversion.convertNtoXYZ(n);
		return isEmpty(dim[0], dim[1], dim[2]);
	}

	public boolean isEmpty(int x, int y, int z) {
		if (exists(x, y, z) && empty[Conversion.convertXYZtoN(x, y, z)])
			return true;
		else
			return false;
	}

	public byte possibileInserts(int space) {
		byte count = 0;
		Vector v = Block.getAllPossibilities(space);
		if (!isEmpty(space)) {
			return -1;
		}
		for (int i = 0; i < v.size(); i++) {
			int[] block = (int[]) v.get(i);
			if (insertPossibile(block))
				count++;
		}
		return count;
	}

	public Vector<Integer> possibileInsertsVector(int space) {
		Vector<Integer> result = new Vector<Integer>();
		if (!isEmpty(space))
			return result;

		Vector v = Block.getAllPossibilities(space);
		for (int i = 0; i < v.size(); i++) {
			int block[] = (int[]) v.get(i);
			if (insertPossibile(block))
				result.add(i);
		}
		return result;
	}

	public boolean remove(int block[]) {

		removeTries++;
		if (!removePossibile(block))
			return false;

		setBlockcount(getBlockcount() - 1);
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

	public void setBlockcount(int blockcount) {
		this.blockcount = blockcount;
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