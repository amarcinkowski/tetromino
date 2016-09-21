package io.github.amarcinkowski.algorithm;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.tetromino.Main;
import io.github.amarcinkowski.tetromino.visualisation.SVG;
import io.github.amarcinkowski.tetromino.visualisation.Text;

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

	private static int factor = 0;

	private static Vector<Integer> v = new Vector<Integer>();

	public static void bnb() {
		
		while (!cv.isEmpty(space)) {
			space++;
		}
		
		factor = cv.factor();

		if (allSolutionsFound()) {
			System.out.println("all solutions found");
			System.exit(0);
		}

		if (factor == 0) {
			logger.trace("REMOVE");
			int r_type = resultCurrentType[cv.getBlockCount() - 1];
			int r_space = resultBlockStart[cv.getBlockCount() - 1];
			Integer block[] = Block.getBlock(r_type, r_space);
			if (cv.remove(block)) {
				space = r_space;
				resultCurrentType[cv.getBlockCount()] = -1;
				resultCurrentTypeCount[cv.getBlockCount() + 1] = 0;
			}
		} else {
			v = cv.possibileInsertsVector(space);
			resultMaxTypes[cv.getBlockCount()] = v.size();

			Integer index = resultCurrentTypeCount[cv.getBlockCount()];
			Integer r_type = v.get(index);

			Integer block[] = Block.getBlock(r_type, space);

			if (cv.insert(block)) {
				logger.trace("INSERT");
				resultCurrentType[cv.getBlockCount() - 1] = r_type;
				resultCurrentTypeCount[cv.getBlockCount() - 1]++;
				resultBlockStart[cv.getBlockCount() - 1] = space;
			}
		}

		solutionFound();
	}

	private static void solutionFound() {
		if (cv.getBlockCount() == MAX_BLOCK) {
			Main.resultCount++;

			printResults();
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
				int index = resultCurrentTypeCount[cv.getBlockCount()];
				int r_type = v.get(index - 1);
				cv.insert(Block.getBlock(r_type, resultBlockStart[j]));

			}
		}
	}

	private static void printResults() {
		Text.printFilled(cv.getFilled());
		SVG.create(cv.getFilled());
		Text.printTables();

		System.out.println("\nRESULT " + Main.resultCount);
	}

	private static boolean allSolutionsFound() {
		if (resultCurrentTypeCount[cv.getBlockCount()] == resultMaxTypes[cv.getBlockCount()]
				&& resultCurrentType[cv.getBlockCount()] == -1) {
			factor = 0;
			if (cv.getBlockCount() == 0) {
				return true;
			}

		}
		return false;
	}

}