package ca.ualberta.cs.deborsi_notes;

public class ItemListController {
	
	// Lazy Singleton
	private static ItemList itemList = null;
	static public ItemList getItemList(){
		if (itemList == null){
			itemList = new ItemList();
		}
		return itemList;	
	}
	public void addItem(Item item) {
		getItemList().addItem(item);
	}

}
