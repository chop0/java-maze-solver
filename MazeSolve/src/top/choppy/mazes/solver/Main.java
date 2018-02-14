package top.choppy.mazes.solver;

import java.io.File;
import java.util.Arrays;

public class Main {
	public static void main(String[] argv) {
		Maze maze = new Maze(new File(argv[0]));
		
		System.out.println(Arrays.toString(maze.getEntrance().toArray()));
		System.out.println(Arrays.toString(maze.getEnd().toArray()));
	}
}
