package com.epicproportionstour.user.service;
import java.util.List;

import com.epicproportionstour.user.model.Users;

	
	public class UserResponse {
	    
	    private List<Users> rows;
	    private Integer total;
	    private Integer records;
	    private Integer page;

	    public List<Users> getRows() {
	        return rows;
	    }

	    public void setRows(List<Users> rows) {
	        this.rows = rows;
	    }

	    public Integer getTotal() {
	        return total;
	    }

	    public void setTotal(Integer total) {
	        this.total = total;
	    }

	    public Integer getRecords() {
	        return records;
	    }

	    public void setRecords(Integer records) {
	        this.records = records;
	    }

	    public Integer getPage() {
	        return page;
	    }

	    public void setPage(Integer page) {
	        this.page = page;
	    }
	} 
