package com.example.analyztrafficaccident.service;

import com.example.analyztrafficaccident.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class AccidentCountByLicenceStatus {
    public ResponseDto getAccidentCountByLisence(){
        String dosya_yolu = "C:\\Users\\cahan\\IdeaProjects\\readCsvFile\\src\\Crash_Reporting_-_Drivers_Data.csv";
        Map<String, Integer> licenceAnalyze = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya_yolu))) {
            String satir;
            bufferedReader.readLine();

            while ((satir = bufferedReader.readLine()) != null) {
                String[] sutunlar = satir.split(";");

                if (sutunlar.length > 24) {
                    String licence = sutunlar[24].trim();

                    licenceAnalyze.put(licence,licenceAnalyze.getOrDefault(licence,0)+1);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseDto().getResponseDto(licenceAnalyze);
    }

}
