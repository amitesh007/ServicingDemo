
package com.finastra.sme.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.ToString;

@JsonPropertyOrder({
    "Message"
})
@ToString
public class Messages {

    @JsonProperty("Message")
    private String message;


    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }

}
