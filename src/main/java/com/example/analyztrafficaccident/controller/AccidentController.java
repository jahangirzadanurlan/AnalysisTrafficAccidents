package com.example.analyztrafficaccident.controller;

import com.example.analyztrafficaccident.dto.response.ResponseDto;
import com.example.analyztrafficaccident.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccidentController {
    private final AccidentCountByCause accidentCountByCause;
    private final AccidentCountByDay accidentCountByDay;
    private final AccidentCountByHour accidentCountByHour;
    private final AccidentCountByLicenceStatus accidentCountByLicenceStatus;
    private final AccidentCountByPlace accidentCountByPlace;
    private final AccidentCountByVehiceModel accidentCountByVehiceModel;
    private final AccidentCountByDriverAnalysis accidentCountByDriverAnalysis;
    private final InjuryCountByDay injuryCountByDay;

    @GetMapping("/accidentCountByCause")
    public String accidentCountByCause(Model model) {
        String accidentCauseData = accidentCountByCause.getAccidentCauseCount(model);
        return accidentCauseData;
    }

    @GetMapping
    public String homePage(){
        return "causeCount";
    }

    @GetMapping("/accidentCountByDay")
    public String accidentCountByDay(Model model) {
        accidentCountByDay.getChartData(model);
        return "dayCount";
    }

    @GetMapping("/accidentCountByHour")
    public ResponseEntity<ResponseDto> accidentCountByHour(){
        return ResponseEntity.ok().body(accidentCountByHour.getAccidentCountByHour());
    }

    @GetMapping("/accidentCountByLicence")
    public ResponseEntity<ResponseDto> accidentCountByLicence(){
        return ResponseEntity.ok().body(accidentCountByLicenceStatus.getAccidentCountByLisence());
    }

    @GetMapping("/accidentCountByPlace")
    public ResponseEntity<ResponseDto> accidentCountByPlace(){
        return ResponseEntity.ok().body(accidentCountByPlace.getAccidentCountByPlace());
    }

    @GetMapping("/accidentCountByVehicleModel")
    public ResponseEntity<ResponseDto> accidentCountByVehicleModel(){
        return ResponseEntity.ok().body(accidentCountByVehiceModel.getAccidentCountByVehicleModel());
    }

    @GetMapping("/driverAnalysis")
    public ResponseEntity<ResponseDto> accidentCountByDriverAnalysis(){
        return ResponseEntity.ok().body(accidentCountByDriverAnalysis.getDriverStatus());
    }

    @GetMapping("/injuryCountByDay")
    public ResponseEntity<ResponseDto> injuryCountByDay(){
        return ResponseEntity.ok().body(injuryCountByDay.getInjuryCount());
    }
}

