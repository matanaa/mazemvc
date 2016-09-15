package controller;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonController. Abstract class for all controllers
 */
public abstract class CommonController implements Controller {

	/** The model. */
	protected Model model;

	/** The view. */
	protected View view;

	/** The commands manager. */
	protected CommandsManager commandsManager;

	/**
	 * Instantiates a new common controller.
	 *
	 * @param view
	 *            the view
	 * @param model
	 *            the model
	 */
	public CommonController(View view, Model model) {
		this.view = view;
		this.model = model;
		commandsManager = new CommandsManager(model, view);
		view.setCommands(commandsManager.getCommandsMap());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.Controller#notifyMazeIsReady(java.lang.String)
	 */
	@Override
	public abstract void notifyMazeIsReady(String name);

}
