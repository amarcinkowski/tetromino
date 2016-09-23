package io.github.amarcinkowski.tetromino.algorithm;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Algorithm {

	private static Logger logger = LoggerFactory.getLogger(Algorithm.class);

	private CubeVolume cubeVolume = new CubeVolumeBuilder().empty().build();

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

	public Vector<CubeVolume> run() {
		while (hasMoreSteps()) {
		}
		return solutions;
	}

	public boolean hasMoreSteps() {

		cubeVolume.findNextEmptySpace();
		boolean irregularEmptySpace = cubeVolume.hasEmptySpaceThatCannotBeFilledWithABlock();
		boolean noMorePossibleInserts = noMorePossibleInsertsInThisBranch(cubeVolume.getBlockCount());

		if (noMorePossibleInserts && cubeVolume.isEmpty()) {
			logger.info("All solutions found");
			return false;
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
		Vector<Integer> vector = cubeVolume.possibileInserts(cubeVolume.cubeVolumePointer);
		resultMaxTypes[cubeVolume.getBlockCount()] = vector.size();

		Integer index = resultCurrentTypeCount[cubeVolume.getBlockCount()];
		Integer r_type = vector.get(index);

		int block[] = BlockHelper.getBlock(r_type, cubeVolume.cubeVolumePointer);

		if (cubeVolume.insert(block)) {
			resultCurrentType[cubeVolume.getBlockCount() - 1] = r_type;
			resultCurrentTypeCount[cubeVolume.getBlockCount() - 1]++;
			resultBlockStart[cubeVolume.getBlockCount() - 1] = cubeVolume.cubeVolumePointer;
		}
	}

	private void removeLastInsertedBlock() {
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
			logger.info("solution found");
			solutions.add(cubeVolume);
			stepBackForTheNextSolution();
		}
	}

	private int findNextBranchStep() {
		int n;
		for (n = CubeVolume.MAX_BLOCK - 1; n > 0; n--) {
			if (resultCurrentTypeCount[n] == resultMaxTypes[n]) {
				resultCurrentType[n] = -1;
			} else {
				break;
			}
		}
		return n;
	}

	private void stepBackForTheNextSolution() {
		cubeVolume = new CubeVolumeBuilder().empty().build();
		int n = findNextBranchStep();
		recreateCube(n);
	}

	private void recreateCube(int n) {
		for (int j = 0; j < n; j++) {
			Vector<Integer> possibleInserts = cubeVolume.possibileInserts(resultBlockStart[j]);
			int index = resultCurrentTypeCount[cubeVolume.getBlockCount()];
			int r_type = possibleInserts.get(index - 1);
			cubeVolume.insert(BlockHelper.getBlock(r_type, resultBlockStart[j]));
		}

	}

}
