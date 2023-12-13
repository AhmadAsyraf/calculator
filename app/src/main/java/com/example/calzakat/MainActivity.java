package com.example.calzakat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText value;
    EditText value2;
    Button btnCalculate;
    Button btnClear;
    TextView tvOutput;
    TextView tvOutput2;
    TextView tvOutput3;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = findViewById(R.id.editTextNumberDecimal2);
        value2 = findViewById(R.id.editTextNumberDecimal3);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        tvOutput = findViewById(R.id.tvOutput);
        tvOutput2 = findViewById(R.id.tvOutput2);
        tvOutput3 = findViewById(R.id.tvOutput3);

        radioGroup = findViewById(R.id.radioGroup);

        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalculate) {
            if (TextUtils.isEmpty(value.getText()) || TextUtils.isEmpty(value2.getText())) {
                Toast.makeText(this, "Please enter both weight and current gold value", Toast.LENGTH_SHORT).show();
                return;
            }

            double w = Double.parseDouble(value.getText().toString());
            double cg = Double.parseDouble(value2.getText().toString());

            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            double selectedValue = 0.0;

            if (selectedRadioButton != null) {
                if (selectedRadioButton.getId() == R.id.radioKeep) {
                    selectedValue = 85.0;
                } else if (selectedRadioButton.getId() == R.id.radioWear) {
                    selectedValue = 200.0;
                }
            }

            double x = w * cg;
            double y = w - selectedValue;
            double y2 = cg * y;
            double z = y2 * 0.025;

            if (x < 0 || y < 0 || z < 0) {
                Toast.makeText(this, "Error: Invalid input values", Toast.LENGTH_SHORT).show();
                return;
            }

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedX = decimalFormat.format(x);
            String formattedY2 = decimalFormat.format(y2);
            String formattedZ = decimalFormat.format(z);

            tvOutput.setText("Value of the gold : RM" + formattedX);
            tvOutput2.setText("Total gold value that is zakat payable : RM" + formattedY2);
            tvOutput3.setText("The total zakat  : RM" + formattedZ);
        } else if (v.getId() == R.id.btnClear) {
            value.getText().clear();
            value2.getText().clear();
            tvOutput.setText("");
            tvOutput2.setText("");
            tvOutput3.setText("");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==R.id.item_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
        }
        return false;
    }
}