package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel extends CommonModel {

	protected List<generateMazeRunnable> generateMazeTasks = new ArrayList<generateMazeRunnable>();
	protected int openFileCount = 0;// willsave how many files are open new

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

		public void terminate() {
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
		for (generateMazeRunnable task : generateMazeTasks) {
			task.terminate();
		}
	}

	@Override
	public void loadMaze(String file, String name) {
		InputStream in;
		openFileCount++;
		try {
			in = new MyDecompressorInputStream(new FileInputStream(file + ".maz"));
			byte b[] = new byte[50 * 50 * 50]; // TODO fix this line
			in.read(b);
			in.close();
			Maze3d loaded = new Maze3d(b);
			mazeMap.put(name, loaded);
		} catch (FileNotFoundException e) {
			controller.printErrorMessage(new String[] { "File location Error", "can't find the file" });

		} catch (IOException e) {
			controller.printErrorMessage(new String[] { "Errorr", "can't Load the maze" });

		} finally {
			openFileCount--;
		}

	}

	@Override
	public void solveMaze3d(String name, CommonSearcher<Position> searcher) {
		if (searcher == null) {
			// TODO add error
		}
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				Maze3d maze = mazeMap.get(name);
				Searchable searchableMaze = new MazeAdapter(maze);
				Solution<Position> solution = searcher.search(searchableMaze);
				solutionMap.put(name, solution);
				controller.notifySolutionIsReady(name);
			}

		});
		thread.start();
		threadPool.submit(thread);

	}

	@Override
	public Solution<Position> getMazeSolution(String name) {
		return solutionMap.get(name);
	}

	@Override
	public void finishThreads() {
		threadPool.shutdown();

	}

	@Override
	public void save_maze(String name, String file_name) {
		Maze3d maze = getMaze(name);
		OutputStream savedFile;
		openFileCount++;
		try {
			savedFile = new MyCompressorOutputStream(new FileOutputStream(file_name + ".maz"));

			savedFile.write(maze.toByteArray());
			savedFile.flush();
			savedFile.close();
		} catch (IOException e) {
			controller.printErrorMessage(new String[] { "File location Error", "can't save in thst psth" });
		} finally {
			openFileCount--;
		}

	}

	public void waitUntilCloseAllFiles() {
		while (openFileCount != 0) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
