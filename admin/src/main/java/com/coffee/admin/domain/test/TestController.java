package com.coffee.admin.domain.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/")
	public String testMessage() {
		return "Welcome Na-chan go to Test method";
	}
	
}
