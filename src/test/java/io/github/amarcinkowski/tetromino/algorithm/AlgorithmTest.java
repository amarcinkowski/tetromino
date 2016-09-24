package io.github.amarcinkowski.tetromino.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlgorithmTest {

	@Test
	public void solutionCountTest() {
		int solutionsCount = new Algorithm().run().size();
		assertEquals(43, solutionsCount);
	}

}
