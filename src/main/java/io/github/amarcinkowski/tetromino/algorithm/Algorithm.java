package io.github.amarcinkowski.tetromino.algorithm;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.tetromino.visualisation.SVG;
import io.github.amarcinkowski.tetromino.visualisation.Text;

public class Algorithm {

	private static Logger logger = LoggerFactory.getLogger(Algorithm.class);

	private static CubeVolume cubeVolume = new CubeVolume();

	/**
	 * np. [1,14,18] 0 > rCT > 32
	 */
	public static int resultCurrentType[] = new int[CubeVolume.MAX_BLOCK];
	/**
	 * np. [1,3,3] 0 > rCTC > possibilities(space)
	 */
	public static int resultCurrentTypeCount[] = new int[CubeVolume.MAX_BLOCK];
	/**
	 * np. [4,3,5] rMT = possib(space)
	 */
	public static int resultMaxTypes[] = new int[CubeVolume.MAX_BLOCK];
	/**
	 * np. [1,5,8] 3 block, starts in 1, 5, and 8 of CV
	 */
	public static int resultBlockStart[] = new int[CubeVolume.MAX_BLOCK];

	public static void run() {
		while (Algorithm.step()) {
		}

	}

	public static boolean step() {

		cubeVolume.findNextEmptySpace();

		boolean failureEmptySpace = cubeVolume.hasEmptySpaceThatCannotBeFilledWithABlock();
		int blockcount = cubeVolume.getBlockCount();
		boolean noMorePossibleInserts = noMorePossibleInsertsInThisBranch(blockcount);
		
		if (noMorePossibleInserts) {
			if (blockcount == 0) {
				logger.info("All solutions found");
				return false;
			}
		}

		if (noMorePossibleInserts || failureEmptySpace) {
			removeLastInsertedBlock();
		} else {
			insertNextPossibleBlock();
		}

		solutionFound();
		return true;
	}

	private static boolean noMorePossibleInsertsInThisBranch(int blockcount) {
		return resultCurrentTypeCount[blockcount] == resultMaxTypes[blockcount] && resultCurrentType[blockcount] == -1;
	}

	private static void insertNextPossibleBlock() {
		Vector<Integer> vector = cubeVolume.possibileInsertsVector(CubeVolume.cubeVolumePointer);
		resultMaxTypes[cubeVolume.getBlockCount()] = vector.size();

		Integer index = resultCurrentTypeCount[cubeVolume.getBlockCount()];
		Integer r_type = vector.get(index);

		int block[] = BlockHelper.getBlock(r_type, CubeVolume.cubeVolumePointer);

		if (cubeVolume.insert(block)) {
			logger.trace("INSERT");
			resultCurrentType[cubeVolume.getBlockCount() - 1] = r_type;
			resultCurrentTypeCount[cubeVolume.getBlockCount() - 1]++;
			resultBlockStart[cubeVolume.getBlockCount() - 1] = CubeVolume.cubeVolumePointer;
		}
	}

	private static void removeLastInsertedBlock() {
		logger.trace("REMOVE");
		int r_type = resultCurrentType[cubeVolume.getBlockCount() - 1];
		int r_space = resultBlockStart[cubeVolume.getBlockCount() - 1];
		int block[] = BlockHelper.getBlock(r_type, r_space);
		if (cubeVolume.remove(block)) {
			CubeVolume.cubeVolumePointer = r_space;
			resultCurrentType[cubeVolume.getBlockCount()] = -1;
			resultCurrentTypeCount[cubeVolume.getBlockCount() + 1] = 0;
		}
	}

	private static void solutionFound() {
		if (cubeVolume.isSolution()) {
			logger.info("Found result");

			Text.printFilled(cubeVolume.getFilled());

			List<Block> blocks = cubeVolume.getBlockList();
			SVG.create(blocks);

			stepBackForNextSolution();
		}
	}

	private static void stepBackForNextSolution() {
		cubeVolume = new CubeVolume();

		int i;
		for (i = CubeVolume.MAX_BLOCK - 1; i > 0; i--)
			if (resultCurrentTypeCount[i] == resultMaxTypes[i]) {
				resultCurrentType[i] = -1;
			} else {
				break;
			}
		Vector<Integer> vector = new Vector<Integer>();
		for (int j = 0; j < i; j++) {
			vector = cubeVolume.possibileInsertsVector(resultBlockStart[j]);
			int index = resultCurrentTypeCount[cubeVolume.getBlockCount()];
			int r_type = vector.get(index - 1);
			cubeVolume.insert(BlockHelper.getBlock(r_type, resultBlockStart[j]));
		}
	}

}
