
package com.finastra.sme.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.ToString;

@JsonPropertyOrder({
    "Response"
})
@ToString
public class ResponseData {

    @JsonProperty("Response")
    private Response response;

    @JsonProperty("Response")
    public Response getResponse() {
        return response;
    }

    @JsonProperty("Response")
    public void setResponse(Response response) {
        this.response = response;
    }
}
