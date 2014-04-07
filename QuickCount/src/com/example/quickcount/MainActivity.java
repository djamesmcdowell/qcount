package com.example.quickcount;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ViewFlipper;

public class MainActivity extends ActionBarActivity {

	Button group, individual;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		group = (Button) findViewById(R.id.main_group);
		individual = (Button) findViewById(R.id.main_individual);
		
//		Spinner spinner = new Spinner(this);
//		ArrayAdapter<?> spinnerArrayAdapter = new ArrayAdapter<Object>(this,
//	        android.R.layout.simple_spinner_dropdown_item,
//	            new String[] { "Apple", "Peach", "Banana" });
//	    spinner.setAdapter(spinnerArrayAdapter);
//
//	    
//	    //Add spinner to this activity's view (a LinearLayout)
//	    View mainLayout = (View) findViewById(R.layout.fragment_main);
//	    
//	    
//	    ((ViewGroup) mainLayout).addView(spinner, new LinearLayout.LayoutParams(
//	        LinearLayout.LayoutParams.WRAP_CONTENT,
//	        LinearLayout.LayoutParams.WRAP_CONTENT));
//	    
//	    ListView listView = new ListView(this);
//		@SuppressWarnings("unchecked")
//		ArrayAdapter<?> listViewArrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, new String[] {
//	            "Apple", "Peach","Banane" });
//	    listView.setAdapter(listViewArrayAdapter);
//	    listView.setFocusableInTouchMode(true);
//	    listView.setOnFocusChangeListener(
//	        new View.OnFocusChangeListener() {
//	      @Override
//	      public void onFocusChange(View arg0, boolean arg1) {
//	        Log.i("SampleApp", "onFocusChanged() - view=" + arg0);
//	      }
//	    });
//	    listView.setOnItemClickListener(
//	        new AdapterView.OnItemClickListener() {
//	      @Override
//	      public void onItemClick(@SuppressWarnings("rawtypes") AdapterView adapterView, View view,
//	          int arg2, long arg3) {
//	        int selectedPosition = adapterView.getSelectedItemPosition();
//	        Log.i("SampleApp", "Click on position"+selectedPosition);
//	      }
//	    });
//	    listView
//	        .setOnCreateContextMenuListener(
//	            new View.OnCreateContextMenuListener() {
//
//	          public void onCreateContextMenu(ContextMenu menu, View view,
//	              ContextMenu.ContextMenuInfo menuInfo) {
//	            AdapterContextMenuInfo mi =
//	                (AdapterContextMenuInfo) menuInfo;
//	            menu.add(0, 0, 0, "Context-Menu-Entry");
//	          }
//
//	    });	
		
		individual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),IndividualActivity.class);
				startActivity(intent);
			}
		});
		
		group.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),GroupActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
