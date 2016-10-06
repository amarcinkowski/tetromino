package io.github.amarcinkowski.tetromino.algorithm;

import java.util.Arrays;

import io.github.amarcinkowski.tetromino.math.CoordinateConverter;

public class BlockBuilder {

	private Block block = new Block();

	public BlockBuilder xyz(int[] xyz) {
		block.x = xyz[0];
		block.y = xyz[1];
		block.z = xyz[2];
		return this;
	}

	public BlockBuilder type(BlockDirection type) {
		block.direction = type;
		return this;
	}

	public Block build() {
		return block;
	}

	public BlockBuilder array(int[] n4) {
		Arrays.sort(n4);
		BlockDirection direction = BlockDirection.getDirection(n4);
		int xyz[] = CoordinateConverter.n2XYZ(n4[0]);
		xyz(xyz);
		type(direction);
		return this;
	}

}