//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.13 at 09:13:36 AM EET 
//


package dk.frv.eavdam.io.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for antenna complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="antenna">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="directionalAntenna" type="{http://eavdam.frv.dk/schema}directionalAntenna"/>
 *           &lt;element name="omnidirectionalAntenna" type="{http://eavdam.frv.dk/schema}omnidirectionalAntenna"/>
 *         &lt;/choice>
 *         &lt;element name="antennaHeight" type="{http://eavdam.frv.dk/schema}nonNegativeDouble"/>
 *         &lt;element name="terrainHeight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "antenna", propOrder = {
    "directionalAntenna",
    "omnidirectionalAntenna",
    "antennaHeight",
    "terrainHeight"
})
public class Antenna {

    protected DirectionalAntenna directionalAntenna;
    protected OmnidirectionalAntenna omnidirectionalAntenna;
    protected double antennaHeight;
    protected double terrainHeight;

    /**
     * Gets the value of the directionalAntenna property.
     * 
     * @return
     *     possible object is
     *     {@link DirectionalAntenna }
     *     
     */
    public DirectionalAntenna getDirectionalAntenna() {
        return directionalAntenna;
    }

    /**
     * Sets the value of the directionalAntenna property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectionalAntenna }
     *     
     */
    public void setDirectionalAntenna(DirectionalAntenna value) {
        this.directionalAntenna = value;
    }

    /**
     * Gets the value of the omnidirectionalAntenna property.
     * 
     * @return
     *     possible object is
     *     {@link OmnidirectionalAntenna }
     *     
     */
    public OmnidirectionalAntenna getOmnidirectionalAntenna() {
        return omnidirectionalAntenna;
    }

    /**
     * Sets the value of the omnidirectionalAntenna property.
     * 
     * @param value
     *     allowed object is
     *     {@link OmnidirectionalAntenna }
     *     
     */
    public void setOmnidirectionalAntenna(OmnidirectionalAntenna value) {
        this.omnidirectionalAntenna = value;
    }

    /**
     * Gets the value of the antennaHeight property.
     * 
     */
    public double getAntennaHeight() {
        return antennaHeight;
    }

    /**
     * Sets the value of the antennaHeight property.
     * 
     */
    public void setAntennaHeight(double value) {
        this.antennaHeight = value;
    }

    /**
     * Gets the value of the terrainHeight property.
     * 
     */
    public double getTerrainHeight() {
        return terrainHeight;
    }

    /**
     * Sets the value of the terrainHeight property.
     * 
     */
    public void setTerrainHeight(double value) {
        this.terrainHeight = value;
    }

}
