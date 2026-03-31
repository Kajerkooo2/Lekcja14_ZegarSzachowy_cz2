package com.example.zegarszachowy;

import android.os.CountDownTimer;
import android.widget.Button;

public class User {
    private long timeUser1 = 600000;
    private long timeUser2 = 600000;
    private boolean isUser1Active = true;
    private CountDownTimer countDownTimer;
    private Button btnUser1;
    private Button btnUser2;

    public User(Button btnUser1, Button btnUser2) {
        this.btnUser1 = btnUser1;
        this.btnUser2 = btnUser2;
        updateDisplay();
    }

    public void startTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (isUser1Active) {
                    timeUser1 -= 100;
                    if (timeUser1 <= 0) {
                        timeUser1 = 0;
                        stopTimer();
                    }
                } else {
                    timeUser2 -= 100;
                    if (timeUser2 <= 0) {
                        timeUser2 = 0;
                        stopTimer();
                    }
                }
                updateDisplay();
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    public void stopTimer() {
            countDownTimer.cancel();
    }

    public void switchPlayer() {
        isUser1Active = !isUser1Active;
    }

    private void updateDisplay() {
        btnUser1.setText(formatTime(timeUser1));
        btnUser2.setText(formatTime(timeUser2));
    }

    private String formatTime(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    public boolean isUser1Active() {
        return isUser1Active;
    }
}