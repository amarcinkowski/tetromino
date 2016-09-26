package io.github.amarcinkowski.tetromino.visualisation;

import org.apache.commons.lang3.NotImplementedException;

import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;

public abstract class FileOutput {

	protected final CubeVolume cubeVolume;
	public static String EXTENSION; 

	public FileOutput(CubeVolume cubeVolume) {
		this.cubeVolume = cubeVolume;
	}

	protected String getContents() throws Exception {
		throw new NotImplementedException("implement");
	}

	public void save() {
		try {
			String filename = String.format("%s.%s", cubeVolume.hashCode(), EXTENSION);
			FileHelper.string2File(filename, getContents());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
