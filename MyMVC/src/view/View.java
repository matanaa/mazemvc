package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Interface View.
 */
public interface View {

	/**
	 * Notify maze is ready.
	 *
	 * @param name
	 *            the name
	 */
	void notifyMazeIsReady(String name);

	/**
	 * Display maze.
	 *
	 * @param maze
	 *            the maze
	 */
	void displayMaze(Maze3d maze);

	/**
	 * Sets the commands.
	 *
	 * @param commands
	 *            the commands
	 */
	void setCommands(HashMap<String, Command> commands);

	/**
	 * Start.
	 */
	void start();

	/**
	 * Sets the controller.
	 *
	 * @param controller
	 *            the new controller
	 */
	void setController(Controller controller);

	/**
	 * Prints the answers.
	 *
	 * @param filelist
	 *            the filelist
	 */
	void printAnswers(String[] filelist);

	/**
	 * Prints the cross.
	 *
	 * @param cross
	 *            the cross
	 */
	void printCross(int[][] cross);

	/**
	 * Notify solution is ready.
	 *
	 * @param name
	 *            the name
	 */
	void notifySolutionIsReady(String name);

	/**
	 * Display maze solution.
	 *
	 * @param solution
	 *            the solution
	 */
	void displayMazeSolution(Solution<Position> solution);

	/**
	 * Prints the error message.
	 *
	 * @param msg
	 *            the msg
	 */
	void printErrorMessage(String[] msg);
}
