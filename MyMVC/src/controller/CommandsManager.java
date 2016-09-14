package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.DFS;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
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
		commands.put("display_cross", new Display_Cross_Section());
		commands.put("save_maze", new save_maze());
		commands.put("load_maze", new load_maze());
		commands.put("solve",new solveMaze3d());
		commands.put("display_solution",new displayMazeSolution());
		commands.put("exit", new exit());
		return commands;
	}

	public class GenerateMazeCommand implements Command {
		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int floors = Integer.parseInt(args[1]);
			int rows = Integer.parseInt(args[2]);
			int cols = Integer.parseInt(args[3]);
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

	public class Display_Cross_Section implements Command {
		@Override
		public void doCommand(String[] args) {
			String index = args[0];
			String cross = args[1].toLowerCase();
			String name = args[2];
			Maze3d maze = model.getMaze(name);
			switch (cross) {
			case "z":
				view.printCross(maze.getCrossSectionByZ(Integer.parseInt(index)));
				break;
			case "y":
				view.printCross(maze.getCrossSectionByY(Integer.parseInt(index)));
				break;
			case "x":
				view.printCross(maze.getCrossSectionByX(Integer.parseInt(index)));
				break;
			default:
				break;
			}

		}

	}

	public class save_maze implements Command {
		@Override
		public void doCommand(String[] args) throws IOException {
			String name = args[0];
			String file_name = args[1];
			Maze3d maze = model.getMaze(name);
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(file_name + ".maz"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		}

	}

	public class load_maze implements Command {
		@Override
		public void doCommand(String[] args) throws FileNotFoundException, IOException {
			model.loadMaze(args[0], args[1]);
		}

	}
	
	public class solveMaze3d implements Command{

		@Override
		public void doCommand(String[] args) {
			String name=args[0];
			String algorithm=args[1];
			model.solveMaze3d(name,getCommandsMap(algorithm));		
		}
		
		public CommonSearcher<Position> getCommandsMap(String algName) {
			HashMap<String,  CommonSearcher<Position>> commands = new HashMap<String,  CommonSearcher<Position>>();
			commands.put("bfs", new BFS<Position>());
			commands.put("dfs", new DFS<Position>());
			return commands.get(algName);
		}
		
	}
	
	public class displayMazeSolution implements Command{

		@Override
		public void doCommand(String[] args) {
			String name=args[0];
			//Solution<Position> solution=model.getMazeSolution(name);
			//view.displayMazeSolution(solution);
		}
		
	} 
	
	public class exit implements Command{

		@Override
		public void doCommand(String[] args) {
			
		}
		
	}

}
