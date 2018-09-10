package com.ecjtu.rwx.dbutils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页显示工具类
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class PageBean<T> {
	private int currentpage;// 当前页数
	private int pagesize; // 分页显示行数

	private int totolRecode; // 总记录数

	private int startIndex; // 开始索引
	private int endIndex;

	private int totalpages; // 最大页数
	
	// 动态显示条 模拟百度前面显示前2后2
	private int start;

	private int end;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	private List<T> data;// 查询的数据

	public PageBean(int currentPage, int pageSize, int totolRecode) {
		super();
		this.currentpage = currentPage;
		this.pagesize = pageSize;
		this.totolRecode = totolRecode;

		// 计算当前开始索引 总页数
		this.startIndex = (this.currentpage - 1) * this.pagesize;
		this.endIndex=this.startIndex+this.pagesize;
		//防止越界
		if(this.endIndex>this.totolRecode){
			this.endIndex=totolRecode;
		}
		this.totalpages = (this.totolRecode + this.pagesize - 1) / this.pagesize;
		// 初始化数据
		this.start = 1;
		this.end = 5;
		if (this.totalpages <= 5) {
			this.end = this.totalpages;
		} else {
			// 前面3显示3
			this.start = this.currentpage - 2;
			this.end = this.currentpage + 2;
			// 如果currentpage=1时
			if (this.start < 1) {
				this.start = 1;
				this.end = 5;
			}
			// 如果currentpage=时
			if (this.end > this.totalpages) {
				this.end = this.totalpages;
				this.start = this.totalpages - 4;
			}
		}
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotolRecode() {
		return totolRecode;
	}

	public void setTotolRecode(int totolRecode) {
		this.totolRecode = totolRecode;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}

	public List<T> getData() {
		return data;
	}

	/**
	 * 设置分页数据的开始索引 不需要修改sql语句直接查询 但是这样会影响效率
	 * 
	 * @param data
	 */
	public void setData(List<T> data) {
		List<T> subList = data.subList(startIndex, endIndex);
		this.data = subList;
	}

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list2.addAll(list1.subList(0, 2));
		System.out.println(list2);
		System.out.println(list1.subList(0, 2));
	}

}
