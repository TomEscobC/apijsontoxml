package com.example.apijson.service;

import com.example.apijson.dto.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlService {

    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;

    public XmlService() {
        this.objectMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();
    }

    public String convertirJsonAXml(String jsonInput) throws Exception {
        try {
            if (jsonInput == null || jsonInput.trim().isEmpty()) {
                throw new IllegalArgumentException("JSON input no puede estar vacío");
            }

            JsonNode jsonNode = objectMapper.readTree(jsonInput);

            DistributionOrderXmlDTO dto = new DistributionOrderXmlDTO();
            
            dto.setDistributionOrderId(obtenerTexto(jsonNode, "distributionOrderId"));
            dto.setOrderType(obtenerTexto(jsonNode, "orderType"));
            dto.setOrderedDttm(obtenerTexto(jsonNode, "orderedDateTime"));
            dto.setDoFulfillmentStatus(obtenerTexto(jsonNode, "doFulfillmentStatus"));
            dto.setIsCancelled(obtenerTexto(jsonNode, "isCancelled"));
            dto.setBusinessUnit(obtenerTexto(jsonNode, "businessUnit"));
            dto.setReferenceField3(obtenerTexto(jsonNode, "referenceField3"));
            dto.setReferenceField4(obtenerTexto(jsonNode, "referenceField4"));
            dto.setPickupStartDttm(obtenerTexto(jsonNode, "pickupStartDateTime"));
            dto.setPickupEndDttm(obtenerTexto(jsonNode, "pickupEndDateTime"));
            dto.setDeliveryStartDttm(obtenerTexto(jsonNode, "deliveryStartDateTime"));
            dto.setDeliveryEndDttm(obtenerTexto(jsonNode, "deliveryEndDateTime"));
            dto.setLpnCubingIndicator(obtenerTexto(jsonNode, "lpnCubingIndicator"));
            dto.setPartlShipConfFlag(obtenerTexto(jsonNode, "partialShipConfFlag"));
            dto.setContentLabelType(obtenerTexto(jsonNode, "contentLabelType"));
            dto.setLpnLabelType(obtenerTexto(jsonNode, "lpnLabelType"));
            dto.setOriginFacilityAliasId(obtenerTexto(jsonNode, "originFacilityAliasId"));
            dto.setDestinationFacilityAliasId(obtenerTexto(jsonNode, "destinationFacilityAliasId"));
            dto.setRouteTo(obtenerTexto(jsonNode, "routeTo"));
            dto.setDistributionOrderType(obtenerTexto(jsonNode, "type"));

            dto.setLineItems(construirLineItems(jsonNode.get("lineItem")));

            String xmlString = xmlMapper.writeValueAsString(dto);
            
            String fileName = guardarXmlEnArchivo(xmlString);
            System.out.println("Archivo XML guardado en: " + fileName);
            
            return xmlString;
            
        } catch (IOException e) {
            throw new Exception("Error de IO: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Error en la conversión: " + e.getMessage(), e);
        }
    }

    private List<LineItemXmlDTO> construirLineItems(JsonNode lineItemsNode) {
        if (lineItemsNode == null || !lineItemsNode.isArray()) {
            return new ArrayList<>();
        }

        List<LineItemXmlDTO> lineItems = new ArrayList<>();
        
        for (JsonNode itemNode : lineItemsNode) {
            LineItemXmlDTO lineItem = new LineItemXmlDTO();
            
            lineItem.setName(obtenerTexto(itemNode, "name"));
            lineItem.setDoLineNumber(obtenerTexto(itemNode, "doLineNumber"));
            lineItem.setFulfillmentType(obtenerTexto(itemNode, "fulfillmentType"));
            lineItem.setStoreDepartment(obtenerTexto(itemNode, "storeDepartment"));
            lineItem.setMerchandizeGroup(obtenerTexto(itemNode, "merchandizeGroup"));
            lineItem.setAllocationSourceId(obtenerTexto(itemNode, "allocationSourceId"));
            lineItem.setAllocationSourceType(obtenerTexto(itemNode, "allocationSourceType"));
            lineItem.setAllocationSourceLineId(obtenerTexto(itemNode, "allocationSourceLineId"));
            lineItem.setShelfDays(obtenerEntero(itemNode, "shelfDays"));
            
            lineItem.setWeight(construirWeight(itemNode.get("weight")));
            lineItem.setQuantity(construirQuantity(itemNode.get("quantity")));
            lineItem.setInventoryAttributes(construirInventoryAttributes(itemNode.get("inventoryAttributes")));
            
            lineItems.add(lineItem);
        }
        
        return lineItems;
    }

    private WeightXmlDTO construirWeight(JsonNode weightNode) {
        if (weightNode == null) return null;
        
        WeightXmlDTO weight = new WeightXmlDTO();
        weight.setUom(obtenerTexto(weightNode, "uom"));
        weight.setPlanned(obtenerDouble(weightNode, "planned"));
        return weight;
    }

    private QuantityXmlDTO construirQuantity(JsonNode quantityNode) {
        if (quantityNode == null) return null;
        
        QuantityXmlDTO quantity = new QuantityXmlDTO();
        quantity.setUom(obtenerTexto(quantityNode, "uom"));
        quantity.setOrder(obtenerEntero(quantityNode, "order"));
        return quantity;
    }

    private InventoryAttributesXmlDTO construirInventoryAttributes(JsonNode inventoryNode) {
        if (inventoryNode == null) return null;
        
        InventoryAttributesXmlDTO inventory = new InventoryAttributesXmlDTO();
        inventory.setBatchNumber(obtenerTexto(inventoryNode, "batchNumber"));
        inventory.setInventoryType(obtenerTexto(inventoryNode, "inventoryType"));
        inventory.setCountryOfOrigin(obtenerTexto(inventoryNode, "countryOfOrigin"));
        return inventory;
    }

    private String obtenerTexto(JsonNode node, String field) {
        if (node != null && node.has(field) && !node.get(field).isNull()) {
            return node.get(field).asText();
        }
        return null;
    }

    private Integer obtenerEntero(JsonNode node, String field) {
        if (node != null && node.has(field) && !node.get(field).isNull()) {
            return node.get(field).asInt();
        }
        return null;
    }

    private Double obtenerDouble(JsonNode node, String field) {
        if (node != null && node.has(field) && !node.get(field).isNull()) {
            return node.get(field).asDouble();
        }
        return null;
    }
    
    private String guardarXmlEnArchivo(String xmlString) throws IOException {
        File directory = new File("output");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "output/DistributionOrder_" + timestamp + ".xml";
        
        File file = new File(fileName);
        
        java.nio.file.Files.write(
            file.toPath(), 
            xmlString.getBytes()
        );
        
        return fileName;
    }
}