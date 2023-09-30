package com.example.passlock;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Social extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private TextView textView8,social,newpass,google,more,ott,hint;
    private View view3,backtomain;
    MyAdapter myAdapter;
    ArrayList<ParentItem> parentItemArrayList;
    RecyclerView RVparent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social);
        RVparent = findViewById(R.id.RVparent);
        more=findViewById(R.id.more);
        ott=findViewById(R.id.ott);
        hint=findViewById(R.id.hint);

        String[] orderID = {"Instagram","Facebook","Discord","Linked In","Twitter X","Snapchat","Reddit","Pinterest"};
        int[] imageId = {R.drawable.instagram,R.drawable.facebook,R.drawable.discord,R.drawable.linkedin,R.drawable.twitter,R.drawable.snapchat,R.drawable.reddit,R.drawable.pinterest};
        parentItemArrayList = new ArrayList<>();

        for (int i=0 ; i<orderID.length; i++ ){
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Passwords", Context.MODE_PRIVATE);
            String LoginIdJson = sharedPreferences.getString(orderID[i]+"uids", "[]");
            String PasswordJson = sharedPreferences.getString(orderID[i]+"pass", "[]");
            if(!LoginIdJson.equals("[]") && !PasswordJson.equals("[]")){
                ParentItem parentItem = new ParentItem(orderID[i],imageId[i]);
                parentItemArrayList.add(parentItem);}
        }
        if(parentItemArrayList.isEmpty())
        {
            hint.setVisibility(View.VISIBLE);
        }
        else {
            hint.setVisibility(View.GONE);
        }

        myAdapter = new MyAdapter(parentItemArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RVparent.setLayoutManager(layoutManager);
        RVparent.setAdapter(myAdapter);

        // Initialize your views
        constraintLayout = findViewById(R.id.socialid);
        textView8 = findViewById(R.id.dom);
        view3 = findViewById(R.id.view3);
        backtomain=findViewById(R.id.backsocial);
        social=findViewById(R.id.social);
        newpass=findViewById(R.id.newpass);
        google=findViewById(R.id.google);

        // Hide ActionBar and set StatusBar color
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawableblk = ContextCompat.getDrawable(getApplicationContext(), R.drawable.newbtnblk);
                Intent i=new Intent(getApplicationContext(),NewPass.class);
                i.putExtra("Password","");
                i.putExtra("source_activity","Social");
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());
                newpass.setBackground(drawableblk);
                newpass.setTextColor(Color.WHITE);
                finish();
            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Google.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());
                finish();

                Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
                Drawable drawablew = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbg);
                google.setBackground(drawableb);
                google.setTextColor(Color.WHITE);
                social.setBackground(drawablew);
                social.setTextColor(Color.BLACK);
            }
        });

        ott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Ott.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());
                finish();

                Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
                Drawable drawablew = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbg);
                ott.setBackground(drawableb);
                ott.setTextColor(Color.WHITE);
                social.setBackground(drawablew);
                social.setTextColor(Color.BLACK);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),More.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());
                finish();

                Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
                Drawable drawablew = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbg);
                google.setBackground(drawablew);
                google.setTextColor(Color.BLACK);
                more.setBackground(drawableb);
                more.setTextColor(Color.WHITE);
                social.setBackground(drawablew);
                social.setTextColor(Color.BLACK);
            }
        });

        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Drawable drawablewht = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnnew);
        Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
        social.setBackground(drawableb);
        social.setTextColor(Color.WHITE);
        newpass.setBackground(drawablewht);
        newpass.setGravity(Gravity.CENTER);
        newpass.setTextColor(Color.BLACK);
        // Define the new constraints for textView8
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(R.id.dom, ConstraintSet.TOP);
        constraintSet.clear(R.id.dom, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.dom, ConstraintSet.TOP, R.id.newpass, ConstraintSet.BOTTOM, 0);
        constraintSet.setMargin(R.id.dom, ConstraintSet.TOP, 150);

        // Define the new size for view3
        ValueAnimator sizeAnimator = ValueAnimator.ofInt(view3.getHeight(), 3000);
        sizeAnimator.setDuration(2000);
        sizeAnimator.setInterpolator(new DecelerateInterpolator());
        sizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
                layoutParams.height = animatedValue;
                view3.setLayoutParams(layoutParams);
            }
        });

        // Apply the new constraints and start the animation
        constraintSet.applyTo(constraintLayout);
        sizeAnimator.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
