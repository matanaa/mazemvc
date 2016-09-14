package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;

	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
	}

	public void setCommands(HashMap<String, Command> commnds) {
		this.commands = commnds;
	}

	private void printMenu() {
		out.println("Please choose Command to run:");
		Integer i=1;
		for (String command : commands.keySet()) {
			out.println("\t"+i.toString()+")"+ command);
			i++;
		}
		out.println("\n\texample: dir c:\\" );
		out.flush();
	}

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					printMenu();
					try {
						String commandLine = in.readLine();
						String commandsArray[] = commandLine.split(" ");
						String command = commandsArray[0];

						if (!commands.containsKey(command)) {
							out.println("Command doesn't exist!");
						} else {
							String[] args = null;
							if (commandsArray.length > 1) {
								String commandArgs = commandLine.substring(commandLine.indexOf(" ") + 1);
								args = commandArgs.split(" ");
							}
							Command cmd = commands.get(command);
							try {
								cmd.doCommand(args);
								
							} catch (NullPointerException e) {
								out.println("Arguments Error: No arguments");

							}
							
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
