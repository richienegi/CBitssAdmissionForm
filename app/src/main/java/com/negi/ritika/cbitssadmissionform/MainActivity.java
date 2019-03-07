package com.negi.ritika.cbitssadmissionform;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements information.OnFragmentInteractionListener, GetImage.OnFragmentInteractionListener,
        Contact_info.OnFragmentInteractionListener,CurrentDetails.OnFragmentInteractionListener,
        Qualification.OnFragmentInteractionListener
,findUs.OnFragmentInteractionListener,one.OnFragmentInteractionListener{
    static StateProgressBar sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
sp=(StateProgressBar)findViewById(R.id.your_state_progress_bar_id);

information i=new information();

        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).replace(R.id.ll1,i).addToBackStack(null).commit();



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

          }
}
