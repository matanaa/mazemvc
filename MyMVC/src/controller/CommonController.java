package controller;

import model.Model;
import view.View;

public abstract class CommonController implements Controller {
	
	protected Model model;
	protected View view;
	protected CommandsManager commandsManager;
	
	public CommonController(View view, Model model) {
		this.view = view;
		this.model = model;
		commandsManager = new CommandsManager(model, view);
		view.setCommands(commandsManager.getCommandsMap());
	}

	@Override
	public abstract void notifyMazeIsReady(String name);

}
