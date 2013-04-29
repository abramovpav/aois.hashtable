package by.bsuir.iit.abramov.aois.hashtable.model;

public class Entry {
	private String		id;
	private Integer		data;
	private Integer		hash;

	private Entry		next;
	private Entry		prev;
	private Entry		collusionNext;

	private final int	index;

	public Entry(final String id, final Integer data, final int index, Integer hash) {

		this.id = id;
		hash = hash;
		this.data = data;
		this.index = index;
	}

	public final Entry collusionNext() {

		return collusionNext;
	}

	public final Integer getData() {

		return data;
	}

	public final Integer getHash() {

		return hash;
	}

	public final String getId() {

		return id;
	}

	public final int getIndex() {

		return index;
	}

	public final boolean hasCollusionNext() {

		return collusionNext != null;
	}

	public final boolean hasNext() {

		return next != null;
	}

	public final boolean hasPrev() {

		return prev != null;
	}

	public boolean isEmpty() {

		return id == null;
	}

	public final Entry next() {

		return next;
	}

	public final Entry prev() {

		return prev;
	}

	public void remove() {

		id = null;
		data = null;
		hash = null;
		next = null;
		prev = null;

	}

	public void setCollusionNext(final Entry collusionNext) {

		this.collusionNext = collusionNext;
	}

	public void setHashandData(final Integer data, final Integer hash) {

		this.data = data;
		this.hash = hash;
	}

	public void setIDandData(final String id, final Integer data, final Integer hash) {

		this.id = id;
		this.data = data;
		this.hash = hash;
	}

	public void setNext(final Entry next) {

		this.next = next;
	}

	public void setPrev(final Entry prev) {

		this.prev = prev;
	}

}
