
package com.example.jackson;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address1",
    "address2",
    "address3",
    "kana1",
    "kana2",
    "kana3",
    "prefcode",
    "zipcode"
})
public class Result {

    @JsonProperty("address1")
    public String address1;
    @JsonProperty("address2")
    public String address2;
    @JsonProperty("address3")
    public String address3;
    @JsonProperty("kana1")
    public String kana1;
    @JsonProperty("kana2")
    public String kana2;
    @JsonProperty("kana3")
    public String kana3;
    @JsonProperty("prefcode")
    public String prefcode;
    @JsonProperty("zipcode")
    public String zipcode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
