package io.github.amarcinkowski.tetromino;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.neuland.jade4j.exceptions.JadeException;
import io.github.amarcinkowski.tetromino.algorithm.Algorithm;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;
import io.github.amarcinkowski.tetromino.visualisation.FileHelper;
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
		int i = 0;
		try {
			for (CubeVolume cubeVolume : solutions) {
				saveToFile("s" + ++i, cubeVolume);
			}
			addAuxiliaryFiles();
		} catch (JadeException | IOException e) {
			e.printStackTrace();
		}
		logger.info(String.format("Solutions: %d | Time taken %d sec", solutions.size(),
				(System.currentTimeMillis() - startTime) / 1000));
	}

	private static void addAuxiliaryFiles() throws IOException {
		// SVG auxiliary files
		FileHelper.string2File("svg.css", FileHelper.file2String("src/main/resources/svg.css"));
		HashMap<String, String> svgFiles = FileHelper.readAll2String("src/main/resources", "svg");
		svgFiles.entrySet().stream().forEach(entry -> {
			try {
				FileHelper.string2File(entry.getKey().replaceAll("src/main/resources/", ""), entry.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	private static void saveToFile(String name, CubeVolume cubeVolume) throws JadeException, IOException {
		FileHelper.string2File(name + ".txt", Text.toString(cubeVolume));
		FileHelper.string2File(name + ".html", SVG.toString(cubeVolume));
	}

}
