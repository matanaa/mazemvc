package model;

import algorithms.mazeGenerators.Maze3d;

public class MyModel implements Model {
	
	private Controller controller;
	
	public MyModel(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void generateMaze(String name, int floors, int rows, int colums) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Maze3d getMaze(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
