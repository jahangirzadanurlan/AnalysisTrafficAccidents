package com.example.analyztrafficaccident.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class AccidentCountByDay {
    public void getChartData(Model model) { // Model Attribute olarak güncelledik
        String dosya_yolu = "C:\\Users\\cahan\\IdeaProjects\\readCsvFile\\src\\Crash_Reporting_-_Drivers_Data.csv";
        Map<String, Integer> kazaSayilari = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dosya_yolu))) {
            String satir;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

            // Header satırını atlayın
            bufferedReader.readLine();
            while ((satir = bufferedReader.readLine()) != null) {
                String[] sutunlar = satir.split(";"); // Sütunları ayırmak için uygun ayırıcıyı belirtin

                if (sutunlar.length > 4) {
                    String tarihMetni = sutunlar[4].trim(); // Tarihlerin olduğu sütunun indeksini belirtin ve boşlukları kaldırın

                    try {
                        LocalDate tarih = LocalDate.parse(tarihMetni, formatter);
                        String formattedDate = tarih.toString(); // Tarihi string formatına çevirin

                        kazaSayilari.put(formattedDate, kazaSayilari.getOrDefault(formattedDate, 0) + 1);
                    } catch (DateTimeParseException e) {
                        //System.err.println("Geçersiz tarih değeri: " + tarihMetni);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, Integer>> sortedAccidentCauses = new ArrayList<>(kazaSayilari.entrySet());
        sortedAccidentCauses.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        // Create a new LinkedHashMap to maintain the sorted order
        Map<String, Integer> sortedAccidentCauseCount = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedAccidentCauses) {
            sortedAccidentCauseCount.put(entry.getKey(), entry.getValue());
        }

        model.addAttribute("accidentCountDay", sortedAccidentCauseCount); // Model Attribute olarak verileri ekle
    }
}
