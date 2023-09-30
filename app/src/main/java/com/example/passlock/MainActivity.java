package com.example.passlock;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView category, note, generate,newpass,social,google,more,ott,total,goodstat,fairstat,weakstat,comprostat;


    @Override
    protected void onResume() {
        super.onResume();
        Drawable drawablewht = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnnew);
        Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
        Drawable drawablew = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbg);


        category.setBackground(drawableb);
        category.setTextColor(Color.WHITE);
        generate.setBackground(drawablew);
        generate.setTextColor(Color.BLACK);
        note.setBackground(drawablew);
        note.setTextColor(Color.BLACK);
        newpass.setBackground(drawablewht);
        newpass.setGravity(Gravity.CENTER);
        newpass.setTextColor(Color.BLACK);
        social.setBackground(drawablew);
        social.setTextColor(Color.BLACK);
        google.setBackground(drawablew);
        google.setTextColor(Color.BLACK);
        more.setBackground(drawablew);
        more.setTextColor(Color.BLACK);
        ott.setBackground(drawablew);
        ott.setTextColor(Color.BLACK);


        int totalCount = 0;
        int Good=0,Fair=0,Weak=0,Compro=0;

        // Get the SharedPreferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("Passwords", Context.MODE_PRIVATE);

        // Get all keys in the SharedPreferences
        Map<String, ?> allKeys = sharedPreferences.getAll();

        // Iterate through all the keys
        for (Map.Entry<String, ?> entry : allKeys.entrySet()) {
            String key = entry.getKey();
            if(!key.contains("uids")){
                String json = sharedPreferences.getString(key, "[]");

                // Parse the JSON array and count its members
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                ArrayList<String> passjsonArray = gson.fromJson(json, type);
                for(int j=0;j<passjsonArray.size();j++){
                    String temppass=passjsonArray.get(j);
                    if(temppass.length()<=4){
                        ++Compro;
                    }
                    else if(temppass.length()<=6){
                        ++Weak;
                    }
                    else if(temppass.length()<=8){
                        ++Fair;
                    }
                    else {
                        ++Good;
                    }
                }
            }
            String json = sharedPreferences.getString(key, "[]");

            // Parse the JSON array and count its members
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> jsonArray = gson.fromJson(json, type);

            // Update the total count
            totalCount += jsonArray.size();
        }
        total.setText("Total Password\n"+totalCount/2);
        goodstat.setText(Good+"");
        fairstat.setText(Fair+"");
        comprostat.setText(Compro+"");
        weakstat.setText(Weak+"");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category = findViewById(R.id.category);
        note = findViewById(R.id.notes);
        generate = findViewById(R.id.generate);
        newpass=findViewById(R.id.newpass);
        social=findViewById(R.id.social);
        google=findViewById(R.id.google);
        more=findViewById(R.id.more);
        ott=findViewById(R.id.ott);
        total=findViewById(R.id.textView7);
        weakstat=findViewById(R.id.weakstat);
        fairstat=findViewById(R.id.fairstat);
        comprostat=findViewById(R.id.comprostat);
        goodstat=findViewById(R.id.goodstat);


        // Hide ActionBar and set StatusBar color
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        // Set click listener for the "Generate" button
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Generate.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());

                Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
                Drawable drawablew = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbg);
                generate.setBackground(drawableb);
                generate.setTextColor(Color.WHITE);
                category.setBackground(drawablew);
                category.setTextColor(Color.BLACK);
            }
        });

        newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawableblk = ContextCompat.getDrawable(getApplicationContext(), R.drawable.newbtnblk);
                Intent i=new Intent(getApplicationContext(),NewPass.class);
                i.putExtra("Password","");
                i.putExtra("source_activity","Main");
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());
                newpass.setBackground(drawableblk);
                newpass.setTextColor(Color.WHITE);
            }
        });

        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Social.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());

                Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
                social.setBackground(drawableb);
                social.setTextColor(Color.WHITE);
            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Google.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());

                Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
                google.setBackground(drawableb);
                google.setTextColor(Color.WHITE);
            }
        });

        ott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Ott.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());

                Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
                ott.setBackground(drawableb);
                ott.setTextColor(Color.WHITE);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),More.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());

                Drawable drawableb = ContextCompat.getDrawable(getApplicationContext(), R.drawable.btnbgblk);
                more.setBackground(drawableb);
                more.setTextColor(Color.WHITE);
            }
        });

    }
}
