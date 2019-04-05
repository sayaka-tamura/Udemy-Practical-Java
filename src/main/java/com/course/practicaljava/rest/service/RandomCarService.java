package com.course.practicaljava.rest.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.course.practicaljava.rest.domain.Car;
import com.course.practicaljava.util.RandomDateUtil;

@Service
public class RandomCarService implements CarService {

	private Random random = new Random();

	@Override
	public Car generateCar() {
		var randomBrand = BRANDS.get(random.nextInt(BRANDS.size()));
		var randomColor = COLORS.get(random.nextInt(COLORS.size()));
		var randomType = TYPES.get(random.nextInt(TYPES.size()));

		var car = new Car(randomBrand, randomColor, randomType);

		car.setAvailable(random.nextBoolean());
		car.setPrice(5000 + random.nextInt(7001));
		car.setFirstReleaseDate(RandomDateUtil.generateRandomDate());

		var randomCount = random.nextInt(ADDITONAL_FEATURES.size());
		var additionalFeatures = new ArrayList<String>();

		for (int i = 1; i < randomCount; i++) {
			additionalFeatures.add(ADDITONAL_FEATURES.get(i - 1));
		}

		car.setAdditionalFeatures(additionalFeatures);

		return car;
	}

}
