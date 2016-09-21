package pl.siata83.tetromino.newalgo;

import java.util.TreeSet;

import pl.siata83.tetromino.algorithm.CubeVolume;
import pl.siata83.tetromino.math.Transformations3D;

public class NewAlgorithm {

	static {
		map = NewAlgorithm.getAllPossibileNewBlocksInN();
	}

	public final static BlockMap map;

	/**
	 * Gets the all possibile new blocks.
	 * 
	 * @return the all possibile new blocks
	 */
	public static TreeSet<NewBlock> getAllPossibileNewBlocks() {
		TreeSet<NewBlock> set = new TreeSet<NewBlock>();
		for (int k = 0; k < CubeVolume.VOLUME; k++) {
			for (int i = 1; i <= Transformations3D.MAX_BLOCK_TYPE; i++) {
				Integer block[] = Transformations3D.block(i, k);
				if (Transformations3D.isInsertionPossibile(block)) {
					set.add(new NewBlock(block));
				}
			}
		}
		Transformations3D.logger.debug("Number of possibile block positions: "
				+ set.size());
		return set;
	}

	public static BlockMap getAllPossibileNewBlocksInN() {
		TreeSet<NewBlock> set = getAllPossibileNewBlocks();
		BlockMap map = new BlockMap();
		for (NewBlock nb : set) {
			map.put(nb);
		}
		Transformations3D.logger.debug("Map size: " + map.size());
		return map;
	}

	public static String table2String(Integer[] table, int numWyrozniony) {
		String s = "[";
		int index = 0;
		for (int i : table) {
			if (index++ == numWyrozniony) {
				s += String.format("{%2d}", i);
			} else {
				s += String.format(" %2d ", i);
			}
		}
		s += "]";
		return s;
	}

	private static void print(BnBData data, NewBlock nb, String command) {
		Transformations3D.logger.debug(command + ": " + nb + " from \n"
				+ data.getNewVolume());
		Transformations3D.logger.debug("nF"
				+ table2String(data.getnFieldNumbers(), data.getJ()));
		Transformations3D.logger.debug("bN"
				+ table2String(data.getBlockNumInN(), data.getJ()));
	}

	public static void generateResult(BnBData data) {
		for (int i = data.getJ(); i < 18; i++) {
			Transformations3D.logger.debug("i " + i);
			if (data.getnFieldNumbers()[i] > data.getNewVolume().getFirstFree()) {
				Transformations3D.logger
						.trace("Left an empty volume without possibility to fill it");
				data.getBlockNumInN()[i] = 0;
				data.getnFieldNumbers()[i] = 0;
				i--;
				NewBlock nb = map.get(data.getnFieldNumbers()[i],
						data.getBlockNumInN()[i]);
				data.getNewVolume().removeNewBlock(nb);
				// X1
				data.getBlockNumInN()[i]++;
				if (data.getBlockNumInN()[i] == map.get(
						data.getnFieldNumbers()[i]).size()) {
					data.getBlockNumInN()[i] = 0;
					data.getnFieldNumbers()[i]++;
				}
				data.setJ(i);
				print(data, nb, "Removing");
				generateResult(data);
				return;
				// X1
			} else {
				data.getnFieldNumbers()[i] = data.getNewVolume().getFirstFree();
			}
			NewBlock nb = map.get(data.getnFieldNumbers()[i],
					data.getBlockNumInN()[i]);
			data.setJ(i);
			// 244700 [main] DEBUG pl.siata83.tetromino.math.Transformations3D -
			// i 10
			// nF[ 0 3 6 8 9 11 19 21 22 30 {35} 0 0 0 0 0 0 0 ]
			// bN[ 0 0 2 4 6 0 2 5 2 0 { 0} 0 0 0 0 0 0 0 ]
			if (nb != null) {

			} else if (data.getNewVolume().addNewBlock(nb) == false) {
				// X2
				data.getBlockNumInN()[i]++;
				if (data.getBlockNumInN()[i] == NewAlgorithm.map.get(
						data.getnFieldNumbers()[i]).size()) {
					data.getBlockNumInN()[i] = 0;
					data.getnFieldNumbers()[i]++;
				}
				data.setJ(i);
				print(data, nb, "Failed adding");
				generateResult(data);
				return;
				// X2
			} else {
				print(data, nb, "Adding");
			}
		}
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		generateResult(new BnBData());
	}

}
