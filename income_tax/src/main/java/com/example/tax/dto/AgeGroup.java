
package com.example.tax.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author manasi
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "age", "nonTaxableIncome", "taxableIncomeRate" })
public class AgeGroup {

	@JsonProperty("age")
	private String age;
	@JsonProperty("nonTaxableIncome")
	private Integer nonTaxableIncome;
	@JsonProperty("taxableIncomeRate")
	private List<String> taxableIncomeRate = null;

	@JsonProperty("age")
	public String getAge() {
		return age;
	}

	@JsonProperty("age")
	public void setAge(String age) {
		this.age = age;
	}

	@JsonProperty("nonTaxableIncome")
	public Integer getNonTaxableIncome() {
		return nonTaxableIncome;
	}

	@JsonProperty("nonTaxableIncome")
	public void setNonTaxableIncome(Integer nonTaxableIncome) {
		this.nonTaxableIncome = nonTaxableIncome;
	}

	@JsonProperty("taxableIncomeRate")
	public List<String> getTaxableIncomeRate() {
		return taxableIncomeRate;
	}

	@JsonProperty("taxableIncomeRate")
	public void setTaxableIncomeRate(List<String> taxableIncomeRate) {
		this.taxableIncomeRate = taxableIncomeRate;
	}

}
