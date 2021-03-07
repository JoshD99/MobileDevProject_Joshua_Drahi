package com.example.bankingproject_joshua_drahi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private String password;
    private EditText password_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connect(View view){
        boolean success = false;
        try {
            success = checkRequired();
        }
        catch (Exception e){
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "No MD5...";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if (success == true){
            Intent intent = new Intent(this,AccountsDisplay.class);
            startActivity(intent);
        }
        else{
            //Intent intent = new Intent(this,AccountsDisplay.class);
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "Invalid password";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private boolean checkRequired() throws NoSuchAlgorithmException {
        password_field = findViewById(R.id.login_field);
        password = password_field.getText().toString();;
        password.toUpperCase();
        String hash = "40C3775ED86E79AB86934BB7B4F3C1CD";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = bytesToHex(digest, "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII)).toUpperCase();
        return hash.equals(myHash);
    }

    public static String bytesToHex(byte[] bytes, byte[] HEX_ARRAY) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}