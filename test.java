import java.util.*;

public class test {
	
	public static void main(String[] args) {
		GraphInterface<String> graph1 = new DirectedGraph<>();
		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		String e = "E"; 
		String f = "F"; 
		String g = "G"; 
		String h = "H"; 
		String i = "I"; 
		graph1.addVertex(a);
		graph1.addVertex(b); 
		graph1.addVertex(c); 
		graph1.addVertex(d); 
		graph1.addVertex(e); 
		graph1.addVertex(f); 
		graph1.addVertex(g); 
		graph1.addVertex(h); 
		graph1.addVertex(i); 
		graph1.addEdge(a, b); 
		graph1.addEdge(a, e);
		graph1.addEdge(a, d);
		graph1.addEdge(b, e);
		graph1.addEdge(e, f);
		graph1.addEdge(e, h);
		graph1.addEdge(d, g); 
		graph1.addEdge(g, h);
		graph1.addEdge(f, h);
		graph1.addEdge(h, i);
		graph1.addEdge(i, f);
		graph1.addEdge(f, c);
		graph1.addEdge(c, b);
		Queue<String> breadth = graph1.getBreadthFirstTraversal(a); 
		System.out.println("Breadth first: "); 
		for (int index = 0; index < graph1.getNumberOfVertices(); index++) {
			System.out.print(" " + breadth.remove());
		} 
		Queue<String> depth = graph1.getDepthFirstTraversal(a); 
		System.out.println("\nDepth first: "); 
		for (int index = 0; index < graph1.getNumberOfVertices(); index++) {
			System.out.print(" " + depth.remove());
		}
	}

}
