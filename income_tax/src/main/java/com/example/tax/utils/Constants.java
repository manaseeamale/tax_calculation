package com.example.tax.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tax.dto.TaxRate;
import com.example.tax.dto.TaxRateCountries;

/**
 * 
 * @author manasi
 *
 */
public class Constants {

	public static Map<String, TaxRate> countryWithTaxRate = new HashMap<>();

	static {
		try {
			Utils util = new Utils();
			List<TaxRateCountries> allCountriesTaxRate = util.readTaxRateBasedOnCountryFile();
			for (TaxRateCountries taxRateCountry : allCountriesTaxRate) {
				countryWithTaxRate.put(taxRateCountry.getCountry(), taxRateCountry.getTaxRate());
			}
		} catch (Exception e) {
		}
	}

}
