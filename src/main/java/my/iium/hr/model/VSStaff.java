package my.iium.hr.model;

import java.util.Arrays;

public class VSStaff {
	private int currentPage;
	private int TotalPages;
	private int TotalItems;

	private ViewSisStaff[] staffList;

	public VSStaff() {
		super();
	}

	public VSStaff(int currentPage, int totalPages, int totalItems, ViewSisStaff[] staffList) {
		super();
		this.currentPage = currentPage;
		TotalPages = totalPages;
		TotalItems = totalItems;
		this.staffList = staffList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return TotalPages;
	}

	public void setTotalPages(int totalPages) {
		TotalPages = totalPages;
	}

	public int getTotalItems() {
		return TotalItems;
	}

	public void setTotalItems(int totalItems) {
		TotalItems = totalItems;
	}

	public ViewSisStaff[] getStaffList() {
		return staffList;
	}

	public void settStaffList(ViewSisStaff[] staffList) {
		this.staffList = staffList;
	}

	@Override
	public String toString() {
		return "VSStaff [currentPage=" + currentPage + ", TotalPages=" + TotalPages + ", TotalItems=" + TotalItems
				+ ", vsStaff=" + Arrays.toString(staffList) + "]";
	}

}
