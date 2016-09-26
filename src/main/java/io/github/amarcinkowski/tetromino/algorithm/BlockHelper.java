package io.github.amarcinkowski.tetromino.algorithm;

import java.util.Vector;

import io.github.amarcinkowski.tetromino.math.BlockConverter;
import io.github.amarcinkowski.tetromino.math.CoordinateConverter;
import io.github.amarcinkowski.tetromino.math.Transform3D;

public class BlockHelper {

	public final static int BLOCK_VOLUME = 4;
	public static final int MAX_BLOCK_TYPE = 32;

	@SuppressWarnings("unchecked")
	private static Vector<int[]>[] cubeVector = new Vector[CubeVolume.VOLUME_SIZE];

	static {
		for (int k = 0; k < CubeVolume.VOLUME_SIZE; k++) {
			cubeVector[k] = new Vector<int[]>();
			for (int i = 1; i <= BlockHelper.MAX_BLOCK_TYPE; i++) {
				int block[] = BlockHelper.block(i, k);
				if (CubeVolume.isInsertionPossibile(block)) {
					cubeVector[k].add(block);
				}
			}
		}
	}

	public static int[] getBlock(int number, int space) {
		return cubeVector[space].get(number);
	}

	public static Vector<int[]> getAllPossibilities(int space) {
		return cubeVector[space];
	}

	// TODO switcha zmienic na 2 fory (po 4 obroty w 2 plaszczyznach x 4
	// pozycje)
	/**
	 * Block type.
	 * 
	 * @param type
	 *            the type
	 * @param shift
	 *            the shift
	 * @return the double[][]
	 * @throws Exception
	 *             the exception
	 */

	private static double[][] type2Vector(int type) {
		double[][] a, b, c;
		double array[][] = null;
		a = Transform3D.BLOCK_3D_VERTICES;

		switch (type) {
		// UP
		case 1: // 0001.svg
			array = Transform3D.translate(0, 0, 0, a);
			break; // 0,1,2,37
		case 2: // --
			array = Transform3D.translate(-1, 0, 0, a);
			break;
		case 3: // --
			array = Transform3D.translate(-2, 0, 0, a);
			break;
		case 4: // --
			b = Transform3D.rotate('z', -1, a);
			array = Transform3D.translate(0, 0, 0, b);
			break;
		case 5: // --
			b = Transform3D.rotate('z', -1, a);
			array = Transform3D.translate(0, -1, 0, b);
			break;
		case 6: // 005.svg
			b = Transform3D.rotate('z', -1, a);
			array = Transform3D.translate(0, -2, 0, b);
			break;
		case 7: // --
			array = Transform3D.translate(-1, 0, -1, a);
			break;
		case 8: // --
			b = Transform3D.rotate('z', -1, a);
			array = Transform3D.translate(0, -1, -1, b);
			break;
		// DOWN
		case 9: // --
			b = Transform3D.rotate('x', 2, a);
			array = Transform3D.translate(0, 0, 0, b);
			break;
		case 10: // --
			b = Transform3D.rotate('x', 2, a);
			array = Transform3D.translate(-1, 0, 0, b);
			break;
		case 11: // --
			b = Transform3D.rotate('x', 2, a);
			array = Transform3D.translate(-2, 0, 0, b);
			break;
		case 12: // 0003.svg
			b = Transform3D.rotate('x', 2, a);
			array = Transform3D.translate(-1, 0, 1, b);
			break;
		case 13: // --
			b = Transform3D.rotate('x', 2, a);
			c = Transform3D.translate(0, 0, 0, b);
			array = Transform3D.rotate('z', -1, c);
			break;
		case 14: // --
			b = Transform3D.rotate('x', 2, a);
			c = Transform3D.translate(-1, 0, 0, b);
			array = Transform3D.rotate('z', -1, c);
			break;
		case 15: // --
			b = Transform3D.rotate('x', 2, a);
			c = Transform3D.translate(-2, 0, 0, b);
			array = Transform3D.rotate('z', -1, c);
			break;
		case 16: // 0007.svg
			b = Transform3D.rotate('x', 2, a);
			c = Transform3D.translate(-1, 0, 1, b);
			array = Transform3D.rotate('z', -1, c);
			break;
		// EAST
		case 17:
			b = Transform3D.rotate('x', 1, a);
			array = Transform3D.rotate('z', 1, b);
			break;
		case 18:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 1, b);
			array = Transform3D.translate(0, 1, 0, c);
			break;
		case 19:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 1, b);
			array = Transform3D.translate(0, 2, 0, c);
			break;
		case 20:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 1, b);
			array = Transform3D.translate(-1, 1, 0, c);
			break;
		// NORTH
		case 21:
			b = Transform3D.rotate('x', 1, a);
			array = Transform3D.rotate('z', 2, b);
			break;
		case 22:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 2, b);
			array = Transform3D.translate(1, 0, 0, c);
			break;
		case 23:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 2, b);
			array = Transform3D.translate(2, 0, 0, c);
			break;
		case 24:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 2, b);
			array = Transform3D.translate(1, 1, 0, c);
			break;
		// WEST
		case 25:
			b = Transform3D.rotate('x', 1, a);
			array = Transform3D.rotate('z', 3, b);
			break;
		case 26:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 3, b);
			array = Transform3D.translate(0, -1, 0, c);
			break;
		case 27:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 3, b);
			array = Transform3D.translate(0, -2, 0, c);
			break;
		case 28:
			b = Transform3D.rotate('x', 1, a);
			c = Transform3D.rotate('z', 3, b);
			array = Transform3D.translate(1, -1, 0, c);
			break;
		// SOUTH
		case 29:
			array = Transform3D.rotate('x', 1, a);
			break;
		case 30:
			b = Transform3D.rotate('x', 1, a);
			array = Transform3D.translate(-1, 0, 0, b);
			break;
		case 31:
			b = Transform3D.rotate('x', 1, a);
			array = Transform3D.translate(-2, 0, 0, b);
			break;
		case 32:
			b = Transform3D.rotate('x', 1, a);
			array = Transform3D.translate(-1, -1, 0, b);
			break;
		}
		return array;
	}

	private static double[][] typeShift2Vector(int type, int shift) throws Exception {
		if (type < 1 && type > BlockHelper.MAX_BLOCK_TYPE) {
			throw new Exception("No such block type.");
		}
		double[][] array = type2Vector(type);
		array = Transform3D.translate(CoordinateConverter.n2XYZ(shift), array);
		return array;
	}

	/**
	 * Block.
	 * 
	 * @param type
	 *            the type
	 * @param shift
	 *            the shift
	 * @return the int[]
	 */
	private static int[] block(int type, int shift) {
		double[][] array = null;
		try {
			array = typeShift2Vector(type, shift);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BlockConverter.vectorToNBlock(array);
	}

}
