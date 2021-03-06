package io.github.amarcinkowski.tetromino;

import java.io.IOException;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.neuland.jade4j.exceptions.JadeException;
import io.github.amarcinkowski.tetromino.algorithm.Algorithm;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;
import io.github.amarcinkowski.tetromino.visualisation.SVG;
import io.github.amarcinkowski.tetromino.visualisation.Text;

/**
 * The Class Main.
 */
public class Main {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	/** The start time. */
	public static long startTime = System.currentTimeMillis();

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Vector<CubeVolume> solutions = new Algorithm().run();
		try {
			for (CubeVolume cubeVolume : solutions) {
				saveToFile(cubeVolume);
			}
			SVG.addAuxiliaryFiles();
		} catch (JadeException | IOException e) {
			e.printStackTrace();
		}
		logger.info(String.format("Solutions: %d | Time taken %d sec", solutions.size(),
				(System.currentTimeMillis() - startTime) / 1000));
	}

	private static void saveToFile(CubeVolume cubeVolume) throws JadeException, IOException {
		new SVG(cubeVolume).save();
		new Text(cubeVolume).save();
	}

}
