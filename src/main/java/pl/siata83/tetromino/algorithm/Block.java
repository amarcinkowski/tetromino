/*
 * Main.java
 *
 * Created on 27 grudzie� 2007, 18:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pl.siata83.tetromino.algorithm;

import java.util.Vector;

import pl.siata83.tetromino.math.Transformations3D;

/**
 * 
 * @author siatan
 */
public class Block {

	public static Vector[] cubeVector = Transformations3D
			.getAllPossibileBlocks();

	public Block() {
	}

	public static int[] getBlock(int number, int space) {
		return (int[]) cubeVector[space].get(number);
	}

	public static Vector getAllPossibilities(int space) {
		return cubeVector[space];
	}

	public static int getPossibilitiesCount(int space) {
		return (int) cubeVector[space].size();
	}

}
