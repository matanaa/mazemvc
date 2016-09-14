package model;

import java.io.FileNotFoundException;
import java.io.IOException;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.CommonSearcher;
import algorithms.search.Solution;
import controller.Controller;

public interface Model {
	void generateMaze(String name, int floors, int rows, int colums);
	Maze3d getMaze(String name);
	void exit();
	void setController(Controller controller);
	void loadMaze(String file_name, String name) throws FileNotFoundException, IOException;
	void solveMaze3d(String name, CommonSearcher<Position> commonSearcher);
	Solution<Position> getMazeSolution(String name);
	void finishThreads();
	void save_maze(String name, String file_name);
	void waitUntilCloseAllFiles();
	
}
