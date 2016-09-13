package model;


import java.util.ArrayList;
import java.util.List;

import algorithms.mazeGenerators.CommonMaze3dGenerator;
import algorithms.mazeGenerators.GrowingTreeMaze3dGenerator;
import algorithms.mazeGenerators.Maze3d;

public class MyModel extends CommonModel {

	private List<generateMazeRunnable> generateMazeTasks = new ArrayList<generateMazeRunnable>();

	public MyModel() {
		super();
	}
	
	class generateMazeRunnable implements Runnable {
		private int floors, rows, colums;
		private String name;
		private CommonMaze3dGenerator generator;
		
		public generateMazeRunnable(int floors, int rows, int colums, String name) {
			super();
			this.floors = floors;
			this.rows = rows;
			this.colums = colums;
			this.name = name;
		}

		@Override
		public void run() {
			generator = new GrowingTreeMaze3dGenerator();
			Maze3d maze = generator.generate(floors, rows, colums);
			mazeMap.put(name, maze);
			controller.notifyMazeIsReady(name);
		}
		
		public void terminate(){
			generator.setDone(true);
		}
		
	}

	@Override
	public void generateMaze(String name, int floors, int rows, int cols) {
		generateMazeRunnable generateMaze = new generateMazeRunnable(floors, rows, cols, name);
		generateMazeTasks.add(generateMaze);
		Thread thread = new Thread(generateMaze);
		thread.start();
		threadPool.submit(generateMaze);
	}

	@Override
	public Maze3d getMaze(String name) {
		return mazeMap.get(name);
	}

	@Override
	public void exit() {
		for (generateMazeRunnable task : generateMazeTasks){
			task.terminate();
		}
	}



}
