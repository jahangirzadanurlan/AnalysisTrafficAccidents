package com.example.analyztrafficaccident.service;

import com.example.analyztrafficaccident.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class InjuryCountByDay {
    public ResponseDto getInjuryCount(){
        String dosya_yolu = "C:\\Users\\cahan\\IdeaProjects\\readCsvFile\\src\\Crash_Reporting_-_Drivers_Data.csv";
        Map<String, Integer> injurySeverities = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya_yolu))) {
            String satir;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            bufferedReader.readLine();

            while ((satir = bufferedReader.readLine()) != null) {
                String[] sutunlar = satir.split(";");

                if (sutunlar.length > 21) {
                    String tarihMetni = sutunlar[4].trim();

                    try {
                        LocalDate tarih = LocalDate.parse(tarihMetni, formatter);
                        String injurySeverity = sutunlar[21].trim();
                        String data = tarih + ": " + injurySeverity;
                        injurySeverities.put(data, (injurySeverities.getOrDefault(data, 0) + 1));

                    } catch (Exception e) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseDto().getResponseDto(injurySeverities);
    }
}
