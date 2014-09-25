package ca.ualberta.cs.deborsi_notes;

import java.io.Serializable;

public class Item implements Serializable {
	
	/**
	 * Item Serialization ID
	 */
	private static final long serialVersionUID = -8540572367716383907L;
	
	protected String itemName;
	private Boolean status;
	
	// constructor
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
	
	public void setItem(String itemName){
		this.itemName = itemName;
	}
	
	public void setStatus(Boolean status){
		this.status = status;
	}
}
