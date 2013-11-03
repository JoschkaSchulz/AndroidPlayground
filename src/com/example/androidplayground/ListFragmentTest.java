package com.example.androidplayground;

import java.util.ArrayList;
import java.util.LinkedList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ListFragmentTest extends Fragment {

	private ListView lv;
	private ArrayAdapter<String> adapter;
	LinkedList<String> listItems = new LinkedList<String>();
	private int counter = 1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.list_fragment,
                container, false);
		
		listItems.add("Objekt 0");
		
		adapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, listItems);
		
		lv = ((ListView) mLinearLayout.findViewById(R.id.listView1));
		lv.setAdapter(adapter);
		
		Button addButton = (Button) mLinearLayout.findViewById(R.id.button_add);
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addElement();
			}
		});
		
		Button removeButton = (Button) mLinearLayout.findViewById(R.id.button_remove);
		removeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				removeElement();
			}
		});
		
		return mLinearLayout;
	}

	public void addElement() {
		listItems.add("Objekt " + (counter++));
		adapter.notifyDataSetChanged();
	}
	
	public void removeElement() {
		if(listItems.size() > 0) {
			counter--;
			listItems.removeLast();
			adapter.notifyDataSetChanged();
		}
	}
	
}
