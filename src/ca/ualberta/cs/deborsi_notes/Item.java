package ca.ualberta.cs.deborsi_notes;

import java.io.Serializable;

// Implements an item of type Item to be put on the list

public class Item implements Serializable {
	
	/**
	 * Item Serialization ID
	 */
	private static final long serialVersionUID = -8540572367716383907L;
	
	protected String itemName;
	private Boolean status;
	
	// constructor for type Item
	public Item(String itemName) {
		this.itemName = itemName;
		this.status = false;
	}

	public String getItem() {
		return itemName;
	}
	
	public String toString(){
		return getItem();
	}
	
	public Boolean getStatus(){
		return status;
	}

	public void setStatus(Boolean status){
		this.status = status;
	}
}