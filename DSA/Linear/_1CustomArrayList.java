interface CustomList {
	boolean add(int value);
	boolean isEmpty();
	int size();
	boolean remove(int value);
	void clear();
	boolean contains(int value);
	int get(int index);
	int set(int index, int value);
	int indexOf(int value);
	int getCapacity();
}

class CustomArrayList implements CustomList {
	private int array[];
	private int capacity;
	private int currentSize;
	private int size = 5;
	
	public CustomArrayList() {
		this.capacity = size;
		this.array = new int[capacity];
		currentSize = 0;
	}
	
	public CustomArrayList(int size) {
		this.size = size;
		this.capacity = this.size;
		this.array = new int[capacity];
		this.currentSize = 0;
	}
	
	public boolean add(int value) {
		if(this.capacity == this.currentSize) {
			int newCapacity = ((capacity * 3) / 2) + 1;		// 50% size increase 
			int [] temp = array;
			array = new int[newCapacity];
			for(int i =0; i< capacity; i++) {
				this.array[i] = temp[i];
			}
			capacity = newCapacity;
		}
		
		this.array[currentSize] = value;
		++currentSize;
		return true;
	}
	
	@Override
	public boolean remove(int value) {
		int valIndex = -1;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == value) {
				valIndex = i;
			}
			if(valIndex > -1 && i < currentSize) {
				this.array[i] = this.array[i+1];	// Caution: ArrayIndexOutOfBound bcoz i+1
			}
		}
		--currentSize;
		return true;
	}
	
	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean isEmpty() {
		if(this.currentSize == 0)
			return true;
		return false;
	}

	@Override
	public void clear() {
		array = new int [this.size];
		this.capacity = this.size;
		this.currentSize = 0;		
	}

	@Override
	public boolean contains(int value) {
		for (int i = 0; i < this.currentSize; i++) {
			if(array[i] == value) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int get(int index) {
		if(index > this.currentSize)
			throw new IndexOutOfBoundsException();
		return this.array[index];
	}

	@Override
	public int set(int index, int value) {
		if(index > this.currentSize)
			throw new IndexOutOfBoundsException();
		int oldValue = this.array[index];
		this.array[index] = value;
		return oldValue;
	}

	@Override
	public int indexOf(int value) {
		for (int i = 0; i < this.currentSize; i++) {
			if(array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i = 0; i< currentSize; i++) {
			str.append(this.array[i]);
			if((i+1)<this.currentSize)
				str.append(", ");
		}
		str.append("]");
		
		return str.toString();
	}
}

public class _1CustomArrayList {
    public static void main(String[] args) {
    	CustomList ls = new CustomArrayList(5);
    	ls.add(11);
    	ls.add(22);
    	ls.add(33);
    	ls.add(44);
    	ls.add(55);
    	ls.add(66);
    	ls.remove(22);
    	ls.set(2,33);
    	System.out.println(ls.contains(33));
    	System.out.println(ls);
    	
    	
    }
}
