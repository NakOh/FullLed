package com.myApplication.fullLed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class FullLedActivity extends AppCompatActivity {
    static {
        System.loadLibrary("fullcolorled");
    }

    public native int FLEDControl(int led_num, int val1, int val2, int val3);

    static SeekBar[] sb;
    static int temp;
    int[] led_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        led_val = new int[4];
        led_val[0] = 9;
        for (int i = 1; i < 4; i++) {
            led_val[i] = 0;
        }
        findViewById(R.id.fcl_ledbut1).setOnClickListener(mLedButtonListener);
        findViewById(R.id.fcl_ledbut2).setOnClickListener(mLedButtonListener);
        findViewById(R.id.fcl_ledbut3).setOnClickListener(mLedButtonListener);
        findViewById(R.id.fcl_ledbut4).setOnClickListener(mLedButtonListener);
        findViewById(R.id.fcl_ledbut5).setOnClickListener(mLedButtonListener);

        findViewById(R.id.fcl_red).setOnClickListener(mColorButtonListener);
        findViewById(R.id.fcl_green).setOnClickListener(mColorButtonListener);
        findViewById(R.id.fcl_blue).setOnClickListener(mColorButtonListener);
        findViewById(R.id.fcl_random).setOnClickListener(mColorButtonListener);
        findViewById(R.id.fcl_off).setOnClickListener(mColorButtonListener);

        //
        sb = new SeekBar[3];
        sb[0] = (SeekBar) findViewById(R.id.fcl_seekBar_red);
        sb[1] = (SeekBar) findViewById(R.id.fcl_seekBar_green);
        sb[2] = (SeekBar) findViewById(R.id.fcl_seekBar_blue);

        sb[0].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
                led_val[1] = temp;
                FLEDControl(led_val[0], led_val[1], led_val[2], led_val[3]);
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
                led_val[1] = temp;
                FLEDControl(led_val[0], led_val[1], led_val[2], led_val[3]);
            }
        });

        sb[1].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
                led_val[2] = temp;
                FLEDControl(led_val[0], led_val[1], led_val[2], led_val[3]);
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
                led_val[2] = temp;
                FLEDControl(led_val[0], led_val[1], led_val[2], led_val[3]);
            }
        });
        sb[2].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
                led_val[3] = temp;
                FLEDControl(led_val[0], led_val[1], led_val[2], led_val[3]);
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub
                temp = arg0.getProgress();
                led_val[3] = temp;
                FLEDControl(led_val[0], led_val[1], led_val[2], led_val[3]);
            }
        });
    }
    RadioButton.OnClickListener mLedButtonListener = new RadioButton.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.fcl_ledbut1:  led_val[0] = 9; break;
                case R.id.fcl_ledbut2:  led_val[0] = 8; break;
                case R.id.fcl_ledbut3:  led_val[0] = 7; break;
                case R.id.fcl_ledbut4:  led_val[0] = 6; break;
                case R.id.fcl_ledbut5:  led_val[0] = 5; break;
            }
            led_val[1] = sb[0].getProgress();
            led_val[2] = sb[1].getProgress();
            led_val[3] = sb[2].getProgress();
            FLEDControl(led_val[0], led_val[1], led_val[2], led_val[3]);
        }
    };

    RadioButton.OnClickListener mColorButtonListener = new RadioButton.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.fcl_red:
                    led_val[1] = 100; led_val[2] = 0;    led_val[3] = 0;
                    break;
                case R.id.fcl_green:
                    led_val[1] = 0;    led_val[2] = 0;    led_val[3] = 100;
                    break;
                case R.id.fcl_blue:
                    led_val[1] = 0;    led_val[2] = 100; led_val[3] = 0;
                    break;
                case R.id.fcl_random:
                    led_val[1] = (int) (Math.random() * 101);
                    led_val[2] = (int) (Math.random() * 101);
                    led_val[3] = (int) (Math.random() * 101);
                    break;
                case R.id.fcl_off:
                    led_val[1] = 0; led_val[2] = 0; led_val[3] = 0;
                    break;
            }
            FLEDControl(led_val[0], led_val[1], led_val[2], led_val[3]);
            sb[0].setProgress(led_val[1]);
            sb[1].setProgress(led_val[2]);
            sb[2].setProgress(led_val[3]);
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_led, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
