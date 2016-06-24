package com.example.lufei.java8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.baiwanlu.rxbus.RxBus;

public class MainActivity extends AppCompatActivity {
    private Button textBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBtn = (Button) findViewById(R.id.text_btn);
        textBtn.setOnClickListener(view -> {
                    RxBus.getDefault().post(new TestEvent("test RxBus"));
                    RxBus.getDefault().postSticky(new TestEvent("test RxBus sticky"));

                    startActivity(new Intent(MainActivity.this, RxBusStickyTestActivity.class));
                }
        );

        RxBus.getDefault().subscribe(TestEvent.class, testEvent -> {
            Toast.makeText(MainActivity.this, testEvent.test, Toast.LENGTH_SHORT).show();
        }, throwable -> {

        });
    }
}
