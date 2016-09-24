package io.github.amarcinkowski.tetromino.algorithm;

public class Block implements Comparable<Block> {
	public int x, y, z;
	public BlockDirection direction;
	public double screenx, screeny;

	protected Block() {
	}

	@Override
	public int hashCode() {
		int maxZ = CubeVolume.SIZE_Z;
		int maxX = CubeVolume.SIZE_X;
		int maxY = CubeVolume.SIZE_Y;
		return (x + 1) + (y + 1) * maxX * 2 + (z + 1) * maxX * maxY * 4
				+ (direction.ordinal() + 1) * maxX * maxY * maxZ * 8;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Block && this.hashCode() == obj.hashCode()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Block arg0) {
		return hashCode() - arg0.hashCode();
	}

	@Override
	public String toString() {
		return String.format("%d,%d,%d-%s", x, y, z, direction);
	}

}
