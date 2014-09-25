package ca.ualberta.cs.deborsi_notes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.SparseBooleanArray;

public class ItemListManager {
	private ItemList List;
	public ItemList getItemList(){
		if(List == null){
			List = new ItemList();
		}
		return List;
	}
	
	

	//Taken from http://wptrafficanalyzer.in/blog/deleting-selected-items-from-listview-in-android/ 2014-09-21
	
	public void updateChecked(SparseBooleanArray checkedItemPositions) {
		
      for(int i = (checkedItemPositions.size()) - 1; i >= 0; i--){
		if(checkedItemPositions.get(i)){
          	setStatus(i, true);
          }
          else {
          	setStatus(i, false);
          }
      }
	}

	public void setStatus(int i, boolean status) {
		List.get(i).setStatus(status);
	}
	
	public void loadApp(String FILENAME, Context context) throws ClassNotFoundException {
		ItemList lts = new ItemList();
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			lts = (ItemList) ois.readObject();		    
		} catch (IOException e) {
		    	e.printStackTrace();
		} 
		List = lts;
	}

	public void saveApp(String FILENAME, Context context) {
		try {
		  FileOutputStream fos = context.openFileOutput(FILENAME, 0);
		  ObjectOutputStream oos = new ObjectOutputStream(fos);
		  oos.writeObject(List);
		  fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*static final String prefFile = "ItemList";
	static final String ilKey = "itemList";
	static final String prefFile1 = "ArchiveList";
	static final String alKey = "archiveList";
	
	Context context;
	
	static private ItemListManager itemListManager = null;
	
	public static void initManager(Context context){
		if (itemListManager == null){
			if (context == null){
				throw new RuntimeException("missing context for ItemListManager");
			}
			itemListManager = new ItemListManager(context);
		}
	}
	
	public static ItemListManager getManager(){
		if (itemListManager == null){
			throw new RuntimeException("did not initialize Manager");
		}
		return itemListManager;
	}
	
	public ItemListManager(Context context){
		this.context = context;
	}
	
	public ItemList loadItemList() throws IOException, ClassNotFoundException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String itemListData = settings.getString(ilKey,"");
		if (itemListData.equals("")){
			return new ItemList();
		}else{
			return itemListFromString(itemListData);
		}
	}
	
	public ItemList loadArchiveList() throws IOException, ClassNotFoundException{
		SharedPreferences settings = context.getSharedPreferences(prefFile1, Context.MODE_PRIVATE);
		String itemListData = settings.getString(alKey,"");
		if (itemListData.equals("")){
			return new ItemList();
		}else{
			return itemListFromString(itemListData);
		}
	}
	
	static public ItemList itemListFromString(String itemListData) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(itemListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ItemList)oi.readObject();
	}
	
	static public String itemListToString(ItemList il) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(il);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}

	public void saveItemList(ItemList il) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(ilKey, itemListToString(il));
		editor.commit();
	}
	
	public void saveArchiveList(ItemList il) throws IOException{
		SharedPreferences settings = context.getSharedPreferences(prefFile1, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(alKey, itemListToString(il));
		editor.commit();
	}	
}*/
