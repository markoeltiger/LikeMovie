package com.mark.likemovies.Models;


import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Movie {
    @SerializedName("items")

    private List<Item> items = null;
    @SerializedName("errorMessage")

    private String errorMessage;
    @SerializedName("additionalProperties")

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}