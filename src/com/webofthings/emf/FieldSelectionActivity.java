package com.webofthings.emf;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.webofthings.emf.utils.Params;

public class FieldSelectionActivity extends ListActivity {
	String[] fields;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.field_selection);
		fields = getResources().getStringArray(R.array.frequencies_array);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fields));
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		Bundle params = new Bundle();
		switch (position) {
		case 0:
			params.putString("frequency", Params.LF_COMMAND);
			break;
		case 1:
			params.putString("frequency", Params.HF_COMMAND);
			break;
		default:
			params.putString("frequency", Params.LF_COMMAND);
		}
		Intent intent = new Intent(this, DevicePickerActivity.class);
		intent.putExtras(params);
		startActivity(intent);
	}
}
