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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void emailMenuFlash(MenuItem menu){
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
        return super.onOptionsItemSelected(item);
    }
}
