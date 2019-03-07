package com.negi.ritika.cbitssadmissionform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.negi.ritika.cbitssadmissionform.MainActivity.sp;


public class GetImage extends Fragment {
    TextView mname;
    CircleImageView mimage;
    Button mbrowse,mnext;
    static String mimagee;
    Bitmap bmp;
    private static final int CAPTURE_PICCODE = 989;

    private OnFragmentInteractionListener mListener;

    public GetImage() {
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
        View view=inflater.inflate(R.layout.fragment_get_image, container, false);

        Bundle b=getArguments();
        String str=b.getString("name");
        mname=(TextView)view.findViewById(R.id.name);
        mname.setText("* Dear "+str+" Please upload your image");
        mimage=(CircleImageView)view.findViewById(R.id.circleimage);
        mbrowse=(Button)view.findViewById(R.id.browse);
        mnext=(Button)view.findViewById(R.id.next);
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getStringImage(bmp)==null) {

                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast,(ViewGroup)v.findViewById(R.id.custom_toast_layout));
                    TextView tv=(TextView)layout.findViewById(R.id.custom_toast_message);
                    tv.setText("Please Click Your image");
                    Toast toast = new Toast(getContext());
                    toast.setDuration(Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
                     } else {
                    sp.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);

                    mimagee = getStringImage(bmp);
                    Contact_info ci = new Contact_info();
                    getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.ll1, ci).addToBackStack(null).commit();



                }
            }
        });
        mbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAPTURE_PICCODE );

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_PICCODE) {
            if (resultCode == Activity.RESULT_OK) {

                 bmp = (Bitmap) data.getExtras().get("data");
                /*ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);
*/
                mimage.setImageBitmap(bmp);

            }
        }
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




}
