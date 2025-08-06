package com.example.apijson.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuantityXmlDTO {

    @JacksonXmlProperty(localName = "Uom")
    private String uom;

    @JacksonXmlProperty(localName = "Order")
    private Integer order;
}