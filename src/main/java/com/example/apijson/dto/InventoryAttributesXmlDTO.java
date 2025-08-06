package com.example.apijson.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryAttributesXmlDTO {

    @JacksonXmlProperty(localName = "BatchNumber")
    private String batchNumber;

    @JacksonXmlProperty(localName = "InventoryType")
    private String inventoryType;

    @JacksonXmlProperty(localName = "CountryOfOrigin")
    private String countryOfOrigin;
}