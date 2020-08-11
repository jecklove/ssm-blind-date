package org.jecklove.utils;

import java.util.logging.Logger;

/**
 * 分页工具类
 *    1.封装属性数据 [前台页面数据，后台数据]
 *    2.页面逻辑处理[总页数，初始化分页数据...]
 *    
 * 优化调整
 *    1.加入分段取的列表数据  List<T> dataList
 *    2.优化分页属性
 *    	 requestPage 与 currentPage 可以合并  ===》requestPage
 *    	 pageSize 与 offset 可以合并   ===》pageSize
 * @author 15256
 *
 */
public class PageUtil {
	
	private static final Logger logger = Logger.getLogger(PageUtil.class.getName());
	
	private static final PageUtil instance = new PageUtil();
	
	private PageUtil() {}	
	
	public static PageUtil getInstance() {
		return instance;
	}

//-------------------1.封装属性数据 [前台页面数据，后台数据]代码开始----------------------------
	
	//====================页面相关属性========================
	private int firstPage; //首页
	private int lastPage; //尾页
	private int previousPage; //上一页
	private int nextPage; //下一页	
	private int pageSize = 10; //每页条数，默认是10
	private int pageCount; //总页数
	private int currentPage; //当前页    = 请求页
	private int requestPage; //请求页
	
	//====================后台相关属性+数据库===================
	private int totalCount; //总记录数
	private int limit;  //分段取数据    开始索引  ===》分段取的起点
	private int offset; //分段取数据    分段条数  ===》分段取的终点 
	
	
	//====================封装列表数据===================
	//private List<T> dataList;
	
	
	//====================getter/setter===================
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRequestPage() {
		return requestPage;
	}
	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
//-------------------1.封装属性数据 [前台页面数据，后台数据]代码结束----------------------------



//-------------------2.页面逻辑处理[总页数，初始化分页数据...]代码开始--------------------------
	/**
	 * 分页逻辑初始化工作
	 *    1.计算总页数     pageCount
	 *    2.计算分段取数据终点  offset
	 *    3.当前页是首页或尾页的边界问题
	 *    
	 * @param requestPage  页面当前请求页  ===> 页面传过来的数据
	 * @param pageSize     每页显示条数      ===> 页面传过来的数据  + 后台java代码设置
	 * @param totalCount   总记录数             ===>  后台获取数据  + 数据库
	 */
	public void init(int requestPage, int pageSize, int totalCount) {
		
		this.requestPage = requestPage; //赋值给请求页属性
		this.pageSize = pageSize; //赋值给每页显示条数属性
		this.totalCount = totalCount; //总记录数
		
		logger.info("===========> this.requestPage =  " + this.requestPage);
		logger.info("===========> this.pageSize =  " + this.pageSize);
		logger.info("===========> this.totalCount =  " + this.totalCount);
		
		this.firstPage = 1; //首页  = 第一页	
		
		//------------------------------requestPage------------------------------------------
		this.currentPage = requestPage; //赋值给当前页属性    当前页 = 请求页		
		
		//当前页  = 第一页
		if(this.currentPage == this.firstPage) {
			this.previousPage = this.firstPage;
		}else {
			this.previousPage = this.currentPage - 1; //上一页  = 当前页  - 1
		}
		
		//------------------------------pageSize + totalCount------------------
		this.offset = pageSize; //分段取数据  = pageSize		
		this.limit = (this.currentPage - 1) * this.pageSize; //分段取数据    开始索引 = (当前页 - 1)*每页显示条数
		logger.info("===========> 分段取数据  limit " + this.limit + ", " + this.offset);
		
		//总页数 = 总记录数  % 每页显示条数  != 0 ?  总记录数  / 每页显示条数 + 1 : 总记录数  / 每页显示条数;
		this.pageCount = this.totalCount % this.pageSize != 0 ? this.totalCount / this.pageSize + 1 : this.totalCount / this.pageSize;
		
		this.lastPage = pageCount; //尾页 = 总页数
		
		
		//当前页 = 尾页
		if(this.currentPage == this.lastPage) {
			this.nextPage = this.lastPage;
		}else {
			this.nextPage = this.currentPage + 1; //下一页  = 当前页  + 1
		}
		
		
		logger.info("===========> 总页数  pageCount = " + this.pageCount);
		
	}
//-------------------2.页面逻辑处理[总页数，初始化分页数据...]代码结束--------------------------	

}
