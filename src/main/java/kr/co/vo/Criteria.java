package kr.co.vo;

public class Criteria {

	private int page; //현재 페이지 번호
	private int perPageNum; //한 페이지당 보여질 게시글의 갯수
	private int rowStart; //페이징 중 시작할 숫자 1 ,6 ,11 ,16 등..
	private int rowEnd; //페이징중 끝날 숫자 5,10,15 ~

	//생성자로 , 최초로 보여질 페이지 번호와 페이지당 보여줄 게시글의 갯수를 초기화
	public Criteria() {
		this.page = 1; //처음 보여질 페이지 번호
		this.perPageNum = 10; //보여질 리스트의 개수
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

		//리스트의 개수 제한 ..왜?
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}

	public int getPage() {
		return page;
	}
	
//method for MyBatis SQL Mapper
//특정 페이지의 게시글 시작번호,게시글 시작 행 번호
//현재 페이지의 게시글 시작 번호 = (현재 페이지 번호-1)* 페이지당 보여줄 게시글 갯수
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	public int getPerPageNum() {
		return this.perPageNum;
	}

	public int getRowStart() {
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}

	public int getRowEnd() {
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}

}