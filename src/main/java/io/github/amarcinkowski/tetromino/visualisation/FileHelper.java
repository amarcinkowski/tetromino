package io.github.amarcinkowski.tetromino.visualisation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class FileHelper {

	public enum Extension {
		HTML, TXT
	}

	public static void string2File(String fileString, Extension extension) throws IOException {
		Date d = new Date();
		String filename = String.format("bin/%ty%tm%td%tH.%s", d, d, d, d, extension.toString().toLowerCase());
		Files.write(Paths.get(filename), fileString.getBytes());
	}

}
