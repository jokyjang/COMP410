package assignment4;
/**
 * Factory used for creating nodes
 */
public final class NodeFactory {
	
	private static int currentID = 0;

    public static Node create(String name) {

        // TODO: Create a node with the given name based on your implementation and return it
        // e.g. return new MyNode(name);
        return new MyNode(name, currentID++);
    }

}
