/*
 * Space.java
 *
 * Created on 28 grudzie� 2007, 21:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package io.github.amarcinkowski.tetromino;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.algorithm.Algorithm;

/**
 * The Class Main.
 *
 * @author am
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
//				if (nodeCount % 10000 == 0) {
//				logger.info("Count" + nodeCount + "\nTIME(sec)" + (System.currentTimeMillis() - startTime) / 1000);
//			}
				nodeCount++;
			}
	}

}
