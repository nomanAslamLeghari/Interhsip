package com.noman.interhsip;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExchange {



    private String base;
    private String date;

    private RatesEntity rates;

    public List<Currency> getCurrencyList(){
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(new Currency("AUD", rates.getAUD()));
        currencyList.add(new Currency("BGN", rates.getBGN()));
        currencyList.add(new Currency("BRL", rates.getBRL()));

        return currencyList;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RatesEntity getRates() {
        return rates;
    }

    public void setRates(RatesEntity rates) {
        this.rates = rates;
    }

    public static class RatesEntity {
        private double AUD;
        private double BGN;
        private double BRL;

        public double getAUD() {
            return AUD;
        }

        public void setAUD(double AUD) {
            this.AUD = AUD;
        }

        public double getBGN() {
            return BGN;
        }

        public void setBGN(double BGN) {
            this.BGN = BGN;
        }

        public double getBRL() {
            return BRL;
        }

        public void setBRL(double BRL) {
            this.BRL = BRL;
        }
    }
}