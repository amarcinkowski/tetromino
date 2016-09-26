package io.github.amarcinkowski.tetromino.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import io.github.amarcinkowski.tetromino.math.BlockConverter;

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


	public BlockBuilder collection(Collection<Integer> collection) {
		ArrayList<Integer> v = new ArrayList<>(collection);
		Collections.sort(v);
		int n4[] = v.stream().mapToInt(Integer::intValue).toArray();
		block = BlockConverter.n4ToBlock(n4);
		return this;
	}
	
	public BlockBuilder n4(int[] array) {
		block = BlockConverter.n4ToBlock(array);
		return this;
	}
}