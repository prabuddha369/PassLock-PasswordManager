package com.example.passlock;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Generate extends AppCompatActivity {
    private View bkbtn,regen;
    private TextView generatebtn,use,captxt,digtxt,spetxt,total_chars,gen_pass;
    private SeekBar caps,digits,specials;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate);
        bkbtn=findViewById(R.id.backtomain);
        caps=findViewById(R.id.capsseek);
        digits=findViewById(R.id.digitseek);
        specials=findViewById(R.id.specialseek);
        generatebtn=findViewById(R.id.generatebtn);
        captxt=findViewById(R.id.captxt);
        digtxt=findViewById(R.id.digtxt);
        spetxt=findViewById(R.id.spetxt);
        total_chars=findViewById(R.id.total);
        gen_pass=findViewById(R.id.gen_pass);
        regen=findViewById(R.id.regen);
        regen.setVisibility(View.GONE);
        use=findViewById(R.id.usebtn);

        caps.setOnSeekBarChangeListener(seekBarChangeListener);
        digits.setOnSeekBarChangeListener(seekBarChangeListener);
        specials.setOnSeekBarChangeListener(seekBarChangeListener);

        use.setVisibility(View.GONE);

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regen.setVisibility(View.VISIBLE);
                use.setVisibility(View.VISIBLE);
                AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setDuration(500); // Adjust the duration as needed
                generatebtn.startAnimation(fadeOut);

                // Set a listener to hide the button when the animation ends
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // Animation started
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Animation ended
                        generatebtn.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // Animation repeated
                    }
                });

                int capsCount = (int) caps.getProgress() / 10;
                int digitsCount = (int) digits.getProgress() / 10;
                int specialsCount = (int) specials.getProgress() / 10;

                String randomText = generateRandomText(capsCount, digitsCount, specialsCount);
                gen_pass.setText(randomText);
            }
        });
        regen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int capsCount = (int) caps.getProgress() / 10;
                int digitsCount = (int) digits.getProgress() / 10;
                int specialsCount = (int) specials.getProgress() / 10;

                String randomText = generateRandomText(capsCount, digitsCount, specialsCount);
                gen_pass.setText(randomText);
            }
        });

        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),NewPass.class);
                i.putExtra("Password",gen_pass.getText().toString());
                i.putExtra("source_activity","Generate");
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(i,options.toBundle());
                finish();
            }
        });

        bkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });



    }
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // Update the total progress TextView as SeekBars are changed
            captxt.setText(String.valueOf((int)caps.getProgress()/10));
            digtxt.setText(String.valueOf((int)digits.getProgress()/10));
            spetxt.setText(String.valueOf((int)specials.getProgress()/10));

            int totalProgress = (int)caps.getProgress()/10+ (int)digits.getProgress()/10 + (int)specials.getProgress()/10;
            total_chars.setText(""+totalProgress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // Not needed for this implementation
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // Not needed for this implementation
        }
    };


    private String generateRandomText(int capsCount, int digitsCount, int specialsCount) {
        StringBuilder randomText = new StringBuilder();
        Random random = new Random();

        // Define character sets for capital letters, digits, and special symbols
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialSymbols = "!@#$%^&*()_+[]{}|;:',.<>?";

        // Generate random capital letters
        for (int i = 0; i < capsCount; i++) {
            randomText.append(capitalLetters.charAt(random.nextInt(capitalLetters.length())));
        }

        // Generate random digits
        for (int i = 0; i < digitsCount; i++) {
            randomText.append(digits.charAt(random.nextInt(digits.length())));
        }

        // Generate random special symbols
        for (int i = 0; i < specialsCount; i++) {
            randomText.append(specialSymbols.charAt(random.nextInt(specialSymbols.length())));
        }

        // Shuffle the characters to randomize the order
        char[] textArray = randomText.toString().toCharArray();
        for (int i = textArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = textArray[index];
            textArray[index] = textArray[i];
            textArray[i] = temp;
        }

        return new String(textArray);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
