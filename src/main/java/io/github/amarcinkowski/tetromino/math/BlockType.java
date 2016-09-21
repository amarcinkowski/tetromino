package io.github.amarcinkowski.tetromino.math;

/**
 * The Class Conversion.
 */

public enum BlockType {
	HORIZONTAL_UP,
	HORIZONTAL_NORTH,
	HORIZONTAL_DOWN,
	HORIZONTAL_SOUTH,
	VERTICAL_UP,	
	VERTICAL_EAST,
	VERTICAL_DOWN,	
	VERTICAL_WEST,	
	;
	public String toString() {
		return String.format("000%d", ordinal() + 1);
	}
}