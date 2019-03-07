package com.negi.ritika.cbitssadmissionform;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;


public class Contact_info extends Fragment {
    Button mnext;

    static String otpnumber;
    TextInputEditText mobileno,whatsno,fno,mno,alterno,emailid;
   static String mmobile="",mwhatsno="",mfno="",mmno="",malterno="",memailid="";



    private OnFragmentInteractionListener mListener;

    public Contact_info() {
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
        View view=inflater.inflate(R.layout.fragment_contact_info, container, false);
        mobileno=(TextInputEditText)view.findViewById(R.id.phoneNo);
        whatsno=(TextInputEditText)view.findViewById(R.id.Whatsapp);
        fno=(TextInputEditText)view.findViewById(R.id.FPhoneno);
        mno=(TextInputEditText)view.findViewById(R.id.MPhoneNo);
        alterno=(TextInputEditText)view.findViewById(R.id.alternate);
        emailid=(TextInputEditText)view.findViewById(R.id.email);
        mnext=(Button)view.findViewById(R.id.next);
        mobileno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
whatsno.setText(mobileno.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mmobile=mobileno.getText().toString();
                mwhatsno=whatsno.getText().toString();
                mfno=fno.getText().toString();
                mmno=mno.getText().toString();
                malterno=alterno.getText().toString();
                memailid=emailid.getText().toString();
                if(TextUtils.isEmpty(mmobile)) {
                    mobileno.setError("This field is required");
                    mobileno.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(mwhatsno)) {
                    whatsno.setError("This field is required");
                    whatsno.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(memailid)) {
                    emailid.setError("This field is required");
                    emailid.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(malterno))
                {
                    alterno.setError("This field is required");
                    alterno.requestFocus();
                    return;
                }
                else {
                                       otpnumber = getRandomNumberString();

                    String message = "Your OTP is " + otpnumber;
                  //  sendOTP(mmobile, message);


                    Qualification q = new Qualification();
                    getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.ll1, q).addToBackStack(null).commit();
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




    //hiiting OTP
    private void sendOTP(String number, String message) {
        Toast.makeText(getContext(), "send otp hits", Toast.LENGTH_SHORT).show();

        String url = "http://203.129.225.69/API/WebSMS/Http/v1.0a/index.php?username=cbitss&password=123456&sender=CBitss&to=91" + number + "&message=" + message + "&reqid=1&format={json|text}&route_id=7";

        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainnActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue r = Volley.newRequestQueue(getContext());
        r.add(sr);
    }

    //generating randome Number
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
