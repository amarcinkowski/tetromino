/*
 * Transformations3D.java
 *
 * Created on 30 grudzie� 2007, 15:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package io.github.amarcinkowski.tetromino.math;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Transformations3D.
 * 
 * @author siatan
 */
public class Transformations3D {

	/** The logger. */
	public static Logger logger = LoggerFactory.getLogger(Transformations3D.class);

	private final static double[][] BLOCK_3D_VERTICES = { { 0.0, 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0, 1.0 },
			{ 2.0, 0.0, 0.0, 1.0 }, { 1.0, 0.0, 1.0, 1.0 } };

	/** The c. */
	private static double[][] a, b, c;

	/** The Constant MAX_BLOCK_TYPE. */
	public static final int MAX_BLOCK_TYPE = 32;

	/**
	 * Matrix multiply.
	 * 
	 * @param transformation
	 *            the transformation
	 * @param transformed
	 *            the transformed
	 * @return the double[][]
	 */
	private static double[][] matrixMultiply(double[][] transformation, double[][] transformed) {
		double[][] arraysB = transformation;
		double[][] arraysC = new double[4][4];

		// Create three instances of Matrix

		RealMatrix matrixA = MatrixUtils.createRealMatrix(transformed);
		RealMatrix matrixB = MatrixUtils.createRealMatrix(arraysB);
		RealMatrix matrixC = MatrixUtils.createRealMatrix(4, 4);

		// Multiplication C =A.B
		matrixC = matrixA.multiply(matrixB);

		// Get result of multriplication
		arraysC = matrixC.getData();
		return arraysC;
	}

	/**
	 * translate block by x,y,z dimension[0],dimension[1], dimension[2].
	 * 
	 * @param dimension
	 *            the dimension
	 * @param transformed
	 *            the transformed
	 * @return the double[][]
	 */
	private static double[][] translate(int[] dimension, double[][] transformed) {
		if (dimension.length != 3) {
			throw new NumberFormatException("Should be 3 dimensions");
		}
		return translate(dimension[0], dimension[1], dimension[2], transformed);
	}

	/**
	 * translate block by x,y,z dimension[0],dimension[1], dimension[2].
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @param transformed
	 *            the transformed
	 * @return the double[][]
	 */
	private static double[][] translate(int x, int y, int z, double[][] transformed) {
		// Trans
		double[][] trans = { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { x, y, z, 1.0 } };
		double[][] array = matrixMultiply(trans, transformed);
		return array;
	}

	/**
	 * rotate block in q (x,y,z) Axis x90 * PI/2 times.
	 * 
	 * @param q
	 *            the q
	 * @param x90
	 *            the x90
	 * @param rotated
	 *            the rotated
	 * @return the double[][]
	 */
	private static double[][] rotate(char q, int x90, double[][] rotated) {
		double[][] array = null;
		double alfa = Math.PI / 2 * x90;
		switch (q) {
		case 'x': // Rot X
			double[][] rotx = { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, Math.cos(alfa), -Math.sin(alfa), 0.0 },
					{ 0.0, Math.sin(alfa), Math.cos(alfa), 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
			array = matrixMultiply(rotx, rotated);
			break;
		case 'y':
			// Rot Y
			double[][] roty = { { Math.cos(alfa), 0.0, Math.sin(alfa), 0.0 }, { 0.0, 1.0, 0.0, 0.0 },
					{ -Math.sin(alfa), 0.0, Math.cos(alfa), 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
			array = matrixMultiply(roty, rotated);
			break;
		case 'z':
			// Rot Z
			double[][] rotz = { { Math.cos(alfa), -Math.sin(alfa), 0.0, 0.0 },
					{ Math.sin(alfa), Math.cos(alfa), 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
			array = matrixMultiply(rotz, rotated);
			break;
		}
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
	public static Integer[] block(int type, int shift) {
		double[][] array = null;
		try {
			array = blockType(type, shift);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Conversion.convertXYZtoN(array);
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
		double array[][] = null;
		a = BLOCK_3D_VERTICES;
		if (type < 1 && type > MAX_BLOCK_TYPE)
			throw new Exception("No such block type.");

		switch (type) {
		// UP
		case 1:
			array = translate(0, 0, 0, a);
			break; // 0,1,2,37
		case 2:
			array = translate(-1, 0, 0, a);
			break;
		case 3:
			array = translate(-2, 0, 0, a);
			break;
		case 4:
			b = rotate('z', -1, a);
			array = translate(0, 0, 0, b);
			break;
		case 5:
			b = rotate('z', -1, a);
			array = translate(0, -1, 0, b);
			break;
		case 6:
			b = rotate('z', -1, a);
			array = translate(0, -2, 0, b);
			break;
		case 7:
			array = translate(-1, 0, -1, a);
			break;
		case 8:
			b = rotate('z', -1, a);
			array = translate(0, -1, -1, b);
			break;
		// DOWN
		case 9:
			b = rotate('x', 2, a);
			array = translate(0, 0, 0, b);
			break;
		case 10:
			b = rotate('x', 2, a);
			array = translate(-1, 0, 0, b);
			break;
		case 11:
			b = rotate('x', 2, a);
			array = translate(-2, 0, 0, b);
			break;
		case 12:
			b = rotate('x', 2, a);
			array = translate(-1, 0, 1, b);
			break;
		case 13:
			b = rotate('x', 2, a);
			c = translate(0, 0, 0, b);
			array = rotate('z', -1, c);
			break;
		case 14:
			b = rotate('x', 2, a);
			c = translate(-1, 0, 0, b);
			array = rotate('z', -1, c);
			break;
		case 15:
			b = rotate('x', 2, a);
			c = translate(-2, 0, 0, b);
			array = rotate('z', -1, c);
			break;
		case 16:
			b = rotate('x', 2, a);
			c = translate(-1, 0, 1, b);
			array = rotate('z', -1, c);
			break;
		// EAST
		case 17:
			b = rotate('x', 1, a);
			array = rotate('z', 1, b);
			break;
		case 18:
			b = rotate('x', 1, a);
			c = rotate('z', 1, b);
			array = translate(0, 1, 0, c);
			break;
		case 19:
			b = rotate('x', 1, a);
			c = rotate('z', 1, b);
			array = translate(0, 2, 0, c);
			break;
		case 20:
			b = rotate('x', 1, a);
			c = rotate('z', 1, b);
			array = translate(-1, 1, 0, c);
			break;
		// NORTH
		case 21:
			b = rotate('x', 1, a);
			array = rotate('z', 2, b);
			break;
		case 22:
			b = rotate('x', 1, a);
			c = rotate('z', 2, b);
			array = translate(1, 0, 0, c);
			break;
		case 23:
			b = rotate('x', 1, a);
			c = rotate('z', 2, b);
			array = translate(2, 0, 0, c);
			break;
		case 24:
			b = rotate('x', 1, a);
			c = rotate('z', 2, b);
			array = translate(1, 1, 0, c);
			break;
		// WEST
		case 25:
			b = rotate('x', 1, a);
			array = rotate('z', 3, b);
			break;
		case 26:
			b = rotate('x', 1, a);
			c = rotate('z', 3, b);
			array = translate(0, -1, 0, c);
			break;
		case 27:
			b = rotate('x', 1, a);
			c = rotate('z', 3, b);
			array = translate(0, -2, 0, c);
			break;
		case 28:
			b = rotate('x', 1, a);
			c = rotate('z', 3, b);
			array = translate(1, -1, 0, c);
			break;
		// SOUTH
		case 29:
			array = rotate('x', 1, a);
			break;
		case 30:
			b = rotate('x', 1, a);
			array = translate(-1, 0, 0, b);
			break;
		case 31:
			b = rotate('x', 1, a);
			array = translate(-2, 0, 0, b);
			break;
		case 32:
			b = rotate('x', 1, a);
			array = translate(-1, -1, 0, b);
			break;
		}
		array = translate(Conversion.convertNtoXYZ(shift), array);
		return array;
	}

	/**
	 * Possibile.
	 * 
	 * @param block
	 *            the block
	 * @return true, if successful
	 */
	public static boolean isInsertionPossibile(Integer[] block) {
		for (int i = 0; i < 4; i++) {
			if (block[i] == -1) {
				return false;
			}
		}
		return true;
	}
}
