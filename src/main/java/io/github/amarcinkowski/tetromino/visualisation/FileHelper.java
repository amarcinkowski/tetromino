package io.github.amarcinkowski.tetromino.visualisation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class FileHelper {

	public static void string2File(String fileString) throws IOException {
		Date d = new Date();
		String filename = String.format("bin/%ty%tm%td%tH.html", d, d, d, d);
		Files.write(Paths.get(filename), fileString.getBytes());
	}

}
