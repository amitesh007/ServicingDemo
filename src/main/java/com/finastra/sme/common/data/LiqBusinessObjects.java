
package com.finastra.sme.common.data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.ToString;

@JsonPropertyOrder({
    "@class",
    "LiqBusinessObject"
})
@ToString
public class LiqBusinessObjects {

    @JsonProperty("@class")
    private String _class;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String ownerClass;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String ownerId;
    @JsonProperty("LiqBusinessObject")
    private List<LiqBusinessObject> liqBusinessObject = null;

    @JsonProperty("@class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("@class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @JsonProperty("@ownerClass")
    public String getOwnerClass() {
        return ownerClass;
    }

    @JsonProperty("@ownerClass")
    public void setOwnerClass(String ownerClass) {
        this.ownerClass = ownerClass;
    }

    @JsonProperty("@ownerId")
    public String getOwnerId() {
        return ownerId;
    }

    @JsonProperty("@ownerId")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("LiqBusinessObject")
    public List<LiqBusinessObject> getLiqBusinessObject() {
        return liqBusinessObject;
    }

    @JsonProperty("LiqBusinessObject")
    public void setLiqBusinessObject(List<LiqBusinessObject> liqBusinessObject) {
        this.liqBusinessObject = liqBusinessObject;
    }
}
