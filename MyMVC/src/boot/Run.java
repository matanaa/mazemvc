package boot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerators.CommonMaze3dGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class Run {

	public static void main(String[] args) throws IOException {
		CommonMaze3dGenerator mg =new SimpleMaze3dGenerator();
		Maze3d maze = mg.generate(4, 4, 4);
		maze.printMaze();
		
		System.out.println("Start Point: " +maze.getStartPos().toString()+"\n"+ "Goal Point: "  + maze.getGoalPos().toString()+"\n\n");
		
		BFS<Position> sh =new BFS<Position>();
		Solution<Position> ans= sh.search(new MazeAdapter(maze));
		System.out.println("BFS:"+"\n"+ ans);
		System.out.println("Evaluated Nodes: "+sh.getEvaluatedNodes()+"\n\n");
		
		DFS<Position> Dsh =new DFS<Position>();
		Solution<Position> Dans= Dsh.search(new MazeAdapter(maze));
		System.out.println("DFS:"+"\n"+ Dans);
		System.out.println("Evaluated Nodes: "+Dsh.getEvaluatedNodes()+"\n\n");
		
		// save it to a file
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		InputStream in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte b[]=new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		System.out.println(loaded.equals(maze));

	}

}
