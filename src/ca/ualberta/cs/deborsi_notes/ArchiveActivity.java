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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ArchiveActivity extends ItemListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		final Context context = this;
		ListView = (ListView) findViewById(R.id.archiveList);
		
		activeManager = new ItemListManager();
        archiveManager = new ItemListManager();
        try{
        	activeManager.loadApp(itemFile, context);
        	archiveManager.loadApp(archiveFile, context);
        }catch(ClassNotFoundException e){
        	e.printStackTrace();
        }
	
        ActiveList = activeManager.getItemList();
        ArchiveList = archiveManager.getItemList();
        // Declaring an ArrayAdapter to set items to ListView
        listAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_multiple_choice, ArchiveList.getItems());
        ListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        registerForContextMenu(ListView);
        
		ListView.setOnItemClickListener(new OnItemClickListener() {
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
			ListView.setItemChecked(i, (ArchiveList.get(i).getStatus()) );
		}	
	}
	
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
			ActiveList.add(ArchiveList.get(index));
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
	
        
	
	
	
	
	
	
	
	










































































































/*public class ArchiveActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
		
		ItemListManager.initManager(this.getApplicationContext());
		
		ListView listView = (ListView) findViewById(R.id.archiveList);
        Collection<Item>items = ItemListController.getItemList().getItems();
        // Items entered by the user is stored in this ArrayList
        final ArrayList<Item> list = new ArrayList<Item>(items);
        // Declaring an ArrayAdapter to set items to ListView
        final ArrayAdapter<Item>itemAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_multiple_choice, list);
        listView.setAdapter(itemAdapter);
        
     // Added an observer
        ItemListController.getItemList().addListener(new Listener() {
			@Override
			public void update() {
				list.clear();
				Collection<Item>items = ItemListController.getItemList().getItems();
				list.addAll(items);
				itemAdapter.notifyDataSetChanged();
			}
		});
        
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(ArchiveActivity.this);
				adb.setMessage("Delete "+list.get(position).toString()+"?");
				adb.setCancelable(true);
				final int finalPosition = position;
				adb.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Item item = list.get(finalPosition);
						ItemListController.getItemList().removeItem(item);
					}
				});
				adb.setNegativeButton("Cancel", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				adb.show();
				return false;
			}
		});
        
        listView.setOnItemClickListener(new OnItemClickListener(){
      	  
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				SparseBooleanArray checkedItemPositions = ((ListView) findViewById(R.id.archiveList)).getCheckedItemPositions();
				int itemCount = ((ListView) findViewById(R.id.archiveList)).getCount();
				 
                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                    }
                }
                itemAdapter.notifyDataSetChanged();
				
			}
        });     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
	}
	
	 public void emailMenuFlash(MenuItem menu){
	    Toast.makeText(this, "E-Mail", Toast.LENGTH_SHORT).show();
	 }
	 
	 public void removeItemArchive(View v){
	    Toast.makeText(this, "Archived Item Removed!", Toast.LENGTH_SHORT).show();
	    	
	    
	    
	 }
	 
	 public void unarchiveItemArchive(View v){
		 Toast.makeText(this, "Item UnArchived!", Toast.LENGTH_SHORT).show();
		 
	 
	 
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
*/