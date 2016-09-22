package io.github.amarcinkowski.tetromino.algorithm;

public class Block {
	protected int x, y, z;
	protected BlockType type;
	protected double screenx, screeny;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BlockType getType() {
		return type;
	}

	public void setType(BlockType type) {
		this.type = type;
	}

	public double getScreenx() {
		return screenx;
	}

	public void setScreenx(double screenx) {
		this.screenx = screenx;
	}

	public double getScreeny() {
		return screeny;
	}

	public void setScreeny(double screeny) {
		this.screeny = screeny;
	}

	protected Block() {
	}

}