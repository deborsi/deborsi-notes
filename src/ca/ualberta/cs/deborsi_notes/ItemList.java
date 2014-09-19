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

	public Item selectItem() throws EmptyItemListException {
		int size = itemList.size();
		if  (size <= 0){
			throw new EmptyItemListException();
		}
		int index = (int) (itemList.size() * Math.random());
		return itemList.get(index);
	}

	public int size() {
		return itemList.size();
	}
	
	public boolean contains(Item testItem) {
		return itemList.contains(testItem);
	}

}
