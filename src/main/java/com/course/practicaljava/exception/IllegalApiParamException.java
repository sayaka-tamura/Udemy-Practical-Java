package com.course.practicaljava.exception;

public class IllegalApiParamException extends RuntimeException {

	private static final long serialVersionUID = 2097676485591491781L;

	public IllegalApiParamException(String s) {
		super(s);
	}
}
