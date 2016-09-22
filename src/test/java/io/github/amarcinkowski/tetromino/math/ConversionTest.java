package io.github.amarcinkowski.tetromino.math;

import static org.junit.Assert.*;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

import io.github.amarcinkowski.tetromino.algorithm.Block;

public class ConversionTest {

	int i = 0;

	@org.junit.Test
	public void xyzToNAndBackTest() {
		// 0 = 0,0,0
		assertEquals(0, Conversion.convertXYZtoN(0, 0, 0));
		assertArrayEquals(new int[] { 0, 0, 0 }, Conversion.convertNtoXYZ(0));
		// 1 = 1,0,0
		assertEquals(1, Conversion.convertXYZtoN(1, 0, 0));
		assertArrayEquals(new int[] { 1, 0, 0 }, Conversion.convertNtoXYZ(1));
		// 6
		assertEquals(6, Conversion.convertXYZtoN(0, 1, 0));
		assertArrayEquals(new int[] { 0, 1, 0 }, Conversion.convertNtoXYZ(6));
		// 36
		assertEquals(36, Conversion.convertXYZtoN(0, 0, 1));
		assertArrayEquals(new int[] { 0, 0, 1 }, Conversion.convertNtoXYZ(36));
		// 71
		assertEquals(71, Conversion.convertXYZtoN(5, 5, 1));
		assertArrayEquals(new int[] { 5, 5, 1 }, Conversion.convertNtoXYZ(71));
		// outside of cube
		assertEquals(-1, Conversion.convertXYZtoN(10, 0, 0));
		boolean thrown = false;
		try {
			Conversion.convertNtoXYZ(-1);
		} catch (NumberIsTooSmallException e) {
			thrown = true;
		}
		assertEquals(true, thrown);
	}

	@org.junit.Test
	public void vectorToNTest() {
		// 0 = 0,0,0
		assertArrayEquals(new int[] { 0, 0, 0, 0 }, Conversion.convertXYZtoN(new double[][] { Transformations3D.V000,
				Transformations3D.V000, Transformations3D.V000, Transformations3D.V000 }));
		assertArrayEquals(new int[] { 1, 1, 1, 1 }, Conversion.convertXYZtoN(new double[][] { Transformations3D.V100,
				Transformations3D.V100, Transformations3D.V100, Transformations3D.V100 }));
	}

}
