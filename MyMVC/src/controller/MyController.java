package controller;

import model.Model;
import view.View;

public class MyController extends CommonController {
	

		
	public MyController(View view, Model model) {
		super(view, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notifyMazeIsReady(String name) {
		view.notifyMazeIsReady(name);
	}

	@Override
	public void notifySolutionIsReady(String name) {
		view.notifySolutionIsReady(name);
		
	}

}
