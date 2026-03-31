package com.example.zegarszachowy;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnUser1;
    private Button btnUser2;
    private User user;
    private boolean isGameRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUser1 = findViewById(R.id.User1);
        btnUser2 = findViewById(R.id.User2);

        user = new User(btnUser1, btnUser2);

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


}