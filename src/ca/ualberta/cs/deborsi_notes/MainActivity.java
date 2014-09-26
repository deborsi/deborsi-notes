/*
deborsi-notes : Simple TO-DO List Android Application

Copyright (C) 2014  Deborsi Hazarika deborsi@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ca.ualberta.cs.deborsi_notes;

import android.content.Context;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ItemListActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    protected void onStart(){
    	super.onStart();
    	// set context to this
    	final Context context = this;
    	
    	Button addButton = (Button)findViewById(R.id.addButton);
        ListView = (ListView) findViewById(R.id.itemList);
        
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
        listAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_multiple_choice, ActiveList.getItems());
        ListView.setAdapter(listAdapter);
        
        //--------------------------------------
        // the idea about implementing a context 
        // menu was discussed with Kieran Boyle 
        // ccid : kboyle
        //--------------------------------------
        registerForContextMenu(ListView);
        listAdapter.notifyDataSetChanged();
        
        // setup a listener to add items to the active list
        OnClickListener addListener = new OnClickListener(){
			@Override
			//------------------------------------------------
			// Implementing the code for the addItem button was
			// inspired by Abram Hindle's Youtube Tutorial series     
			//
			// Turotial : Student Picker for Android: 
			//            5 Controllers and adding students
			// url : https://www.youtube.com/watch?v=uLnoI7mbuEo&list=UUTLkh9KmeYXQBR59wJxq1eg
			// Author : Abram Hindle
			//------------------------------------------------
			public void onClick(View v) {
                EditText textView = (EditText) findViewById(R.id.addItemField);
                Item item = new Item(textView.getText().toString());
                ActiveList.addItem(item);
                activeManager.saveApp(itemFile, context);
                textView.setText("");
                listAdapter.notifyDataSetChanged();
			}
        };
        
        addButton.setOnClickListener(addListener);
        
        ListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				SparseBooleanArray checkedItemPositions = ListView.getCheckedItemPositions();
				activeManager.updateChecked(checkedItemPositions);
				activeManager.saveApp(itemFile, context);
			}
		});
        setChecked(ListView);
    }
    
    public void setChecked(ListView listView){
    	for (int i = (ActiveList.size() - 1) ; i >= 0 ; i--){
    		listView.setItemChecked(i, (ActiveList.getItemIndex(i).getStatus()));
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
   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
    		super.onCreateContextMenu(menu, v, menuInfo);
    		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
    		String title = listAdapter.getItem(info.position).getItem();
    		menu.setHeaderTitle(title);
    		menu.add("ARCHIVE");
    		menu.add("REMOVE");
    }
    	
    public boolean onContextItemSelected(MenuItem item){
    		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
    		int index = info.position;
    		SparseBooleanArray checkedItemPositions = ListView.getCheckedItemPositions();
    		if (item.getTitle() == "ARCHIVE"){
    			ArchiveList.add(ActiveList.getItemIndex(index));
    			ActiveList.remove(index);
    	        activeManager.saveApp(itemFile, this);
    	        archiveManager.saveApp(archiveFile, this);
    	        listAdapter.notifyDataSetChanged();
    	        checkedItemPositions.clear();
    	        setChecked(ListView);
    		}
    		else if (item.getTitle() == "REMOVE"){
    			ActiveList.remove(index);
    			activeManager.saveApp(itemFile, this);
    			listAdapter.notifyDataSetChanged();
    			checkedItemPositions.clear();
    			setChecked(ListView);
    		}
    		else{
    			return false;
    		}
    		return true;
    }
    		
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void emailOptionsMenuFlash(MenuItem menu){
    	Toast.makeText(this, "E-Mail", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, EmailActivity.class);
    	startActivity(intent);
    }
    
    public void archiveMenuFlash(MenuItem menu){
    	Toast.makeText(this, "Archives", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
    	startActivity(intent);
    }
    
    public void summaryMenuFlash(MenuItem menu){
    	Toast.makeText(this, "Summary", Toast.LENGTH_SHORT).show();
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
        if (id == R.id.archiveFolderMenuItem) {
	        Intent intent = new Intent(this, ArchiveActivity.class);
	        startActivity(intent);
		}
        return super.onOptionsItemSelected(item);
    }
}