package ca.ualberta.cs.deborsi_notes;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemList implements Serializable {
	
	/**
	 * ItemList Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<Item>itemList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	// constructor for type ItemList
	public ItemList(){
		itemList = new ArrayList<Item>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener>getListeners(){
		if (listeners == null){
			listeners = new ArrayList<Listener>();
		}
	return listeners;
	}

	public ArrayList<Item> getItems() {
		return itemList;
	}

	public void addItem(Item testItem) {
		itemList.add(testItem);	
		notifyListeners();
	}

	private void notifyListeners() {
		for (Listener listener : getListeners()){
			listener.update();
		}
	}
	
	public void removeItem(Item testItem) {
		itemList.remove(testItem);
		notifyListeners();
	}
	
	public int size() {
		return itemList.size();
	}
	
	public void add(Item item) {
		itemList.add(item);
	}
	
	public void remove(int i){
		itemList.remove(i);
	}
	
	public Item getItemIndex(int i){
		return itemList.get(i);
	}
}