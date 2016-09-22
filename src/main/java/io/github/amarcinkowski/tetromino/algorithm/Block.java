package io.github.amarcinkowski.tetromino.algorithm;

import java.util.Vector;

public class Block {

	public final static int BLOCK_VOLUME = 4;

	public static Vector<Integer[]>[] cubeVector = CubeVolume
			.getAllPossibileBlocks();

	public static Integer[] getBlock(int number, int space) {
		return (Integer[]) cubeVector[space].get(number);
	}

	public static Vector<Integer[]> getAllPossibilities(int space) {
		return cubeVector[space];
	}

	public static int getPossibilitiesCount(int space) {
		return (int) cubeVector[space].size();
	}


}
