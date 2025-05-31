package com.example.mangalfera.controller;

import com.example.mangalfera.dto.CityDto;
import com.example.mangalfera.dto.CountryDTO;
import com.example.mangalfera.dto.PincodeDto;
import com.example.mangalfera.dto.StateDto;
import com.example.mangalfera.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/countries")
    public List<CountryDTO> getCountries() {
        return locationService.getAllCountries()
                .stream()
                .map(country -> new CountryDTO(country.getId(), country.getName()))
                .collect(Collectors.toList());
    }
    @GetMapping("/states/{countryId}")
    public List<StateDto> getStatesByCountry(@PathVariable Long countryId) {
        return locationService.getStatesByCountryId(countryId)
                .stream()
                .map(s -> new StateDto(s.getId(), s.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/cities/{stateId}")
    public List<CityDto> getCitiesByState(@PathVariable Long stateId) {
        return locationService.getCitiesByStateId(stateId)
                .stream()
                .map(c -> new CityDto(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }


    @GetMapping("/pincodes/{cityId}")
    public List<PincodeDto> getPincodesByCity(@PathVariable Long cityId) {
        return locationService.getPincodesByCityId(cityId)
                .stream()
                .map(p -> new PincodeDto(p.getId(), p.getCode()))
                .collect(Collectors.toList());
    }


}
