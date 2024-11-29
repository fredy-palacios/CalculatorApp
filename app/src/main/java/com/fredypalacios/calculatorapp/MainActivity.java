package com.fredypalacios.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvOperation = findViewById(R.id.tvOperation);
        TextView tvResult = findViewById(R.id.tvResult);

        EditText etNumber1 = findViewById(R.id.etNumber1);
        EditText etNumber2 = findViewById(R.id.etNumber2);

        RadioButton rbAdd = findViewById(R.id.rbAdd);
        RadioButton rbSubtract = findViewById(R.id.rbSubtract);
        RadioButton rbMultiply = findViewById(R.id.rbMultiply);
        RadioButton rbDivide = findViewById(R.id.rbDivide);

        Button bCalculate = findViewById(R.id.bCalculate);
        Button bDelete = findViewById(R.id.bDelete);
        Button bSave = findViewById(R.id.bSave);
        Button bShow = findViewById(R.id.bShow);

        rbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOperation.setText("+");
                rbAdd.setChecked(true);
            }
        });

        rbSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOperation.setText("―");
                rbSubtract.setChecked(true);
            }
        });

        rbMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOperation.setText("*");
                rbMultiply.setChecked(true);
            }
        });

        rbDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOperation.setText("/");
                rbDivide.setChecked(true);
            }
        });

        bCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get String
                String inputText1 = etNumber1.getText().toString();
                String inputText2 = etNumber2.getText().toString();

                if (!inputText1.isEmpty() && !inputText2.isEmpty()) {
                    try {
                        //String parse to int
                        int etNum1 = Integer.parseInt(inputText1);
                        int etNum2 = Integer.parseInt(inputText2);

                        if (rbAdd.isChecked()) {
                            tvResult.setText(String.valueOf(etNum1 + etNum2));
                        }

                        if (rbSubtract.isChecked()) {
                            tvResult.setText(String.valueOf(etNum1 - etNum2));
                        }

                        if (rbMultiply.isChecked()) {
                            tvResult.setText(String.valueOf(etNum1 * etNum2));
                        }

                        if (rbDivide.isChecked()) {
                            try {
                                int result = etNum1 / etNum2;
                                tvResult.setText(String.valueOf(result));
                            } catch (ArithmeticException e) {
                                Toast.makeText(v.getContext(), "Aviso: No se puede dividir por cero",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Porfavor ingrese un número válido",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Aviso: Un campo esta vacío",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNumber1.setText("");
                etNumber2.setText("");
                tvResult.setText("0");
            }
        });
    }
}