package com.course.practicaljava.rest.service;

import java.util.List;

public interface CarPromotionService {

	List<String> PROMOTION_TYPES = List.of("Bonus", "Discount");

	// Check if a promotion is valid
	boolean isValidPromotionType(String promotionType);
}
