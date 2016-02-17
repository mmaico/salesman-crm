package br.com.kproj.salesman.infrastructure.helpers;

import java.util.Date;

public class RangeDateDTO {

	private Date startDate;
	
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public static RangeDateDTO nullObject() {
		return new RangeDateDTO();
	}
	
	public Boolean isNullObject() {
		
		return this.startDate == null || this.endDate == null;
	}
	
	public static RangeDateDTO build() {
		return new RangeDateDTO();
	}
	
	public RangeDateDTO withStartDate(Date date) {
		this.startDate = date;
		return this;
	}
	
	public RangeDateDTO withEndDate(Date date) {
		this.endDate = date;
		return this;
	}
}
