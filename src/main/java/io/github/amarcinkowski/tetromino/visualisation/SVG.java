package io.github.amarcinkowski.tetromino.visualisation;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.amarcinkowski.tetromino.math.Conversion;

public class SVG {

	/**
	 * svg playground
	 * http://www.w3schools.com/svg/tryit.asp?filename=trysvg_myfirst
	 */

	private static String fileString = "<!DOCTYPE html>\n<html>\n<head><style>body{colors:#999;}svg{width:100%;height:100%;min-width:1024px;min-height:768px;}\nimage {width: 80px; height: 80px;}</style></head><body>\n<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\nINNERSVG\n</body>\n</html>\n";
	private static String transformationString = "<g transform=\"scale(1)\" >\n OBJECTS \n</g>\n</svg> \n";
	private static String imgString = "<image xlink:href='/home/amarcinkowski/git/tetromino/src/main/resources/000##.svg' />";

	private static void createFile(String fileString) throws Exception {
		Date d = new Date();
		String filename = String.format("bin/%ty%tm%td%tH.html", d, d, d, d);
		Files.write(Paths.get(filename), fileString.getBytes());
	}

	private static void createSvg(String allPoly) {
		String returnString = fileString.replace("INNERSVG", transformationString.replace("OBJECTS", allPoly));
		try {
			createFile(returnString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String blockFactory(String s) {
		String ret = "";
		String pos = s.split("]")[0];
		System.out.println(s);
		s = s.split("]")[1];
		switch (s) {
		case "horizU":
			ret = imgString.replace("##", "1");
			break;
		case "horizN":
			ret = imgString.replace("##", "2");
			break;
		case "horizD":
			ret = imgString.replace("##", "3");
			break;
		case "horizS":
			ret = imgString.replace("##", "4");
			break;
		case "vertU":
			ret = imgString.replace("##", "5");
			break;
		case "vertE":
			ret = imgString.replace("##", "6");
			break;
		case "vertD":
			ret = imgString.replace("##", "7");
			break;
		case "vertW":
			ret = imgString.replace("##", "8");
			break;
		default:
			System.out.println("MISSING " + s);
			System.exit(0);
		}
		Matcher matcher = Pattern.compile("([0-9]+), ([0-9]+), ([0-9]+)").matcher(pos);
		matcher.find();
		int x = Integer.parseInt(matcher.group(1));
		int y = Integer.parseInt(matcher.group(2));
		int z = Integer.parseInt(matcher.group(3));
		ret = "<g transform=\"translate(" + (6 * x - y * 2.95) + " " + (y * 2.65 + x * 1.25 - 5.9 * z) + ")\">" + ret + "</g>";
		return ret;
	}

	public static void create(int[] filled) {
		String s[] = Conversion.convertFilledTo2D(filled).split(";");
		create(s);
	}

	public static void create(String[] s) {
		String allPoly = "";
		for (String poly : s) {
			allPoly += blockFactory(poly);
		}
		createSvg(allPoly);
	}

	public static void main(String[] args) {
		SVG.create(new String[] { 

				"[0, 0, 0]horizU",
				 "[0, 0, 1]horizD",
				
		});
	}
}
