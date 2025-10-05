package com.example.clear_sky_backend.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SubscriberRequest {

    @NotBlank(message = "phone is required")
    @Pattern(regexp = "^\\+?1?[-.\\s]?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$", message = "invalid phone format")
    private String phone;

    @NotBlank(message = "area is required")
    private String area;

    public SubscriberRequest() {}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
