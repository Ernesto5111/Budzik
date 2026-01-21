package com.example.budzik;


import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btnPickDateTime;
    private TextView tvSelectedDateTime;

    // Zmienne do przechowywania wyboru użytkownika
    private int selectedYear, selectedMonth, selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPickDateTime = findViewById(R.id.btnPickDateTime);
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime);

        btnPickDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
    }

//    // 1. Metoda otwierająca Kalendarz
//    private void showDatePicker() {
//        // Pobieramy aktualną datę, żeby ustawić ją jako domyślną w dialogu
//        final Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                this,
//                new DatePickerDialog.OnDateSetListener() {
1//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        // Zapisujemy wybraną datę do zmiennych
//                        selectedYear = year;
//                        selectedMonth = monthOfYear; // Pamiętaj: 0-11
//                        selectedDay = dayOfMonth;
//
//                        // Po wybraniu daty, automatycznie otwieramy zegar
//                        showTimePicker();
//                    }
//                },
//                year, month, day // Wartości domyślne (startowe)
//        );
//
//        datePickerDialog.show();
//    }

    // 2. Metoda otwierająca Zegar
    private void showTimePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Tutaj mamy już komplet danych: Rok, Miesiąc, Dzień, Godzinę i Minutę

                        // Tworzymy obiekt LocalDateTime (miesiąc + 1 dla LocalDate!)
                        LocalDateTime dateTime = LocalDateTime.of(year, month + 1, day, hourOfDay, minute);

                        // Formatujemy i wyświetlamy
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        Toast.makeText(MainActivity.this, "Ustawiono budzik na:" +dateTime.format(formatter), Toast.LENGTH_LONG).show();
//                        tvSelectedDateTime.setText("Umówiono: " + dateTime.format(formatter));
                    }
                },
                hour, minute, true // true = format 24h
        );

        timePickerDialog.show();
    }
}