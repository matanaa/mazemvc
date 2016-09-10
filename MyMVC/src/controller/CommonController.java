package controller;

import model.Model;
import view.View;

public abstract class CommonController implements Controller {
	
	private Model model;
	private View view;
	private CommandsManager commandsManager;

	@Override
	public abstract void notifyMazeIsReady(String name);

}
