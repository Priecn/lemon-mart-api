package com.prince.security.model;

import java.io.Serializable;
import java.util.List;

public class ListResponse<T> implements Serializable {
	
	private static final long serialVersionUID = -1335484154532321545L;
	
	private final Integer total;
	private final List<T> items;
	public ListResponse(Integer total, List<T> items) {
		this.total = total;
		this.items = items;
	}
	public Integer getTotal() {
		return total;
	}
	public List<T> getItems() {
		return items;
	}
	
	
}
