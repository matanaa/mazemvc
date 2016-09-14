package model;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;

public abstract class CommonModel implements Model {

	protected Controller controller;
	protected HashMap<String, Maze3d> mazeMap;
	protected HashMap<String, Solution<Position>> solutionMap;
	protected ExecutorService threadPool;

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public HashMap<String, Maze3d> getMazeMap() {
		return mazeMap;
	}

	public void setMazeMap(HashMap<String, Maze3d> mazeMap) {
		this.mazeMap = mazeMap;
	}

	public HashMap<String, Solution<Position>> getSolutionMap() {
		return solutionMap;
	}

	public void setSolutionMap(HashMap<String, Solution<Position>> solutionMap) {
		this.solutionMap = solutionMap;
	}

	public ExecutorService getThreadPool() {
		return threadPool;
	}

	public void setThreadPool(ExecutorService threadPool) {
		this.threadPool = threadPool;
	}

	public CommonModel() {
		this.mazeMap = new HashMap<String, Maze3d>();
		this.solutionMap = new HashMap<String, Solution<Position>>();
		this.threadPool = Executors.newCachedThreadPool();
	}

	@Override
	public abstract void generateMaze(String name, int floors, int rows, int colums);

	@Override
	public abstract Maze3d getMaze(String name);
	
	@Override
	public abstract Solution<Position> getMazeSolution(String name);

}
