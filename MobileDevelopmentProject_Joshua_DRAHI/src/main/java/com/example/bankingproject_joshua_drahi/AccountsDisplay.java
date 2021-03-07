package com.example.bankingproject_joshua_drahi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ByteArrayPool;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.internal.ContextUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public class AccountsDisplay extends AppCompatActivity {


    private ArrayList<TextView> accountNames = new ArrayList<TextView>();
    private ArrayList<TextView> ibans = new ArrayList<TextView>();
    private ArrayList<TextView> amounts = new ArrayList<TextView>();
    private ArrayList<TextView> currencies = new ArrayList<TextView>();
    
    // Instantiate all the accountNames TextViews.
    private TextView accountName1;
    private TextView accountName2;
    private TextView accountName3;
    private TextView accountName4;
    private TextView accountName5;
    private TextView accountName6;
    private TextView accountName7;
    private TextView accountName8;
    private TextView accountName9;
    private TextView accountName10;
    private TextView accountName11;
    private TextView accountName12;
    private TextView accountName13;
    private TextView accountName14;
    private TextView accountName15;
    
    // Instantiate all the amounts TextViews.
    private TextView iban1;
    private TextView iban2;
    private TextView iban3;
    private TextView iban4;
    private TextView iban5;
    private TextView iban6;
    private TextView iban7;
    private TextView iban8;
    private TextView iban9;
    private TextView iban10;
    private TextView iban11;
    private TextView iban12;
    private TextView iban13;
    private TextView iban14;
    private TextView iban15;
    
    // Instantiate all the ibans TextViews.
    private TextView amount1;
    private TextView amount2;
    private TextView amount3;
    private TextView amount4;
    private TextView amount5;
    private TextView amount6;
    private TextView amount7;
    private TextView amount8;
    private TextView amount9;
    private TextView amount10;
    private TextView amount11;
    private TextView amount12;
    private TextView amount13;
    private TextView amount14;
    private TextView amount15;
    
    // Instantiate all the currencies TextViews.
    private TextView currency1;
    private TextView currency2;
    private TextView currency3;
    private TextView currency4;
    private TextView currency5;
    private TextView currency6;
    private TextView currency7;
    private TextView currency8;
    private TextView currency9;
    private TextView currency10;
    private TextView currency11;
    private TextView currency12;
    private TextView currency13;
    private TextView currency14;
    private TextView currency15;
    
    // Instantiate the RequestQueue.
    private RequestQueue queue;
    private String url = "https://60102f166c21e10017050128.mockapi.io/accounts";
    private String data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_display);
        FileInputStream fis = null;
        try {
            fis = openFileInput("storedData");
            File file = new File(getFilesDir(), "storedData");
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                // Error occurred when opening raw file for reading.
            } finally {
                data = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        addToLists();
    }

    public void update(View view) {
        if(data==null) {
            queue = Volley.newRequestQueue(this);
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ArrayList<Account> allAccounts = new ArrayList<Account>();
                            response = response.substring(1, (response.length() - 1));
                            String filename = "storedData";
                            String fileContents = response;
                            byte[] bob = new byte[0];
                            ByteArrayOutputStream lambda = new ByteArrayOutputStream();
                            try (FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE)) {
                                fos.write(fileContents.getBytes());
                            }catch(Exception e){

                            }


                            String[] responseArray = response.split("\\}");
                            response = "";

                            for (String responsePart : responseArray) {
                                Account currentAccount = new Account(responsePart.substring(1, (responsePart.length() - 1)));
                                allAccounts.add(currentAccount);
                            }
                            int i = 0;
                            for (Account account : allAccounts) {
                                accountNames.get(i).setText("Account Name: " + account.getAccountName());
                                ibans.get(i).setText("IBAN: " + account.getIban());
                                amounts.get(i).setText(account.getAmountStr());
                                currencies.get(i).setText("Currency: " + account.getCurrency());
                                i++;
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toast.makeText(getApplicationContext(), "It didn't work", Toast.LENGTH_SHORT);
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
            if(data == null || data == ""){
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                CharSequence text = "Unable to connect to API, nor retrieve local data.";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        else{
            ArrayList<Account> allAccounts = new ArrayList<Account>();
            String[] responseArray = data.split("\\}");
            ArrayList<String> responseArrayList = new ArrayList<String>();
            for(int i = 0; i < responseArray.length - 1; i++)
                responseArrayList.add(responseArray[i]);
            for (String responsePart : responseArrayList) {
                if(responsePart != "\n") {
                    Account currentAccount = new Account(responsePart.substring(1, (responsePart.length() - 1)));
                    allAccounts.add(currentAccount);
                }
            }
            int i = 0;
            for (Account account : allAccounts) {
                accountNames.get(i).setText("Account Name: " + account.getAccountName());
                ibans.get(i).setText("IBAN: " + account.getIban());
                amounts.get(i).setText(account.getAmountStr());
                currencies.get(i).setText("Currency: " + account.getCurrency());
                i++;
            }
        }
    }

    private void addToLists()
    {
        accountName1 = findViewById(R.id.accountName1);
        accountName2 = findViewById(R.id.accountName2);
        accountName3 = findViewById(R.id.accountName3);
        accountName4 = findViewById(R.id.accountName4);
        accountName5 = findViewById(R.id.accountName5);
        accountName6 = findViewById(R.id.accountName6);
        accountName7 = findViewById(R.id.accountName7);
        accountName8 = findViewById(R.id.accountName8);
        accountName9 = findViewById(R.id.accountName9);
        accountName10 = findViewById(R.id.accountName10);
        accountName11 = findViewById(R.id.accountName11);
        accountName12 = findViewById(R.id.accountName12);
        accountName13 = findViewById(R.id.accountName13);
        accountName14 = findViewById(R.id.accountName14);
        accountName15 = findViewById(R.id.accountName15);
        
        iban1 = findViewById(R.id.iban1);
        iban2 = findViewById(R.id.iban2);
        iban3 = findViewById(R.id.iban3);
        iban4 = findViewById(R.id.iban4);
        iban5 = findViewById(R.id.iban5);
        iban6 = findViewById(R.id.iban6);
        iban7 = findViewById(R.id.iban7);
        iban8 = findViewById(R.id.iban8);
        iban9 = findViewById(R.id.iban9);
        iban10 = findViewById(R.id.iban10);
        iban11 = findViewById(R.id.iban11);
        iban12 = findViewById(R.id.iban12);
        iban13 = findViewById(R.id.iban13);
        iban14 = findViewById(R.id.iban14);
        iban15 = findViewById(R.id.iban15);
        
        amount1 = findViewById(R.id.amount1);
        amount2 = findViewById(R.id.amount2);
        amount3 = findViewById(R.id.amount3);
        amount4 = findViewById(R.id.amount4);
        amount5 = findViewById(R.id.amount5);
        amount6 = findViewById(R.id.amount6);
        amount7 = findViewById(R.id.amount7);
        amount8 = findViewById(R.id.amount8);
        amount9 = findViewById(R.id.amount9);
        amount10 = findViewById(R.id.amount10);
        amount11 = findViewById(R.id.amount11);
        amount12 = findViewById(R.id.amount12);
        amount13 = findViewById(R.id.amount13);
        amount14 = findViewById(R.id.amount14);
        amount15 = findViewById(R.id.amount15);
        
        currency1 = findViewById(R.id.currency1);
        currency2 = findViewById(R.id.currency2);
        currency3 = findViewById(R.id.currency3);
        currency4 = findViewById(R.id.currency4);
        currency5 = findViewById(R.id.currency5);
        currency6 = findViewById(R.id.currency6);
        currency7 = findViewById(R.id.currency7);
        currency8 = findViewById(R.id.currency8);
        currency9 = findViewById(R.id.currency9);
        currency10 = findViewById(R.id.currency10);
        currency11 = findViewById(R.id.currency11);
        currency12 = findViewById(R.id.currency12);
        currency13 = findViewById(R.id.currency13);
        currency14 = findViewById(R.id.currency14);
        currency15 = findViewById(R.id.currency15);

        accountName1.setText("");
        accountName2.setText("");
        accountName3.setText("");
        accountName4.setText("");
        accountName5.setText("");
        accountName6.setText("");
        accountName7.setText("");
        accountName8.setText("");
        accountName9.setText("");
        accountName10.setText("");
        accountName11.setText("");
        accountName12.setText("");
        accountName13.setText("");
        accountName14.setText("");
        accountName15.setText("");

        iban1.setText("");
        iban2.setText("");
        iban3.setText("");
        iban4.setText("");
        iban5.setText("");
        iban6.setText("");
        iban7.setText("");
        iban8.setText("");
        iban9.setText("");
        iban10.setText("");
        iban11.setText("");
        iban12.setText("");
        iban13.setText("");
        iban14.setText("");
        iban15.setText("");

        amount1.setText("");
        amount2.setText("");
        amount3.setText("");
        amount4.setText("");
        amount5.setText("");
        amount6.setText("");
        amount7.setText("");
        amount8.setText("");
        amount9.setText("");
        amount10.setText("");
        amount11.setText("");
        amount12.setText("");
        amount13.setText("");
        amount14.setText("");
        amount15.setText("");

        currency1.setText("");
        currency2.setText("");
        currency3.setText("");
        currency4.setText("");
        currency5.setText("");
        currency6.setText("");
        currency7.setText("");
        currency8.setText("");
        currency9.setText("");
        currency10.setText("");
        currency11.setText("");
        currency12.setText("");
        currency13.setText("");
        currency14.setText("");
        currency15.setText("");
        
        accountNames.add(accountName1);
        accountNames.add(accountName2);
        accountNames.add(accountName3);
        accountNames.add(accountName4);
        accountNames.add(accountName5);
        accountNames.add(accountName6);
        accountNames.add(accountName7);
        accountNames.add(accountName8);
        accountNames.add(accountName9);
        accountNames.add(accountName10);
        accountNames.add(accountName11);
        accountNames.add(accountName12);
        accountNames.add(accountName13);
        accountNames.add(accountName14);
        accountNames.add(accountName15);
        ibans.add(iban1);
        ibans.add(iban2);
        ibans.add(iban3);
        ibans.add(iban4);
        ibans.add(iban5);
        ibans.add(iban6);
        ibans.add(iban7);
        ibans.add(iban8);
        ibans.add(iban9);
        ibans.add(iban10);
        ibans.add(iban11);
        ibans.add(iban12);
        ibans.add(iban13);
        ibans.add(iban14);
        ibans.add(iban15);
        amounts.add(amount1);
        amounts.add(amount2);
        amounts.add(amount3);
        amounts.add(amount4);
        amounts.add(amount5);
        amounts.add(amount6);
        amounts.add(amount7);
        amounts.add(amount8);
        amounts.add(amount9);
        amounts.add(amount10);
        amounts.add(amount11);
        amounts.add(amount12);
        amounts.add(amount13);
        amounts.add(amount14);
        amounts.add(amount15);
        currencies.add(currency1);
        currencies.add(currency2);
        currencies.add(currency3);
        currencies.add(currency4);
        currencies.add(currency5);
        currencies.add(currency6);
        currencies.add(currency7);
        currencies.add(currency8);
        currencies.add(currency9);
        currencies.add(currency10);
        currencies.add(currency11);
        currencies.add(currency12);
        currencies.add(currency13);
        currencies.add(currency14);
        currencies.add(currency15);
    }
}