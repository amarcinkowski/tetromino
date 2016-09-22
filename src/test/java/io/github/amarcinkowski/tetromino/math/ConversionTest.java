package io.github.amarcinkowski.tetromino.math;

import static org.junit.Assert.*;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

import io.github.amarcinkowski.tetromino.algorithm.Block;

public class ConversionTest {

	int i = 0;

	@org.junit.Test
	public void xyzToNAndBackTest() {
		// 0 = 0,0,0
		assertEquals(0, Conversion.xyz2N(0, 0, 0));
		assertArrayEquals(new int[] { 0, 0, 0 }, Conversion.n2XYZ(0));
		// 1 = 1,0,0
		assertEquals(1, Conversion.xyz2N(1, 0, 0));
		assertArrayEquals(new int[] { 1, 0, 0 }, Conversion.n2XYZ(1));
		// 6
		assertEquals(6, Conversion.xyz2N(0, 1, 0));
		assertArrayEquals(new int[] { 0, 1, 0 }, Conversion.n2XYZ(6));
		// 36
		assertEquals(36, Conversion.xyz2N(0, 0, 1));
		assertArrayEquals(new int[] { 0, 0, 1 }, Conversion.n2XYZ(36));
		// 71
		assertEquals(71, Conversion.xyz2N(5, 5, 1));
		assertArrayEquals(new int[] { 5, 5, 1 }, Conversion.n2XYZ(71));
		// outside of cube
		assertEquals(-1, Conversion.xyz2N(10, 0, 0));
		boolean thrown = false;
		try {
			Conversion.n2XYZ(-1);
		} catch (NumberIsTooSmallException e) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	@org.junit.Test
	public void vectorToNTest() {
		// 0 = 0,0,0
		assertArrayEquals(new int[] { 0, 0, 0, 0 },
				Conversion.convertVectortoN(new double[][] { Block.V000, Block.V000, Block.V000, Block.V000 }));
		assertArrayEquals(new int[] { 1, 1, 1, 1 },
				Conversion.convertVectortoN(new double[][] { Block.V100, Block.V100, Block.V100, Block.V100 }));
	}

}
