package com.example.analyztrafficaccident.service;

import com.example.analyztrafficaccident.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class AccidentCountByPlace {
    public ResponseDto getAccidentCountByPlace(){
        String dosya_yolu = "C:\\Users\\cahan\\IdeaProjects\\readCsvFile\\src\\Crash_Reporting_-_Drivers_Data.csv";
        TreeMap<String,Integer> accidentPlaces=new TreeMap<>();

        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(dosya_yolu))) {
            String satir;
            bufferedReader.readLine();

            while ((satir=bufferedReader.readLine())!=null){
                String[] sutunlar=satir.split(";");

                if (sutunlar.length>4){
                    String routeType=sutunlar[5].trim();
                    String roadName=sutunlar[6].trim();
                    String crossStreetType=sutunlar[7].trim();
                    String crossStreetName=sutunlar[8].trim();

                    String place = routeType + " - " + roadName + " - " + crossStreetType + " - " + crossStreetName;
                    accidentPlaces.put(place,(accidentPlaces.getOrDefault(place,0)+1));
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseDto().getResponseDto(accidentPlaces);
    }
}
