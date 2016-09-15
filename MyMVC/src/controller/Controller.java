package controller;

// TODO: Auto-generated Javadoc
/**
 * The Interface Controller.
 */
public interface Controller {

	/**
	 * Notify maze is ready.
	 *
	 * @param name
	 *            the name
	 */
	void notifyMazeIsReady(String name);

	/**
	 * Notify solution is ready.
	 *
	 * @param name
	 *            the name
	 */
	void notifySolutionIsReady(String name);

	/**
	 * Prints the error message.
	 *
	 * @param msg
	 *            the msg
	 */
	void printErrorMessage(String[] msg);
}
