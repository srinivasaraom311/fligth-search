package com.example.flightSearch.controllers;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightSearch.model.FlightModel;
import com.example.flightSearch.model.SearchQueryModel;
import com.example.flightSearch.repository.FlightRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FlightController {
	
	@Autowired
	FlightRepository flightRepo;
	
	@PostMapping("/search")
	public ResponseEntity<List<FlightModel>> search(@Valid @RequestBody SearchQueryModel searchQuery) throws JsonProcessingException {
		
		Calendar c = Calendar.getInstance();
		c.set(searchQuery.getDate().getYear().intValue(), searchQuery.getDate().getMonth().intValue() - 1, searchQuery.getDate().getDay().intValue(), 0, 0);  
		log.info("{}",c.getTime().toInstant());
		log.info(searchQuery.toString());
		List<FlightModel> response;
		if(null!=searchQuery.getFlightNumber())
		{
			response=flightRepo.findFlightNumberANDDate(searchQuery.getFlightNumber(),c.getTime().toInstant().toString());
		}
		else if(null!= searchQuery.getOrigin() && null!= searchQuery.getDestination())
		{
			response=flightRepo.findByOriginANDDestinationANDDate(searchQuery.getOrigin(), searchQuery.getDestination(), c.getTime().toInstant().toString());
		}
		else
		{
			response=null;// means error occured
		}
		
		return new ResponseEntity<List<FlightModel>>(response,HttpStatus.OK);
	}

}
