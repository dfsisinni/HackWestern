/**
 * RentalListing.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.amazon.webservices.AWSECommerceService._2013_08_01;

public class RentalListing  implements java.io.Serializable {
    private com.amazon.webservices.AWSECommerceService._2013_08_01.Price price;

    private com.amazon.webservices.AWSECommerceService._2013_08_01.NonNegativeIntegerWithUnits period;

    private java.lang.Boolean isFulfilledByAmazon;

    private java.lang.String disclaimer;

    public RentalListing() {
    }

    public RentalListing(
           com.amazon.webservices.AWSECommerceService._2013_08_01.Price price,
           com.amazon.webservices.AWSECommerceService._2013_08_01.NonNegativeIntegerWithUnits period,
           java.lang.Boolean isFulfilledByAmazon,
           java.lang.String disclaimer) {
           this.price = price;
           this.period = period;
           this.isFulfilledByAmazon = isFulfilledByAmazon;
           this.disclaimer = disclaimer;
    }


    /**
     * Gets the price value for this RentalListing.
     * 
     * @return price
     */
    public com.amazon.webservices.AWSECommerceService._2013_08_01.Price getPrice() {
        return price;
    }


    /**
     * Sets the price value for this RentalListing.
     * 
     * @param price
     */
    public void setPrice(com.amazon.webservices.AWSECommerceService._2013_08_01.Price price) {
        this.price = price;
    }


    /**
     * Gets the period value for this RentalListing.
     * 
     * @return period
     */
    public com.amazon.webservices.AWSECommerceService._2013_08_01.NonNegativeIntegerWithUnits getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this RentalListing.
     * 
     * @param period
     */
    public void setPeriod(com.amazon.webservices.AWSECommerceService._2013_08_01.NonNegativeIntegerWithUnits period) {
        this.period = period;
    }


    /**
     * Gets the isFulfilledByAmazon value for this RentalListing.
     * 
     * @return isFulfilledByAmazon
     */
    public java.lang.Boolean getIsFulfilledByAmazon() {
        return isFulfilledByAmazon;
    }


    /**
     * Sets the isFulfilledByAmazon value for this RentalListing.
     * 
     * @param isFulfilledByAmazon
     */
    public void setIsFulfilledByAmazon(java.lang.Boolean isFulfilledByAmazon) {
        this.isFulfilledByAmazon = isFulfilledByAmazon;
    }


    /**
     * Gets the disclaimer value for this RentalListing.
     * 
     * @return disclaimer
     */
    public java.lang.String getDisclaimer() {
        return disclaimer;
    }


    /**
     * Sets the disclaimer value for this RentalListing.
     * 
     * @param disclaimer
     */
    public void setDisclaimer(java.lang.String disclaimer) {
        this.disclaimer = disclaimer;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RentalListing)) return false;
        RentalListing other = (RentalListing) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.isFulfilledByAmazon==null && other.getIsFulfilledByAmazon()==null) || 
             (this.isFulfilledByAmazon!=null &&
              this.isFulfilledByAmazon.equals(other.getIsFulfilledByAmazon()))) &&
            ((this.disclaimer==null && other.getDisclaimer()==null) || 
             (this.disclaimer!=null &&
              this.disclaimer.equals(other.getDisclaimer())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getIsFulfilledByAmazon() != null) {
            _hashCode += getIsFulfilledByAmazon().hashCode();
        }
        if (getDisclaimer() != null) {
            _hashCode += getDisclaimer().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RentalListing.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", ">RentalListing"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "Price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "Price"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "NonNegativeIntegerWithUnits"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isFulfilledByAmazon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "IsFulfilledByAmazon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disclaimer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "Disclaimer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
