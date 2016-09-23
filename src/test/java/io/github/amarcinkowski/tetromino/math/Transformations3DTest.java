package io.github.amarcinkowski.tetromino.math;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Transformations3DTest {

	@Test
	public void translateTest() {
		double array[][] = Transformations3D.translate(3, 4, 5, Transformations3D.BLOCK_3D_VERTICES);
		assertArrayEquals(new double[][] { { 3.0, 4.0, 5.0, 1.0 }, { 4.0, 4.0, 5.0, 1.0 }, { 5.0, 4.0, 5.0, 1.0 },
				{ 4.0, 4.0, 6.0, 1.0 } }, array);
	}

	@Test
	public void rotateTest() {
		// x 90
		double array[][] = Transformations3D.rotate('x', 1, Transformations3D.BLOCK_3D_VERTICES);
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0, 1.0 }, { 2.0, 0.0, 0.0, 1.0 },
				{ 1.0, 1.0, 0.0, 1.0 } }, array);
		// z 90
		array = Transformations3D.rotate('z', 1, Transformations3D.BLOCK_3D_VERTICES);
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 1.0 }, { 0.0, -1.0, 0.0, 1.0 }, { 0.0, -2.0, 0.0, 1.0 },
				{ 0.0, -1.0, 1.0, 1.0 } }, array);
		// y 180
		array = Transformations3D.rotate('y', 2, Transformations3D.BLOCK_3D_VERTICES);
		assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 1.0 }, { -1.0, 0.0, 0.0, 1.0 }, { -2.0, 0.0, 0.0, 1.0 },
				{ -1.0, 0.0, -1.0, 1.0 } }, array);
	}

}
