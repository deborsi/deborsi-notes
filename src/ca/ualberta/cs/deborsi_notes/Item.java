package ca.ualberta.cs.deborsi_notes;

import java.io.Serializable;

public class Item implements Serializable {
	
	/**
	 * Item Serialization ID
	 */
	private static final long serialVersionUID = -8540572367716383907L;
	protected String itemName;
	
	public Item(String itemName) {
		this.itemName = itemName;
	}

	public String getItem() {
		return this.itemName;
	}
	
	public String toString(){
		return getItem();
	}
	
	public boolean equals (Object compareItem){
		if (compareItem != null && 
				compareItem.getClass() == this.getClass()){
			return this.equals((Item)compareItem);
		}else{
			return false;
		}
	}
	
	public boolean equals(Item compareItem){
		if(compareItem == null){
			return false;
		}
		return getItem().equals(compareItem.getItem());
	}
	public int hashCode(){
		return ("Item:"+getItem()).hashCode();
	}
}
