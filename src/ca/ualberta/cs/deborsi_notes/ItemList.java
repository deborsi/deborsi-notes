package ca.ualberta.cs.deborsi_notes;

import java.util.ArrayList;
import java.util.Collection;

public class ItemList {
	
	protected ArrayList<Item>itemList;
	protected ArrayList<Listener> listeners;
	
	public ItemList(){
		itemList = new ArrayList<Item>();
		listeners = new ArrayList<Listener>();
	}

	public Collection<Item> getItems() {
		return itemList;
	}

	public void addItem(Item testItem) {
		itemList.add(testItem);	
		notifyListeners();
	}

	private void notifyListeners() {
		for (Listener listener : listeners){
			listener.update();
		}
		
	}

	public void removeItem(Item testItem) {
		itemList.remove(testItem);
		notifyListeners();
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

	public void addListener(Listener l) {
		listeners.add(l);
	}

	public void removeListener(Listener l) {
		listeners.remove(l);		
	}

}
