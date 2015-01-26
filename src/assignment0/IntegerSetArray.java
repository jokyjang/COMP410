package assignment0;

public class IntegerSetArray implements IntegerSet {
	
	private boolean[] set = new boolean[RANGE+1];
	
	@Override
	public void union(IntegerSet u1) {
		// TODO Auto-generated method stub
		for(int i = 0; i <= RANGE+1; ++i) {
			if(set[i]) continue;
			if(u1.elementOf(i)) set[i] = true;
		}
	}

	@Override
	public void intersection(IntegerSet u2) {
		// TODO Auto-generated method stub
		for(int i = 0; i <= RANGE+1; ++i) {
			if(!set[i]) continue;
			if(!u2.elementOf(i)) set[i] = false;
		}
	}

	@Override
	public void addToSet(int i) {
		// TODO Auto-generated method stub
		set[i] = true;
	}

	@Override
	public void removeFromSet(int i) {
		// TODO Auto-generated method stub
		set[i] = false;
	}

	@Override
	public boolean elementOf(int i) {
		// TODO Auto-generated method stub
		return set[i];
	}

	@Override
	public void printSet() {
		// TODO Auto-generated method stub
		for(int i = 0; i <= RANGE; ++i) {
			System.out.println(i + " " + set[i]);
		}
	}
	
	public static void main(String[] args) {
		IntegerSet is1 = new IntegerSetArray();
		IntegerSet is2 = new IntegerSetArray();
		is1.addToSet(49);
		is2.addToSet(72);
		is1.printSet();
		is2.printSet();
	}

}
