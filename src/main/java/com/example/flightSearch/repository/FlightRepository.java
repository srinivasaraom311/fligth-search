package com.example.flightSearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.flightSearch.model.FlightModel;

public interface FlightRepository extends CrudRepository<FlightModel, Long> {

	@Query("SELECT p FROM FlightModel p WHERE LOWER(p.flightNumber) = LOWER(:flightNumber) AND TRUNC(p.arrival) = TRUNC(:date)")
    public List<FlightModel> findFlightNumberANDDate(@Param("flightNumber") String flightNumber,@Param("date") String date);

	@Query("SELECT p FROM FlightModel p WHERE LOWER(p.origin) = LOWER(:origin) AND LOWER(p.destination) = LOWER(:destination) AND TRUNC(p.arrival) = TRUNC(:date)")
    public List<FlightModel> findByOriginANDDestinationANDDate(@Param("origin") String origin,@Param("destination") String destination,@Param("date") String date);

}
