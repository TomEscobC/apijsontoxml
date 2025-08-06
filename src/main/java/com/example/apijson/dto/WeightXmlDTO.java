package com.example.apijson.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeightXmlDTO {

    @JacksonXmlProperty(localName = "Uom")
    private String uom;

    @JacksonXmlProperty(localName = "Planned")
    private Double planned;
}