package com.course.practicaljava.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.practicaljava.repository.CarElasticRepository;
import com.course.practicaljava.rest.domain.Car;
import com.course.practicaljava.rest.domain.ErrorResponse;
import com.course.practicaljava.rest.service.CarService;

@RestController
@RequestMapping("/api/car/v1")
public class CarRestController {

	@Autowired
	private CarService carService;

	@Autowired
	private CarElasticRepository carElasticRepository;

	private Random random = new Random();

	private Logger log = LoggerFactory.getLogger(CarRestController.class);

//	@RequestMapping(path = "/random", method = RequestMethod.GET) //Same meaning as below
	@GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_VALUE)

	public Car random() {
		return carService.generateCar();
	}

	@PostMapping(path = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String echo(@RequestBody Car car) {
		log.info("The car is : " + car);
		return car.toString();
	}

	@GetMapping(path = "/random-cars", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Car> randomCars() {
		var result = new ArrayList<Car>();

		for (int i = 0; i < random.nextInt(6); i++) {
			result.add(carService.generateCar());
		}

		return result;
	}

	@GetMapping(path = "/cars/count")
	public long countCar() {
		return carElasticRepository.count();
	}

	@PostMapping(path = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Car createCar(@RequestBody Car car) {
		return carElasticRepository.save(car);
	}

	@GetMapping(path = "/cars/{id}")
	public Car findCarsById(@PathVariable String id) {
		return carElasticRepository.findById(id).orElse(null);
	}

	@PutMapping(path = "/cars/{id}")
	public Car updateCarById(@PathVariable String id, @RequestBody Car updatedCar) {
		updatedCar.setId(id);
		return carElasticRepository.save(updatedCar);
	}

	@GetMapping(path = "/cars/{brand}/{color}")
	public ResponseEntity<Object> findCarsByPath(@PathVariable String brand, @PathVariable String color,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		// Add HTTP Header to into ResponseEntity
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.SERVER, "Spring");
		headers.add("Custom", "Coustom Response Header");

		// Check if the parameter 'color' is numeric
		// if the color is numeric
		if (StringUtils.isNumeric(color)) {
			// create Error message
			var errorResponse = new ErrorResponse("Invalid Color", System.currentTimeMillis());
			return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
		}

		var pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "price"));
		var cars = carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();

		// return List<Car> and HTTP headers with HTTP status 200
		return ResponseEntity.ok().headers(headers).body(cars);

	}

	@GetMapping(path = "/cars")
	public List<Car> findCarsByParam(@RequestParam String brand, @RequestParam String color,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		var pageable = PageRequest.of(page, size);
		return carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();
	}

	@GetMapping(path = "/cars/date")
	public List<Car> findCarsReleasedAfter(
			@RequestParam(name = "first_release_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date firstReleaseDate) {
		return carElasticRepository.findByFirstReleaseDateAfter(firstReleaseDate.getTime());
	}

}
