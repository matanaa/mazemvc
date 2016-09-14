package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;
import controller.Controller;

public abstract class CommonView implements View {

	protected Controller controller;
	protected BufferedReader in;
	protected PrintWriter out;
	protected CLI cli;


	public CommonView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		cli = new CLI(in,out);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}


	@Override
	public abstract void notifyMazeIsReady(String name);

	@Override
	public abstract void displayMaze(Maze3d maze);

	@Override
	public abstract void setCommands(HashMap<String, Command> commands);

	
}
