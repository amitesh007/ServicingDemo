
package com.finastra.sme.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finastra.sme.request.data.Objects;

import lombok.ToString;

@ToString
public class Response {

	@JsonProperty("@success")
    private String success;
    @JsonProperty("sessionId")
    private String sessionId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("FormId")
    private String formId;
    @JsonProperty("TemplateId")
    private String templateId;
    @JsonProperty("objects")
    private Objects objects;
    @JsonProperty("Result")
    private Result result;
    @JsonProperty("Messages")
    private Messages messages;

    @JsonProperty("@success")
    public String getSuccess() {
        return success;
    }

    @JsonProperty("@success")
    public void setSuccess(String success) {
        this.success = success;
    }

    @JsonProperty("sessionId")
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("sessionId")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("FormId")
    public String getFormId() {
        return formId;
    }

    @JsonProperty("FormId")
    public void setFormId(String formId) {
        this.formId = formId;
    }

    @JsonProperty("TemplateId")
    public String getTemplateId() {
        return templateId;
    }

    @JsonProperty("TemplateId")
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @JsonProperty("Result")
    public Result getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(Result result) {
        this.result = result;
    }

    @JsonProperty("Messages")
    public Messages getMessages() {
        return messages;
    }

    @JsonProperty("Messages")
    public void setMessages(Messages messages) {
        this.messages = messages;
    }

	public Objects getObjects() {
		return objects;
	}

	public void setObjects(Objects objects) {
		this.objects = objects;
	}
}
