package io.github.amarcinkowski.tetromino.algorithm;

/**
 * The Class Conversion.
 */

public enum BlockDirection {
	HORIZONTAL_UP, HORIZONTAL_NORTH, HORIZONTAL_DOWN, HORIZONTAL_SOUTH, VERTICAL_UP, VERTICAL_EAST, VERTICAL_DOWN, VERTICAL_WEST,;

	@Override
	public String toString() {
		return name().toLowerCase().replace("_", "-");
	}
}