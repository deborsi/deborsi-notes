package ca.ualberta.cs.deborsi_notes;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// sets managers, adapters required to create an Item List

public class ItemListActivity extends Activity{
	
	// ItemListActivity class contains all variable declarations
	protected static final String itemFile = "itemList.sav";
	protected static final String archiveFile = "archiveList.sav";
	
	// Items entered by the user is stored in these ArrayLists
	ItemList ActiveList;
	ItemList ArchiveList;
	
	// Both the lists are managed by their respective managers
	ItemListManager activeManager;
	ItemListManager archiveManager;
	
	// Declaring an ArrayAdapter to set items to ListView
	ArrayAdapter<Item> listAdapter;
	ListView ListView;
}