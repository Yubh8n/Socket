package com.example.myfirstapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String outPut = "";
    boolean first_push = true;
    int progress = 80;
    String ip = "192.168.8.108";
    int port = 65432;
    private Socket client;
    DataOutputStream DOS;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //final Button btnSend = (Button) findViewById(R.id.Send_button);
        //final TextView insert = (TextView) findViewById(R.id.edit_message);
        final TextView Show = (TextView) findViewById(R.id.output);
        final SeekBar simpleSeekBar = (SeekBar) findViewById(R.id.Trackbar);
        simpleSeekBar.setProgress(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    client = new Socket(ip, port);
                    DOS = new DataOutputStream(client.getOutputStream());
                    DOS.write(progress);
                    //client.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();

        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {
                 // TODO Auto-generated method stub
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {
                 // TODO Auto-generated method stub

             }

             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 // TODO Auto-generated method stub
                 progress = simpleSeekBar.getProgress();
                 Show.setText(Integer.toString(progress) + "% of max");

             }
         }
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



/*btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inserted_value = insert.getText().toString();
                if (first_push){
                    Show.setText("");
                    Show.getEditableText().insert(0, inserted_value);
                    insert.setText("");
                    first_push = false;
                }
                else{
                    Show.getEditableText().insert(0, "\n");
                    Show.getEditableText().insert(0, inserted_value);
                    insert.setText("");
                }

                //outPut += inserted_value + "\n";
                //Show.setText(outPut);
                //outPut = "";
                /*outPut += inserted_value;
                outPut += "\n";

                for (int i = 0; i< outPut.length(); i++)
                    Show.append(outPut[i]);

                insert.setText("");
                if (outPut.length() != 0)
                {
                    Show.setText(outPut);
                }*/