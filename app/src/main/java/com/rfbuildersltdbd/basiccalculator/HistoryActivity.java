package com.rfbuildersltdbd.basiccalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import org.w3c.dom.Text;

public class HistoryActivity extends AppCompatActivity {
    TextView historyView;
    Button back;
    //SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyView = (TextView) findViewById(R.id.text_view_id3);
        back = (Button) findViewById(R.id.button_idBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        historyView.setText("");
        Intent intent = getIntent();
        //String value = pref.getString("1.", null);
        //historyView.setText(value);
        int historyCount = Integer.parseInt(intent.getStringExtra("historyCount").toString());
        //historyView.setText(intent.getStringExtra("historyCount").toString());
        //historyView.append(intent.getStringExtra("1."));
        try {
           // int historyCount = Integer.parseInt(intent.getStringExtra("historyCount").toString());
           // int historyCount = intent.getStringExtra("historyCount");
            while (historyCount > 0) {
                historyView.append(historyCount +" : "+ intent.getStringExtra(historyCount + ".") + ";\n");
                historyCount--;
            }
        }
        catch (Exception e){

        }

    }
}
