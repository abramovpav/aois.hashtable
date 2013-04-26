package by.bsuir.iit.abramov.aois.hashtable.model;

public class Entry {
	private String	id;
	private Integer	data;
	private Entry	next;
	private Entry  collusionNext;
	private final int index;
	private Integer hash;
	
	public Entry(final String id, final Integer data, int index, Integer hash) {

		this.id = id;
		hash = hash;
		this.data = data;
		this.index = index;
	}
	
	public final int getIndex()  {
		return index;
	}

	public final Integer getData() {

		return data;
	}

	public final String getId() {

		return id;
	}
	
	public final boolean hasNext() {
		return next != null;
	}
	
	public final boolean hasCollusionNext() {
		return collusionNext != null;
	}

	public final Entry next() {

		return next;
	}
	
	public final Entry collusionNext() {

		return collusionNext;
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
	
	public boolean isEmpty() {
		return id == null;
	}
	
	public final Integer getHash() {
		return hash;
	}

	public void setNext(final Entry next) {

		this.next = next;
	}
	
	public void setCollusionNext(final Entry collusionNext) {

		this.collusionNext = collusionNext;
	}

}
