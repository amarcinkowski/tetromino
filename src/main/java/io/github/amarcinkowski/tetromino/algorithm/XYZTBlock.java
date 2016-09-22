package io.github.amarcinkowski.tetromino.algorithm;

public class XYZTBlock {
	public int x, y, z;
	public BlockType type;
	public double screenx, screeny;
	
	private double[] xyz2xy(int[] xyz) {
		int x = xyz[0];
		int y = xyz[1];
		int z = xyz[2];
		return new double[]{(6 * x - y * 2.95), (y * 2.65 + x * 1.25 - 5.9 * z)};
	}

	public XYZTBlock(int[] xyz, BlockType type) {
		this.x = xyz[0];
		this.y = xyz[1];
		this.z = xyz[2];
		double[] screen_xy = xyz2xy(xyz);
		this.screenx = screen_xy[0];
		this.screeny = screen_xy[1];
		this.type = type;
	}
}