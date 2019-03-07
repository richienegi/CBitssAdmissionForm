package com.negi.ritika.cbitssadmissionform;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ravi Sharma on 21-Dec-17.
 */


public class my_adapter extends RecyclerView.Adapter<my_adapter.MyViewHolder>
{
    private ArrayList<Bitmap> objList;
    private LayoutInflater inflater;
    Context context;

    public my_adapter(Context context, ArrayList<Bitmap>arr)
    {
        this.context=context;
        inflater = LayoutInflater.from(context);
        objList=arr;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.holder,parent,false);//xml file inflate, viewGroup parent,for boolean
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.im.setImageBitmap(objList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
showCourse(view,position);
                // display a toast with person name on item click
              //  Toast.makeText(context,"pos: "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return objList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView im;

        public MyViewHolder(View itemView) {
            super(itemView);
            im = (CircleImageView) itemView.findViewById(R.id.image);

        }
    }
    private void showCourse(View view, final int pos) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(context).inflate(R.layout.viewimage, viewGroup, false);
        Button b=(Button)dialogView.findViewById(R.id.buttonOk);
        Button b1=(Button)dialogView.findViewById(R.id.btncancel);
        ImageView img=(ImageView)dialogView.findViewById(R.id.imageview);

        img.setImageBitmap(objList.get(pos));
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

alertDialog.dismiss();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "pos"+pos, Toast.LENGTH_SHORT).show();
                one.arr1.remove(pos);
                notifyDataSetChanged();
                new my_adapter(context,one.arr1);
                alertDialog.dismiss();

                /* objList.remove(pos);

                my_adapter a = new my_adapter(context, objList);
          */  }
        });
    }

}