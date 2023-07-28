package com.example.analyztrafficaccident.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto {
    Map<String,Integer> accidentMap;

    public ResponseDto getResponseDto(Map<String, Integer> accidetMap) {
        List<Map.Entry<String, Integer>> sortedMap = new ArrayList<>(accidetMap.entrySet());
        sortedMap.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        Map<String, Integer> sortedAccident = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedMap) {
            sortedAccident.put(entry.getKey(), entry.getValue());
        }

        return new ResponseDto(sortedAccident);
    }
}
