package pl.siata83.tetromino.algorithm;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.siata83.tetromino.Main;
import pl.siata83.tetromino.visualisation.SVG;
import pl.siata83.tetromino.visualisation.Text;

public class Algorithm {

	private static CubeVolume cv = new CubeVolume();

	/**
	 * Gets the cv.
	 * 
	 * @return the cv
	 */
	public static CubeVolume getCv() {
		return cv;
	}

	private static int space = 0;

	/** The Constant BLOCK_VOLUME. */
	private final static int BLOCK_VOLUME = 4;

	/** The Constant MAX_BLOCK. */
	private final static int MAX_BLOCK = CubeVolume.VOLUME / BLOCK_VOLUME;

	private static Logger logger = LoggerFactory.getLogger(Algorithm.class);
	/**
	 * np. [1,14,18] 0 > rCT > 32
	 */
	public static int resultCurrentType[] = new int[MAX_BLOCK];
	/**
	 * np. [1,3,3] 0 > rCTC > possibilities(space)
	 */
	public static int resultCurrentTypeCount[] = new int[MAX_BLOCK];
	/**
	 * np. [4,3,5] rMT = possib(space)
	 */
	public static int resultMaxTypes[] = new int[MAX_BLOCK];
	/**
	 * np. [1,5,8] 3 block, starts in 1, 5, and 8 of CV
	 */
	public static int resultBlockStart[] = new int[MAX_BLOCK];

	// TODO kolejno�� wype�niania przestrzeni !!!
	// najpierw pola o najmniejszej liczbie mo�liwych blockType
	// TODO czy mozliwe jest po usunieciu od razu proba wstawienia
	// w jednym cyklu bnb
	// FIXME pseudo rekurencja !!!
	public static void bnb() {
		while (!cv.isEmpty(space))
			space++;
		Vector<Integer> v = new Vector<Integer>();
		int factor = cv.factor();

		if (resultCurrentTypeCount[cv.getBlockcount()] == resultMaxTypes[cv
				.getBlockcount()]
				&& resultCurrentType[cv.getBlockcount()] == -1) {
			factor = 0;
			if (cv.getBlockcount() == 0) {
				System.out.println("ALL RESULTS SEARCHED ");
				System.exit(0);
			}

		}

		if (factor == 0) {
			logger.trace("REMOVE");
			int r_type = resultCurrentType[cv.getBlockcount() - 1];
			int r_space = resultBlockStart[cv.getBlockcount() - 1];
			int block[] = Block.getBlock(r_type, r_space);
			if (cv.remove(block)) {
				space = r_space;
				resultCurrentType[cv.getBlockcount()] = -1;
				resultCurrentTypeCount[cv.getBlockcount() + 1] = 0;
			}
		} else {
			v = cv.possibileInsertsVector(space);
			resultMaxTypes[cv.getBlockcount()] = v.size();

			int index = resultCurrentTypeCount[cv.getBlockcount()];
			int r_type = v.get(index);

			int block[] = Block.getBlock(r_type, space);

			if (cv.insert(block)) {
				logger.trace("INSERT");
				resultCurrentType[cv.getBlockcount() - 1] = r_type;
				resultCurrentTypeCount[cv.getBlockcount() - 1]++;
				resultBlockStart[cv.getBlockcount() - 1] = space;
			}
		}

		if (cv.getBlockcount() == MAX_BLOCK) {
			Main.resultCount++;

			Text.printFilled(cv.getFilled());
			SVG.create(cv.getFilled());
			// Text.printTables();

			System.out.println("\nRESULT " + Main.resultCount);
			cv = new CubeVolume();
			space = 0;

			// PO UZYSKANIU WYNIKU COFNIJ SIE DO OSTATNIEJ GALEZI Z DWOMA LISCMI
			// I WEJSDZ DO 2 LISCIA
			int i;
			for (i = MAX_BLOCK - 1; i > 0; i--)
				if (resultCurrentTypeCount[i] == resultMaxTypes[i]) {
					resultCurrentType[i] = -1;
				} else
					break;
			for (int j = 0; j < i; j++) {
				v = cv.possibileInsertsVector(resultBlockStart[j]);
				int index = resultCurrentTypeCount[cv.getBlockcount()];
				int r_type = v.get(index - 1);
				cv.insert(Block.getBlock(r_type, resultBlockStart[j]));

			}

		}
	}

}
