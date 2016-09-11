package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import controller.Command;

public class abstract CommonView implements View {

	void printAnswers (String[] args){
		for (String line : args) {
			// prints filename and directory name
			out.println(line);
			out.flush();
		}
	}
	
	public void notifyMazeIsReady(String name) {
		out.println("The Maze" + name+" is ready");

	}

}
