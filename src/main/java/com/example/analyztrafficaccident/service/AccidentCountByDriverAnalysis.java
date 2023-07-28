package com.example.analyztrafficaccident.service;

import com.example.analyztrafficaccident.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class AccidentCountByDriverAnalysis {
    public ResponseDto getDriverStatus(){
        String dosya_yolu = "C:\\Users\\cahan\\IdeaProjects\\readCsvFile\\src\\Crash_Reporting_-_Drivers_Data.csv";
        Map<String, Integer> driverStatusList = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya_yolu))) {
            String satir;
            bufferedReader.readLine();

            while ((satir = bufferedReader.readLine()) != null) {
                String[] sutunlar = satir.split(";");

                if (sutunlar.length > 16) {
                    String driverSubstanceAbuse = sutunlar[17].trim();
                    String nonMotoristSubstanceAbuse = sutunlar[18].trim();
                    String driverDistractedBy = sutunlar[23].trim();

                    String driverStatus="Driver Substance Abuse: "+driverSubstanceAbuse + " - " + "Non Motorist Substance Abuse: "+nonMotoristSubstanceAbuse + " - " + "Driver Distracted By: "+driverDistractedBy;
                    driverStatusList.put(driverStatus,(driverStatusList.getOrDefault(driverStatus,0)+1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseDto().getResponseDto(driverStatusList);
    }
}
