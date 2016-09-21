package pl.siata83.tetromino.visualisation;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.Date;

import pl.siata83.tetromino.math.Conversion;

public class SVG {

	private static DateFormat format = DateFormat.getDateTimeInstance(
			DateFormat.SHORT, DateFormat.MEDIUM);
	/**
	 * svg playground
	 * http://www.w3schools.com/svg/tryit.asp?filename=trysvg_myfirst
	 */

	private static String fileString = "<!DOCTYPE html>\n<html>\n<body>\n<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\nINNERSVG\n</body>\n</html>\n";
	private static String transformationString = "<g transform=\"scale(3)\" >\n OBJECTS \n</g>\n</svg> \n";
	private static String blockFlatString = "<polygon points=\"10,10 20,10 20,0 30,0 30,10 40,10 40,20 10,20\"\n  style=\"fill:rgba(COLOR,0.8);stroke:black;stroke-width:2\" transform=\"translate(TRANSX,TRANSY),rotate(ROT 0,0)\"/>\n  ";
	private static String blockUpString = "<polygon points=\"10,10 40,10 40,20 10,20\"\n  style=\"fill:rgba(COLOR,0.5);stroke:black;stroke-width:2\" transform=\"translate(TRANSX,TRANSY),rotate(ROT 0,0)\"/>\n"
			+ "\t\t<polygon points=\"20,10 30,10 30,20 20,20\"\n  style=\"fill:rgba(COLOR,0.5);stroke:black;stroke-width:2\" transform=\"translate(TRANSX,TRANSY),rotate(ROT 0,0)\"/>\n  ";
	private static String blockDownString = "<polygon points=\"10,10 40,10 40,20 10,20 \"\n  style=\"fill:rgba(COLOR,0.5);stroke:black;stroke-width:2\" transform=\"translate(TRANSX,TRANSY),rotate(ROT 0,0)\"/>\n  ";
//	private static String blockOneString = "<polygon points=\"10,10 20,10 20,20 10,20 \"\n  style=\"fill:rgba(COLOR,0.5);stroke:black;stroke-width:2\" transform=\"translate(TRANSX,TRANSY),rotate(ROT 0,0)\"/>\n  ";

	private static void createFile(String fileString) throws Exception {
		File file = new File("svg/out"
				+ new String(format.format(new Date())).replaceAll("[ \\:\\.]",
						"") + (int) (Math.random() * 100) + ".html");
		if (!file.createNewFile()) {
			throw new Exception("Cannot create file!");
		} else {
			FileWriter writer = new FileWriter(file);
			writer.append(fileString);
			writer.flush();
			writer.close();
		}
	}

	private static void createSvg(String allPoly) {
		String returnString = fileString.replace("INNERSVG",
				transformationString.replace("OBJECTS", allPoly));
		try {
			createFile(returnString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String blockFactory(String s) {
		int transx = 0;
		String ret = "";
		if (s.contains("U")) {
			ret = blockUpString;
		} else if (s.contains("D")) {
			ret = blockDownString;
			transx -= 10;
		} else if (s.contains("W") || s.contains("S") || s.contains("E")
				|| s.contains("N")) {
			ret = blockFlatString;
		}
		if (s.contains("vert")) {
			ret = ret.replaceAll("ROT", "90");
			transx += 30;
		} else {
			ret = ret.replaceAll("ROT", "0");
		}
		String xy = s.split("]")[0].replace("[", "").replaceAll(" ", "");
		ret = ret.replaceAll("TRANSX", ""
				+ (Integer.parseInt(xy.split(",")[0]) * 10 + transx));
		ret = ret.replaceAll("TRANSY", ""
				+ (Integer.parseInt(xy.split(",")[1]) * 10));
		ret = ret.replaceAll("COLOR", "" + (int) (Math.random() * 255) + ","
				+ (int) (Math.random() * 255) + ","
				+ (int) (Math.random() * 255));
		return ret;
	}

	public static void create(int[] filled) {
		String allPoly = "";
		String s[] = Conversion.convertFilledTo2D(filled).split(";");
		for (String poly : s) {
			allPoly += blockFactory(poly);
		}
		createSvg(allPoly);
	}
}
