package com.alsalam.userapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.alsalam.userapp.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Payment extends AppCompatActivity implements PaymentResultListener {

    private String Name,Email,Phone;
    String AMOUNT,TYPE;
    AppCompatButton pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);

        pay = findViewById(R.id.pay);

        Bundle bundle = getIntent().getExtras();
        AMOUNT = bundle.getString("AMOUNT");
        TYPE = bundle.getString("TYPE");
        Name = bundle.getString("Name");
        Email = bundle.getString("Email");
        Phone = bundle.getString("Phone");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartPayment(TYPE,AMOUNT);
            }
        });

    }



    public void StartPayment(String product,String amt) {
        String appName = getApplicationName(getApplicationContext());
        float amount = Float.parseFloat(amt)*100;
        String s = String.format("%.2f", amount);
        Checkout checkout = new Checkout();

        checkout.setImage(R.drawable.ic_launcher_foreground);  // add the actual app logo here

        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();


            options.put("name", appName);

            options.put("description", product);

            options.put("currency", "INR");
            options.put("data-prefill.name", Name);

            JSONObject preFill = new JSONObject();
            preFill.put("email", Email);
            preFill.put("contact", Phone);
            options.put("prefill", preFill);

            options.put("amount", s);

            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }
    @Override
    public void onPaymentSuccess(String s) {
        finish();
        Toast.makeText(getApplicationContext(), "Payment successful", Toast.LENGTH_SHORT).show();

        // Update the data to firebase that this student has paid his fee
    }


    @Override
    public void onPaymentError(int i, String s) {
        finish();
        Toast.makeText(getApplicationContext(), "Payment not successful", Toast.LENGTH_SHORT).show();

    }
    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }
}

