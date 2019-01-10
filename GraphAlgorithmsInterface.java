
import java.util.*;

public interface GraphAlgorithmsInterface<T> {
	/** Performs a breadth first traversal of the graph
	 * @param origin an object that labels the origin vertex of the traversal
	 * @return a queue of labels of the vertices in the traversal, with the label of the origin
	 * vertex at the queue's front
	 */
	public Queue<T> getBreadthFirstTraversal(T origin);
	/** Performs a depth first traversal of the graph
	 * @param origin an object that labels the origin vertex of the traversal
	 * @return a queue of labels of the vertices in the traversal, with the label of the origin 
	 * vertex at the queue's front
	 */
	public Queue<T> getDepthFirstTraversal(T origin); 
}
