package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyGraph extends Graph {
	private int size;
	private Node[] node;	// store all the vertices
	private List<Node>[] list;	// store all the edges
	private HashTable<String, Integer> hashTable;
	private enum Color {
		WHITE, GREY, BLACK
	}
	
	public MyGraph(int count) {
		size = count;
		node = new Node[count];
		list = new LinkedList[count];
		for(int i = 0; i < count; ++i) {
			list[i] = new LinkedList<Node>();
		}
		hashTable = HashTableFactory.<String, Integer>create();
	}

	@Override
	public void addNode(Node node) {
		// TODO Auto-generated method stub
		hashTable.put(node.getName(), node.getId());
		this.node[node.getId()] = node;
	}

	@Override
	public void addEdge(Node node1, Node node2) {
		// TODO Auto-generated method stub
		int id1 = node1.getId();
		list[id1].add(node2);
	}

	@Override
	public Node lookupNode(int id) {
		// TODO Auto-generated method stub
		return node[id];
	}

	@Override
	public Node lookupNode(String name) {
		// TODO Auto-generated method stub
		int id = hashTable.get(name);
		return lookupNode(id);
	}

	@Override
	public boolean isAcyclic() {
		// TODO Auto-generated method stub
		Color[] color = new Color[size];
		for(int i = 0; i < size; ++i) {
			color[i] = Color.WHITE;
		}
		for(int i = 0; i < size; ++i) {
			if(color[i] == Color.BLACK) continue;
			if(color[i] == Color.WHITE && dfs(color, i)) continue;
			return false;
		}
		return true;
	}
	
	/**
	 * If there is no cycle starting from Node i, return true
	 * @param color
	 * @param i
	 * @return true is acyclic or false
	 */
	private boolean dfs(Color[] color, int i) {
		if(color[i] == Color.BLACK) return true;
		color[i] = Color.GREY;
		for(Node n : list[i]) {
			int id = n.getId();
			if(color[id] == Color.GREY) return false;
			if(!dfs(color, id)) return false;
		}
		color[i] = Color.BLACK;
		return true;
	}

	@Override
	public int[] sort() {
		// TODO Auto-generated method stub
		List<Integer> sorted = new LinkedList<Integer>();
		Color[] color = new Color[size];
		for(int i = 0; i < size; ++i) {
			color[i] = Color.WHITE;
		}
		for(int i = 0; i < size; ++i) {
			try {
				visit(color, i, sorted);
			} catch(Exception e) {
				return null;
			}
		}
		int[] s = new int[size];
		for(int i = 0; i < sorted.size(); ++i) {
			s[i] = sorted.get(i);
		}
		return s;
	}
	
	private void visit(Color[] color, int i, List<Integer> sorted) throws Exception {
		if(color[i] == Color.GREY) throw new Exception("Cycle found!");
		if(color[i] == Color.BLACK) return;
		color[i] = Color.GREY;
		for(Node n : list[i]) {
			int id = n.getId();
			visit(color, id, sorted);
		}
		color[i] = Color.BLACK;
		sorted.add(0, i);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
