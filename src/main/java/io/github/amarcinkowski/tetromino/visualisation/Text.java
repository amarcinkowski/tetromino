package io.github.amarcinkowski.tetromino.visualisation;

import io.github.amarcinkowski.algorithm.Algorithm;
import io.github.amarcinkowski.tetromino.math.Conversion;

public class Text {

	public static void printTables() {
		System.out.println("");
		for (int i = 0; i < 18; i++)
			if (i == Algorithm.getCv().getBlockCount())
				System.out.print("_\t");
			else
				System.out.print("\t");
		System.out.println("");
		for (int i = 0; i < 18; i++)
			System.out.print(Algorithm.resultCurrentType[i] + "\t");
		System.out.println("");
		for (int i = 0; i < 18; i++)
			System.out.print(Algorithm.resultCurrentTypeCount[i] + "\t");
		System.out.println("");
		for (int i = 0; i < 18; i++)
			System.out.print(Algorithm.resultMaxTypes[i] + "\t");
		System.out.println("");
		for (int i = 0; i < 18; i++)
			System.out.print(Algorithm.resultBlockStart[i] + "\t");
		System.out.println("");
	}

	public static void printFilled(int[] filled) {
		for (int z = 0; z < 2; z++) {
			System.out.println("\n== " + z + " ==");
			for (int y = 0; y < 6; y++) {
				System.out.print("\n" + y + "\t");
				for (int x = 0; x < 6; x++) {
					System.out.print(String.format("%3s",
							filled[Conversion.convertXYZtoN(x, y, z)]));
				}
			}
		}
	}

}
