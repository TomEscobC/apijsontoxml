package com.example.apijson.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JacksonXmlRootElement(localName = "DistributionOrder")
public class DistributionOrderXmlDTO {

    @JacksonXmlProperty(localName = "DistributionOrderId")
    private String distributionOrderId;

    @JacksonXmlProperty(localName = "OrderType")
    private String orderType;

    @JacksonXmlProperty(localName = "OrderedDttm")
    private String orderedDttm;

    @JacksonXmlProperty(localName = "DoFulfillmentStatus")
    private String doFulfillmentStatus;

    @JacksonXmlProperty(localName = "IsCancelled")
    private String isCancelled;

    @JacksonXmlProperty(localName = "BusinessUnit")
    private String businessUnit;

    @JacksonXmlProperty(localName = "ReferenceField3")
    private String referenceField3;

    @JacksonXmlProperty(localName = "ReferenceField4")
    private String referenceField4;

    @JacksonXmlProperty(localName = "PickupStartDttm")
    private String pickupStartDttm;

    @JacksonXmlProperty(localName = "PickupEndDttm")
    private String pickupEndDttm;

    @JacksonXmlProperty(localName = "DeliveryStartDttm")
    private String deliveryStartDttm;

    @JacksonXmlProperty(localName = "DeliveryEndDttm")
    private String deliveryEndDttm;

    @JacksonXmlProperty(localName = "LpnCubingIndicator")
    private String lpnCubingIndicator;

    @JacksonXmlProperty(localName = "PartlShipConfFlag")
    private String partlShipConfFlag;

    @JacksonXmlProperty(localName = "ContentLabelType")
    private String contentLabelType;

    @JacksonXmlProperty(localName = "LpnLabelType")
    private String lpnLabelType;

    @JacksonXmlProperty(localName = "OriginFacilityAliasId")
    private String originFacilityAliasId;

    @JacksonXmlProperty(localName = "DestinationFacilityAliasId")
    private String destinationFacilityAliasId;

    @JacksonXmlProperty(localName = "RouteTo")
    private String routeTo;

    @JacksonXmlProperty(localName = "DistributionOrderType")
    private String distributionOrderType;

    @JacksonXmlElementWrapper(localName = "LineItems")
    @JacksonXmlProperty(localName = "LineItem")
    private List<LineItemXmlDTO> lineItems;
}