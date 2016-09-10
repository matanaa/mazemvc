package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.activation.CommandMap;

import controller.Command;

public class CLI {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;

	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> commands) {
		this.in = in;
		this.out = out;
		this.commands = commands;
	}

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try{
					Command c = null;
					String buffer = in.readLine();
					while (!buffer.equals("exit")) {
						c = CommandMap.get(buffer.split(" ")[0]);
						if (c != null) {
							if (buffer.split(" ").length < 1) {
								c.doCommand(buffer.substring(buffer.indexOf(' ') + 1));
							} else {
								out.println("Missing Parameters!");
								out.flush();
							}
						} else {
							out.println(buffer + " is not a valid command!");
							out.flush();
						}
						buffer = in.readLine();
					}
					CommandMap.get("exit").doCommand("");
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}).start();
	}
	
	void printAnswers (String[] args){
		for (String line : args) {
			// prints filename and directory name
			out.println(line);
			out.flush();
		}
		
		
	}
}
