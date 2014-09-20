package ca.ualberta.cs.deborsi_notes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ArchiveActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);
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
