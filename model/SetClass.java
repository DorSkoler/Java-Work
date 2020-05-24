package model;

public class SetClass <T> {
	
	private T[] arr;
	private int topIndex;
	
	public SetClass() {
		arr = ((T[]) new Object[2]);
		topIndex = -1;
	}
	
	public T getTop() {
		return arr[topIndex];
	}
	
	public boolean add(T t) {
		if (topIndex == arr.length-1) {
			T [] temp = (T[]) new Object[arr.length*2];
			System.arraycopy(arr,  0, temp, 0,  arr.length);
			arr = temp;
		}
		for (int i = 0; i <= topIndex; i++) {
			if (arr[i].equals(t)) 
				return false;
		}
		arr[++topIndex] = t;
		return true;
	}
	
	public int getIndexOfObject(T t) {
		for (int i = 0; i <= topIndex; i++) {
			if (t.equals(arr[i]))
				return i;
		}
		return -1;
	}
	
	public int size() {
		return (topIndex + 1);
	}
	
	public boolean isEmpty() {
		return topIndex == -1;
	}
	
	public void set(int index, T other) {
		arr[index] = other;
	}
	
	public T get(int index) {
		return arr[index];
	}
	
	@Override
	public String toString() {
		if (isEmpty())
			return "\n";
		String print = "";
		for (int i = 0; i <= topIndex; i++) {
			print += arr[i].toString() + "\n\n";
		}
		return print;
	}

	public void addAll(SetClass<T> voters) {
		arr = voters.arr;
	}

}
