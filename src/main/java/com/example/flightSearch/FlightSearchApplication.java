package com.example.flightSearch;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.flightSearch.model.FlightModel;
import com.example.flightSearch.repository.FlightRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class FlightSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FlightRepository flightRepo) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<FlightModel>> typeReference = new TypeReference<List<FlightModel>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/FlightModels.json");
			try {
				List<FlightModel> FlightModels = mapper.readValue(inputStream,typeReference);
				FlightModels.forEach(e->{
					flightRepo.save(e);
				});
				
				System.out.println("FlightModels Saved!");
			} catch (IOException e){
				System.out.println("Unable to save FlightModels: " + e.getMessage());
			}
		};
	}
	
}
