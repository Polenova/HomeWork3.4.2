package ru.android.polenova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button buttonOK;
    private Spinner spinnerLanguage;
    private Spinner spinnerTheme;
    private Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(MainActivity.this);
        setContentView(R.layout.activity_main);
        initView();
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (spinnerLanguage.getSelectedItem().toString()) {
                    case "Србски":
                        locale = new Locale("sr");
                        break;
                    case "English":
                        locale = new Locale("en");
                        break;
                    case "Русский":
                        locale = new Locale("ru");
                        break;
                }
                Configuration config = new Configuration();
                config.setLocale(locale);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
                String stringTheme = spinnerTheme.getSelectedItem().toString();
                switch (spinnerTheme.getSelectedItem().toString()) {
                    case "Маленький":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_SMALL);
                        break;
                    case "Средний":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_MEDIUM);
                        break;
                    case "Большой":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BIG);
                        break;
                }
            }
        });

    }

    private void initView() {
        spinnerLanguage = findViewById(R.id.spinnerLang);
        spinnerTheme = findViewById(R.id.spinnerTheme);
        buttonOK = findViewById(R.id.buttonOK);
        initSpinnerLanguage();
        initSpinnerTheme();
    }

    private void initSpinnerTheme() {
        ArrayAdapter<CharSequence> adapterTheme = ArrayAdapter.createFromResource(this, R.array.Theme, android.R.layout.simple_spinner_item);
        adapterTheme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTheme.setAdapter(adapterTheme);
    }

    private void initSpinnerLanguage() {
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapterLanguage);
    }
}
