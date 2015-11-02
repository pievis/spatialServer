package it.isac.db.search;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import it.isac.commons.model.Unit;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
//@JsonSubTypes({ @JsonSubTypes.Type(value = RangeSearch.class, name = "range"),
//		@JsonSubTypes.Type(value = NearestNSearch.class, name = "nearest") })
public class SearchCriteria {

	/**
	 * Get the default search criteria. (Range search 100 m)
	 * 
	 * @return
	 */
	public static SearchCriteria getDefault() {
		SearchCriteria def = new RangeSearch(100, Unit.M);
		return def;
	}
}
