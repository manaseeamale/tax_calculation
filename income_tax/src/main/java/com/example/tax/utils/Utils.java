package com.example.tax.utils;

import java.io.InputStreamReader;
import java.util.List;

import com.example.tax.dto.TaxRateCountries;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author manasi
 *
 */
public class Utils {

	List<TaxRateCountries> readTaxRateBasedOnCountryFile() {
		List<TaxRateCountries> taxRateInDiffCountries = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			taxRateInDiffCountries = objectMapper.readValue(
					new InputStreamReader(getClass().getResourceAsStream("/TaxRateBasedOnCountry.json")),
					new TypeReference<List<TaxRateCountries>>() {
					});
			return taxRateInDiffCountries;
		} catch (Exception e) {
		}
		return taxRateInDiffCountries;
	}

}
