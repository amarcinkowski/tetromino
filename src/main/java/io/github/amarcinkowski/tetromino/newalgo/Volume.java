package io.github.amarcinkowski.tetromino.newalgo;

import java.util.Arrays;
import java.util.TreeSet;

public class Volume {

	private TreeSet<NewBlock> blocks = new TreeSet<NewBlock>();

	private TreeSet<Integer> filledVolume = new TreeSet<Integer>();

	public int getFirstFree() {
		int index = 0;
		while (filledVolume.contains(index)) {
			index++;
		}
		return index;
	}

	public boolean addNewBlock(NewBlock nb) {
		for (Integer i : nb.getN()) {
			if (filledVolume.contains(i)) {
				return false;
			}
		}
		blocks.add(nb);
		filledVolume.addAll(Arrays.asList(nb.getN()));
		return true;
	}

	public void removeNewBlock(NewBlock nb) {
		if (blocks.contains(nb)) {
			blocks.remove(nb);
			filledVolume.removeAll(Arrays.asList(nb.getN()));
		}
	}

	public boolean isResult() {
		if (blocks.size() == 18 && filledVolume.size() == 72) {
			System.out.println("@@@@@@@@@@@@@@########### RESULT");
			return true;

		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String s = "";
		int v[] = new int[72];
		int blockNubmer = 0;
		// iter over blocks
		for (NewBlock nb : blocks) {
			blockNubmer++;
			for (int i : nb.getN()) {
				v[i] = blockNubmer;
			}
		}
		for (int i = 0; i < 36; i += 6) {
			s += String.format("%2d%2d%2d%2d%2d%2d \t %2d%2d%2d%2d%2d%2d \n",
					v[i], v[i + 1], v[i + 2], v[i + 3], v[i + 4], v[i + 5],
					v[i + 36], v[i + 37], v[i + 38], v[i + 39], v[i + 40],
					v[i + 41]);
		}
		return s;
	}
}
