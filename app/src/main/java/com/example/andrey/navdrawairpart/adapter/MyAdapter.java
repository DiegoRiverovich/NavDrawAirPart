package com.example.andrey.navdrawairpart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrey.navdrawairpart.R;

/**
 * Created by Andrey on 08.03.2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private Context context;
    private int images[];
    private String names[];

    public MyAdapter(Context context, int[] images, String[] names) {
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.productslist, null);
        MyHolder myHolder = new MyHolder(layout);

        //int height = parent.getHeight() * 2;
        //layout.setMinimumHeight(height);

        return myHolder;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.img.setTag(position);

        //ImageView imageView = view.findViewById(R.id.product_image);
        //imageView.setTag();

        holder.img.setImageResource(images[position]);
        holder.txt.setText(names[position]);


        final View itemView = holder.itemView;
        if (position == 18) {
            int width = getDensityName(context);
            itemView.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.WRAP_CONTENT));
            holder.img.getLayoutParams().width = width;
            holder.img.requestLayout();
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public static class MyHolder extends  RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;

        public MyHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.product_image);
            txt = itemView.findViewById(R.id.product_name);

        }
    }

    private static int getDensityName(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        if (density >= 4.0) {  //xxxhdpi
            return 1200;
        }
        if (density >= 3.0) {  //xxhdpi
            return 900;
        }
        if (density >= 2.0) {  //xhdpi
            return 600;
        }
        if (density >= 1.5) {  //hdpi
            return 450;
        }
        if (density >= 1.0) {  //mdpi
            return 300;
        }
        return 200;  //ldpi

    }

}
