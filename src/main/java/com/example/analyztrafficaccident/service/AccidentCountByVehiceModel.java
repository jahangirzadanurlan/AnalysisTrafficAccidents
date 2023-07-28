package com.example.analyztrafficaccident.service;

import com.example.analyztrafficaccident.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class AccidentCountByVehiceModel {
    public ResponseDto getAccidentCountByVehicleModel(){
        String dosya_yolu = "C:\\Users\\cahan\\IdeaProjects\\readCsvFile\\src\\Crash_Reporting_-_Drivers_Data.csv";
        Map<String, Integer> vehicleAnalysis = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya_yolu))) {
            String satir;
            bufferedReader.readLine();

            while ((satir = bufferedReader.readLine()) != null) {
                String[] sutunlar = satir.split(";");

                if (sutunlar.length > 36) {
                    String vehicleYear = sutunlar[36].trim();
                    String vehicleMake = sutunlar[37].trim();
                    String vehicleModel = sutunlar[38].trim();

                    String data = vehicleYear + " " + vehicleMake + " " + vehicleModel;
                    vehicleAnalysis.put(data, vehicleAnalysis.getOrDefault(data, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseDto().getResponseDto(vehicleAnalysis);
    }
}
