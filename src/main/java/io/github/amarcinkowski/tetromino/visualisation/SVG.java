package io.github.amarcinkowski.tetromino.visualisation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.exceptions.JadeException;
import de.neuland.jade4j.template.JadeTemplate;
import io.github.amarcinkowski.tetromino.algorithm.Block;
import io.github.amarcinkowski.tetromino.algorithm.BlockType;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;

public class SVG {

	public static String toString(CubeVolume cubeVolume) throws JadeException, IOException {
		List<Block> blocks = cubeVolume.getBlockList();
		JadeConfiguration configuration = new JadeConfiguration();
		configuration.setMode(Jade4J.Mode.XHTML);
		configuration.setPrettyPrint(true);
		JadeTemplate template = configuration.getTemplate("src/main/resources/svg.jade");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pageName", "tetromino");
		model.put("defs", FileHelper.readAll2String("src/main/resources", "svg"));
		model.put("blocks", blocks);
		model.put("blockTypes", BlockType.values());

		String s = configuration.renderTemplate(template, model);
		return s;
	}

}
