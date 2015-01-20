package assignment4;
/**
 * Factory used for creating graphs
 */
public final class GraphFactory {

    public static Graph create(int nodeCount) {

        // TODO: Create a graph based on your implementation and return it
        // You may use (you don't have to) the number of nodes that will be added to the graph for the constructor
        // e.g. return new MyGraph();
        // e.g. return new YourGraph(nodeCount);
    	return new MyGraph(nodeCount);
    }

}
