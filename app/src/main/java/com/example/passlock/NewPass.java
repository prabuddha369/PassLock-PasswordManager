package com.example.passlock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class NewPass extends AppCompatActivity {
    private View bkbtn,showP;
    private TextView cancel,save;
    private EditText edittextpass,logid;
    private AutoCompleteTextView serviceAutoComplete;
    private String s;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pass);

        bkbtn=findViewById(R.id.backtomain);
        edittextpass=findViewById(R.id.editTextpass);
        cancel=findViewById(R.id.cancel);
        logid=findViewById(R.id.logid);
        serviceAutoComplete = findViewById(R.id.serviceAutoComplete);
        showP=findViewById(R.id.showP);
        save=findViewById(R.id.save);

        Intent intent =getIntent();
        String passw =intent.getStringExtra("Password");
        String sourceActivityName = intent.getStringExtra("source_activity");
        if (sourceActivityName != null) {
            if (sourceActivityName.equals("Generate")) {
                edittextpass.setText(passw);
            }
        }

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        String services[] = {
                "Facebook",
                "Instagram",
                "Twitter X",
                "Snapchat",
                "Reddit",
                "Pinterest",
                "Spotify",
                "Disney+ Hotstar",
                "Zee5",
                "SonyLIV",
                "Discord",
                "Linked In",
                "Gmail",
                "Microsoft",
                "Amazon",
                "Netflix",
                "GitHub",
                "Apple"
        };

        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner_dropdown_item,
                services
        );
        serviceAutoComplete.setAdapter(autoCompleteAdapter);

        serviceAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                s = adapterView.getItemAtPosition(i).toString();
            }
        });

        bkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        showP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // If the input type is currently text, switch to textPassword
                    edittextpass.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Drawable show = ContextCompat.getDrawable(NewPass.this, R.drawable.ic_baseline_remove_red_eye_24);
                    showP.setBackground(show);
                } else {
                    // If the input type is currently textPassword, switch to text
                    edittextpass.setInputType(InputType.TYPE_CLASS_TEXT);
                    Drawable close = ContextCompat.getDrawable(NewPass.this, R.drawable.ic_baseline_close_24);
                    showP.setBackground(close);
                }

                // Move the cursor to the end of the text
                edittextpass.setSelection(edittextpass.getText().length());

                isPasswordVisible = !isPasswordVisible;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logid.setText("");
                logid.setHint("Login id...");
                edittextpass.setText("");
                edittextpass.setHint("Password to save ...");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginId = logid.getText().toString();
                String password = edittextpass.getText().toString();
                if (s.equals("Facebook") || s.equals("Instagram") || s.equals("Twitter X") || s.equals("Snapchat") ||
                        s.equals("Reddit") || s.equals("Pinterest") || s.equals("Spotify") || s.equals("Disney+ Hotstar") ||
                        s.equals("Zee5") || s.equals("SonyLIV") || s.equals("Discord") || s.equals("Linked In") ||
                        s.equals("Gmail") || s.equals("Microsoft") || s.equals("Amazon") || s.equals("Netflix") ||
                        s.equals("GitHub") || s.equals("Apple")) {
                    if (!loginId.isEmpty() && !password.isEmpty()) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Passwords", Context.MODE_PRIVATE);

                        // Check if preference keys for login IDs and passwords exist
                        String loginIdJson = sharedPreferences.getString(s + "uids", "[]");
                        String passwordJson = sharedPreferences.getString(s + "pass", "[]");

                        // Convert JSON arrays to ArrayLists
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<String>>() {}.getType();
                        ArrayList<String> loginIdList = gson.fromJson(loginIdJson, type);
                        ArrayList<String> passwordList = gson.fromJson(passwordJson, type);

                        // Check if the ArrayLists are empty (no existing data)
                        if (loginIdList.isEmpty() || passwordList.isEmpty()) {
                            // Create new JSON arrays and add the new login ID and password
                            loginIdList = new ArrayList<>();
                            passwordList = new ArrayList<>();
                            loginIdList.add(loginId);
                            passwordList.add(password);
                        } else {
                            // Add the new login ID and password to existing ArrayLists
                            loginIdList.add(loginId);
                            passwordList.add(password);
                        }

                        // Convert ArrayLists back to JSON arrays
                        String updatedLoginIdJson = gson.toJson(loginIdList);
                        String updatedPasswordJson = gson.toJson(passwordList);

                        // Save the updated JSON arrays back to SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(s + "uids", updatedLoginIdJson);
                        editor.putString(s + "pass", updatedPasswordJson);
                        editor.apply();

                        // Clear the input fields
                        logid.setText("");
                        logid.setHint("Login id...");
                        edittextpass.setText("");
                        edittextpass.setHint("Password to save ...");

                        Toast.makeText(NewPass.this, "Password saved successfully!", Toast.LENGTH_SHORT).show();
                        if(s.equals("Instagram") || s.equals("Facebook") || s.equals("Discord") || s.equals("Linked In") || s.equals("Twitter X") || s.equals("Snapchat") || s.equals("Reddit") || s.equals("Pinterest"))
                        {
                            Intent i=new Intent(getApplicationContext(),Social.class);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                            startActivity(i,options.toBundle());
                            finish();
                        }
                        else if(s.equals("Gmail")){
                            Intent i=new Intent(getApplicationContext(),Google.class);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                            startActivity(i,options.toBundle());
                            finish();
                        }
                        else if(s.equals("Netflix") || s.equals("Disney+ Hotstar") || s.equals("Zee5") || s.equals("SonyLIV"))
                        {
                            Intent i=new Intent(getApplicationContext(),Ott.class);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                            startActivity(i,options.toBundle());
                            finish();
                        }
                        else {
                            Intent i=new Intent(getApplicationContext(),More.class);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                            startActivity(i,options.toBundle());
                            finish();
                        }
                    } else {
                        Toast.makeText(NewPass.this, "Please fill in both login ID and password.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {

                }
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}