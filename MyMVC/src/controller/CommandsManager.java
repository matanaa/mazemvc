package controller;

import java.io.File;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public class CommandsManager {

	private Model model;
	private View view;

	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("dir", new Dir());

		return commands;
	}

	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int rows = Integer.parseInt(args[1]);
			int cols = Integer.parseInt(args[2]);
			int floors = Integer.parseInt(args[3]);
			model.generateMaze(name, floors, rows, cols);
		}
	}

	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Maze3d maze = model.getMaze(name);
			view.displayMaze(maze);
		}

	}

	public class Dir implements Command {

		@Override
		public void doCommand(String[] args) {
			String paths = args[0];
			File folderPath = null;
			String[] filelist;

			try {
				// create new file
				folderPath = new File(paths);

				// array of files and directory
				filelist = folderPath.list();

				// for each name in the path array
				view.printAnswers(filelist);
			} catch (Exception e) {
				// if any error occurs
				e.printStackTrace();
			}

		}

	}

}
