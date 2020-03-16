package com.maverapp.hw1_guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button validateButton;
    private Button continuarButton;
    private OtpView otpView;
    private TextView lblNumeroIntentos;

    int intNumeroAleatorio = 0;
    int intNumeroIntentos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUi();
        setListeners();
        GenerarNumeroAleatorio();
    }

    private void initializeUi() {
        otpView = findViewById(R.id.otp_view);
        validateButton = findViewById(R.id.validate_button);
        continuarButton = findViewById(R.id.continuar_button);
        lblNumeroIntentos = findViewById(R.id.lblNumeroIntentos);
    }

    private void setListeners() {
        validateButton.setOnClickListener(this);
        //otpView.setOtpCompletionListener(this);

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                Log.d("onOtpCompleted=>", otp);
            }
        });

        continuarButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.validate_button) {
            String strPrueba = otpView.getText().toString();
            if (strPrueba.isEmpty())
                Toast.makeText(this, "Intenta adivinar el Numero Aleatorio, digitando un numero del 0001 al 1000", Toast.LENGTH_LONG).show();
            else {
                if (intNumeroAleatorio < Integer.parseInt(otpView.getText().toString()))
                {
                    Toast.makeText(this, "El valor correcto se encuentra por debajo", Toast.LENGTH_SHORT).show();
                    intNumeroIntentos = intNumeroIntentos + 1;
                    lblNumeroIntentos.setText("Numero de intentos: " + intNumeroIntentos);
                }
                else if (intNumeroAleatorio > Integer.parseInt(otpView.getText().toString()))
                {
                    Toast.makeText(this, "El valor correcto se encuentra por encima", Toast.LENGTH_SHORT).show();
                    intNumeroIntentos = intNumeroIntentos + 1;
                    lblNumeroIntentos.setText("Numero de intentos: " + intNumeroIntentos);
                }
                else
                {
                    Toast.makeText(this, "Felicitaciones acertaste el numero aleatorio", Toast.LENGTH_LONG).show();
                    otpView.setEnabled(false);
                    validateButton.setEnabled(false);
                    continuarButton.setEnabled(true);
                }
            }

        }
        else if (view.getId() == R.id.continuar_button)
        {
            otpView.setEnabled(true);
            GenerarNumeroAleatorio();
            intNumeroIntentos = 0;
            lblNumeroIntentos.setText("Numero de intentos:" + intNumeroIntentos);
            otpView.setEnabled(true);
            validateButton.setEnabled(true);
            continuarButton.setEnabled(false);
        }

    }

    /*
    @Override
    public void onOtpCompleted(String otp) {
        Toast.makeText(this, "OnOtpCompletionListener called", Toast.LENGTH_SHORT).show();
    }
     */

    private void GenerarNumeroAleatorio(){
        intNumeroAleatorio = (int) (Math.random() * 1000) + 1;

        //Toast.makeText(this, "Numero aleatorio 1 es: " + intNumeroAleatorio, Toast.LENGTH_SHORT).show();

        Random aleatorio = new Random(System.currentTimeMillis());
        // Producir nuevo int aleatorio entre 0 y 1000
        intNumeroAleatorio = aleatorio.nextInt(1001);

        //Toast.makeText(this, "Numero aleatorio 2 es: " + intNumeroAleatorio, Toast.LENGTH_SHORT).show();
    }

}
