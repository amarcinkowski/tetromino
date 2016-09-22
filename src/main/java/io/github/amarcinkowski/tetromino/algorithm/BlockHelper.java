package io.github.amarcinkowski.tetromino.algorithm;

import java.util.Vector;

public class BlockHelper {

	public final static int BLOCK_VOLUME = 4;

	public static Vector<int[]>[] cubeVector = CubeVolume
			.getAllPossibileBlocks();

	public static int[] getBlock(int number, int space) {
		return cubeVector[space].get(number);
	}

	public static Vector<int[]> getAllPossibilities(int space) {
		return cubeVector[space];
	}

	public static int getPossibilitiesCount(int space) {
		return (int) cubeVector[space].size();
	}


}
