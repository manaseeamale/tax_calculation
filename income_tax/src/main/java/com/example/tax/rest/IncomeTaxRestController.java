package com.example.tax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tax.dto.RequestDto;
import com.example.tax.service.CalculateTaxImplementation;

/**
 * 
 * @author manasi
 *
 */
@RestController
public class IncomeTaxRestController {

	@Autowired
	CalculateTaxImplementation taxCal;

	/**
	 * 
	 * @param requestDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/tax")
	public @ResponseBody double calculateTax(@RequestBody(required = true) RequestDto requestDto) throws Exception {
		try {
			return taxCal.calculateTax(requestDto);
		} catch (Exception e) {
			throw new Exception("Exception while calculating tax");
		}
	}
}
