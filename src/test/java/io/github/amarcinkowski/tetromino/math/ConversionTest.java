package io.github.amarcinkowski.tetromino.math;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

public class ConversionTest {

	@org.junit.Test
	public void xyzToNAndBackTest() {
		// 0 = 0,0,0
		int i = BlockConverter.xyz2N(0, 0, 0);
		assertEquals(0, i);
		// back
		assertArrayEquals(new int[] { 0, 0, 0 }, BlockConverter.n2XYZ(i));
		// 1 = 1,0,0
		i = BlockConverter.xyz2N(1, 0, 0);
		assertEquals(1, i);
		assertArrayEquals(new int[] { 1, 0, 0 }, BlockConverter.n2XYZ(i));
		// 6
		i = BlockConverter.xyz2N(0, 1, 0);
		assertEquals(6, i);
		assertArrayEquals(new int[] { 0, 1, 0 }, BlockConverter.n2XYZ(i));
		// 36
		i = BlockConverter.xyz2N(0, 0, 1);
		assertEquals(36, i);
		assertArrayEquals(new int[] { 0, 0, 1 }, BlockConverter.n2XYZ(i));
		// 71
		i = BlockConverter.xyz2N(5, 5, 1);
		assertEquals(71, i);
		assertArrayEquals(new int[] { 5, 5, 1 }, BlockConverter.n2XYZ(i));
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
