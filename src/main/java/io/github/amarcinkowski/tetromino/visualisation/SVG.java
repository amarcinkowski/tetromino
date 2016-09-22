package io.github.amarcinkowski.tetromino.visualisation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.exceptions.JadeException;
import de.neuland.jade4j.template.JadeTemplate;
import io.github.amarcinkowski.tetromino.math.BlockType;
import io.github.amarcinkowski.tetromino.math.XYZTBlock;

public class SVG {

	public static void create(List<XYZTBlock> blocks) {
		JadeConfiguration configuration = new JadeConfiguration();
		configuration.setMode(Jade4J.Mode.XHTML);
		configuration.setPrettyPrint(true);
		try {
			JadeTemplate template = configuration.getTemplate("src/main/resources/svg.jade");

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("pageName", "tetromino");
			model.put("blocks", blocks);
			model.put("blockTypes", BlockType.values());

			String s = configuration.renderTemplate(template, model);
			System.out.println(s);
			FileHelper.string2File(s);
		} catch (JadeException | IOException e1) {
			e1.printStackTrace();
		}
	}

}
