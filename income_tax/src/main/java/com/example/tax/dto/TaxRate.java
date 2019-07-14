
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
@JsonPropertyOrder({ "ageGroup" })
public class TaxRate {

	@JsonProperty("ageGroup")
	private List<AgeGroup> ageGroup = null;

	@JsonProperty("ageGroup")
	public List<AgeGroup> getAgeGroup() {
		return ageGroup;
	}

	@JsonProperty("ageGroup")
	public void setAgeGroup(List<AgeGroup> ageGroup) {
		this.ageGroup = ageGroup;
	}

}
