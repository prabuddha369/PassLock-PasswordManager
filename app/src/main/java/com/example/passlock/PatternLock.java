package com.example.passlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.itsxtt.patternlock.PatternLockView;

import java.util.ArrayList;

public class PatternLock extends AppCompatActivity {
    PatternLockView patternLockView;
    TextView changepattern;
    private ArrayList<Integer> drawnPattern = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pattern_lock);
        patternLockView = findViewById(R.id.patternLockView);
        changepattern=findViewById(R.id.changepattern);

        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        changepattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPatternSet()) {
                    // A pattern is already set; prompt the user to enter the previous pattern
                    Toast.makeText(PatternLock.this, "Enter your previous pattern", Toast.LENGTH_LONG).show();

                    patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
                        @Override
                        public void onStarted() {
                            // Pattern drawing has started
                        }

                        @Override
                        public void onProgress(ArrayList<Integer> ids) {
                            // Pattern drawing is in progress
                        }

                        @Override
                        public boolean onComplete(ArrayList<Integer> ids) {
                            drawnPattern = new ArrayList<>(ids);

                            if (isPatternCorrect()) {
                                // Previous pattern is correct; prompt the user to enter a new pattern
                                Toast.makeText(PatternLock.this, "Enter a new pattern", Toast.LENGTH_LONG).show();

                                // Handle setting a new pattern
                                patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
                                    @Override
                                    public void onStarted() {
                                        // Pattern drawing has started
                                    }

                                    @Override
                                    public void onProgress(ArrayList<Integer> ids) {
                                        // Pattern drawing is in progress
                                    }

                                    @Override
                                    public boolean onComplete(ArrayList<Integer> ids) {
                                        drawnPattern = new ArrayList<>(ids);

                                        // Save the new pattern to SharedPreferences
                                        savePattern(drawnPattern);

                                        // Redirect the user to the main activity
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                        finish();

                                        return true;
                                    }
                                });

                                return true;
                            } else {
                                // Previous pattern is incorrect; notify the user
                                Toast.makeText(PatternLock.this, "Incorrect Pattern", Toast.LENGTH_SHORT).show();
                            }

                            return false;
                        }
                    });
                } else {
                    Toast.makeText(PatternLock.this, "Set a new pattern.", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Check if a pattern is already set
        if (!isPatternSet()) {
            // Prompt the user to set a new pattern
            Toast.makeText(this, "Set a new pattern.", Toast.LENGTH_LONG).show();

            // Handle setting a new pattern
            patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
                @Override
                public void onStarted() {
                    // Pattern drawing has started
                }

                @Override
                public void onProgress(ArrayList<Integer> ids) {
                    // Pattern drawing is in progress
                }

                @Override
                public boolean onComplete(ArrayList<Integer> ids) {
                    drawnPattern = new ArrayList<>(ids);

                    // Save the pattern to SharedPreferences
                    savePattern(drawnPattern);

                    // Redirect the user to the main activity
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();

                    return true;
                }
            });
        } else {
            // A pattern is already set, check if it's correct
            patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
                @Override
                public void onStarted() {
                    // Pattern drawing has started
                }

                @Override
                public void onProgress(ArrayList<Integer> ids) {
                    // Pattern drawing is in progress
                }

                @Override
                public boolean onComplete(ArrayList<Integer> ids) {
                    drawnPattern = new ArrayList<>(ids);
                    boolean isCorrect = isPatternCorrect();

                    if (isCorrect) {
                        // Pattern is correct; allow access to the main activity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        // Pattern is incorrect; notify the user
                        Toast.makeText(PatternLock.this, "Incorrect Pattern", Toast.LENGTH_SHORT).show();
                    }

                    return isCorrect;
                }
            });
        }
    }

    private boolean isPatternCorrect() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedPattern = preferences.getString("pattern", "");
        StringBuilder drawnPatternStringBuilder = new StringBuilder();

        for (Integer id : drawnPattern) {
            drawnPatternStringBuilder.append(id);
        }

        String drawnPatternString = drawnPatternStringBuilder.toString();

        return savedPattern.equals(drawnPatternString);
    }

    private boolean isPatternSet() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.contains("pattern");
    }
    private void savePattern(ArrayList<Integer> pattern) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        StringBuilder patternStringBuilder = new StringBuilder();

        for (Integer id : pattern) {
            patternStringBuilder.append(id);
        }

        String patternString = patternStringBuilder.toString();
        preferences.edit().putString("pattern", patternString).apply();
    }

}