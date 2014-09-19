package ca.ualberta.cs.deborsi_notes;

import java.util.ArrayList;
import java.util.Collection;

public class ItemList {
	
	protected ArrayList<Item>itemList;
	
	public ItemList(){
		itemList = new ArrayList<Item>();
	}

	public Collection<Item> getItems() {
		return itemList;
	}

	public void addItem(Item testItem) {
		itemList.add(testItem);		
	}

	public void removeItem(Item testItem) {
		itemList.remove(testItem);
			
	}

	public Item selectItem() {
		int index = (int) (itemList.size() * Math.random());
		return itemList.get(index);
	}

}
