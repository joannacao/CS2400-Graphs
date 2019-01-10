import java.util.*;
import java.lang.*;

public class DirectedGraph<T> implements GraphInterface<T>{
	private int edgeCount; 
	private Map<T, VertexInterface<T>> vertices; //using a Map instead of a Dictionary
	
	public DirectedGraph() {
		vertices = new HashMap<T, VertexInterface<T>>(); //using a hashmap java provides instead of the dictionary
														 //implemented in the textbook
		edgeCount = 0; 
	}
	
	public boolean addVertex(T vertexLabel) {
		VertexInterface<T> isDuplicate = vertices.put(vertexLabel, new Vertex(vertexLabel));
		return isDuplicate == null; 
	}
	public boolean addEdge(T begin, T end, double edgeWeight) {
		boolean result = false; 
		VertexInterface<T> beginVertex = vertices.get(begin);
		VertexInterface<T> endVertex = vertices.get(end); 
		if ((beginVertex != null) && (endVertex != null)) {
			result = beginVertex.connect(endVertex, edgeWeight); 
		}
		if (result) {
			edgeCount++;
		}
		return result; 
	}
	public boolean addEdge(T begin, T end) {
		return addEdge(begin, end, 0);
	}
	public boolean hasEdge(T begin, T end) {
		boolean found = false; 
		VertexInterface<T> beginVertex = vertices.get(begin); 
		VertexInterface<T> endVertex = vertices.get(end); 
		
		if ((beginVertex != null) && (endVertex != null)) {
			Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator(); 
			while (!found && neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next(); 
				if (endVertex.equals(nextNeighbor)) {
					found = true; 
				}
			}
		}
		return found; 
	}
	public boolean isEmpty() {
		return vertices.isEmpty(); 
	}
	public void clear() {
		vertices.clear(); 
		edgeCount = 0; 
	}
	public int getNumberOfVertices() {
		return vertices.size(); 
	}
	public int getNumberOfEdges() {
		return edgeCount; 
	}
	//resets data (making sure everything is unvisited, etc..) so that traversal will be correct
	protected void resetVertices() {
		Collection<VertexInterface<T>> valueCollection = vertices.values(); 
		Iterator<VertexInterface<T>> vertexIterator = valueCollection.iterator(); //iterates through vertices values
		while (vertexIterator.hasNext()) {
			VertexInterface<T> nextVertex = vertexIterator.next(); 
			//resets nextVertex's data so that we can traverse through it properly
			nextVertex.unvisit();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		}
	}
	
	public Queue<T> getBreadthFirstTraversal(T origin){
		resetVertices();
		Queue<T> traversalOrder = new LinkedList<T>(); //using java's LinkedList instead of queue implementation in textbook 
		Queue<VertexInterface<T>> vertexQueue = new LinkedList<VertexInterface<T>>(); //using LinkedList
		
		VertexInterface<T> originVertex = vertices.get(origin); 
		originVertex.visit(); 
		traversalOrder.add(origin);
		vertexQueue.add(originVertex); 
		
		while (!vertexQueue.isEmpty()) {
			VertexInterface<T> frontVertex = vertexQueue.remove();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			
			while (neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next(); 
				if (!nextNeighbor.isVisited()) {
					nextNeighbor.visit(); 
					traversalOrder.add(nextNeighbor.getLabel());
					vertexQueue.add(nextNeighbor);
				}
			}
		}
		return traversalOrder; 
	}
	
	public Queue<T> getDepthFirstTraversal(T origin){
		resetVertices();
		Queue<T> traversalOrder = new LinkedList<T>(); 
		Stack<VertexInterface<T>> vertexStack = new Stack<>(); //java's stack implementation 
		
		VertexInterface<T> originVertex = vertices.get(origin); 
		originVertex.visit(); 
		traversalOrder.add(origin);
		vertexStack.push(originVertex);
		
		while (!vertexStack.isEmpty()) {
			VertexInterface<T> topVertex = vertexStack.peek(); 
			VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();
			
			if (nextNeighbor != null) {
				nextNeighbor.visit(); 
				traversalOrder.add(nextNeighbor.getLabel()); 
				vertexStack.push(nextNeighbor);
			} else {
				vertexStack.pop(); 
			}
		}
		return traversalOrder; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
