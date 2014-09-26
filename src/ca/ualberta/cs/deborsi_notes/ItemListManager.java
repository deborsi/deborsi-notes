package ca.ualberta.cs.deborsi_notes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.SparseBooleanArray;

public class ItemListManager {
	
	// lazy singleton
	private ItemList List;
	public ItemList getItemList(){
		if(List == null){
			List = new ItemList();
		}
		return List;
	}
	
	//------------------------------------------------------------------------------------------
	// Code for the function updateChecked() has been taken from :
	// 
	// Turotial : Deleting Selected Items From ListView in Android
	// url : http://wptrafficanalyzer.in/blog/deleting-selected-items-from-listview-in-android/
	// Author : George Mathew
	// Website : http://wptrafficanalyzer.in/blog
	//------------------------------------------------------------------------------------------
	
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
		List.getItemIndex(i).setStatus(status);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------
	// Code for the functions loadApp() and saveApp() have been taken from :
	// 
	// Repository : LonelyTwitterGson 
	// url : https://github.com/deborsi/LonelyTwitterGson/blob/master/LonelyTwitter/src/ca/ualberta/cs/lonelytwitter/data/FileDataManager.java
	// Author : dfserrano
	// Website : https://github.com/dfserrano
	//-----------------------------------------------------------------------------------------------------------------------------------------
	
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