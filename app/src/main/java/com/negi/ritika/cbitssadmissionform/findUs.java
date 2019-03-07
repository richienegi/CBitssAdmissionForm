package com.negi.ritika.cbitssadmissionform;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadStatusDelegate;

import java.util.ArrayList;
import java.util.UUID;

import static com.negi.ritika.cbitssadmissionform.Contact_info.otpnumber;
import static com.negi.ritika.cbitssadmissionform.MainActivity.sp;
import static com.negi.ritika.cbitssadmissionform.fullinfo.fname;


public class findUs extends Fragment {
    static String items="";
    String []account_course={"QuickBooks","Tally","HR Management","Finance",
            "Basic Computer","Excel","Advance Excel","Payroll","Myob","other"};


    String []Marketing_Courses={"Digital Marketing","SEO","PPC","Adsense","other"};
    String[]IT_Courses={"Android","Java","Advance Java","PHP (Web Development)","Web Designing","Photoshop",
            "Corel Draw","Graphic Designing","Bootstrap","Animation Courses","Video editing course",
            "Linux","AWS","CCNA","CCNP","C Language","C++",".Net","other"};
    String Soft_Skill[]={"courses","Spoken English","Personality Development","IELTS","PTE","other"};



   static ArrayList<String> selecteditems=new ArrayList<>();
    ListView l1,l2,l3,l4;
    Button submit;
    CheckBox cb1,cb2,cb3,cb4;
    static String mfindus;

    private OnFragmentInteractionListener mListener;

    public findUs() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_find_us, container, false);
        sp.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE);
        submit=(Button)view.findViewById(R.id.next);
        cb1=(CheckBox)view.findViewById(R.id.facebook);
        cb2=(CheckBox)view.findViewById(R.id.google);
        cb3=(CheckBox)view.findViewById(R.id.reference);
        cb4=(CheckBox)view.findViewById(R.id.Other);
//List one for Accounts
        l1=(ListView)view.findViewById(R.id.chechablelist1);
        l1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        ArrayAdapter arr=new ArrayAdapter(getContext(),R.layout.rowlayout,account_course);
        l1.setAdapter(arr);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem=((TextView)view).getText().toString();
                if(selecteditems.contains(selectedItem))
                {
                    selecteditems.remove(selectedItem);
                }
                else
                {
                    selecteditems.add(selectedItem);
                }
            }
        });



//list for digital cource


        l2=(ListView)view.findViewById(R.id.chechablelist2);
        l2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        ArrayAdapter arr1=new ArrayAdapter(getContext(),R.layout.rowlayout,Marketing_Courses);
        l2.setAdapter(arr1);
        l2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem=((TextView)view).getText().toString();
                if(selecteditems.contains(selectedItem))
                {
                    selecteditems.remove(selectedItem);
                }
                else
                {
                    selecteditems.add(selectedItem);
                }
            }
        });





//list for it courses


        l3=(ListView)view.findViewById(R.id.chechablelist3);
        l3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        ArrayAdapter arr3=new ArrayAdapter(getContext(),R.layout.rowlayout,IT_Courses);
        l3.setAdapter(arr3);
        l3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem=((TextView)view).getText().toString();
                if(selecteditems.contains(selectedItem))
                {
                    selecteditems.remove(selectedItem);
                }
                else
                {
                    selecteditems.add(selectedItem);
                }
            }
        });



//list for soft skills
        l4=(ListView)view.findViewById(R.id.chechablelist4);
        l4.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        ArrayAdapter arr4=new ArrayAdapter(getContext(),R.layout.rowlayout,Soft_Skill);
        l4.setAdapter(arr4);
        l4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem=((TextView)view).getText().toString();
                if(selecteditems.contains(selectedItem))
                {
                    selecteditems.remove(selectedItem);
                }
                else
                {
                    selecteditems.add(selectedItem);
                }
            }
        });






        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder("");
                if (!cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked()) {
                    Toast.makeText(getContext(), "please select one", Toast.LENGTH_SHORT).show();
                } else {
                    if (cb1.isChecked()) {
                        String s1 = cb1.getText().toString();
                        sb.append(s1);
                    }

                    if (cb2.isChecked()) {
                        String s2 = cb2.getText().toString();
                        sb.append(" " + s2);

                    }
                    if (cb3.isChecked()) {
                        String s3 = cb3.getText().toString();
                        sb.append(" " + s3);
                    }
                    if (cb4.isChecked()) {
                        String s4 = cb4.getText().toString();
                        sb.append(" " + s4);
                    }

showSelectecdItems(view);
                    mfindus = sb.toString();
                    one o=new one();
                    getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.ll1,o).addToBackStack(null).commit();
                   // sp.setAllStatesCompleted(true);

                }
            }
        });
        return view;
    }

    public void showSelectecdItems(View view)
    {

        for(String item:selecteditems)
        {
            items+="-"+item+"\n";
        }
        Toast.makeText(getContext(), "slected items"+items, Toast.LENGTH_SHORT).show();
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








