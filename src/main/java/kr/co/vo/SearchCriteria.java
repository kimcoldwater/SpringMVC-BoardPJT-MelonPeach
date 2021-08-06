package kr.co.vo;

//검색기능 VO
public class SearchCriteria extends Criteria{
	
	private String searchType = "";
	private String Keyword = "";
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return Keyword;
	}
	public void setKeyword(String keyword) {
		Keyword = keyword;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", Keyword=" + Keyword + "]";
	}
	
	
	
}