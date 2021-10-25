package com.finastra.sme.common.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class LiqBusinessObject {

    @JsonProperty("@name")
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String objectType;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String className;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String objectId;
    @JsonProperty("group")
    private List<Group> group = null;
    @JsonProperty("LiqBusinessObject")
    private List<LiqBusinessObject> liqBusinessObject = null;

    @JsonProperty("@name")
    public String getName() {
        return name;
    }

    @JsonProperty("@name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("@objectType")
    public String getObjectType() {
        return objectType;
    }

    @JsonProperty("@objectType")
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @JsonProperty("@className")
    public String getClassName() {
        return className;
    }

    @JsonProperty("@className")
    public void setClassName(String className) {
        this.className = className;
    }

    @JsonProperty("@objectId")
    public String getObjectId() {
        return objectId;
    }

    @JsonProperty("@objectId")
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @JsonProperty("group")
    public List<Group> getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(List<Group> group) {
        this.group = group;
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
