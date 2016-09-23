package io.github.amarcinkowski.tetromino.algorithm;

import java.util.Vector;

import io.github.amarcinkowski.tetromino.math.BlockConverter;
import io.github.amarcinkowski.tetromino.math.Transformations3D;

public class BlockHelper {

	public final static int BLOCK_VOLUME = 4;
	public static final int MAX_BLOCK_TYPE = 32;

	@SuppressWarnings("unchecked")
	private static Vector<int[]>[] cubeVector = new Vector[CubeVolume.VOLUME];

	static {
		for (int k = 0; k < CubeVolume.VOLUME; k++) {
			cubeVector[k] = new Vector<int[]>();
			for (int i = 1; i <= BlockHelper.MAX_BLOCK_TYPE; i++) {
				int block[] = BlockHelper.block(i, k);
				if (BlockHelper.isInsertionPossibile(block)) {
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

	public static int getPossibilitiesCount(int space) {
		return (int) cubeVector[space].size();
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
	private static double[][] blockType(int type, int shift) throws Exception {
		double[][] a, b, c;
		double array[][] = null;
		a = Transformations3D.BLOCK_3D_VERTICES;
		if (type < 1 && type > BlockHelper.MAX_BLOCK_TYPE) {
			throw new Exception("No such block type.");
		}

		switch (type) {
		// UP
		case 1:
			array = Transformations3D.translate(0, 0, 0, a);
			break; // 0,1,2,37
		case 2:
			array = Transformations3D.translate(-1, 0, 0, a);
			break;
		case 3:
			array = Transformations3D.translate(-2, 0, 0, a);
			break;
		case 4:
			b = Transformations3D.rotate('z', -1, a);
			array = Transformations3D.translate(0, 0, 0, b);
			break;
		case 5:
			b = Transformations3D.rotate('z', -1, a);
			array = Transformations3D.translate(0, -1, 0, b);
			break;
		case 6:
			b = Transformations3D.rotate('z', -1, a);
			array = Transformations3D.translate(0, -2, 0, b);
			break;
		case 7:
			array = Transformations3D.translate(-1, 0, -1, a);
			break;
		case 8:
			b = Transformations3D.rotate('z', -1, a);
			array = Transformations3D.translate(0, -1, -1, b);
			break;
		// DOWN
		case 9:
			b = Transformations3D.rotate('x', 2, a);
			array = Transformations3D.translate(0, 0, 0, b);
			break;
		case 10:
			b = Transformations3D.rotate('x', 2, a);
			array = Transformations3D.translate(-1, 0, 0, b);
			break;
		case 11:
			b = Transformations3D.rotate('x', 2, a);
			array = Transformations3D.translate(-2, 0, 0, b);
			break;
		case 12:
			b = Transformations3D.rotate('x', 2, a);
			array = Transformations3D.translate(-1, 0, 1, b);
			break;
		case 13:
			b = Transformations3D.rotate('x', 2, a);
			c = Transformations3D.translate(0, 0, 0, b);
			array = Transformations3D.rotate('z', -1, c);
			break;
		case 14:
			b = Transformations3D.rotate('x', 2, a);
			c = Transformations3D.translate(-1, 0, 0, b);
			array = Transformations3D.rotate('z', -1, c);
			break;
		case 15:
			b = Transformations3D.rotate('x', 2, a);
			c = Transformations3D.translate(-2, 0, 0, b);
			array = Transformations3D.rotate('z', -1, c);
			break;
		case 16:
			b = Transformations3D.rotate('x', 2, a);
			c = Transformations3D.translate(-1, 0, 1, b);
			array = Transformations3D.rotate('z', -1, c);
			break;
		// EAST
		case 17:
			b = Transformations3D.rotate('x', 1, a);
			array = Transformations3D.rotate('z', 1, b);
			break;
		case 18:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 1, b);
			array = Transformations3D.translate(0, 1, 0, c);
			break;
		case 19:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 1, b);
			array = Transformations3D.translate(0, 2, 0, c);
			break;
		case 20:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 1, b);
			array = Transformations3D.translate(-1, 1, 0, c);
			break;
		// NORTH
		case 21:
			b = Transformations3D.rotate('x', 1, a);
			array = Transformations3D.rotate('z', 2, b);
			break;
		case 22:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 2, b);
			array = Transformations3D.translate(1, 0, 0, c);
			break;
		case 23:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 2, b);
			array = Transformations3D.translate(2, 0, 0, c);
			break;
		case 24:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 2, b);
			array = Transformations3D.translate(1, 1, 0, c);
			break;
		// WEST
		case 25:
			b = Transformations3D.rotate('x', 1, a);
			array = Transformations3D.rotate('z', 3, b);
			break;
		case 26:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 3, b);
			array = Transformations3D.translate(0, -1, 0, c);
			break;
		case 27:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 3, b);
			array = Transformations3D.translate(0, -2, 0, c);
			break;
		case 28:
			b = Transformations3D.rotate('x', 1, a);
			c = Transformations3D.rotate('z', 3, b);
			array = Transformations3D.translate(1, -1, 0, c);
			break;
		// SOUTH
		case 29:
			array = Transformations3D.rotate('x', 1, a);
			break;
		case 30:
			b = Transformations3D.rotate('x', 1, a);
			array = Transformations3D.translate(-1, 0, 0, b);
			break;
		case 31:
			b = Transformations3D.rotate('x', 1, a);
			array = Transformations3D.translate(-2, 0, 0, b);
			break;
		case 32:
			b = Transformations3D.rotate('x', 1, a);
			array = Transformations3D.translate(-1, -1, 0, b);
			break;
		}
		array = Transformations3D.translate(BlockConverter.n2XYZ(shift), array);
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
			array = blockType(type, shift);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BlockConverter.vectorToNBlock(array);
	}

	/**
	 * Possibile.
	 * 
	 * @param block
	 *            the block
	 * @return true, if successful
	 */
	private static boolean isInsertionPossibile(int[] block) {
		for (int i = 0; i < 4; i++) {
			if (block[i] == -1) {
				return false;
			}
		}
		return true;
	}


}
