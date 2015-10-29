package it.isac.db;

public class NearestNSearch extends SearchCriteria {
	int number;

	public NearestNSearch(){}
	
	/**
	 * Get the number of nearest node to retrieve in the search.
	 * @return
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Set the number of nearest nodes to retrieve in the search.
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
}
