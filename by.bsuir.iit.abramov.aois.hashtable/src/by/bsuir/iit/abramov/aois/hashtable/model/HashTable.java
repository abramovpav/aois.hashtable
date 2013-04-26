package by.bsuir.iit.abramov.aois.hashtable.model;


public class HashTable {
	public static int hash(int h, int capacity){

		h ^= (h >>> 20) ^ (h >>> 12);
		int n = h ^ (h >>> 7) ^ (h >>> 4);
		return indexFor(n, capacity);
	}

	static int indexFor(final int h, int capacity) {
		//return h & (capacity - 1);
		return Math.abs(h) % capacity;
	}

	private final Entry[]	table;
	private int				size;
	private final int		capacity	= 5;
	
	public final int getCapacity() {
		return capacity;
	}

	public HashTable() {

		table = new Entry[capacity];
		prepareTable();
	}
	
	private void prepareTable() {
		for (int i = 0; i < table.length; ++i) {
			table[i] = new Entry(null, null, i, null);
		}
	}
	
	
	private int findPlace(final String id, final Integer data, final int index) {
		if (table[index] != null) {
			if (table[index].getHash() == index) {
				Entry entry = table[index];
				while(entry.hasNext()) {
					if (entry.getId() == id) {
						entry.setHashandData(data, index);
						return entry.getIndex();
					}
					entry = entry.next();
				}
				if (entry.getId() == id) {
					entry.setHashandData(data, index); 
					return entry.getIndex();
				}
				else
				{
					searchForEmptyEntry(id, data, index, entry);
				}
			}
			else
			{
				Entry entry = table[index];
				while(entry.hasNext()) {
					entry = entry.next();
				}
				
				Integer result = searchForEmptyEntry(id, data, index, entry);
				if (result != null) {
					table[index].setCollusionNext(table[result]);
				}
				
			}
		}
		else {
			return index;
		}
		
		
		return -1;
	}

	private Integer searchForEmptyEntry(final String id, final Integer data,
			final int index, Entry entry) {

		int curIndex = entry.getIndex() + 1;
		int beginIndex = curIndex - 1;
		if (curIndex >= table.length) {
			curIndex = 0;
		}
		for (; curIndex < table.length; ++curIndex) {
			if (curIndex == beginIndex)
				break;
			if (table[curIndex].isEmpty()) {
				table[curIndex].setIDandData(id, data, index);
				entry.setNext(table[curIndex]);
				size++;
				return curIndex;
			}
			if (curIndex + 1 == table.length)
				curIndex = -1;
		}
		return null;
	}

	public void put(final String id, final Integer data) {

		if (size == capacity) {
			return;
		}
		final int hash = hash(id.hashCode(), capacity);
		System.out.println(id + " " + hash);
		if (table[hash].getId() != null) {
			
			if (!isCollusionNext(id, data, hash)) {
				findPlace(id, data, hash);
			}
		} else {
			table[hash].setIDandData(id, data, hash(id.hashCode(), capacity));
			size++;
		}
	}
	
	private final boolean isCollusionNext(String id, Integer data, int index) {
		if (!table[index].hasCollusionNext())
			return false;
		else
		{
			Entry entry = table[index].collusionNext();
			findPlace(id, data, index);
			return true;
		}
	}
}
