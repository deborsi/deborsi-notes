package ca.ualberta.cs.deborsi_notes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ItemListManager {
	
	static final String prefFile = "ItemList";
	static final String ilKey = "itemList";
	
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

	
}
