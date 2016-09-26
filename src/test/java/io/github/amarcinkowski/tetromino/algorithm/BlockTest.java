package io.github.amarcinkowski.tetromino.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.TreeSet;

import org.junit.Test;

public class BlockTest {

	Block b000_ = new BlockBuilder().xyz(new int[] { 0, 0, 0 }).type(BlockDirection.HORIZONTAL_UP).build();
	Block b000u = new BlockBuilder().xyz(new int[] { 0, 0, 0 }).type(BlockDirection.HORIZONTAL_UP).build();
	Block b000n = new BlockBuilder().xyz(new int[] { 0, 0, 0 }).type(BlockDirection.HORIZONTAL_NORTH).build();
	Block b000d = new BlockBuilder().xyz(new int[] { 0, 0, 0 }).type(BlockDirection.HORIZONTAL_DOWN).build();
	Block b100d = new BlockBuilder().xyz(new int[] { 1, 0, 0 }).type(BlockDirection.HORIZONTAL_DOWN).build();
	Block b010d = new BlockBuilder().xyz(new int[] { 0, 1, 0 }).type(BlockDirection.HORIZONTAL_DOWN).build();
	Block b001d = new BlockBuilder().xyz(new int[] { 0, 0, 1 }).type(BlockDirection.HORIZONTAL_DOWN).build();

	@Test
	public void equalsTest() {
		assertTrue(b000_.equals(b000u));
		assertTrue(b000_.hashCode() == b000u.hashCode());
		assertTrue(!b000u.equals(b000d));
		assertTrue(!b000u.equals(new String()));
		assertTrue(b000u.toString().equals("0,0,0-horizontal-up"));
	}

	@Test
	public void setTest() {
		TreeSet<Block> set = new TreeSet<>();
		set.add(b000_);
		set.add(b000u);
		assertEquals(1, set.size());
	}

	@Test
	public void hashCodeUniqnessTest() {
		TreeSet<Block> blockSet = new TreeSet<>();
		blockSet.add(b001d);
		blockSet.add(b010d);
		blockSet.add(b100d);
		blockSet.add(b000d);
		blockSet.add(b000n);
		blockSet.add(b000_);
		blockSet.add(b000u);
		assertEquals(6, blockSet.size());
		assertEquals(b000_, blockSet.iterator().next());
	}

}
