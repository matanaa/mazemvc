package controller;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class MyController.
 */
public class MyController extends CommonController {

	/**
	 * Instantiates a new my controller.
	 *
	 * @param view
	 *            the view
	 * @param model
	 *            the model
	 */
	public MyController(View view, Model model) {
		super(view, model);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.CommonController#notifyMazeIsReady(java.lang.String)
	 */
	@Override
	public void notifyMazeIsReady(String name) {
		view.notifyMazeIsReady(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.Controller#notifySolutionIsReady(java.lang.String)
	 */
	@Override
	public void notifySolutionIsReady(String name) {
		view.notifySolutionIsReady(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.Controller#printErrorMessage(java.lang.String[])
	 */
	@Override
	public void printErrorMessage(String[] msg) {
		view.printErrorMessage(msg);
	}

}
