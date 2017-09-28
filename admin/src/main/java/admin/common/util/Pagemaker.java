package admin.common.util;

import admin.common.controller.CodeController;

public class Pagemaker {
//	private int PER = 300;
//	private double DPER = 300.0;
	private int page;
	private int count;
	private int start;
	private int end;
	private boolean prev;
	private boolean next;
	private int from;
	private int to;
	private int totalPage;
	private int prevZone;
	private int nextZone;
	
	private CodeController code = new CodeController();
	
	public int getPrevZone() {
		return prevZone;
	}

	public void setPrevZone(int prevZone) {
		this.prevZone = prevZone;
	}

	public int getNextZone() {
		return nextZone;
	}

	public void setNextZone(int nextZone) {
		this.nextZone = nextZone;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
	
	public int getCount() {
		return count;
	}

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

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public void setCount(int count) {
		if (count < 1) {
//			this.from = 0;
//			this.to= 0;
//			this.count = 0;
//			this.page = 1;
			return;
		}
		this.count = count;
//		System.out.println("총 컬럼 갯수 = " + count);
		calcPage();
	}
	
	public int getPER() {
		try {
			return code.getValueInt("config_limit_admin");
		} catch(Exception e) {
			e.printStackTrace();
			return 300;
		}
	}

	private void calcPage() { 
		int PER = getPER();
		double DPER = PER * 1.0;
		// page변수는 현재 페이지번호 
		int tempEnd = (int)(Math.ceil(page / DPER) * PER);
		this.totalPage = this.count / PER;
		int remaind = this.count % PER;
		if(remaind>0) {
			this.totalPage++;
		}
		// 현재 페이지번호를 기준으로 끝 페이지를 계산한다.
//		System.out.println("page = " +page);
//		System.out.println("tempEnd = "+tempEnd);
//		System.out.println("this.count =" +this.count);
//		System.out.println("totalPage =" + this.totalPage);
		
		// 시작 페이지 계산
		this.start = tempEnd - (PER - 1);
		if (tempEnd * PER > this.count) { // 가상으로 계산한 tempEnd크기가 실제 count보다 많을경우
			this.end = (int) Math.ceil(this.count / DPER);
		} else { 
			this.end = tempEnd; // 실제 count가 tempEnd보다 많을경우 
		}
//		System.out.println("this.end = "+this.end);
		this.prev = this.page != 1;
		this.next = this.page != this.totalPage;
		
		this.from = ((page -1) * PER) + 1;
		this.to = page * PER;
		if(this.to > this.count) {
			this.to= this.count;
		}
		if(this.page!=1) {
			this.prevZone = this.page-1;
		} else {
			this.prevZone = 1;
		}
		
		if(this.page!=totalPage) {
			this.nextZone = this.page+1;
		} else {
			this.nextZone = totalPage;
		}
	}
}
