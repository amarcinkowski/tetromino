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
import io.github.amarcinkowski.tetromino.algorithm.BlockDirection;
import io.github.amarcinkowski.tetromino.algorithm.CubeVolume;

public class SVG extends FileOutput {

	public SVG(CubeVolume cubeVolume) {
		super(cubeVolume);
		EXTENSION = "html";
	}

	protected String getContents() throws JadeException, IOException {
		List<Block> blocks = cubeVolume.getBlockList();
		JadeConfiguration configuration = new JadeConfiguration();
		configuration.setMode(Jade4J.Mode.XHTML);
		configuration.setPrettyPrint(true);
		JadeTemplate template = configuration.getTemplate("src/main/resources/svg.jade");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pageName", "tetromino");
		model.put("defs", FileHelper.readAll2String("src/main/resources", "svg"));
		model.put("blocks", blocks);
		model.put("blockTypes", BlockDirection.values());

		String s = configuration.renderTemplate(template, model);
		return s;
	}

	public static void addAuxiliaryFiles() throws IOException {
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

}
