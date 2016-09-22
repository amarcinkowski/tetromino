package io.github.amarcinkowski.tetromino.visualisation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.amarcinkowski.tetromino.math.Conversion;

public class Text {

//	private static Logger logger = LoggerFactory.getLogger(Text.class);

	public static void printFilled(int[] filled) {
		for (int z = 0; z < 2; z++) {
			System.out.println("\n== " + z + " ==");
			for (int y = 0; y < 6; y++) {
				System.out.print("\n" + y + "\t");
				for (int x = 0; x < 6; x++) {
					System.out.print(String.format("%3s", filled[Conversion.xyz2N(x, y, z)]));
				}
			}
		}
	}

}
