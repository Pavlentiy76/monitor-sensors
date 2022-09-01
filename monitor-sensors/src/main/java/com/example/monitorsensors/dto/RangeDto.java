package com.example.monitorsensors.dto;

import com.example.monitorsensors.annotation.RangeCheck;

import javax.validation.constraints.NotNull;

@RangeCheck(message = "From must be less then to")
public class RangeDto {

    @NotNull(message = "From must not be empty")
    private Integer from;
    @NotNull(message = "To must not be empty")
    private Integer to;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
