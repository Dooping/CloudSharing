package cloud_sharing;

public class IteratorClass implements Iterator {

	private int current;
	public int counter;
	public Object array[];

	public IteratorClass(Object obj[], int counter) {
		array = obj;
		this.counter = counter;
		this.current = -1;
	}

	@Override
	public void init() {
		current = -1;
	}

	@Override
	public boolean hasNext() {
		if (current < counter - 1)
			return true;
		else
			return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return array[++current];
	}
}