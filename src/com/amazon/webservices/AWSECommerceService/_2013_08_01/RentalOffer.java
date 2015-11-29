/**
 * RentalOffer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.amazon.webservices.AWSECommerceService._2013_08_01;

public class RentalOffer  implements java.io.Serializable {
    private com.amazon.webservices.AWSECommerceService._2013_08_01.Merchant merchant;

    private com.amazon.webservices.AWSECommerceService._2013_08_01.RentalListing[] rentalListing;

    public RentalOffer() {
    }

    public RentalOffer(
           com.amazon.webservices.AWSECommerceService._2013_08_01.Merchant merchant,
           com.amazon.webservices.AWSECommerceService._2013_08_01.RentalListing[] rentalListing) {
           this.merchant = merchant;
           this.rentalListing = rentalListing;
    }


    /**
     * Gets the merchant value for this RentalOffer.
     * 
     * @return merchant
     */
    public com.amazon.webservices.AWSECommerceService._2013_08_01.Merchant getMerchant() {
        return merchant;
    }


    /**
     * Sets the merchant value for this RentalOffer.
     * 
     * @param merchant
     */
    public void setMerchant(com.amazon.webservices.AWSECommerceService._2013_08_01.Merchant merchant) {
        this.merchant = merchant;
    }


    /**
     * Gets the rentalListing value for this RentalOffer.
     * 
     * @return rentalListing
     */
    public com.amazon.webservices.AWSECommerceService._2013_08_01.RentalListing[] getRentalListing() {
        return rentalListing;
    }


    /**
     * Sets the rentalListing value for this RentalOffer.
     * 
     * @param rentalListing
     */
    public void setRentalListing(com.amazon.webservices.AWSECommerceService._2013_08_01.RentalListing[] rentalListing) {
        this.rentalListing = rentalListing;
    }

    public com.amazon.webservices.AWSECommerceService._2013_08_01.RentalListing getRentalListing(int i) {
        return this.rentalListing[i];
    }

    public void setRentalListing(int i, com.amazon.webservices.AWSECommerceService._2013_08_01.RentalListing _value) {
        this.rentalListing[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RentalOffer)) return false;
        RentalOffer other = (RentalOffer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.merchant==null && other.getMerchant()==null) || 
             (this.merchant!=null &&
              this.merchant.equals(other.getMerchant()))) &&
            ((this.rentalListing==null && other.getRentalListing()==null) || 
             (this.rentalListing!=null &&
              java.util.Arrays.equals(this.rentalListing, other.getRentalListing())));
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
        if (getMerchant() != null) {
            _hashCode += getMerchant().hashCode();
        }
        if (getRentalListing() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRentalListing());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRentalListing(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RentalOffer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", ">RentalOffer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchant");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "Merchant"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", ">Merchant"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rentalListing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "RentalListing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webservices.amazon.com/AWSECommerceService/2013-08-01", "RentalListing"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
