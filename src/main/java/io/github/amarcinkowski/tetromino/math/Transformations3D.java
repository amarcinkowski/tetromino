package io.github.amarcinkowski.tetromino.math;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transformations3D {

	public static final double[] V101 = { 1.0, 0.0, 1.0, 1.0 };

	public static final double[] V200 = { 2.0, 0.0, 0.0, 1.0 };

	public static final double[] V100 = { 1.0, 0.0, 0.0, 1.0 };

	public static final double[] V000 = { 0.0, 0.0, 0.0, 1.0 };

	public static Logger logger = LoggerFactory.getLogger(Transformations3D.class);

	public final static double[][] BLOCK_3D_VERTICES = { V000, V100, V200, V101 };

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
	public static double[][] translate(int[] dimension, double[][] transformed) {
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
	public static double[][] translate(int x, int y, int z, double[][] transformed) {
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
	public static double[][] rotate(char q, int x90, double[][] rotated) {
		double[][] array = null;
		double alfa = Math.PI / 2 * x90;
		switch (q) {
		case 'x': // Rot X
			double[][] rotx = { { 1.0, 0.0, 0.0, 0.0 }, { 0.0, Math.cos(alfa), -Math.sin(alfa), 0.0 },
					{ 0.0, Math.sin(alfa), Math.cos(alfa), 0.0 }, V000 };
			array = matrixMultiply(rotx, rotated);
			break;
		case 'y':
			// Rot Y
			double[][] roty = { { Math.cos(alfa), 0.0, Math.sin(alfa), 0.0 }, { 0.0, 1.0, 0.0, 0.0 },
					{ -Math.sin(alfa), 0.0, Math.cos(alfa), 0.0 }, V000 };
			array = matrixMultiply(roty, rotated);
			break;
		case 'z':
			// Rot Z
			double[][] rotz = { { Math.cos(alfa), -Math.sin(alfa), 0.0, 0.0 },
					{ Math.sin(alfa), Math.cos(alfa), 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, V000 };
			array = matrixMultiply(rotz, rotated);
			break;
		}
		return array;
	}
}
