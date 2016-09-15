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
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.CommonSearcher;
import algorithms.search.Searchable;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class MyModel.
 */
public class MyModel extends CommonModel {

	/** The generate maze tasks. */
	protected List<generateMazeRunnable> generateMazeTasks = new ArrayList<generateMazeRunnable>();

	/** The open file count. */
	// will save how many files are open new
	protected int openFileCount = 0;

	/**
	 * Instantiates a new my model.
	 */
	public MyModel() {
		super();
	}

	/**
	 * The Class generateMazeRunnable - an adapter to make the generation
	 * process runnable on threads
	 */
	class generateMazeRunnable implements Runnable {

		/** The colums. */
		private int floors, rows, colums;

		/** The name. */
		private String name;

		/** The generator. */
		private Maze3dGenerator generator;

		/**
		 * Instantiates a new generate maze runnable.
		 *
		 * @param floors
		 *            the floors
		 * @param rows
		 *            the rows
		 * @param colums
		 *            the colums
		 * @param name
		 *            the name
		 */
		public generateMazeRunnable(int floors, int rows, int colums, String name, Maze3dGenerator generator) {
			super();
			this.floors = floors;
			this.rows = rows;
			this.colums = colums;
			this.name = name;
			this.generator = generator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// choosing the generator type
			Maze3d maze = generator.generate(floors, rows, colums);
			mazeMap.put(name, maze);
			controller.notifyMazeIsReady(name);
		}

		/**
		 * Terminate.
		 */
		public void terminate() {
			generator.setDone(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.CommonModel#generateMaze(java.lang.String, int, int, int)
	 */
	@Override
	public void generateMaze(String name, int floors, int rows, int cols, CommonMaze3dGenerator generator) {
		generateMazeRunnable generateMaze = new generateMazeRunnable(floors, rows, cols, name, generator);
		generateMazeTasks.add(generateMaze);
		Thread thread = new Thread(generateMaze);
		thread.start();
		threadPool.submit(generateMaze);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.CommonModel#getMaze(java.lang.String)
	 */
	@Override
	public Maze3d getMaze(String name) {
		return mazeMap.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Model#exit()
	 */
	@Override
	public void exit() {
		for (generateMazeRunnable task : generateMazeTasks) {
			task.terminate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Model#loadMaze(java.lang.String, java.lang.String)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Model#solveMaze3d(java.lang.String,
	 * algorithms.search.CommonSearcher)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.CommonModel#getMazeSolution(java.lang.String)
	 */
	@Override
	public Solution<Position> getMazeSolution(String name) {
		return solutionMap.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Model#finishThreads()
	 */
	@Override
	public void finishThreads() {
		threadPool.shutdown();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Model#save_maze(java.lang.String, java.lang.String)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Model#waitUntilCloseAllFiles()
	 */
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
