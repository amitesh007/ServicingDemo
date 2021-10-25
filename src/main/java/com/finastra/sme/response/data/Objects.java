
package com.finastra.sme.response.data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class Objects {

    @JsonProperty("object")
    private List<Object> object = null;

    @JsonProperty("object")
    public List<Object> getObject() {
        return object;
    }

    @JsonProperty("object")
    public void setObject(List<Object> object) {
        this.object = object;
    }
}
