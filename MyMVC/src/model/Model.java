package model;

import algorithms.mazeGenerators.Maze3d;

public interface Model {
	void generateMaze(String name, int floors, int rows, int colums);
	Maze3d getMaze(String name);
	
}
