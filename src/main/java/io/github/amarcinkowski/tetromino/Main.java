package io.github.amarcinkowski.tetromino;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.tetromino.algorithm.Algorithm;

/**
 * The Class Main.
 */
public class Main {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	/** The start time. */
	public static long startTime = System.currentTimeMillis();

	/** The node count. */
	private static int nodeCount = 0;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		while (Algorithm.branchNBound()) {
			nodeCount++;
		}
		logger.info(String.format("Node Count %d \nTIME %d sec", nodeCount,
				(System.currentTimeMillis() - startTime) / 1000));
	}

}
