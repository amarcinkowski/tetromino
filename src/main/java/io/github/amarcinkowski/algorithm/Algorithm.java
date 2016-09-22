package io.github.amarcinkowski.algorithm;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.tetromino.Main;
import io.github.amarcinkowski.tetromino.math.Conversion;
import io.github.amarcinkowski.tetromino.math.XYZTBlock;
import io.github.amarcinkowski.tetromino.visualisation.SVG;
import io.github.amarcinkowski.tetromino.visualisation.Text;

public class Algorithm {

	private static CubeVolume cv = new CubeVolume();
	
	private static int resultCount = 0;

	/**
	 * Gets the cv.
	 * 
	 * @return the cv
	 */
	public static CubeVolume getCv() {
		return cv;
	}

	private static int space = 0;

	/** The Constant MAX_BLOCK. */
	private final static int MAX_BLOCK = CubeVolume.VOLUME / Block.BLOCK_VOLUME;

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

	public static boolean branchNBound() {

		while (!cv.isEmpty(space)) {
			space++;
		}

		factor = cv.factor();

		if (allSolutionsFound()) {
			logger.info("--->>>> all solutions found");
			return false;
		}

		if (factor == 0) {
			removeLastInsertedBlock();
		} else {
			insertNextPossibleBlock();
		}

		solutionFound();
		
		return true;
	}

	private static void insertNextPossibleBlock() {
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

	private static void removeLastInsertedBlock() {
		logger.trace("REMOVE");
		int r_type = resultCurrentType[cv.getBlockCount() - 1];
		int r_space = resultBlockStart[cv.getBlockCount() - 1];
		Integer block[] = Block.getBlock(r_type, r_space);
		if (cv.remove(block)) {
			space = r_space;
			resultCurrentType[cv.getBlockCount()] = -1;
			resultCurrentTypeCount[cv.getBlockCount() + 1] = 0;
		}
	}

	private static void solutionFound() {
		if (cv.getBlockCount() == MAX_BLOCK) {
			resultCount++;

			printResults();
//			saveToSVG();
			
			cv = new CubeVolume();
			space = 0;

			// PO UZYSKANIU WYNIKU COFNIJ SIE DO OSTATNIEJ GALEZI Z DWOMA LISCMI
			// I WEJSDZ DO 2 LISCIA
			int i;
			for (i = MAX_BLOCK - 1; i > 0; i--)
				if (resultCurrentTypeCount[i] == resultMaxTypes[i]) {
					resultCurrentType[i] = -1;
				} else {
					break;
				}
			for (int j = 0; j < i; j++) {
				v = cv.possibileInsertsVector(resultBlockStart[j]);
				int index = resultCurrentTypeCount[cv.getBlockCount()];
				int r_type = v.get(index - 1);
				cv.insert(Block.getBlock(r_type, resultBlockStart[j]));

			}
		}
	}

	private static void printResults() {
		logger.info(String.format("Result", resultCount));
		Text.printFilled(cv.getFilled());
	}
	
	private static void saveToSVG() {
		List<XYZTBlock> blocks = Conversion.convertFilledToBlocks(cv.getFilled());
		SVG.create(blocks);
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
