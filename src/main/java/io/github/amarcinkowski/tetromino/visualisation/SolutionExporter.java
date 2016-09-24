package io.github.amarcinkowski.tetromino.visualisation;

import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;

public interface SolutionExporter {

	public String getContents(CubeVolume cubeVolume) throws Exception;
	
}
