package com.example.mangalfera.service;

import com.example.mangalfera.model.City;
import com.example.mangalfera.model.Country;
import com.example.mangalfera.model.Pincode;
import com.example.mangalfera.model.State;
import com.example.mangalfera.repository.CityRepository;
import com.example.mangalfera.repository.CountryRepository;
import com.example.mangalfera.repository.PincodeRepository;
import com.example.mangalfera.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private CountryRepository countryRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private PincodeRepository pincodeRepo;

    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    public List<State> getStatesByCountryId(Long countryId) {
        return stateRepo.findByCountryId(countryId);
    }

    public List<City> getCitiesByStateId(Long stateId) {
        return cityRepo.findByStateId(stateId);
    }

    public List<Pincode> getPincodesByCityId(Long cityId) {
        return pincodeRepo.findByCityId(cityId);
    }

}
