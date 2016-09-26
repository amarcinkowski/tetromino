package io.github.amarcinkowski.tetromino.visualisation;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import io.github.amarcinkowski.tetromino.algorithm.Block;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolumeBuilder;

public class InputOutputTest {

	// empty
	private final static int E = 0;

	public int[] solutionArray = { 1, 1, 1, 2, 2, 2, 3, 4, 4, 4, 2, 5, 6, 7, 7, 7, 8, 9, 6, 6, 7, 10, 9, 9, 6, 11, 10,
			10, 10, 9, 11, 11, 11, 12, 12, 12, 3, 1, 13, 13, 13, 5, 3, 14, 4, 13, 8, 5, 3, 14, 14, 15, 8, 5, 16, 14, 15,
			15, 8, 17, 16, 16, 18, 15, 17, 17, 16, 18, 18, 18, 12, 17 };

	public int[] singleBlockArray = { 1, 1, 1, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
			E, E, E, E, E, E, E, E, E, E, 1, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E,
			E, E, E, E, E, E, E, E, E };

	private String expectedTxt = "\n  1  1  1  2  2  2 |  3  1 13 13 13  5 |\n  3  4  4  4  2  5 |  3 14  4 13  8  5 |\n"
			+ "  6  7  7  7  8  9 |  3 14 14 15  8  5 |\n  6  6  7 10  9  9 | 16 14 15 15  8 17 |\n  6 11 10 10 10  9 |"
			+ " 16 16 18 15 17 17 |\n 11 11 11 12 12 12 | 16 18 18 18 12 17 |";

	private String expected2Txt = "\n  1  1  1  0  0  0 |  0  1  0  0  0  0 |\n  0  0  0  0  0  0 |  0  0  0  0  0  0 |\n"
			+ "  0  0  0  0  0  0 |  0  0  0  0  0  0 |\n  0  0  0  0  0  0 |  0  0  0  0  0  0 |\n  0  0  0  0  0  0 |"
			+ "  0  0  0  0  0  0 |\n  0  0  0  0  0  0 |  0  0  0  0  0  0 |";
	private CubeVolume cubeVolume;

	@Test
	public void textOutputTest() {
		cubeVolume = new CubeVolumeBuilder().filled(solutionArray).build();
		String result = new Text(cubeVolume).getContents();
		assertEquals(expectedTxt, result);
	}

	@Test
	public void textOutput2Test() {
		cubeVolume = new CubeVolumeBuilder().filled(singleBlockArray).build();
		List<Block> blocks = cubeVolume.getBlockList();
		assertEquals(1, blocks.size());
		assertEquals(expected2Txt, new Text(cubeVolume).getContents());
	}

	@Test
	public void loadTest() throws FileNotFoundException {
		cubeVolume = FileHelper.loadCube("src/main/resources/solution.cube");
		assertEquals(18, cubeVolume.getBlockCount());
	}

	@Test
	public void load2Test() throws FileNotFoundException {
		cubeVolume = FileHelper.loadCube("src/main/resources/singleBlock.cube");
		assertEquals(1, cubeVolume.getBlockCount());
	}

}
