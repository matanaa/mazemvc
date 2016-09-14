package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {
	void doCommand(String[] command) throws FileNotFoundException, IOException;
}
