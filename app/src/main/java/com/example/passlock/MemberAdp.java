package com.example.passlock;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberAdp extends RecyclerView.Adapter<MemberAdp.ViewHolder> {

    ArrayList<ChildItem> childarrayList;
    private boolean[] isPasswordVisible = new boolean[1000];
    Context ctx;
    String socialMedia;

    public MemberAdp(ArrayList<ChildItem> childarrayList, String socialMedia, Context mcontext) {
        this.childarrayList = childarrayList;
        this.socialMedia = socialMedia;
        this.ctx = mcontext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChildItem childItem = childarrayList.get(position);

        holder.userid.setText(childItem.userid);
        holder.password.setText(childItem.password);
        holder.eye.setImageResource(childItem.imageID);
        holder.del.setImageResource(childItem.imageID2);
        holder.persondef.setImageResource(childItem.imageID3);
        holder.keydef.setImageResource(childItem.imageID4);

        if (isPasswordVisible[position]) {
            holder.userid.setTextIsSelectable(true);
            holder.password.setTransformationMethod(null);
            holder.eye.setImageResource(R.drawable.ic_baseline_close_24);
        } else {
            holder.userid.setTextIsSelectable(true);
            holder.password.setTransformationMethod(new PasswordTransformationMethod());
            holder.eye.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
        }
    }

    @Override
    public int getItemCount() {
        return childarrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userid, password;
        CircleImageView persondef, keydef;
        ImageView del, eye;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userid = itemView.findViewById(R.id.userid);
            password = itemView.findViewById(R.id.passsword);
            persondef = itemView.findViewById(R.id.persondef);
            keydef = itemView.findViewById(R.id.keydef);
            del = itemView.findViewById(R.id.del);
            eye = itemView.findViewById(R.id.eye);

            eye.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        // Toggle the visibility state for this item
                        isPasswordVisible[adapterPosition] = !isPasswordVisible[adapterPosition];
                        // Notify the adapter of the data change for this item
                        notifyDataSetChanged();
                    }
                }
            });

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        showDeleteConfirmationDialog(getAdapterPosition(),userid.getText().toString());
                    }
                }
            });

        }
    }
    private void showDeleteConfirmationDialog(final int position,String userid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to delete?\n\n    "+userid+"\n    **********");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked Yes, so remove the item
                removeItem(position);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked No, do nothing
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < childarrayList.size()) {
            ChildItem removedItem = childarrayList.remove(position);
            notifyItemRemoved(position);

            // Remove the data from SharedPreferences
            SharedPreferences sharedPreferences = ctx.getSharedPreferences("Passwords", Context.MODE_PRIVATE);
            String socialMedia = this.socialMedia;
            String loginId = removedItem.userid;
            String password = removedItem.password;

            // Remove login ID and password from SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String loginIdKey = socialMedia + "uids";
            String passwordKey = socialMedia + "pass";
            ArrayList<String> loginIdList = getStoredDataAsList(sharedPreferences, loginIdKey);
            ArrayList<String> passwordList = getStoredDataAsList(sharedPreferences, passwordKey);

            loginIdList.remove(loginId);
            passwordList.remove(password);

            // Save the updated lists back to SharedPreferences
            editor.putString(loginIdKey, convertListToJson(loginIdList));
            editor.putString(passwordKey, convertListToJson(passwordList));
            editor.apply();
        }
    }
    private ArrayList<String> getStoredDataAsList(SharedPreferences sharedPreferences, String key) {
        String jsonData = sharedPreferences.getString(key, "[]");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(jsonData, type);
    }
    private String convertListToJson(ArrayList<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }


}