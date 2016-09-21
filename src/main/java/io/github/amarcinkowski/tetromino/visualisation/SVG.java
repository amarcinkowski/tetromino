package io.github.amarcinkowski.tetromino.visualisation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.exceptions.JadeException;
import de.neuland.jade4j.template.JadeTemplate;
import io.github.amarcinkowski.tetromino.math.Conversion;
import io.github.amarcinkowski.tetromino.math.XYZTBlock;

public class SVG {

	private static void createFile(String fileString) throws IOException {
		Date d = new Date();
		String filename = String.format("bin/%ty%tm%td%tH.html", d, d, d, d);
		Files.write(Paths.get(filename), fileString.getBytes());
	}

	private static void createSvg(List<XYZTBlock> blocks) {
		JadeConfiguration configuration = new JadeConfiguration();
		configuration.setMode(Jade4J.Mode.XHTML);
		configuration.setPrettyPrint(true);
		try {
			JadeTemplate template = configuration.getTemplate("src/main/resources/svg.jade");

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("pageName", "tetromino");
			model.put("blocks", blocks);

			String s = configuration.renderTemplate(template, model);
			System.out.println(s);
			createFile(s);
		} catch (JadeException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void create(int[] filled) {
		List<XYZTBlock> blocks = Conversion.convertFilledToBlocks(filled);
		createSvg(blocks);
	}

}
