package com.course.practicaljava.rest.service;

import java.util.List;

import com.course.practicaljava.rest.domain.Car;

public interface CarService {
	List<String> BRANDS = List.of("Toyota", "Honda", "Ford");
	List<String> COLORS = List.of("Red", "Black", "White");
	List<String> TYPES = List.of("Sedan", "SUV", "MPV");
	List<String> ADDITONAL_FEATURES = List.of("GPS", "Alarm", "Sunroof", "Media Player", "Leather Seat");

	Car generateCar();
}
