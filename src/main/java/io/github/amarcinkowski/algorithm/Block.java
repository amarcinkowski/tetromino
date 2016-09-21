/*
 * Main.java
 *
 * Created on 27 grudzie� 2007, 18:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package io.github.amarcinkowski.algorithm;

import java.util.Vector;

import io.github.amarcinkowski.tetromino.math.Transformations3D;

/**
 * 
 * @author siatan
 */
public class Block {

	public static Vector<Integer[]>[] cubeVector = Transformations3D
			.getAllPossibileBlocks();

	public Block() {
	}

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
