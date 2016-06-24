package com.example.lufei.java8;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.baiwanlu.rxbus.RxBus;

/**
 * Created by lufei on 6/24/16.
 */
public class RxBusStickyTestActivity extends Activity {

    private TextView textTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);

        textTextView = (TextView) findViewById(R.id.text_text_view);


        RxBus.getDefault().subscribeSticky(TestEvent.class,
                testEvent -> textTextView.setText(testEvent.test),
                null);
    }
}
