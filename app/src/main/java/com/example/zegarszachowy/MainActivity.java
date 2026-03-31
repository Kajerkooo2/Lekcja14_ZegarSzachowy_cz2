package com.example.zegarszachowy;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnUser1;
    private Button btnUser2;
    private User user;
    private boolean isGameRunning = false;

    private static final String KEY_GAME_RUNNING = "isGameRunning";
    private static final String KEY_TIME_USER1 = "timeUser1";
    private static final String KEY_TIME_USER2 = "timeUser2";
    private static final String KEY_ACTIVE_USER = "isUser1Active";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUser1 = findViewById(R.id.User1);
        btnUser2 = findViewById(R.id.User2);

        user = new User(btnUser1, btnUser2);

        if (savedInstanceState != null) {
            isGameRunning = savedInstanceState.getBoolean(KEY_GAME_RUNNING);
            user.restoreState(
                    savedInstanceState.getLong(KEY_TIME_USER1),
                    savedInstanceState.getLong(KEY_TIME_USER2),
                    savedInstanceState.getBoolean(KEY_ACTIVE_USER)
            );
            if (isGameRunning) {
                user.startTimer();
            }
        }

        btnUser1.setOnClickListener(v -> {
            if (!isGameRunning) {
                isGameRunning = true;
                user.startTimer();
            } else if (user.isUser1Active()) {
                user.switchPlayer();
            }
        });

        btnUser2.setOnClickListener(v -> {
            if (!isGameRunning) {
                isGameRunning = true;
                user.startTimer();
            } else if (!user.isUser1Active()) {
                user.switchPlayer();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (isGameRunning) {
            user.stopTimer();
        }

        outState.putBoolean(KEY_GAME_RUNNING, isGameRunning);
        outState.putLong(KEY_TIME_USER1, user.getTimeUser1());
        outState.putLong(KEY_TIME_USER2, user.getTimeUser2());
        outState.putBoolean(KEY_ACTIVE_USER, user.isUser1Active());
    }
}