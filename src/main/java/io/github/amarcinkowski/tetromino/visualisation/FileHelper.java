package io.github.amarcinkowski.tetromino.visualisation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.amarcinkowski.tetromino.algorithm.Algorithm;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolumeBuilder;

public class FileHelper {

	private static final String VOLUME_LINE_FORMAT = "(([\n ]+[0-9]+)+[ \\|]+)+";

	private static Logger logger = LoggerFactory.getLogger(Algorithm.class);

	final static Date date = new Date();

	private static String createDir() {
		String directory = String.format("out/%ty%tm%td%tH", date, date, date, date);
		new File(directory).mkdirs();
		return directory;
	}

	public static void string2File(String filename, String contents) throws IOException {
		String filepath = String.format("%s/%s", createDir(), filename);
		Files.write(Paths.get(filepath), contents.getBytes());
	}

	public static String file2String(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

	public static HashMap<String, String> readAll2String(String dir, String extension) throws IOException {
		HashMap<String, String> map = new HashMap<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
			for (Path entry : stream) {
				if (entry.getFileName().toString().endsWith(extension)) {
					map.put(entry.toString(), file2String(entry.toString()));
				}
			}
		}
		return map;
	}

	public static String ltrim(String s) {
		return s.replaceAll("^\\s+", "");
	}

	private static List<String> toList(String s) {
		return Arrays.asList(ltrim(s).split("\\s+"));
	}

	private static List<Integer> parseIntList(List<String> list) {
		return list.stream().map(Integer::parseInt).collect(Collectors.toList());
	}

	private static Multimap<Integer, List<Integer>> line2map(String line) {
		Multimap<Integer, List<Integer>> map = ArrayListMultimap.create();
		if (line.matches(VOLUME_LINE_FORMAT)) {
			String level[] = line.split("\\|");
			int levels = StringUtils.countMatches(line, "|");
			for (int i = 0; i < levels; i++) {
				List<Integer> l1 = parseIntList(toList(level[i]));
				map.put(i, l1);
			}
		}
		return map;
	}

	private static List<Integer> mergeLists(Multimap<Integer, List<Integer>> map) {
		return map.values().stream().flatMap(List::stream).collect(Collectors.toList());
	}

	public static CubeVolume loadCube(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		Multimap<Integer, List<Integer>> map = ArrayListMultimap.create();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			logger.trace(line);
			map.putAll(line2map(line));
		}
		scanner.close();
		List<Integer> blocksList = mergeLists(map);
		int[] filled = blocksList.stream().mapToInt(Integer::intValue).toArray();
		return new CubeVolumeBuilder().filled(filled).build();
	}

}
