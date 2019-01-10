import java.util.*; 

public class Vertex<T> implements VertexInterface<T> {
	private T label; 
	private List<Edge> edgeList; 
	private boolean visited; 
	private VertexInterface<T> previousVertex; 
	private double cost; 
	
	public Vertex(T vertexLabel) {
		label = vertexLabel;
		edgeList = new LinkedList<Edge>(); //each vertex has a linked list indicating all of its different end vertices
		visited = false; 
		previousVertex = null; 
		cost = 0;
	}
	public boolean connect(VertexInterface<T> endVertex, double edgeWeight) { //if graph is weighted
		boolean result = false; 
		if (!this.equals(endVertex)) {
			Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator(); 
			boolean duplicateEdge = false; 
			
			while (!duplicateEdge && neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if (endVertex.equals(nextNeighbor)) {
					duplicateEdge = true; 
				}
			}
			
			if (!duplicateEdge) {
				edgeList.add(new Edge(endVertex, edgeWeight));
				result = true; 
			}
		}
		return result; 
	}
	public boolean connect(VertexInterface<T> endVertex) { //if graph is unweighted (weight is 0)
		return connect(endVertex, 0);
	}
	public Iterator<VertexInterface<T>> getNeighborIterator(){
		return new neighborIterator();
	}
	public boolean hasNeighbor() {
		return !edgeList.isEmpty(); 
	}
	public VertexInterface<T> getUnvisitedNeighbor(){
		VertexInterface<T> result = null; 
		Iterator<VertexInterface<T>> neighbors = getNeighborIterator(); 
		
		while (neighbors.hasNext() && (result == null)) {
			VertexInterface<T> nextNeighbor = neighbors.next(); 
			if (!nextNeighbor.isVisited()) {
				result = nextNeighbor; 
			}
		}
		return result;
	}
	public boolean equals(Object other) {
		boolean result;
		if ((other == null) || (getClass() != other.getClass())) {
			result = false; 
		} else {
			Vertex<T> otherVertex = (Vertex<T>)other; 
			result = label.equals(otherVertex.label); 
		}
		return result;
	}
	public void visit() {
		visited = true; 
	}
	public void unvisit() {
		visited = false; 
	}
	public boolean isVisited() {
		return visited; 
	}
	public T getLabel() {
		return label; 
	}
	public double getCost() {
		return cost; 
	}
	public void setCost(double newCost) {
		cost = newCost; 
	}
	public boolean hasPredecessor() {
		return (previousVertex != null);
	}
	public VertexInterface<T> getPredecessor(){
		return previousVertex; 
	}
	public void setPredecessor(VertexInterface<T> predecessor) {
		previousVertex = predecessor; 
	}
	public Iterator<Double> getWeightIterator(){
		return new weightIterator(); 
	}
	
	private class weightIterator implements Iterator<Double>{		
		private Iterator<Edge> edges;				
		private weightIterator(){
			edges = edgeList.iterator();		
		} 				
		public boolean hasNext() {		
			return edges.hasNext();	
		} 				
		public Double next(){			
			Double edgeWeight = new Double(0);						
			if (edges.hasNext()){				
				Edge edgeToNextNeighbor = edges.next();				
				edgeWeight = edgeToNextNeighbor.getWeight();			
			}else				
				throw new NoSuchElementException();					
			return edgeWeight;
		} 		
		public void remove(){		  
			throw new UnsupportedOperationException();
		} 
	}
	
	//private class that creates iterator that iterates through neighbors of a vertex (using edges)
	private class neighborIterator implements Iterator<VertexInterface<T>> {
		private Iterator<Edge> edges; 
		private neighborIterator() {
			edges = edgeList.iterator(); 
		}
		public boolean hasNext() {
			return edges.hasNext();
		}
		public VertexInterface<T> next() {
			VertexInterface<T> nextNeighbor = null;
			
			if (edges.hasNext()) {
				Edge edgeToNextNeighbor = edges.next(); 
				nextNeighbor = edgeToNextNeighbor.getEndVertex(); 
			} else {
				throw new NoSuchElementException(); 
			}
			return nextNeighbor; 
		}
		public void remove() {
			throw new UnsupportedOperationException(); 
		}
	}
	
	//protected class that creates an edge object holding the end vertex and weight (if any). used for our edgeList
	protected class Edge {
		private VertexInterface<T> vertex; 
		private double weight; 
		
		protected Edge(VertexInterface<T> endVertex, double edgeWeight) {
			vertex = endVertex; 
			weight = edgeWeight; 
		}
		protected VertexInterface<T> getEndVertex() {
			return vertex; 
		}
		protected double getWeight() {
			return weight; 
		}
	}
}
