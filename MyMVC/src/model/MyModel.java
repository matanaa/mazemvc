package model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import algorithm.demo.MazeAdapter;
import algorithms.mazeGenerators.CommonMaze3dGenerator;
import algorithms.mazeGenerators.GrowingTreeMaze3dGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.CommonSearcher;
import algorithms.search.Searchable;
import algorithms.search.Solution;
import io.MyDecompressorInputStream;

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

	@Override
	public void loadMaze(String file, String name) throws IOException {
		try {
			InputStream in = new FileInputStream(file);
			MyDecompressorInputStream input = new MyDecompressorInputStream(in);
			byte[] arr =new byte [(int)file.length()];
			try {
				input.read(arr);
				Maze3d maze = new Maze3d(arr);
				mazeMap.put(name, maze);
				input.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void solveMaze3d(String name, CommonSearcher<Position> searcher) {
if (searcher==null)
{
	// TODO add error
}
		Thread thread=new Thread(new Runnable() { 
			
			@Override
			public void run() {

				Maze3d maze=mazeMap.get(name);
				Searchable searchableMaze= new MazeAdapter(maze);
				Solution<Position> solution=searcher.search(searchableMaze);
				solutionMap.put(name, solution);
				controller.notifySolutionIsReady(name);
			}
					
		});
		thread.start();
		threadPool.submit(thread);
		
	
	}


}
