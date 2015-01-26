package assignment0;

public interface IntegerSet {
	public static final int RANGE = 99;
	public void union(IntegerSet u1);
	public void intersection(IntegerSet u2);
	public void addToSet(int i);
	public void removeFromSet(int i);
	public boolean elementOf(int i);
	public void printSet();
}
