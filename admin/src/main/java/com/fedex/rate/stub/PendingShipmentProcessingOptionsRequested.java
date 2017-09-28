/**
 * PendingShipmentProcessingOptionsRequested.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.rate.stub;

public class PendingShipmentProcessingOptionsRequested  implements java.io.Serializable {
    private com.fedex.rate.stub.PendingShipmentProcessingOptionType[] options;

    public PendingShipmentProcessingOptionsRequested() {
    }

    public PendingShipmentProcessingOptionsRequested(
           com.fedex.rate.stub.PendingShipmentProcessingOptionType[] options) {
           this.options = options;
    }


    /**
     * Gets the options value for this PendingShipmentProcessingOptionsRequested.
     * 
     * @return options
     */
    public com.fedex.rate.stub.PendingShipmentProcessingOptionType[] getOptions() {
        return options;
    }


    /**
     * Sets the options value for this PendingShipmentProcessingOptionsRequested.
     * 
     * @param options
     */
    public void setOptions(com.fedex.rate.stub.PendingShipmentProcessingOptionType[] options) {
        this.options = options;
    }

    public com.fedex.rate.stub.PendingShipmentProcessingOptionType getOptions(int i) {
        return this.options[i];
    }

    public void setOptions(int i, com.fedex.rate.stub.PendingShipmentProcessingOptionType _value) {
        this.options[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PendingShipmentProcessingOptionsRequested)) return false;
        PendingShipmentProcessingOptionsRequested other = (PendingShipmentProcessingOptionsRequested) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.options==null && other.getOptions()==null) || 
             (this.options!=null &&
              java.util.Arrays.equals(this.options, other.getOptions())));
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
        if (getOptions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOptions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOptions(), i);
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
        new org.apache.axis.description.TypeDesc(PendingShipmentProcessingOptionsRequested.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/rate/v20", "PendingShipmentProcessingOptionsRequested"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("options");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/rate/v20", "Options"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/rate/v20", "PendingShipmentProcessingOptionType"));
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
