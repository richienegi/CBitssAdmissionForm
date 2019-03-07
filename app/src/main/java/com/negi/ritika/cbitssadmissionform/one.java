package com.negi.ritika.cbitssadmissionform;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadStatusDelegate;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;
import static com.negi.ritika.cbitssadmissionform.Contact_info.otpnumber;
import static com.negi.ritika.cbitssadmissionform.findUs.mfindus;
import static com.negi.ritika.cbitssadmissionform.findUs.selecteditems;
import static com.negi.ritika.cbitssadmissionform.fullinfo.flname;
import static com.negi.ritika.cbitssadmissionform.fullinfo.fname;
import static com.negi.ritika.cbitssadmissionform.fullinfo.malterno;
import static com.negi.ritika.cbitssadmissionform.fullinfo.mcollegeadress;
import static com.negi.ritika.cbitssadmissionform.fullinfo.mcollge;
import static com.negi.ritika.cbitssadmissionform.fullinfo.mcomapny_name;
import static com.negi.ritika.cbitssadmissionform.fullinfo.mdesignation;
import static com.negi.ritika.cbitssadmissionform.fullinfo.mfno;
import static com.negi.ritika.cbitssadmissionform.fullinfo.mmname;
import static com.negi.ritika.cbitssadmissionform.fullinfo.mmno;
import static com.negi.ritika.cbitssadmissionform.fullinfo.mwebsite;
import static com.negi.ritika.cbitssadmissionform.fullinfo.passing;
import static com.negi.ritika.cbitssadmissionform.fullinfo.qualification;
import static com.negi.ritika.cbitssadmissionform.fullinfo.university;


public class one extends Fragment {
    RecyclerView recyclerView;

    String usrotp;

     static String strDate;

    public static final String UPLOAD_URL = "http://fossfoundation.com/AdmissionForm/registartion.php";
    Button mclick;
    ImageView img, imgv;
    Button mdone;
     int j = 0;
    static ArrayList<Bitmap> arr1 = new ArrayList<>();
    ArrayList<String> arr = new ArrayList<>();

    private final int requestCode = 20;
    private OnFragmentInteractionListener mListener;

    public one() {
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
        final View view = inflater.inflate(R.layout.fragment_one, container, false);
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        arr.add("");
        getCurrentDate(view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);



        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mclick = (Button) view.findViewById(R.id.click);
        img = (ImageView) view.findViewById(R.id.image);
        mdone = (Button) view.findViewById(R.id.done);
        TextView tv=(TextView)view.findViewById(R.id.date);
        tv.setText(strDate);
        mdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Otp(view);
                uploadMultipart(view);
            }
        });

        mclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode == requestCode && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            arr1.add(bitmap);
            arr.set(j,getStringImage(bitmap));
            img.setImageBitmap(bitmap);
            mdone.setVisibility(View.VISIBLE);

            if (arr1.size() > 0) {
                my_adapter customAdapter = new my_adapter(getContext(), arr1);
                recyclerView.setAdapter(customAdapter);

                Log.d("myimage", arr.get(0));

            }
            j++;
            img.setImageResource(R.mipmap.ic_launcher);
            Log.d("arraylenght",String.valueOf(arr.get(0)));
            Log.d("arraylenght1",String.valueOf(arr.get(1)));
        }
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

    public String getStringImage(Bitmap bmp) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        } catch (Exception e) {
            Toast.makeText(getContext(), "Please select Image", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /*private void showOfficepin(final View view) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.officepin, viewGroup, false);
        Button b = (Button) dialogView.findViewById(R.id.buttonOk);
        final EditText e = (EditText) dialogView.findViewById(R.id.edit);
        TextView tv = (TextView) dialogView.findViewById(R.id.textv);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e.getText().toString().equals("12345")) {
                    showCourse(view);
                    alertDialog.dismiss();


                }
            }
        });
    }
*/
    /*private void showCustomDialog(final View view) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.officeuse, viewGroup, false);
        Button b = (Button) dialogView.findViewById(R.id.buttonOk);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOfficepin(view);
                alertDialog.dismiss();

            }
        });
    }
*/
    private void Otp(final View view) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.course, viewGroup, false);
        Button b = (Button) dialogView.findViewById(R.id.buttonOk);
        final EditText e = (EditText) dialogView.findViewById(R.id.edit);
        TextView tv = (TextView) dialogView.findViewById(R.id.textv);
        tv.setText("Dear "+fname + " We had send you Otp \n Please Enter Your Otp Here ");

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrotp  = e.getText().toString();
                if (usrotp.equals(otpnumber)) {
                    thanku(view);

                } else {
                    verified(view);
                }
                alertDialog.dismiss();
                /*Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
                Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
*/
            }
        });
    }


    /*synchronized public void Otp(final View view) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Please Enter Your OTP here");

        final EditText input = new EditText(getActivity());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                //Toast.makeText(getApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
            }

        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
*/
    public void verified(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.verify, null);
        builder.setView(alertLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                //Toast.makeText(getApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
            }

        });
        builder.show();
    }

    ProgressDialog d;

    public void uploadMultipart(final View view) {


       /* String path = mpdf + ".pdf";
        Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        if (path == null || path == "") {

            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else*/
        {
            //Uploading code
            try {
                    Log.d("registartiondate",strDate);
                d = new ProgressDialog(getContext());
                d.setMessage("Please Wait");
                d.setCancelable(false);
                d.show();

                String uploadId = UUID.randomUUID().toString();

                String name=fullinfo.fname+" "+fullinfo.mname+" "+fullinfo.lname;
                String fathername=fullinfo.ffname+" "+fullinfo.fmname+" "+fullinfo.flname;
                String gender=fullinfo.genderr;
                String dob=fullinfo.dateofb;
                String mothername=fullinfo.mfname+" "+fullinfo.mmname+" "+fullinfo.mlname;
                //Log.d("mydateofbirth",dob);
                //Log.d("mygender",gender);
                //Creating a multi part request
                new MultipartUploadRequest(getContext(), uploadId, UPLOAD_URL)//Adding file//Adding text parameter to the request
                         .addParameter("name",name)
                        .addParameter("gender",gender)
                        .addParameter("dob",dob)
                        .addParameter("fathername",fathername)
                        .addParameter("mothername",mothername)
                         .addParameter("image",fullinfo.image)
                        .addParameter("mobileno",fullinfo.mmobile)
                        .addParameter("whatsno",fullinfo.mwhatsno)
                        .addParameter("alternatno", malterno)
                        .addParameter("email",fullinfo.memailid)
                        .addParameter("fatherno",mfno)
                        .addParameter("motherno",mmno)
                        .addParameter("qualifi",qualification)
                        .addParameter("university",university)
                        .addParameter("passing",passing)
                        .addParameter("designation",mdesignation)
                        .addParameter("company_name",mcomapny_name)
                        .addParameter("website",mwebsite)
                        .addParameter("college",mcollge)
                        .addParameter("college_add",mcollegeadress)
                        .addParameter("findus",mfindus)
                        .addParameter("course",selecteditems.toString())
                        .addParameter("regisdate",strDate)
                        .addParameter("image1",arr.get(0))
                        .addParameter("image2",arr.get(1))
                        .addParameter("image3",arr.get(2))
                        .addParameter("image4",arr.get(3))
                        .addParameter("image5",arr.get(4))
                        .addParameter("image6",arr.get(5))
                        .addParameter("image7",arr.get(6))
                        .addParameter("image8",arr.get(7))
                        .addParameter("image9",arr.get(8))
                        .addParameter("image10",arr.get(9))

                 .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .setDelegate(new UploadStatusDelegate() {
                            @Override
                            public void onProgress(Context context, UploadInfo uploadInfo) {

                            }

                            @Override
                            public void onError(Context context, UploadInfo uploadInfo, ServerResponse serverResponse, Exception exception) {
                                Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
                                d.dismiss();
                            }

                            @Override
                            public void onCompleted(Context context, UploadInfo uploadInfo, ServerResponse serverResponse) {
                                d.dismiss();
                                thanku(view);
                            }

                            @Override
                            public void onCancelled(Context context, UploadInfo uploadInfo) {
                                d.dismiss();
                            }
                        })
                        .startUpload();


            } catch (Exception exc) {
                Toast.makeText(getContext(), exc.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {


            }
        }

    }



    public void thanku(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.thanku, null);
        TextView name = alertLayout.findViewById(R.id.name);
        name.setText(fname);

        builder.setView(alertLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Toast.makeText(getApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
            }

        });
        builder.show();
    }

    public void getCurrentDate(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
         strDate =mdformat.format(calendar.getTime());

    }
}
