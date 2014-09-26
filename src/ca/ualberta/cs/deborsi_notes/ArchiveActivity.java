package ca.ualberta.cs.deborsi_notes;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ArchiveActivity extends ItemListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		
		// set context to this
		final Context context = this;
		ListView = (ListView) findViewById(R.id.archiveList);
		
		 // initialize managers to manage both the lists
		activeManager = new ItemListManager();
        archiveManager = new ItemListManager();
        try{
        	activeManager.loadApp(itemFile, context);
        	archiveManager.loadApp(archiveFile, context);
        }catch(ClassNotFoundException e){
        	e.printStackTrace();
        }
	
        // initialize the lists
        ActiveList = activeManager.getItemList();
        ArchiveList = archiveManager.getItemList();
        
        // initialize an adapter to set items to ListView
        listAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_multiple_choice, ArchiveList.getItems());
        ListView.setAdapter(listAdapter);
        
        //--------------------------------------
        // the idea about implementing a context 
        // menu was discussed with Kieran Boyle 
        // ccid : kboyle
        //--------------------------------------
        registerForContextMenu(ListView);
        listAdapter.notifyDataSetChanged();
        
        // setup a listener to add items to the active list
		ListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
			public void onItemClick(AdapterView<?> parent, View view,
                   int position, long id) {
                SparseBooleanArray checkedItemPositions = ListView.getCheckedItemPositions();
            	archiveManager.updateChecked(checkedItemPositions);
            	archiveManager.saveApp(archiveFile, context);
            }
        });
        setChecked(ListView);
	}
	
	public void setChecked(ListView ListView) {
		for (int i = ( ArchiveList.size() - 1 ); i >= 0; i--) { 
			ListView.setItemChecked(i, (ArchiveList.getItemIndex(i).getStatus()) );
		}	
	}
	
	//---------------------------------------------------------------------------------
	// Code for implementing the context menu was taken from:
	// 
	// Turotial : Show a context menu for long-clicks in an Android ListView
	// url : http://www.mikeplate.com/2010/01/21/show-a-context-menu-for-long-clicks-in-an-android-listview/
	// Author : Mikael Plate
	// Website : http://www.mikeplate.com/
	//---------------------------------------------------------------------------------
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
	    String title = listAdapter.getItem(info.position).getItem();
	    menu.setHeaderTitle(title);
	    menu.add("UNARCHIVE");
	    menu.add("REMOVE");
	}

	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    int index = info.position;
		SparseBooleanArray checkedItemPositions = ListView.getCheckedItemPositions();
		if (item.getTitle() == "UNARCHIVE") {
			ActiveList.add(ArchiveList.getItemIndex(index));
			ArchiveList.remove(index);
			activeManager.saveApp(itemFile, this);
			listAdapter.notifyDataSetChanged();
			checkedItemPositions.clear();
			setChecked(ListView);
		}
		else if (item.getTitle() == "REMOVE") {
	    	ArchiveList.remove(index);
			archiveManager.saveApp(archiveFile, this);
            listAdapter.notifyDataSetChanged();
            checkedItemPositions.clear();
            setChecked(ListView);
	    } 
		else {
	        return false;
	    }
	    return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}