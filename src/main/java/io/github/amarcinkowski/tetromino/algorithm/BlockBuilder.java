package io.github.amarcinkowski.tetromino.algorithm;

public class BlockBuilder {

	private Block block = new Block();

	public BlockBuilder xyz(int[] xyz) {
		block.x = xyz[0];
		block.y = xyz[1];
		block.z = xyz[2];
		return this;
	}

	public BlockBuilder type(BlockType type) {
		block.type = type;
		return this;
	}

	private double[] xyz2ScreenCoordinates(int x, int y, int z) {
		return new double[] { (6 * x - y * 2.95), (y * 2.65 + x * 1.25 - 5.9 * z) };
	}

	public Block build() {
		double[] screen_xy = xyz2ScreenCoordinates(block.x, block.y, block.z);
		block.screenx = screen_xy[0];
		block.screeny = screen_xy[1];
		return block;
	}

	public BlockBuilder n4(int[] array) {
		return null;
	}
}