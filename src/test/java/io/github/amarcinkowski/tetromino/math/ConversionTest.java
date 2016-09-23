package io.github.amarcinkowski.tetromino.math;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

public class ConversionTest {

	int i = 0;

	@org.junit.Test
	public void xyzToNAndBackTest() {
		// 0 = 0,0,0
		assertEquals(0, BlockConverter.xyz2N(0, 0, 0));
		assertArrayEquals(new int[] { 0, 0, 0 }, BlockConverter.n2XYZ(0));
		// 1 = 1,0,0
		assertEquals(1, BlockConverter.xyz2N(1, 0, 0));
		assertArrayEquals(new int[] { 1, 0, 0 }, BlockConverter.n2XYZ(1));
		// 6
		assertEquals(6, BlockConverter.xyz2N(0, 1, 0));
		assertArrayEquals(new int[] { 0, 1, 0 }, BlockConverter.n2XYZ(6));
		// 36
		assertEquals(36, BlockConverter.xyz2N(0, 0, 1));
		assertArrayEquals(new int[] { 0, 0, 1 }, BlockConverter.n2XYZ(36));
		// 71
		assertEquals(71, BlockConverter.xyz2N(5, 5, 1));
		assertArrayEquals(new int[] { 5, 5, 1 }, BlockConverter.n2XYZ(71));
		// outside of cube
		assertEquals(-1, BlockConverter.xyz2N(10, 0, 0));
		boolean thrown = false;
		try {
			BlockConverter.n2XYZ(-1);
		} catch (NumberIsTooSmallException e) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	@org.junit.Test
	public void vectorToNTest() {
		// 0 = 0,0,0
		assertArrayEquals(new int[] { 0, 0, 0, 0 }, BlockConverter.vectorToNBlock(new double[][] {
				Transformations3D.V000, Transformations3D.V000, Transformations3D.V000, Transformations3D.V000 }));
		assertArrayEquals(new int[] { 1, 1, 1, 1 }, BlockConverter.vectorToNBlock(new double[][] {
				Transformations3D.V100, Transformations3D.V100, Transformations3D.V100, Transformations3D.V100 }));
	}

}
