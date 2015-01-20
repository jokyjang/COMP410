package assignment4;
/**
 * Some examples of tests. You'll want to write more tests.
 */
public class ExampleTests {

    private static interface Animal {
        public String speak();
    }
    private static class Dog implements Animal {
        public String speak() {
            return "woof";
        }
    }

    private static class Cat implements Animal {
        public String speak() {
            return "meow";
        }
    }

    public static void main(String[] args) {

        // Hash table
        HashTable<String, Animal> animals = HashTableFactory.create();
        animals.put("dog", new Dog());
        animals.put("cat", new Cat());
        System.out.println("Expecting 'woof': " + animals.get("dog").speak());
        System.out.println("Expecting 'meow': " + animals.get("cat").speak());

        // Graph building
        Graph graph = GraphFactory.create(4);
        Node a = NodeFactory.create("a");
        Node b = NodeFactory.create("b");
        Node c = NodeFactory.create("c");
        Node d = NodeFactory.create("d");
        graph.addNodes(a, b, d);
        graph.addEdge(a, b);
        graph.addEdge(c, b);
        graph.addEdge(b, d);
        graph.addEdge(d, c);
        System.out.println("Expecting unique ids from nodes c, b, a:");
        System.out.println("ID for c: " + graph.lookupNode("c").getId());
        System.out.println("ID for b: " + graph.lookupNode("b").getId());
        System.out.println("ID for a: " + graph.lookupNode("a").getId());
        System.out.println("ID for d: " + graph.lookupNode("d").getId());

        // Graph analysis
        System.out.println("Expecting an acyclic graph with sorted output: a, b, c, d");
        graph.analyze();
    }

}
