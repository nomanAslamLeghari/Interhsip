package com.noman.interhsip;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements Callback<CurrencyExchange>, CurrencyItemClickListener {

    //private ListView lvCurrency;
    //private TextView dat;

    @BindView (R.id.lvCurrency) ListView lvCurrency;
    @Nullable
    @BindView (R.id.textView_date) TextView dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //lvCurrency = (ListView) findViewById(R.id.lvCurrency);
        //dat = (TextView) findViewById(R.id.TextView_date);


        if(dat != null){

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    //your method
                    loadCurrencyExchangeData();

                }
            }, 0, 2000);
        }



    }
    public void debugMsg(String msg) {
        final String str = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dat.setText(str);
            }
        });
    }

    @Override

    protected void onStart() {
        super.onStart();
        loadCurrencyExchangeData();

    }

    public void loadCurrencyExchangeData(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        CurrencyExchangeService service = retrofit.create(CurrencyExchangeService.class);
        Call<CurrencyExchange> call = service.loadCurrencyExchange();
        call.enqueue(this);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        //to convert Date to String, use format method of SimpleDateFormat class.
        String strDate = dateFormat.format(date);



        debugMsg(strDate);

    }

    @Override
    public void onResponse(Call<CurrencyExchange> call, Response<CurrencyExchange> response) {
        CurrencyExchange currencyExchange = response.body();
        lvCurrency.setAdapter(new CurrencyAdapter(this, currencyExchange.getCurrencyList(), this));
    }

    @Override
    public void onFailure(Call<CurrencyExchange> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
    }


}
