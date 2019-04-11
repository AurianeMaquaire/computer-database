package com.excilys.model;

import java.util.ArrayList;

public class Page<T> {
	
	private ArrayList<T> data;
	private int currentPage;
	private int length;
	private int pageSize;
	
	
	public Page(ArrayList<T> list) {
		super();
		this.data = list;
		this.currentPage = 0;
		this.length = list.size();
		this.pageSize = 20;
	}
	
	
	public ArrayList<T> getData() {
		return this.data;
	}
	
	public int getCurrentPage() {
		return this.currentPage;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getPageSize() {
		return this.pageSize;
	}
	
	
	public void setData(ArrayList<T> data) {
		this.data = data;
		this.length = data.size();
	}
	
	public void setCurrentPage(int currentPage) {
		if (currentPage >= 0 && currentPage < this.length) {
			this.currentPage = currentPage;
		}
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public int previousPage() {
		if (this.currentPage * this.pageSize > this.pageSize) {
			return this.currentPage - 1;
		} else {
			return 0;
		}
	}
	
	public int nextPage() {
		if (this.currentPage * this.pageSize < this.length) {
			return this.currentPage + 1;
		} else {
			return this.currentPage;
		}
	}
	
	public int start() {
		return this.currentPage * this.pageSize;
	}
	
	public int end() {
		return (this.currentPage + 1) * this.pageSize - 1;
	}
	
	public int getMaxPages() {
		return this.length / this.pageSize + ((this.length % this.pageSize > 0) ? 1 : 0);
	}
	
	public int startIndex() {
		if (this.currentPage < 3) {
			return 1;
		}
		return this.currentPage - 2;
	}
	
	public int endIndex() {
		if (this.currentPage > getMaxPages() - 3) {
			return getMaxPages();
		}
		return this.currentPage + 2;
	}
	
}
