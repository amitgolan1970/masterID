package com.golan.amit.masterid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ControlCalculateActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    ImageView ivMatrix;
    TextView tvId;
    EditText etControl;
    ImageButton ivAnswerBtn;
    Button btnDicover;
    IdHelper id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_calculate);

        init();

        play();
    }

    private void play() {
        id.generate();
        id.calculateControl();
        tvId.setText(id.stringRepresentation());
        if(MainActivity.DEBUG) {
            Log.d(MainActivity.DEBUGTAG, "control digit = " + id.getControl());
        }
    }

    private void init() {
        tvId = (TextView) findViewById(R.id.tvIdDisplayId);
        ivMatrix = (ImageView) findViewById(R.id.ivMatrixId);
        etControl = (EditText) findViewById(R.id.etControlDigitId);
        etControl.requestFocus();
        ivAnswerBtn = (ImageButton) findViewById(R.id.ivBtnId);
        ivAnswerBtn.setOnClickListener(this);
        ivAnswerBtn.setOnLongClickListener(this);
        btnDicover = (Button)findViewById(R.id.btnDiscoverId);
        btnDicover.setOnClickListener(this);
        btnDicover.setOnLongClickListener(this);
        id = new IdHelper();
    }

    @Override
    public void onClick(View v) {
        if (v == ivAnswerBtn) {
            String controlStr = null;
            Integer controlInt = -1;
            try {
                if (etControl.getText() != null) {
                    controlStr = etControl.getText().toString();
                    if (controlStr == "" || controlStr.length() == 0) {
                        if(MainActivity.DEBUG) {
                            Log.d(MainActivity.DEBUGTAG, "setting to 0");
                        }
                        controlStr = "0";
                    }
                    controlInt = Integer.parseInt(controlStr);
                    if (controlInt != -1) {
                        if (controlInt == id.getControl()) {
                            Toast.makeText(this, "תשובה נכונה, מעולה", Toast.LENGTH_SHORT).show();
                            id.generate();
                            id.calculateControl();
                            tvId.setText(id.stringRepresentation());
                            etControl.setText("");
                            id.setFails(1);
                            if(MainActivity.DEBUG) {
                                Log.d(MainActivity.DEBUGTAG, "control digit = " + id.getControl());
                            }
                        } else {
                            if (id.getFails() == 3) {
                                Toast.makeText(this, "המשחק נגמר. התשובה הנכונה הייתה " + id.getControl(), Toast.LENGTH_LONG).show();
                                id.generate();
                                id.calculateControl();
                                tvId.setText(id.stringRepresentation());
                                etControl.setText("");
                                id.setFails(1);
                                if(MainActivity.DEBUG) {
                                    Log.d(MainActivity.DEBUGTAG, "control digit = " + id.getControl());
                                }
                            } else {
                                Toast.makeText(this, "תשובה שגוייה, נסה/י שוב", Toast.LENGTH_SHORT).show();
                                id.increaseFails();
                            }
                        }
                    } else {
                        Toast.makeText(this, "-1 , please check", Toast.LENGTH_SHORT).show();
                        Log.e(MainActivity.DEBUGTAG, "value of -1 is illegal, please check");
                    }
                } else {
                    Toast.makeText(this, "getText is null", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "תו לא חוקי, נא להקליק סיפרה", Toast.LENGTH_SHORT).show();
            }
        } else if(v == btnDicover) {
//            Toast.makeText(this, "discover is clicked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, DiscoverActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == ivAnswerBtn) {
            Toast.makeText(this, "מחליף מספר תעודת זהות לבקשתך", Toast.LENGTH_SHORT).show();
            if(MainActivity.DEBUG) {
                Log.d(MainActivity.DEBUGTAG, "replacing id generation due to long click");
            }
            id.generate();
            id.calculateControl();
            tvId.setText(id.stringRepresentation());
            etControl.setText("");
            id.setFails(1);
            if(MainActivity.DEBUG) {
                Log.d(MainActivity.DEBUGTAG, "control digit = " + id.getControl());
            }
            return true;
        } else if (v == btnDicover) {
            Log.d(MainActivity.DEBUGTAG, "discover long clicked invoked = " + id.HelpHint());
            Toast.makeText(this, "רמז:\n" + id.HelpHint(), Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
