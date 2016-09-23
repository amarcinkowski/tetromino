package io.github.amarcinkowski.tetromino.visualisation;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;

public class FileHelper {

	final static Date date = new Date();

	private static String createDir() {
		String directory = String.format("svg/%ty%tm%td%tH", date, date, date, date);
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

}
