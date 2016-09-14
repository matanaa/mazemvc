package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;
import controller.Controller;

public interface View {
	void notifyMazeIsReady(String name);

	void displayMaze(Maze3d maze);

	void setCommands(HashMap<String, Command> commands);
	
	void start();

	void setController(Controller controller);

	void printAnswers(String[] filelist);

	void printCross(int[][] cross);

	void notifySolutionIsReady(String name);


	
	

}
