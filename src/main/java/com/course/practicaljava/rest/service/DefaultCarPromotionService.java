package com.course.practicaljava.rest.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultCarPromotionService implements CarPromotionService {

	@Override
	// Check if a promotion is valid
	public boolean isValidPromotionType(String promotionType) {
		return CarPromotionService.PROMOTION_TYPES.stream().anyMatch(p -> p.equalsIgnoreCase(promotionType));
	}
}
