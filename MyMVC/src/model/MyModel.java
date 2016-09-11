package model;

import algorithms.mazeGenerators.GrowingTreeMaze3dGenerator;
import algorithms.mazeGenerators.Maze3d;

public class MyModel extends CommonModel {



	public MyModel() {
		super();
	}

	@Override
	public void generateMaze(String name, int floors, int rows, int cols) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				GrowingTreeMaze3dGenerator generator = new GrowingTreeMaze3dGenerator();
				Maze3d maze = generator.generate(floors, rows, cols);
				mazeMap.put(name, maze);

				controller.notifyMazeIsReady(name);
			}
		});
		thread.start();
		threadPool.submit(thread);
	}

	@Override
	public Maze3d getMaze(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
