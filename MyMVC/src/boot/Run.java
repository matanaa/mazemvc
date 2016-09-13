package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.Controller;
import controller.MyController;
import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

public class Run {

	public static void main(String[] args) throws IOException {
		/*
		 * CommonMaze3dGenerator mg =new SimpleMaze3dGenerator(); Maze3d maze =
		 * mg.generate(4, 4, 4); maze.printMaze();
		 * 
		 * System.out.println("Start Point: "
		 * +maze.getStartPos().toString()+"\n"+ "Goal Point: " +
		 * maze.getGoalPos().toString()+"\n\n");
		 * 
		 * BFS<Position> sh =new BFS<Position>(); Solution<Position> ans=
		 * sh.search(new MazeAdapter(maze)); System.out.println("BFS:"+"\n"+
		 * ans);
		 * System.out.println("Evaluated Nodes: "+sh.getEvaluatedNodes()+"\n\n")
		 * ;
		 * 
		 * DFS<Position> Dsh =new DFS<Position>(); Solution<Position> Dans=
		 * Dsh.search(new MazeAdapter(maze)); System.out.println("DFS:"+"\n"+
		 * Dans);
		 * System.out.println("Evaluated Nodes: "+Dsh.getEvaluatedNodes()+"\n\n"
		 * );
		 * 
		 * // save it to a file OutputStream out=new
		 * MyCompressorOutputStream(new FileOutputStream("1.maz"));
		 * out.write(maze.toByteArray()); out.flush(); out.close(); InputStream
		 * in=new MyDecompressorInputStream(new FileInputStream("1.maz")); byte
		 * b[]=new byte[maze.toByteArray().length]; in.read(b); in.close();
		 * Maze3d loaded=new Maze3d(b); System.out.println(loaded.equals(maze));
		 */

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		View view = new MyView(in, out);
		Model model = new MyModel();
		Controller controller = new MyController(view, model);
		
		view.setController(controller);
		model.setController(controller);
		
		view.start();
	}

}
