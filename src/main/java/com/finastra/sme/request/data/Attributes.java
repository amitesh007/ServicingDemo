package com.finastra.sme.request.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finastra.sme.common.data.LiqBusinessObjects;

import lombok.ToString;

@ToString
public class Attributes {

    @JsonProperty("LiqBusinessObjects")
    private LiqBusinessObjects liqBusinessObjects;

    @JsonProperty("LiqBusinessObjects")
    public LiqBusinessObjects getLiqBusinessObjects() {
        return liqBusinessObjects;
    }

    @JsonProperty("LiqBusinessObjects")
    public void setLiqBusinessObjects(LiqBusinessObjects liqBusinessObjects) {
        this.liqBusinessObjects = liqBusinessObjects;
    }
}
