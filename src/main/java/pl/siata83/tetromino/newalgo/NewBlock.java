package pl.siata83.tetromino.newalgo;

import java.util.Arrays;

import pl.siata83.tetromino.math.Conversion;

/**
 * The Class NewBlock.
 */
public class NewBlock implements Comparable<NewBlock> {

	/** The n. */
	private Integer n[] = new Integer[4];
	
	/**
	 * Instantiates a new new block.
	 * 
	 * @param n
	 *            the n
	 */
	public NewBlock(int[] n) {
		Arrays.sort(n);
		this.n[0] = n[0];
		this.n[1] = n[1];
		this.n[2] = n[2];
		this.n[3] = n[3];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NewBlock)) {
			return false;
		} else {
			NewBlock o = (NewBlock) obj;
			for (int i = 0; i < 4; i++) {
				if (n[i] != o.n[i]) {
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * Gets the n.
	 * 
	 * @return the n
	 */
	public Integer[] getN() {
		return n;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("N[%2d,%2d,%2d,%2d] XYZ%s%s%s%s", n[0], n[1], n[2], n[3], 
				Arrays.toString(Conversion.convertNtoXYZ(n[0])),
				Arrays.toString(Conversion.convertNtoXYZ(n[1])),
				Arrays.toString(Conversion.convertNtoXYZ(n[2])),
				Arrays.toString(Conversion.convertNtoXYZ(n[3])));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(NewBlock o) {
		if (this.equals(o)) {
			return 0;
		} else {
			for (int i = 0; i < 4; i++) {
				if (n[i] > o.n[i]) {
					return i + 1;
				} else if (n[i] < o.n[i]) {
					return -i - 1;
				}
			}
		}
		return 0;
	}

}
