package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Command;

public class MyView extends CommonView {

	public MyView(BufferedReader in, PrintWriter out) {
		super(in, out);
	}

	@Override
	public void notifyMazeIsReady(String name) {
		out.println("The Maze" + name + " is ready");
		out.flush();
	}

	@Override
	public void displayMaze(Maze3d maze) {
		out.println(maze);
		out.flush();

	}

	@Override
	public void printAnswers(String[] args) {
		for (String line : args) {
			out.println(line);
			out.flush();
		}
	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		cli.setCommands(commands);

	}

	@Override
	public void start() {
		cli.start();

	}

	@Override
	public void printCross(int[][] cross) {
		for (int[] i : cross) {
			for (int j : i) {
				out.print(j + " ");
			}
			out.println("");
		}
		out.println(""); 
	}

	@Override
	public void notifySolutionIsReady(String name) {
		out.println("The Solution for Maze" + name + " is ready");
		out.flush();
		
	}

	@Override
	public void displayMazeSolution(Solution<Position> solution) {
		out.println(solution);
		
	}

	@Override
	public void printErrorMessage(String[] msg) {
		for(String line : msg){
			out.println(line);
		}
		
	}

}
