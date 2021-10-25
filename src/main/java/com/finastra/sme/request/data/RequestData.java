package com.finastra.sme.request.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class RequestData {

    @JsonProperty("sessionId")
    private String sessionId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String formId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String templateId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Objects objects;
    @JsonProperty("attributes")
    private Attributes attributes;

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

    @JsonProperty("attributes")
    public Attributes getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

	public Objects getObjects() {
		return objects;
	}

	public void setObjects(Objects objects) {
		this.objects = objects;
	}

}
