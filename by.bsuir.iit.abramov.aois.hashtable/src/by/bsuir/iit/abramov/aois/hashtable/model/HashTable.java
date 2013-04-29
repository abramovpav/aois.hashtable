package by.bsuir.iit.abramov.aois.hashtable.model;

public class HashTable {
	public static final String	ERROR_NULL_POINTER_EXCEPTION	= "***ERROR***: NullPointer Exception";
	public static final String	ERROR_NO_SUCH_KEY				= "***ERROR***: No such key";

	public static int hash(int h, final int capacity) {

		h ^= (h >>> 20) ^ (h >>> 12);
		final int n = h ^ (h >>> 7) ^ (h >>> 4);
		return HashTable.indexFor(n, capacity);
	}

	static int indexFor(final int h, final int capacity) {

		// return h & (capacity - 1);
		return Math.abs(h) % capacity;
	}

	private final Entry[]	table;
	private int				size;
	private final int		capacity	= 5;

	public HashTable() {

		table = new Entry[capacity];
		prepareTable();
	}

	private Integer findByID(final String id, final Entry start) {

		Entry entry = start;
		while (entry.hasNext()) {
			if (entry.getId().equalsIgnoreCase(id)) {

				return entry.getData();
			}
			entry = entry.next();
		}
		if (entry.getId().equalsIgnoreCase(id)) {
			return entry.getData();
		}
		System.out.println(HashTable.ERROR_NO_SUCH_KEY);
		return null;
	}

	private Integer findPlace(final String id, final Integer data, final int index) {

		if (table[index] != null) {
			if (table[index].getHash() == HashTable.hash(id.hashCode(), capacity)) {
				Entry entry = table[index];
				while (entry.hasNext()) {
					if (entry.getId() == id) {
						entry.setHashandData(data, index);
						return entry.getIndex();
					}
					entry = entry.next();
				}
				if (entry.getId() == id) {
					entry.setHashandData(data, index);
					return entry.getIndex();
				} else {
					final Integer result = searchForEmptyEntry(id, data, index, entry);
					if (result != 0) {
						return result;
					}
				}
			} else {
				Entry entry = table[index];
				while (entry.hasNext()) {
					entry = entry.next();
				}

				final Integer result = searchForEmptyEntry(id, data, index, entry);
				if (result != null) {
					table[index].setCollusionNext(table[result]);
					return result;
				}

			}
		} else {
			return index;
		}

		return null;
	}

	private Integer findToRemove(final String id, final Entry start) {

		Entry entry = start;
		while (entry.hasNext()) {
			if (entry.getId().equalsIgnoreCase(id)) {
				removeEntry(entry);
				return entry.getIndex();
			}
			entry = entry.next();
		}
		if (entry.getId().equalsIgnoreCase(id)) {
			removeEntry(entry);
			return entry.getIndex();
		}
		return null;
	}

	public final Integer get(final String id) {

		final int hash = HashTable.hash(id.hashCode(), capacity);
		System.out.println("*****************************************");
		System.out.println("get " + id + "\t" + hash);
		final Entry entry = table[hash];
		if (entry.getId().equalsIgnoreCase(id)) {
			return entry.getData();
		} else if (entry.getId() != null) {
			if (entry.hasCollusionNext()) {
				return findByID(id, entry.collusionNext());
			} else {
				if (entry.hasNext()) {
					return findByID(id, entry.next());
				} else {
					System.out.println(HashTable.ERROR_NO_SUCH_KEY);
				}
			}

		} else {
			System.out.println(HashTable.ERROR_NULL_POINTER_EXCEPTION);
		}
		return null;
	}

	public final int getCapacity() {

		return capacity;
	}

	private final boolean isCollusionNext(final String id, final Integer data,
			final int index) {

		if (!table[index].hasCollusionNext()) {
			return false;
		} else {
			final Entry entry = table[index].collusionNext();

			findPlace(id, data, entry.getIndex());
			return true;
		}
	}

	private void prepareTable() {

		for (int i = 0; i < table.length; ++i) {
			table[i] = new Entry(null, null, i, null);
		}
	}

	public void printTable() {

		for (int i = 0; i < table.length; i++) {
			final Entry entry = table[i];
			if (entry.hasNext() && entry.hasCollusionNext()) {
				if (entry.hasPrev()) {
					System.out.println(entry.getIndex() + ". " + entry.getId() + "\t"
							+ entry.getHash() + "\t" + entry.getData() + "\t"
							+ entry.next().getIndex() + "\t" + entry.prev().getIndex()
							+ "\t" + entry.collusionNext().getIndex());
				} else {
					System.out.println(entry.getIndex() + ". " + entry.getId() + "\t"
							+ entry.getHash() + "\t" + entry.getData() + "\t"
							+ entry.next().getIndex() + "\tnull\t"
							+ entry.collusionNext().getIndex());
				}
			} else if (entry.hasNext()) {
				if (entry.hasPrev()) {
					System.out.println(entry.getIndex() + ". " + entry.getId() + "\t"
							+ entry.getHash() + "\t" + entry.getData() + "\t"
							+ entry.next().getIndex() + "\t" + entry.prev().getIndex()
							+ "\tnull");
				} else {
					System.out.println(entry.getIndex() + ". " + entry.getId() + "\t"
							+ entry.getHash() + "\t" + entry.getData() + "\t"
							+ entry.next().getIndex() + "\tnull\tnull");
				}
			} else if (entry.hasCollusionNext()) {
				if (entry.hasPrev()) {
					System.out.println(entry.getIndex() + ". " + entry.getId() + "\t"
							+ entry.getHash() + "\t" + entry.prev() + "\t"
							+ entry.getData() + "\tnull\t" + entry.prev().getIndex()
							+ "\t" + entry.collusionNext().getIndex());
				} else {
					System.out.println(entry.getIndex() + ". " + entry.getId() + "\t"
							+ entry.getHash() + "\t" + entry.getData() + "\tnull\tnull\t"
							+ entry.collusionNext().getIndex());
				}
			} else if (entry.hasPrev()) {
				System.out.println(entry.getIndex() + ". " + entry.getId() + "\t"
						+ entry.getHash() + "\t" + entry.getData() + "\tnull\t"
						+ entry.prev().getIndex() + "\tnull");
			} else {
				System.out
						.println(entry.getIndex() + ". " + entry.getId() + "\t"
								+ entry.getHash() + "\t" + entry.getData()
								+ "\tnull\tnull\tnull");
			}
		}
	}

	public void put(final String id, final Integer data) {

		/*
		 * if (size == capacity) { return; }
		 */
		final int hash = HashTable.hash(id.hashCode(), capacity);
		System.out.println("*****************************************");
		System.out.println("id hash data next prev collusion*********");
		System.out.println("*****************************************");
		System.out.println(id + "\t" + hash + "\t" + data);
		final Entry entry = table[hash];
		if (entry.getId() != null) {

			if (!isCollusionNext(id, data, hash)) {
				if (findPlace(id, data, hash) == null) {
					System.out.println("no place");
				};
			}
		} else {
			entry.setIDandData(id, data, HashTable.hash(id.hashCode(), capacity));
			size++;
			if (entry.hasCollusionNext()) {
				entry.setNext(entry.collusionNext());
				entry.setCollusionNext(null);
			}
		}
	}

	public void remove(final String id) {

		final int hash = HashTable.hash(id.hashCode(), capacity);
		System.out.println("*****************************************");
		System.out.println("remove " + id + "\t" + hash);
		final Entry entry = table[hash];
		if (entry.getId().equalsIgnoreCase(id)) {
			removeByHash(hash);
		} else if (entry.getId() != null) {
			if (entry.hasCollusionNext()) {
				if (findToRemove(id, entry.collusionNext()) == null) {
					System.out.println(HashTable.ERROR_NO_SUCH_KEY);
				}
			} else {
				if (entry.hasNext()) {
					if (findToRemove(id, entry.next()) == null) {
						System.out.println(HashTable.ERROR_NO_SUCH_KEY);
					}
				} else {
					System.out.println(HashTable.ERROR_NO_SUCH_KEY);
				}
			}

		} else {
			System.out.println(HashTable.ERROR_NULL_POINTER_EXCEPTION);
		}
	}

	private void removeByHash(final int hash) {

		final Entry entry = table[hash];
		if (entry.hasPrev()) {
			entry.prev().setNext(entry.next());
		} else {
			if (entry.hasNext()) {
				entry.setCollusionNext(entry.next());
			}
		}
		entry.remove();
	}

	private void removeEntry(final Entry entry) {

		if (entry.hasPrev()) {
			entry.prev().setNext(entry.next());
		} else {
			if (table[entry.getHash()].hasCollusionNext()) {
				table[entry.getHash()].setCollusionNext(entry.next());
			} else if (entry.getIndex() == entry.getHash()) {
				table[entry.getHash()].setCollusionNext(entry.next());
			}
		}
		if (entry.hasNext()) {
			entry.next().setPrev(entry.prev());
		}
		entry.remove();
	}

	private Integer searchForEmptyEntry(final String id, final Integer data,
			final int index, final Entry entry, final String... args) {

		int curIndex = entry.getIndex() + 1;
		final int beginIndex = curIndex - 1;
		if (curIndex >= table.length) {
			curIndex = 0;
		}
		for (; curIndex < table.length; ++curIndex) {
			if (curIndex == beginIndex) {
				break;
			}
			if (table[curIndex].isEmpty()) {
				table[curIndex].setIDandData(id, data, index);
				entry.setNext(table[curIndex]);
				table[curIndex].setPrev(entry);
				if (args.length != 0) {
					table[index].setCollusionNext(table[curIndex]);
				}
				size++;
				return curIndex;
			}
			if (curIndex + 1 == table.length) {
				curIndex = -1;
			}
		}
		return null;
	}
}
