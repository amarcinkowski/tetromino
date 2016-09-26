package io.github.amarcinkowski.tetromino.visualisation;

import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;
import io.github.amarcinkowski.tetromino.math.CoordinateConverter;

public class Text extends FileOutput {

	public Text(CubeVolume cubeVolume) {
		super(cubeVolume);
		EXTENSION = "txt";
	}

	protected String getContents() {
		int[] filled = cubeVolume.getFilled();
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < 6; y++) {
			builder.append("\n");
			for (int z = 0; z < 2; z++) {
				for (int x = 0; x < 6; x++) {
					builder.append(String.format("%3s", filled[CoordinateConverter.xyz2N(x, y, z)]));
				}
				builder.append(" |");
			}
		}
		return builder.toString();
	}

}
