package com.example.androidplayground;

import com.example.intenttest.ItemListActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

public class MainActivity extends FragmentActivity {

	GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(savedInstanceState == null) {
			TestFragment1 fragment1 = new TestFragment1();
	
			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_container, fragment1).commit();
		}
	}

	public void moveToMasterFlow(View view) {
		Intent i = new Intent(this, ItemListActivity.class);
		i.putExtra("intentTest", "Hallo Welt!");
		startActivity(i);
	}
	
	public void moveToList(View view) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		ListFragmentTest listFragment = new ListFragmentTest();
		transaction.replace(R.id.fragment_container, listFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void moveToMap(View view) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		MapFragmentTest fragment = new MapFragmentTest();
		googleMap = fragment.getMap();
//		if (googleMap != null) {
			try{
				transaction.replace(R.id.fragment_container, fragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}catch(Exception e) {
				e.printStackTrace();
			}
//		} else {
//			System.err.println("Map konnte nicht Initalisiert werden");
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
