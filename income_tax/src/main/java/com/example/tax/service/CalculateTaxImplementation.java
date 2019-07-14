package com.example.tax.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.example.tax.dto.AgeGroup;
import com.example.tax.dto.RequestDto;
import com.example.tax.dto.TaxRate;
import com.example.tax.utils.Constants;

/**
 * 
 * @author manasi
 *
 */
@Service
public class CalculateTaxImplementation {

	public double calculateTax(RequestDto personInfo) throws Exception {
		double taxableIncome = personInfo.getTaxableIncome();
		double totalTax = 0;
		TaxRate taxRate = Constants.countryWithTaxRate.get(personInfo.getCountry().toLowerCase());

		try {
			AgeGroup ageGroup = getTaxRateBasedOnAgeGroup(personInfo.getAge(), taxRate);
			if (taxableIncome <= ageGroup.getNonTaxableIncome()) {
				return totalTax;
			}
			Map<Integer, IncomeRange> taxRateBasedOnIncome = getTaxRateWithIncomeRange(ageGroup);
			for (Entry<Integer, IncomeRange> incomeRange : taxRateBasedOnIncome.entrySet()) {
				if (taxableIncome > incomeRange.getValue().getLower()
						&& taxableIncome <= incomeRange.getValue().getUpper()) {
					taxableIncome = taxableIncome - incomeRange.getValue().getLower();
					totalTax = totalTax + calculateTax(taxableIncome, incomeRange.getKey());
				}
			}
		} catch (Exception e) {
			throw new Exception("Exception while calculating tax");
		}
		return totalTax;
	}

	private AgeGroup getTaxRateBasedOnAgeGroup(int age, TaxRate taxRate) throws Exception {
		for (AgeGroup agegroup : taxRate.getAgeGroup()) {
			String ageRange = agegroup.getAge();
			String[] range = ageRange.split("-");
			if (age <= Integer.parseInt(range[1])) {
				return agegroup;
			}
		}
		throw new Exception("Couldn't find agegroup to calculate tax for person age: " + age);
	}

	private Map<Integer, IncomeRange> getTaxRateWithIncomeRange(AgeGroup ageGroup) {
		Map<Integer, IncomeRange> taxRateBasedOnIncome = new HashMap<>();
		for (String taxableAmountWithTaxRate : ageGroup.getTaxableIncomeRate()) {
			String[] rate = taxableAmountWithTaxRate.split("\\|");
			String[] incomeRange = rate[0].split("-");
			double upper = 0;
			if (incomeRange.length < 2) {
				upper = Double.MAX_VALUE;
			} else {
				upper = Integer.parseInt(incomeRange[1]);
			}
			taxRateBasedOnIncome.put(Integer.parseInt(rate[1]),
					new IncomeRange(Double.parseDouble(incomeRange[0]), upper));
		}
		return taxRateBasedOnIncome;
	}

	private double calculateTax(double taxableIncome, int rate) {
		return (taxableIncome * rate) / 100;
	}

	private static class IncomeRange {
		public double lower, upper;

		public IncomeRange(double lower, double upper) {
			super();
			this.lower = lower;
			this.upper = upper;
		}

		public double getLower() {
			return lower;
		}

		public double getUpper() {
			return upper;
		}
	}
}
