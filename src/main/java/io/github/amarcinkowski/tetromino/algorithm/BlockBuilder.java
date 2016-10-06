package io.github.amarcinkowski.tetromino.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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


	public BlockBuilder collection(Collection<Integer> collection) {
		ArrayList<Integer> v = new ArrayList<>(collection);
		Collections.sort(v);
		int n4[] = v.stream().mapToInt(Integer::intValue).toArray();
		block = n4(n4).build();
		return this;
	}
	
	public BlockBuilder n4(int[] array) {
		Arrays.sort(array);
		BlockDirection direction = BlockDirection.getDirection(array);
		int xyz[] = CoordinateConverter.n2XYZ(array[0]);
		block = new BlockBuilder().xyz(xyz).type(direction).build();
		return this;
	}

}