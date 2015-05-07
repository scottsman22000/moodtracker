package com.example.mike.moodtracker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mahoney on 5/6/2015.
 */
public class TriggersList extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<String> moodList;
    public String[] listOfMoods;

    public ArrayAdapter arrayAdapter;
    public ListView lv;
    public View layoutLIst;
    public Context ctx;
    public TextView moodToBeDisplayed;
    public EditText moodText;
    public EditText intesityText;
    public String annotationMessage;
    public EditText annotationBox;


    public String pickedMood = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchTriggerList.
     */
    // TODO: Rename and change types and number of parameters
    public static TriggersList newInstance(String param1, String param2) {
        TriggersList fragment = new TriggersList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TriggersList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ctx = getActivity().getApplicationContext();
        //zach this is where you can change the DBAccess call to get the search Triggers instead
        //of the myMoods
        DBaccessor d = new DBaccessor(ctx);
        moodList = d.getAllTriggers();//this needs to change
        listOfMoods = moodList.toArray(new String[moodList.size()]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_triggers_list, container, false);
        layoutLIst = view.findViewById(R.id.layoutForList);
        lv = (ListView) layoutLIst.findViewById(R.id.listViewInLayout);
        arrayAdapter = new ArrayAdapter(layoutLIst.getContext(), android.R.layout.simple_list_item_1, listOfMoods);
        lv.setAdapter(arrayAdapter);
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new ListClickHandler());
        //moodToBeDisplayed = (TextView)view.findViewById(R.id.textMoodPicked);
        //annotationBox = (EditText)view.findViewById(R.id.annotationText);
        //Button button = (Button) view.findViewById(R.id.AnnotationButton);
        //button.setOnClickListener(new View.OnClickListener()
        //{
        //  @Override
        // public void onClick(View v)
        //{
        //  annotationMessage = annotationBox.getText().toString();
        // Log.i("", "thisis the annotation message that is bieng saved: " + annotationMessage);
        //annotationBox.clearFocus();

        //}
        //});

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arrayAdapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            //TextView listText = (TextView) view.findViewById(R.id.listViewInLayout).findViewById(android.R.id);
            //pickedMood = listText.getText().toString();
            //int itemPosition     = position;

            // ListView Clicked item value
            pickedMood = (String) lv.getItemAtPosition(position);
            //moodText = (EditText) findViewById(R.id.textPickedMood);
            Log.i("", "this is the pickedtrigger the firsst time: " + pickedMood);
            //moodToBeDisplayed = new EditText(ctx);
            // moodToBeDisplayed = (EditText) findViewById(R.id.textMoodPicked);


            //moodToBeDisplayed.setText(pickedMood);

            Log.i("", "this is the pickedtrigger: " + pickedMood);


        }

    }


}
