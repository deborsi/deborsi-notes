package ca.ualberta.cs.deborsi_notes;

import java.io.IOException;

/*public class ItemListController {

	// Lazy Singleton
	private static ItemList itemList = null;
	static public ItemList getItemList(){
		if (itemList == null){
			try {
				//itemList = ItemListManager.getManager().loadItemList();
				itemList.addListener(new Listener(){
					@Override
					public void update() {
						saveItemList();
					}
				});
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could Not Deserialize ItemList from ItemListManager");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Could Not Deserialize ItemList from ItemListManager");
			}
		}
		return itemList;	
	}
	
	static public void saveItemList(){
		try {
			ItemListManager.getManager().saveItemList(getItemList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could Not Deserialize ItemList from ItemListManager");
		}	
	}
	
	public void addItem(Item item) {
		getItemList().addItem(item);
	}
	
	public void removeItem(Item item) {
		getItemList().removeItem(item);
	}
}
*/