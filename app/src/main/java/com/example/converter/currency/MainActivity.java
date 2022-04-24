package com.example.converter.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.converter.currency.databinding.ActivityMainBinding;
import com.example.converter.currency.network.APIInterface;
import com.example.converter.currency.network.ApiBuilder;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        String[] names = {
                "Select Currency",
                "AED - UAE Dirham",
                "AFN - Afghan Afghani",
                "ALL - Albanian Lek",
                "AMD - Armenian Dram",
                "ANG - Netherlands Antillean Gulden",
                "AOA - Angolan Kwanza",
                "ARS - Argentine Peso",
                "AUD - Australian Dollar",
                "AWG - Aruban Florin",
                "AZN - Azerbaijani Manat",
                "BAM - Bosnia And Herzegovina Konvertibilna Marka",
                "BBD - Barbadian Dollar",
                "BDT - Bangladeshi Taka",
                "BGN - Bulgarian Lev",
                "BHD - Bahraini Dinar",
                "BIF - Burundi Franc",
                "BND - Brunei Dollar",
                "BOB - Bolivian Boliviano",
                "BRL - Brazilian Real",
                "BSD - Bahamian Dollar",
                "BTC - Bitcoin",
                "BTN - Bhutanese Ngultrum",
                "BWP - Botswana Pula",
                "BYN - New Belarusian Ruble",
                "BYR - Belarusian Ruble",
                "BZD - Belize Dollar",
                "CAD - Canadian Dollar",
                "CDF - Congolese Franc",
                "CHF - Swiss Franc",
                "CLP - Chilean Peso",
                "CNY - Chinese Yuan",
                "COP - Colombian Peso",
                "CRC - Costa Rican Colon",
                "CUP - Cuban Peso",
                "CVE - Cape Verdean Escudo",
                "CZK - Czech Koruna",
                "DJF - Djiboutian Franc",
                "DKK - Danish Krone",
                "DOP - Dominican Peso",
                "DZD - Algerian Dinar",
                "EGP - Egyptian Pound",
                "ERN - Eritrean Nakfa",
                "ETB - Ethiopian Birr",
                "EUR - Euro",
                "FJD - Fijian Dollar",
                "FKP - Falkland Islands Pound",
                "GBP - British Pound",
                "GEL - Georgian Lari",
                "GHS - Ghanaian Cedi",
                "GIP - Gibraltar Pound",
                "GMD - Gambian Dalasi",
                "GNF - Guinean Franc",
                "GTQ - Guatemalan Quetzal",
                "GYD - Guyanese Dollar",
                "HKD - Hong Kong Dollar",
                "HNL - Honduran Lempira",
                "HRK - Croatian Kuna",
                "HTG - Haitian Gourde",
                "HUF - Hungarian Forint",
                "IDR - Indonesian Rupiah",
                "ILS - Israeli New Sheqel",
                "INR - Indian Rupee",
                "IQD - Iraqi Dinar",
                "IRR - Iranian Rial",
                "ISK - Icelandic Króna",
                "JMD - Jamaican Dollar",
                "JOD - Jordanian Dinar",
                "JPY - Japanese Yen",
                "KES - Kenyan Shilling",
                "KGS - Kyrgyzstani Som",
                "KHR - Cambodian Riel",
                "KMF - Comorian Franc",
                "KPW - North Korean Won",
                "KRW - South Korean Won",
                "KWD - Kuwaiti Dinar",
                "KYD - Cayman Islands Dollar",
                "KZT - Kazakhstani Tenge",
                "LAK - Lao Kip",
                "LBP - Lebanese Lira",
                "LKR - Sri Lankan Rupee",
                "LRD - Liberian Dollar",
                "LSL - Lesotho Loti",
                "LVL - Latvian Lats",
                "LYD - Libyan Dinar",
                "MAD - Moroccan Dirham",
                "MDL - Moldovan Leu",
                "MGA - Malagasy Ariary",
                "MKD - Macedonian Denar",
                "MMK - Myanma Kyat",
                "MNT - Mongolian Tugrik",
                "MOP - Macanese Pataca",
                "MRO - Mauritanian Ouguiya",
                "MUR - Mauritian Rupee",
                "MVR - Maldivian Rufiyaa",
                "MWK - Malawian Kwacha",
                "MXN - Mexican Peso",
                "MYR - Malaysian Ringgit",
                "MZN - Mozambican Metical",
                "NAD - Namibian Dollar",
                "NGN - Nigerian Naira",
                "NIO - Nicaraguan Cordoba",
                "NOK - Norwegian Krone",
                "NPR - Nepalese Rupee",
                "NZD - New Zealand Dollar",
                "OMR - Omani Rial",
                "PAB - Panamanian Balboa",
                "PEN - Peruvian Nuevo Sol",
                "PGK - Papua New Guinean Kina",
                "PHP - Philippine Peso",
                "PKR - Pakistani Rupee",
                "PLN - Polish Zloty",
                "PYG - Paraguayan Guarani",
                "QAR - Qatari Riyal",
                "RON - Romanian Leu",
                "RSD - Serbian Dinar",
                "RUB - Russian Ruble",
                "RWF - Rwandan Franc",
                "SAR - Saudi Riyal",
                "SBD - Solomon Islands Dollar",
                "SCR - Seychellois Rupee",
                "SDG - Sudanese Pound",
                "SEK - Swedish Krona",
                "SGD - Singapore Dollar",
                "SHP - Saint Helena Pound",
                "SLL - Sierra Leonean Leone",
                "SOS - Somali Shilling",
                "SRD - Surinamese Dollar",
                "STD - Sao Tome And Principe Dobra",
                "SYP - Syrian Pound",
                "SZL - Swazi Lilangeni",
                "THB - Thai Baht",
                "TJS - Tajikistani Somoni",
                "TMT - Turkmenistan Manat",
                "TND - Tunisian Dinar",
                "TOP - Paanga",
                "TRY - Turkish New Lira",
                "TTD - Trinidad and Tobago Dollar",
                "TWD - New Taiwan Dollar",
                "TZS - Tanzanian Shilling",
                "UAH - Ukrainian Hryvnia",
                "UGX - Ugandan Shilling",
                "USD - United States Dollar",
                "UYU - Uruguayan Peso",
                "UZS - Uzbekistani Som",
                "VEF - Venezuelan Bolivar",
                "VND - Vietnamese Dong",
                "VUV - Vanuatu Vatu",
                "WST - Samoan Tala",
                "XAF - Central African CFA Franc",
                "XCD - East Caribbean Dollar",
                "XDR - Special Drawing Rights",
                "XOF - West African CFA Franc",
                "XPF - CFP Franc",
                "YER - Yemeni Rial",
                "ZAR - South African Rand",
                "ZMW - Zambian Kwacha"};



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item , names);
        activityMainBinding.spinnerTo.setAdapter(adapter);
        activityMainBinding.spinnerFrom.setAdapter(adapter);
        activityMainBinding.progressBar.setVisibility(View.GONE);

        activityMainBinding.buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityMainBinding.progressBar.setVisibility(View.VISIBLE);
                if (activityMainBinding.editTextAmount.getText().toString().isEmpty()){
                    activityMainBinding.progressBar.setVisibility(View.GONE);
                    activityMainBinding.editTextAmount.setError("Kindly Provide Amount to Convert to!");

                } if (activityMainBinding.spinnerFrom.getSelectedItem().toString().equals("Select Currency")){
                    activityMainBinding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Kindly Select Currency to Convert from!", Toast.LENGTH_SHORT).show();
                } if (activityMainBinding.spinnerTo.getSelectedItem().toString().equals("Select Currency")){
                    activityMainBinding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Kindly Select Currency to Convert to!", Toast.LENGTH_SHORT).show();
                }

                else {
                    APIInterface retrofitInterface = ApiBuilder.getRetrofitInstance().create(APIInterface.class);
                    Call<JsonObject> call = retrofitInterface.getExchangeCurrency(activityMainBinding.spinnerFrom.getSelectedItem().toString().substring(0,3));
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                            assert response.body() != null;
                            activityMainBinding.progressBar.setVisibility(View.GONE);
                            Log.d("Response >>>> ", String.valueOf( response.body()));
                            JsonObject jsonObject = response.body();
                            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
                            double currency = Double.valueOf(activityMainBinding.editTextAmount.getText().toString());
                            double multiplier = Double.valueOf(rates.get(activityMainBinding.spinnerTo.getSelectedItem().toString().substring(0,3)).toString());
                            double result = currency * multiplier;
                            activityMainBinding.textViewResults.setVisibility(View.VISIBLE);
                            double roundOffResults = Math.round(result * 100.0) / 100.0;
                            activityMainBinding.textViewResults.setText(String.valueOf("Results: "+activityMainBinding.spinnerTo.getSelectedItem().toString().substring(0,3)+" "+roundOffResults));
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            activityMainBinding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();


                        }
                    });
                }



            }



            }


        );


    }
}