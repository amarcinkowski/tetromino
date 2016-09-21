package io.github.amarcinkowski.tetromino.newalgo;

import java.util.TreeMap;
import java.util.Vector;

/**
 * The Class NewBlockMap.
 */
public class BlockMap extends TreeMap<Integer, Vector<NewBlock>> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2212249039350003743L;

	/**
	 * Put.
	 * 
	 * @param nb
	 *            the nb
	 * @return the new block
	 */
	public NewBlock put(NewBlock nb) {
		int i = nb.getN()[0];
		if (get(i) == null) {
			put(i, new Vector<NewBlock>());
		}
		Vector<NewBlock> temp = get(i);
		temp.add(nb);
		put(i, temp);
		return nb;
	}

	public NewBlock get(int i, int j) {
		if (get(i) == null) {
			return null;
		} else {
			return get(i).get(j);
		}
	}

}
