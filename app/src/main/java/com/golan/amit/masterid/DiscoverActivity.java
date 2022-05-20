package com.golan.amit.masterid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DiscoverActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    EditText etQueryId;
    TextView tvQuery;
    Button btnDiscover, btnGoBack;
    IdHelper id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicover);

        init();
    }

    private void init() {
        etQueryId = (EditText) findViewById(R.id.etQueryId);
        etQueryId.setOnTouchListener(this);
        etQueryId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Toast.makeText(DiscoverActivity.this, "key pressed (text watcher)", Toast.LENGTH_SHORT).show();
                tvQuery.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        tvQuery = (TextView) findViewById(R.id.tvControl);
        btnDiscover = (Button) findViewById(R.id.btnDiscoverControl);
        btnDiscover.setOnClickListener(this);
        btnGoBack = (Button) findViewById(R.id.btnGoBackId);
        btnGoBack.setOnClickListener(this);
        id = new IdHelper();
    }

    @Override
    public void onClick(View v) {
        if (v == btnDiscover) {
            String idStr = null;
            int len = -1;
            try {
                idStr = etQueryId.getText().toString();
                int tmpForTest = Integer.parseInt(idStr);
                len = idStr.length();
                if (len < 6) {
                    Toast.makeText(this, "קלט קצר מדי: " + idStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (len < IdHelper.NUMBER_OF_DIGITS) {
                    for (int i = 0; i < (IdHelper.NUMBER_OF_DIGITS - len); i++) {
                        idStr = "0" + idStr;
                    }
                }
//                Toast.makeText(this, "קלט: " + idStr, Toast.LENGTH_SHORT).show();
                id.set_id_by_string(idStr);
                id.calculateControl();
                tvQuery.setText(String.valueOf(id.getControl()));

            } catch (Exception e) {
                Toast.makeText(this, "קלט לא חוקי", Toast.LENGTH_SHORT).show();
            }
        } else if (v == btnGoBack) {
//            Toast.makeText(this, "go back is clicked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ControlCalculateActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == etQueryId) {
            tvQuery.setText("");
        }
        return false;
    }
}
