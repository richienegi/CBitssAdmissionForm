package com.negi.ritika.cbitssadmissionform;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kofigyan.stateprogressbar.StateProgressBar;

import static com.negi.ritika.cbitssadmissionform.MainActivity.sp;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentDetails extends Fragment {
    Button next;
    TextInputEditText designation,companyname,webiste,college,collegeaddress;
    RadioGroup status;
    RadioButton rd;
    static String mdesignation="",mcomapny_name="",mwebsite="",mcollge="",mcollegeadress="",mstatus="";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CurrentDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentDetails newInstance(String param1, String param2) {
        CurrentDetails fragment = new CurrentDetails();
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
        View view=inflater.inflate(R.layout.fragment_current_details, container, false);
        sp.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
        next=(Button)view.findViewById(R.id.next);

designation=(TextInputEditText)view.findViewById(R.id.ddesignation);
companyname=(TextInputEditText)view.findViewById(R.id.Company_name);
webiste=(TextInputEditText)view.findViewById(R.id.wwebsite);
college=(TextInputEditText)view.findViewById(R.id.college_name);
collegeaddress=(TextInputEditText)view.findViewById(R.id.college_address);
status=(RadioGroup)view.findViewById(R.id.clgstate);
        if(status.getCheckedRadioButtonId()!=-1)
        {
            int selectedId = status.getCheckedRadioButtonId();
            rd= (RadioButton)view.findViewById(selectedId);
            mstatus =rd.getText().toString();
        }

        final findUs f=new findUs();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdesignation=designation.getText().toString();
                mcomapny_name=companyname.getText().toString();
                mwebsite=webiste.getText().toString();
                mcollge=college.getText().toString();
                mcollegeadress=collegeaddress.getText().toString();




                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter,R.anim.exit).replace(R.id.ll1,f).addToBackStack(null).commit();
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
