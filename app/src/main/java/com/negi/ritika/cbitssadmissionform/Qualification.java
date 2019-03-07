package com.negi.ritika.cbitssadmissionform;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.Calendar;

import static com.negi.ritika.cbitssadmissionform.MainActivity.sp;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Qualification.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Qualification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Qualification extends Fragment {
    final Calendar myCalendar = Calendar.getInstance();
    Button btn;
    TextInputEditText quali,unver,pass;
    static String qualification="";
    static String university="";
    static String passing="";
    int mYear,mMonth, mDay;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Qualification() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Qualification.
     */
    // TODO: Rename and change types and number of parameters
    public static Qualification newInstance(String param1, String param2) {
        Qualification fragment = new Qualification();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_qualification, container, false);
        sp.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
        quali=(TextInputEditText)view.findViewById(R.id.qualifi);
        unver=(TextInputEditText)view.findViewById(R.id.univer);
        pass=(TextInputEditText)view.findViewById(R.id.passyear);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                 mYear = mcurrentDate.get(Calendar.YEAR);
                 mMonth=mcurrentDate.get(Calendar.MONTH);
                 mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        pass.setText(selectedyear+"/"+selectedmonth+1+"/"+selectedday);

                        /*      Your code   to get date and time    */
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });




        btn=(Button)view.findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qualification = quali.getText().toString();
                university = unver.getText().toString();
                passing = pass.getText().toString();
                if (TextUtils.isEmpty(qualification)) {
                    quali.setError("This field is required");
                    quali.requestFocus();
                    return;
                } else {
                    CurrentDetails f = new CurrentDetails();
                    getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.ll1, f).addToBackStack(null).commit();
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
