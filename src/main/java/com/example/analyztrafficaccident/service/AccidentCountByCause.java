package com.example.analyztrafficaccident.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class AccidentCountByCause {
    public String getAccidentCauseCount(Model model) {
        String dosya_yolu = "C:\\Users\\cahan\\IdeaProjects\\readCsvFile\\src\\Crash_Reporting_-_Drivers_Data.csv";
        Map<String, Integer> accidentCauseCount = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya_yolu))) {
            String satir;

            // Header satırını atlayın
            bufferedReader.readLine();

            while ((satir = bufferedReader.readLine()) != null) {
                String[] sutunlar = satir.split(";");
                if (sutunlar.length >= 13) {
                    String collisionType = sutunlar[12].trim();
                    String weather = sutunlar[13].trim();
                    String surfaceCondition = sutunlar[14].trim();
                    String light = sutunlar[15].trim();
                    String trafficControl = sutunlar[16].trim();

                    String accidentCause = collisionType + " - " + weather + " - " + surfaceCondition + " - " + light + " - " + trafficControl;
                    accidentCauseCount.put(accidentCause, accidentCauseCount.getOrDefault(accidentCause, 0) + 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort the map in descending order by values
        List<Map.Entry<String, Integer>> sortedAccidentCauses = new ArrayList<>(accidentCauseCount.entrySet());
        sortedAccidentCauses.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        // Create a new LinkedHashMap to maintain the sorted order
        Map<String, Integer> sortedAccidentCauseCount = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedAccidentCauses) {
            sortedAccidentCauseCount.put(entry.getKey(), entry.getValue());
        }
        // Verileri JSON formatına dönüştürün
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = "{}"; // Boş JSON nesnesi
        try {
            jsonResult = objectMapper.writeValueAsString(accidentCauseCount);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // Model verisini güncelle
        model.addAttribute("accidentCauseData", jsonResult);
        model.addAttribute("accidentCauseEntry", sortedAccidentCauses);

        return "causeCount";

    }
}