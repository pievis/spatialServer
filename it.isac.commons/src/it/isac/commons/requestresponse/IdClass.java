package it.isac.commons.requestresponse;

/**
 * Used to send and receive an Id string.
 * @author Pievis
 */
public class IdClass extends GenericData {

	String id;

	public IdClass(String id){
		this.id = id;
		setType(getClass().getCanonicalName());
	}
	
	public IdClass(){
		//this is necessary to make the parser get
		//the correct class to use since it derives
		//from an abstract type
		setType(getClass().getCanonicalName());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
