package com.museumserver.entity.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class Pagination {

	@JsonView(DataViews.DefaultData.class)
	private Long totalElements;

	@JsonView(DataViews.DefaultData.class)
	private int totalPages;

	@JsonView(DataViews.DefaultData.class)
	private int pageNumber;

	@JsonView(DataViews.DefaultData.class)
	private int pageSize;

	@JsonView(DataViews.DefaultData.class)
	private List<Object> elements;

	public Pagination(Long totalElements, int totalPages, int pageNumber, int pageSize, Object elements) {
		super();
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.elements = (List<Object>) elements;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Object> getElements() {
		return elements;
	}

	public void setElements(List<Object> elements) {
		this.elements = elements;
	}

}
