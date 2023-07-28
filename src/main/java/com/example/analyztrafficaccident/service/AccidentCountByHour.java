package com.example.analyztrafficaccident.service;

import com.example.analyztrafficaccident.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class AccidentCountByHour {
    public ResponseDto getAccidentCountByHour() {
        String dosya_yolu = "C:\\Users\\cahan\\IdeaProjects\\readCsvFile\\src\\Crash_Reporting_-_Drivers_Data.csv";
        Map<String, Integer> accidentTimeMap = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya_yolu))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String satir;
            bufferedReader.readLine();

            while ((satir = bufferedReader.readLine()) != null) {
                String[] sutunlar = satir.split(";");

                if (sutunlar.length > 4) {
                    String time = sutunlar[4].trim();
                    try {
                        LocalDateTime accidentTime = LocalDateTime.parse(time, formatter);
                        int saat = accidentTime.getHour();
                        String key=saat + ":00 - " + (saat+1) + ":00";

                        accidentTimeMap.put(key, (accidentTimeMap.getOrDefault(key, 0) + 1));
                    } catch (DateTimeParseException e) {

                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseDto().getResponseDto(accidentTimeMap);

    }
}
