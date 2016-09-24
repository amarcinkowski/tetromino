package io.github.amarcinkowski.tetromino.visualisation;

import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;
import io.github.amarcinkowski.tetromino.math.CoordinateConverter;

public class Text implements SolutionExporter {

	public String getContents(CubeVolume cubeVolume) {
		int[] filled = cubeVolume.getFilled();
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < 6; y++) {
			builder.append("\t");
			for (int z = 0; z < 2; z++) {
				builder.append("\t");
				for (int x = 0; x < 6; x++) {
					builder.append(String.format("%3s", filled[CoordinateConverter.xyz2N(x, y, z)]));
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}

}
