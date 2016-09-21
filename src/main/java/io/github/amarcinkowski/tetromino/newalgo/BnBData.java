package io.github.amarcinkowski.tetromino.newalgo;


public class BnBData {

	private int j = 0;
	private Volume newVolume = new Volume();
	private Integer[] nFieldNumbers = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0 };
	private Integer[] blockNumInN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0 };

	public Integer[] getnFieldNumbers() {
		return nFieldNumbers;
	}

	public void setnFieldNumbers(Integer[] nFieldNumbers) {
		this.nFieldNumbers = nFieldNumbers;
	}

	public Volume getNewVolume() {
		return newVolume;
	}

	public void setNewVolume(Volume newVolume) {
		this.newVolume = newVolume;
	}

	public Integer[] getBlockNumInN() {
		return blockNumInN;
	}

	public void setBlockNumInN(Integer[] blockNumInN) {
		this.blockNumInN = blockNumInN;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

}
