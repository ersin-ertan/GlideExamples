package com.nullcognition.glideexamples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements ItemFragment.OnFragmentInteractionListener {

   @InjectView(R.id.i1)
   ImageView i1;


   @Override
   protected void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);

	  ButterKnife.inject(this); // which will be changed to the verb .bind in later versions

	  String urlOfPic = "http://goo.gl/gEgYUd";

	  Glide.with(this)
		   .load(urlOfPic)
		   .into(i1);

   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu){

	  getMenuInflater().inflate(R.menu.menu_main, menu);
	  return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item){

	  int id = item.getItemId();

	  if(id == R.id.action_settings){
		 return true;
	  }

	  return super.onOptionsItemSelected(item);
   }

   @Override
   public void onFragmentInteraction(String id){

	  Toast.makeText(this, "clicked on id:" + id, Toast.LENGTH_SHORT)
		   .show();

   }
}
