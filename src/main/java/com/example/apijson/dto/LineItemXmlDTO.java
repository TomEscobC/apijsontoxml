package com.example.apijson.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LineItemXmlDTO {

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlProperty(localName = "DoLineNumber")
    private String doLineNumber;

    @JacksonXmlProperty(localName = "FulfillmentType")
    private String fulfillmentType;

    @JacksonXmlProperty(localName = "StoreDepartment")
    private String storeDepartment;

    @JacksonXmlProperty(localName = "MerchandizeGroup")
    private String merchandizeGroup;

    @JacksonXmlProperty(localName = "AllocationSourceId")
    private String allocationSourceId;

    @JacksonXmlProperty(localName = "AllocationSourceType")
    private String allocationSourceType;

    @JacksonXmlProperty(localName = "AllocationSourceLineId")
    private String allocationSourceLineId;

    @JacksonXmlProperty(localName = "ShelfDays")
    private Integer shelfDays;

    @JacksonXmlProperty(localName = "Weight")
    private WeightXmlDTO weight;

    @JacksonXmlProperty(localName = "Quantity")
    private QuantityXmlDTO quantity;

    @JacksonXmlProperty(localName = "InventoryAttributes")
    private InventoryAttributesXmlDTO inventoryAttributes;
}