package io.github.amarcinkowski.tetromino.algorithm;

import java.io.IOException;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.neuland.jade4j.exceptions.JadeException;
import io.github.amarcinkowski.tetromino.visualisation.FileHelper;
import io.github.amarcinkowski.tetromino.visualisation.SVG;
import io.github.amarcinkowski.tetromino.visualisation.Text;

public class Algorithm {

	private static Logger logger = LoggerFactory.getLogger(Algorithm.class);

	private CubeVolume cubeVolume = new CubeVolume();

	private Vector<CubeVolume> solutions = new Vector<>();

	/**
	 * np. [1,14,18] 0 > rCT > 32
	 */
	public int resultCurrentType[] = new int[CubeVolume.MAX_BLOCK];
	/**
	 * np. [1,3,3] 0 > rCTC > possibilities(space)
	 */
	public int resultCurrentTypeCount[] = new int[CubeVolume.MAX_BLOCK];
	/**
	 * np. [4,3,5] rMT = possib(space)
	 */
	public int resultMaxTypes[] = new int[CubeVolume.MAX_BLOCK];
	/**
	 * np. [1,5,8] 3 block, starts in 1, 5, and 8 of CV
	 */
	public int resultBlockStart[] = new int[CubeVolume.MAX_BLOCK];

	public void run() {
		while (hasMoreSteps()) {
		}
		for (CubeVolume cubeVolume : solutions) {
			saveToFile(cubeVolume);
		}
	}

	public boolean hasMoreSteps() {

		cubeVolume.findNextEmptySpace();

		int blockcount = cubeVolume.getBlockCount();

		boolean irregularEmptySpace = cubeVolume.hasEmptySpaceThatCannotBeFilledWithABlock();
		boolean noMorePossibleInserts = noMorePossibleInsertsInThisBranch(blockcount);

		if (noMorePossibleInserts) {
			if (blockcount == 0) {
				logger.info("All solutions found");
				return false;
			}
		}

		if (noMorePossibleInserts || irregularEmptySpace) {
			removeLastInsertedBlock();
		} else {
			insertNextPossibleBlock();
		}

		checkIfSolutionFound();
		return true;
	}

	private boolean noMorePossibleInsertsInThisBranch(int blockcount) {
		return resultCurrentTypeCount[blockcount] == resultMaxTypes[blockcount] && resultCurrentType[blockcount] == -1;
	}

	private void insertNextPossibleBlock() {
		Vector<Integer> vector = cubeVolume.possibileInsertsVector(cubeVolume.cubeVolumePointer);
		resultMaxTypes[cubeVolume.getBlockCount()] = vector.size();

		Integer index = resultCurrentTypeCount[cubeVolume.getBlockCount()];
		Integer r_type = vector.get(index);

		int block[] = BlockHelper.getBlock(r_type, cubeVolume.cubeVolumePointer);

		if (cubeVolume.insert(block)) {
			logger.trace("INSERT");
			resultCurrentType[cubeVolume.getBlockCount() - 1] = r_type;
			resultCurrentTypeCount[cubeVolume.getBlockCount() - 1]++;
			resultBlockStart[cubeVolume.getBlockCount() - 1] = cubeVolume.cubeVolumePointer;
		}
	}

	private void removeLastInsertedBlock() {
		logger.trace("REMOVE");
		int r_type = resultCurrentType[cubeVolume.getBlockCount() - 1];
		int r_space = resultBlockStart[cubeVolume.getBlockCount() - 1];
		int block[] = BlockHelper.getBlock(r_type, r_space);
		if (cubeVolume.remove(block)) {
			cubeVolume.cubeVolumePointer = r_space;
			resultCurrentType[cubeVolume.getBlockCount()] = -1;
			resultCurrentTypeCount[cubeVolume.getBlockCount() + 1] = 0;
		}
	}

	private void checkIfSolutionFound() {
		if (cubeVolume.isSolution()) {
			solutions.add(cubeVolume);
			stepBackForNextSolution();
		}
	}

	private void saveToFile(CubeVolume cubeVolume) {
		try {
			FileHelper.string2File(Text.toString(cubeVolume), FileHelper.Extension.TXT);
			FileHelper.string2File(SVG.toString(cubeVolume), FileHelper.Extension.HTML);
		} catch (JadeException | IOException e) {
			e.printStackTrace();
		}
	}

	private void stepBackForNextSolution() {
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
