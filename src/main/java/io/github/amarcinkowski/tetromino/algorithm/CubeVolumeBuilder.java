package io.github.amarcinkowski.tetromino.algorithm;

public class CubeVolumeBuilder {

	CubeVolume cubeVolume = CubeVolume.newInstance();

	public CubeVolumeBuilder empty() {
		cubeVolume.cubeVolumePointer = 0;
		cubeVolume.blockcount = 0;
		for (int x = 0; x < CubeVolume.VOLUME_SIZE; x++) {
			cubeVolume.filled[x] = 0;
		}
		return this;
	}

	public CubeVolumeBuilder filled(int[] filled) {
		cubeVolume.cubeVolumePointer = 0;
		cubeVolume.filled = filled;
		cubeVolume.blockcount = cubeVolume.getBlockList().size();
		return this;
	}

	public CubeVolume build() {
		return cubeVolume;
	}

}
