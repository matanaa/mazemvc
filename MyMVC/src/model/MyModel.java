package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import controller.Controller;

public class MyModel extends CommonModel {

	private Controller controller;

	public MyModel(Controller controller) {
		this.controller = controller;
	}

	public MyModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateMaze(String name, int floors, int rows, int colums) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				Maze3d maze = new SimpleMaze3dGenerator().generate(floors,rows,colums);
				mazeMap.put(name, maze);
				controller.display("The " + name + " maze is ready.");
			}
		});

	}

	@Override
	public Maze3d getMaze(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
