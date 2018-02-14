package top.choppy.mazes.solver;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ColorProcessor;
import top.choppy.util.Coordinate2D;

public class Maze {
	protected HashMap<Integer, Integer> walls = new HashMap<Integer, Integer>(); // Locations of black pixels

	protected ArrayList<Coordinate2D> entrance = new ArrayList<Coordinate2D>();
	protected ArrayList<Coordinate2D> end = new ArrayList<Coordinate2D>();

	private final static String toHexString(Color colour) throws NullPointerException {
		  String hexColour = Integer.toHexString(colour.getRGB() & 0xffffff);
		  if (hexColour.length() < 6) {
		    hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
		  }
		  return "#" + hexColour;
		}
	
	public Maze(File file) {

		Opener opener = new Opener();
		ImagePlus imp = opener.openImage(file.getAbsolutePath());
		ColorProcessor ip = imp.getProcessor().convertToColorProcessor();

		int height = ip.getHeight();
		int width = ip.getWidth();
		


		// Find the walls (black pixels)
		for (int y = height; y > 0; y--) {
			for (int x = width; x > 0; x--) {
				Coordinate2D coord = new Coordinate2D(x, y);

				
				int c = ip.getPixel(x, y);
				int r = (c&0xff0000)>>16;
				int g = (c&0xff00)>>8;
				int b = c&0xff;
				Color color = new Color(r,g,b);
				
				if (toHexString(color).equals("#000000")) {
					walls.put(x, y);
					System.out.println(coord);
				}
			}

		}

		System.out.println(Arrays.toString(walls.keySet().toArray()));
		// Get the entrance & exit; I am assuming the entrance is on the top row and
		// exit on the bottom row

		for (int x = width; x > 0; x--) {
			if (!(wallAt(x, height))) {
				entrance.add(new Coordinate2D(x, height));
			}
		}

		for (int x = width; x > 0; x--) {
			if (!(wallAt(x, 1))) {
				end.add(new Coordinate2D(x, 1));
			}
		}

	}

	public ArrayList<Coordinate2D> getEntrance() {
		return entrance;
	}

	public ArrayList<Coordinate2D> getEnd() {
		return end;
	}

	public boolean wallAt(int x, int y) {
		for (Entry<Integer, Integer> entry : walls.entrySet()) {
			if (entry.getKey() == x && entry.getValue() == y)
				return true;
		}

		return false;
	}

}
