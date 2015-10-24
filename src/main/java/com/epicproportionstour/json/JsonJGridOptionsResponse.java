package com.epicproportionstour.json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class JsonJGridOptionsResponse {
	private String Result;
	 
    private List<JsonJGridOptionsBean> Options;
 
    private String Message;
 
    public JsonJGridOptionsResponse(String Result, List<JsonJGridOptionsBean> Options) {
        this.Result = Result;
        this.Options = Options;
    }
 
    public JsonJGridOptionsResponse(String Result, String Message) {
        this.Result = Result;
        this.Message = Message;
    }
 
    @JsonProperty("Result")
    public String getResult() {
        return Result;
    }
 
    public void setResult(String Result) {
        this.Result = Result;
    }
 
    @JsonProperty("Options")
    public List<JsonJGridOptionsBean> getOptions() {
        return Options;
    }
 
    public void setOptions(List<JsonJGridOptionsBean> Options) {
        this.Options = Options;
    }
 
    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }
 
    public void setMessage(String Message) {
        this.Message = Message;
    }

}
