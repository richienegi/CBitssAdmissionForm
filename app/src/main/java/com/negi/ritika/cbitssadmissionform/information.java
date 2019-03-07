package com.negi.ritika.cbitssadmissionform;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.Calendar;

import static com.negi.ritika.cbitssadmissionform.MainActivity.sp;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link information.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link information#newInstance} factory method to
 * create an instance of this fragment.
 */
public class information extends Fragment {
    TextInputEditText mfirst,mmid,mlast,mFfirst,mFlast,mFmid,mMfirst,mMmid,mMlast,dob;
    RadioGroup mgender;
    Button mnext;
    static String fname="",mname="",lname="",ffname="",fmname="",flname="",mfname="",mmname="",mlname="",genderr="",dateofb="";
    String tag="GetImage";
    int mYear,mMonth, mDay;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public information() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment information.
     */
    // TODO: Rename and change types and number of parameters
    public static information newInstance(String param1, String param2) {
        information fragment = new information();
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
        final View view =inflater.inflate(R.layout.fragment_information, container, false);



        dob=(TextInputEditText)view.findViewById(R.id.dateOfBirth);




        mfirst=(TextInputEditText)view.findViewById(R.id.firstname);
        mmid=(TextInputEditText)view.findViewById(R.id.midname);
        mlast=(TextInputEditText)view.findViewById(R.id.lastname);
        mFfirst=(TextInputEditText)view.findViewById(R.id.Ffirstname);
        mFmid=(TextInputEditText)view.findViewById(R.id.Fmidname);
        mFlast=(TextInputEditText)view.findViewById(R.id.Flastname);
        mMfirst=(TextInputEditText)view.findViewById(R.id.Mfirstname);
        mMmid=(TextInputEditText)view.findViewById(R.id.Mmidname);
        mMlast=(TextInputEditText)view.findViewById(R.id.Mlastname);
        mnext=(Button)view.findViewById(R.id.next);

        mgender=(RadioGroup)view.findViewById(R.id.gender);
        //c1=(CardView)findViewById(R.id.card1);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        dob.setText(selectedyear+"-"+(selectedmonth+1)+"-"+selectedday);

                        /*      Your code   to get date and time    */
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //person name
                fname=mfirst.getText().toString();
                mname=mmid.getText().toString();
                lname=mlast.getText().toString();

                //fathers name
                ffname=mFfirst.getText().toString();
                fmname=mFmid.getText().toString();
                flname=mFlast.getText().toString();

                //mothers name
                mfname=mMfirst.getText().toString();
                mmname=mMmid.getText().toString();
                mlname=mMlast.getText().toString();

                dateofb=dob.getText().toString();

                if(TextUtils.isEmpty(fname)) {
                    mfirst.setError("This field is required");
                    mfirst.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(ffname)) {
                    mFfirst.setError("This field is required");
                    mFfirst.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(mfname)) {
                    mMfirst.setError("This field is required");
                    mMfirst.requestFocus();

                    return;
                }
                else if(TextUtils.isEmpty(dob.getText()))
                {
                    dob.setError("This Field is Required");
                    dob.requestFocus();
                    return;
                }
                else if (mgender.getCheckedRadioButtonId()==-1) {
                    //Creating the LayoutInflater instance
                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast,(ViewGroup)view.findViewById(R.id.custom_toast_layout));
                    TextView tv=(TextView)layout.findViewById(R.id.custom_toast_message);
                    tv.setText("Please select your gender");
                    Toast toast = new Toast(getContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
                    Toast.makeText(getContext(), "Please selecte gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    sp.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                    int selectedId = mgender.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton)view. findViewById(selectedId);
                    genderr = radioButton.getText().toString();
                    Log.d("radiodata",genderr);
                    //c1.setVisibility(View.GONE);
                    GetImage g = new GetImage();
                    Bundle b = new Bundle();
                    b.putString("name", fname);
                    g.setArguments(b);
                    getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.ll1, g,tag).commit();
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
