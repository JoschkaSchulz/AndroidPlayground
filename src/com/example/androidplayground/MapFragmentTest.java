package com.example.androidplayground;

import java.util.ArrayList;
import java.util.LinkedList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MapFragmentTest extends SupportMapFragment {

	private LatLng position = new LatLng(53.250138, 10.407104);	//Lüneburg;
	private Polygon poly;
	private LinkedList<LatLng> points = new LinkedList<LatLng>();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		
		initMap();
		
		// after you've done all your manipulation, return your layout to be
		// shown
		return v;
	}
	
	private void initMap(){
	    UiSettings settings = getMap().getUiSettings();
	    settings.setAllGesturesEnabled(true);
	    settings.setMyLocationButtonEnabled(true);
	    settings.setCompassEnabled(true);

	    getMap().setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng point) {
				points.add(point);
				poly.setPoints(points);
				Marker m = getMap().addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(android.R.drawable.radiobutton_on_background)));
				m.setDraggable(true);
			}
		});
	    
	    CameraPosition cameraPosition = CameraPosition.builder()
                .target(position)
                .zoom(13)
                .build();

	    getMap().setMapType(GoogleMap.MAP_TYPE_TERRAIN);
	    
	    
	    getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(position,16));
	    getMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	    
	    //Draw on map
	    
	    poly = getMap().addPolygon(new PolygonOptions().add(new LatLng(0, 0)).fillColor(Color.argb(64, 255, 0, 0)));
	}
}
