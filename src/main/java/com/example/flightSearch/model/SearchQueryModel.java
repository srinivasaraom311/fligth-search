package com.example.flightSearch.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchQueryModel {
	
	private String destination;
	private String flightNumber;
	private String origin;
	private Date date;

}
