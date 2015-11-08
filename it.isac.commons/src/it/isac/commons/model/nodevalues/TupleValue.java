package it.isac.commons.model.nodevalues;

import java.util.ArrayList;
import java.util.List;

import it.isac.commons.interfaces.INodeValue;

// This class models a tuple value for a node (recall, aka field)
public class TupleValue extends BasicNodeValue{
	List<INodeValue> value;
	
	public TupleValue(String key) {
		super(key);
		value = new ArrayList<INodeValue>();
	}
	
	// Overload
	public void setValue(List<INodeValue> value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		// create and return the tuple value
		String val = "<";
		for(int i=0; i<value.size(); i++) {
			if(i != value.size()-1)
				val += value.get(i).getValue() + ", ";
			else
				val += value.get(i).getValue() + ">";
		}
		return val;
	}
}
