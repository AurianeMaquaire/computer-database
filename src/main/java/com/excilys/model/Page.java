package com.excilys.model;

import java.util.ArrayList;

public class Page<T> {
	
	private ArrayList<T> data;
	private int currentPage;
	private int length;
	private int pageSize;
	
	
	public Page (ArrayList<T> list) {
		super();
		this.data = list;
		this.currentPage = 0;
		this.length = list.size();
		this.pageSize = 10;
	}
	
	
	public ArrayList<T> getData () {
		return this.data;
	}
	
	public int getCurrentPage () {
		return this.currentPage;
	}
	
	public int getLength () {
		return this.length;
	}
	
	public int getPageSize () {
		return this.pageSize;
	}
	
	
	public void setData (ArrayList<T> data) {
		this.data = data;
	}
	
	public void setCurrentPage (int currentPage) {
		if (currentPage >= 0 && currentPage <= this.length) {
			this.currentPage = currentPage;
		}
	}
	
	public void setLength (int length) {
		this.length = length;
	}
	
	public void setPageSize (int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public int previous () {
		this.currentPage = Math.max(this.currentPage-1, 0);
		return this.currentPage;
	}
	
	public int next () {
		this.currentPage = Math.min(this.currentPage+1, this.length);
		return this.currentPage;
	}
	
	
	public int debut () {
		return this.currentPage * this.pageSize;
	}
	
	public int fin () {
		return (this.currentPage + 1) * this.pageSize - 1;
	}
	
}
