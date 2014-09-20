package ca.ualberta.cs.deborsi_notes;

public class Item {
	
	protected String itemName;
	
	public Item(String itemName) {
		this.itemName = itemName;
	}

	public String getItem() {
		return itemName;
	}
	
	public String toString(){
		return getItem();
	}
}
