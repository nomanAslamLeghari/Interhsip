package com.noman.interhsip;

import retrofit2.Call;
import retrofit2.http.GET;


public interface CurrencyExchangeService {
    @GET("latest?base=USD")
    Call<CurrencyExchange> loadCurrencyExchange();
}
