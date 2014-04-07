package com.example.quickcount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.example.quickcount.database.CountDBAdapter;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class IndividualActivity extends ActionBarActivity implements
		AddIndividual.AddIndividualListener , OnItemSelectedListener{

	List<String> names = new ArrayList<String>();
	CountDBAdapter DB;
	ArrayAdapter<String> adapter;
	SimpleAdapter adapterList;
	Spinner spinner;
	Button addButton, subButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual);
		
		spinner = (Spinner) findViewById(R.id.individual_spinner);
		spinner.setOnItemSelectedListener(this);	
		
		CountDBAdapter countdb = new CountDBAdapter(this);
		//EditText individualtext = (EditText) findViewById(R.id.popup_text_add_individuals);
		
		List<HashMap<String, String>> nameList = countdb.GetAllMain();
		List<String> nameListDropdown = countdb.GetAlllist();
		
		adapter = new ArrayAdapter<String>(
				IndividualActivity.this, android.R.layout.simple_spinner_item,
				nameListDropdown);
		spinner.setAdapter(adapter);
		
		adapterList = new SimpleAdapter(IndividualActivity.this, nameList, R.layout.row, new String[] { "name", "count" }, new int[] {
                R.id.first, R.id.third }); 
		
		ListView listView = (ListView) findViewById(R.id.individual_listview);
		listView.setAdapter(adapterList);
		
		adapterList.notifyDataSetChanged();
	}

	public void AddName(String name) {
		ArrayList<String> names = new ArrayList<String>();

		names.add(name);

		Collections.sort(names);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				IndividualActivity.this, android.R.layout.simple_spinner_item,
				names);
		Spinner spinner = (Spinner) findViewById(R.id.individual_spinner);
		spinner.setAdapter(adapter);
		
		adapterList.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.individual, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.sub_minus:
		{
			DeleteIndividual();
			return true;
		}
		case R.id.sub_add:
		{
			AddIndividual();
			adapterList.notifyDataSetChanged();
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void AddIndividual() {
		DialogFragment dialog = new AddIndividual();
		dialog.show(getFragmentManager(), "Add Individual");
	}
	
	private void DeleteIndividual() {
//		DialogFragment dialog = new AddIndividual();
//		dialog.show(getFragmentManager(), "Add Individual");
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
			View rootView = inflater.inflate(R.layout.fragment_individual,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {

		CountDBAdapter countdb = new CountDBAdapter(this);
		EditText individualtext = (EditText) dialog.getDialog().findViewById(
				R.id.popup_text_add_individuals);
		String name = individualtext.getText().toString();

		countdb.insertEntry(name);
		
		adapter.clear();
		List<String> nameListDropdown = countdb.GetAlllist();
		
		adapter = new ArrayAdapter<String>(
				IndividualActivity.this, android.R.layout.simple_spinner_item,
				nameListDropdown);
		spinner.setAdapter(adapter);
		
		adapterList.notifyDataSetChanged();
		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		
		
	}

	@Override
	public void onItemSelected(final AdapterView<?> parent, View view, final int position,
			long id) {	
		final CountDBAdapter countdb = new CountDBAdapter(this);
		
		addButton = (Button) findViewById(R.id.individual_button_add);
		subButton = (Button) findViewById(R.id.individual_button_subtract);
		
		subButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = (String) parent.getItemAtPosition(position);
				
				countdb.DeleteOne(name);
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});
		
		
		addButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				String name = (String) parent.getItemAtPosition(position);
				
				countdb.AddOne(name);
				
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
			
		});
	}	  
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}

}
