package com.o_sport.o_sport;

import android.media.Ringtone;

import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
public class alarmActivity extends AppCompatActivity {

    TimePicker alarmTime;
    TextClock currentTime;
    Button stop;
    Timer t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmTime = findViewById(R.id.timePicker);
        currentTime = findViewById(R.id.textClock);
        stop = findViewById(R.id.stop);
        final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentTime.getText().toString().equals(AlarmTime())) {
                    ringtone.play();
                } else {
                    ringtone.stop();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(alarmActivity.this, "Sorry! Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }, 0, 1000);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtone.stop();
                t.cancel();
            }
        });

    }

    private String AlarmTime() {

        Integer alarmHour = alarmTime.getCurrentHour();
        Integer alarmMinute = alarmTime.getCurrentMinute();
        String stringAlarmMinutes;

        if (alarmMinute < 10) {
            stringAlarmMinutes = "0";
            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinute.toString());
        } else {
            stringAlarmMinutes = alarmMinute.toString();
        }

        String stringAlarmTime;

        if (alarmHour > 12) {
            alarmHour = alarmHour - 12;
            stringAlarmTime = alarmHour.toString().concat(":").concat(stringAlarmMinutes).concat(" PM");
        } else {
            stringAlarmTime = alarmHour.toString().concat(":").concat(stringAlarmMinutes).concat(" AM");
        }
        return stringAlarmTime;
    }
}
