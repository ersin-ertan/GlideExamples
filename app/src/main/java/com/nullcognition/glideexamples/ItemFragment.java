package com.nullcognition.glideexamples;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.nullcognition.glideexamples.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */

class CustomArrayAdapter extends ArrayAdapter<DummyContent.DummyItem> {

   Context                      context;
   List<DummyContent.DummyItem> objectsList;
   int layoutOfImageView   = 0;
   int imageViewResourceId = 0;

   public CustomArrayAdapter(Context context, int layoutOfImageView, int imageViewResourceId, List<DummyContent.DummyItem> objects){

	  super(context, layoutOfImageView, imageViewResourceId, objects);
	  this.context = context;
	  this.objectsList = objects;
	  this.layoutOfImageView = layoutOfImageView;
   }

   @Override
   public View getView(int position, View recycled, ViewGroup container){ // int position, View convertView, ViewGroup parent) // original

	  final ImageView myImageView;
	  if(recycled == null){
		 LayoutInflater layoutInflater = LayoutInflater.from(this.context);
		 myImageView = (ImageView)layoutInflater.inflate(layoutOfImageView, container, false);
	  }
	  else{
		 myImageView = (ImageView)recycled;
	  }

	  String url = objectsList.get(position).content;

	  Glide.with(context)
		   .load(url)
		   .centerCrop()
		   .placeholder(android.R.drawable.btn_star)
		   .crossFade()
		   .into(myImageView);

	  return myImageView;
   }
}

public class ItemFragment extends ListFragment {

   @Override
   public void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);

	  if(getArguments() != null){
		 mParam1 = getArguments().getString(ARG_PARAM1);
		 mParam2 = getArguments().getString(ARG_PARAM2);
	  }

	  // the layout, and the id of the imageview in the layout, the list of urls housed in the map
	  setListAdapter(new CustomArrayAdapter(getActivity(), R.layout.simple_image_view, R.id.simple_image_view, DummyContent.ITEMS));
   }


   // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   private static final String ARG_PARAM1 = "param1";
   private static final String ARG_PARAM2 = "param2";

   private String mParam1;
   private String mParam2;

   private OnFragmentInteractionListener mListener;

   public static ItemFragment newInstance(String param1, String param2){

	  ItemFragment fragment = new ItemFragment();
	  Bundle args = new Bundle();
	  args.putString(ARG_PARAM1, param1);
	  args.putString(ARG_PARAM2, param2);
	  fragment.setArguments(args);
	  return fragment;
   }

   /**
	* Mandatory empty constructor for the fragment manager to instantiate the
	* fragment (e.g. upon screen orientation changes).
	*/
   public ItemFragment(){ }

   @Override
   public void onAttach(Activity activity){

	  super.onAttach(activity);
	  try{
		 mListener = (OnFragmentInteractionListener)activity;
	  }
	  catch(ClassCastException e){
		 throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
	  }
   }

   @Override
   public void onDetach(){

	  super.onDetach();
	  mListener = null;
   }

   @Override
   public void onListItemClick(ListView l, View v, int position, long id){

	  super.onListItemClick(l, v, position, id);

	  if(null != mListener){
		 // Notify the active callbacks interface (the activity, if the
		 // fragment is attached to one) that an item has been selected.
		 mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
	  }
   }

   public interface OnFragmentInteractionListener {

	  public void onFragmentInteraction(String id);
   }

}
