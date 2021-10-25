package com.finastra.sme.common.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValueList {

	@JsonProperty("LiqBusinessObject")
	public List<LiqBusinessObject> liqBusinessObject;

	public List<LiqBusinessObject> getLiqBusinessObject() {
		return liqBusinessObject;
	}

	public void setLiqBusinessObject(List<LiqBusinessObject> liqBusinessObject) {
		this.liqBusinessObject = liqBusinessObject;
	}

}
