package com.example.appcamping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcamping.R;
import com.example.appcamping.models.Upload;
import com.example.appcamping.models.Upload1;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter1 extends RecyclerView.Adapter<com.example.appcamping.adapters.ImageAdapter1.ImageViewHolder> {

        private Context mContext;
        private List<Upload1> mUploads1;

        public ImageAdapter1(Context context, List<Upload1> uploads1){
            mContext = context;
            mUploads1 = uploads1;
        }

        @NonNull
        @Override
        public com.example.appcamping.adapters.ImageAdapter1.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
            return new com.example.appcamping.adapters.ImageAdapter1.ImageViewHolder(v);

        }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload1 uploadCurrent = mUploads1.get(position);
        holder.textViewName.setText(uploadCurrent.getmName());

        Picasso.get().load(uploadCurrent.getmImageUrl()).fit().centerCrop().into(holder.imageView);
    }

        @Override
        public int getItemCount() {
            return mUploads1.size();
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder{
            public TextView textViewName;
            public ImageView imageView;


            public ImageViewHolder(View itemView){
                super(itemView);

                textViewName = itemView.findViewById(R.id.text_view_name);
                imageView = itemView.findViewById(R.id.image_view_upload);
            }
        }

    }

