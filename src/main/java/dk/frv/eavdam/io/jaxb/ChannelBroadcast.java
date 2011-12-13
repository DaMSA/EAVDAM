//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.13 at 09:07:38 AM EET 
//


package dk.frv.eavdam.io.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * FATDMA Channel broadcast information.
 * 
 * <p>Java class for channelBroadcast complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="channelBroadcast">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accessScheme" type="{http://eavdam.frv.dk/schema}accessScheme" minOccurs="0"/>
 *         &lt;element name="messageID" type="{http://eavdam.frv.dk/schema}messageID" minOccurs="0"/>
 *         &lt;element name="utcHour" type="{http://eavdam.frv.dk/schema}utcHour" minOccurs="0"/>
 *         &lt;element name="utcMinute" type="{http://eavdam.frv.dk/schema}utcMinute" minOccurs="0"/>
 *         &lt;element name="startSlot" type="{http://eavdam.frv.dk/schema}startSlot"/>
 *         &lt;element name="blockSize" type="{http://eavdam.frv.dk/schema}blockSize"/>
 *         &lt;element name="increment" type="{http://eavdam.frv.dk/schema}increment"/>
 *         &lt;element name="ownership" type="{http://eavdam.frv.dk/schema}ownership" minOccurs="0"/>
 *         &lt;element name="usage" type="{http://eavdam.frv.dk/schema}usage" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "channelBroadcast", propOrder = {
    "accessScheme",
    "messageID",
    "utcHour",
    "utcMinute",
    "startSlot",
    "blockSize",
    "increment",
    "ownership",
    "usage"
})
public class ChannelBroadcast {

    protected AccessScheme accessScheme;
    protected Integer messageID;
    protected Integer utcHour;
    protected Integer utcMinute;
    protected int startSlot;
    protected int blockSize;
    protected int increment;
    protected Ownership ownership;
    protected String usage;

    /**
     * Gets the value of the accessScheme property.
     * 
     * @return
     *     possible object is
     *     {@link AccessScheme }
     *     
     */
    public AccessScheme getAccessScheme() {
        return accessScheme;
    }

    /**
     * Sets the value of the accessScheme property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessScheme }
     *     
     */
    public void setAccessScheme(AccessScheme value) {
        this.accessScheme = value;
    }

    /**
     * Gets the value of the messageID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMessageID() {
        return messageID;
    }

    /**
     * Sets the value of the messageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMessageID(Integer value) {
        this.messageID = value;
    }

    /**
     * Gets the value of the utcHour property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUtcHour() {
        return utcHour;
    }

    /**
     * Sets the value of the utcHour property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUtcHour(Integer value) {
        this.utcHour = value;
    }

    /**
     * Gets the value of the utcMinute property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUtcMinute() {
        return utcMinute;
    }

    /**
     * Sets the value of the utcMinute property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUtcMinute(Integer value) {
        this.utcMinute = value;
    }

    /**
     * Gets the value of the startSlot property.
     * 
     */
    public int getStartSlot() {
        return startSlot;
    }

    /**
     * Sets the value of the startSlot property.
     * 
     */
    public void setStartSlot(int value) {
        this.startSlot = value;
    }

    /**
     * Gets the value of the blockSize property.
     * 
     */
    public int getBlockSize() {
        return blockSize;
    }

    /**
     * Sets the value of the blockSize property.
     * 
     */
    public void setBlockSize(int value) {
        this.blockSize = value;
    }

    /**
     * Gets the value of the increment property.
     * 
     */
    public int getIncrement() {
        return increment;
    }

    /**
     * Sets the value of the increment property.
     * 
     */
    public void setIncrement(int value) {
        this.increment = value;
    }

    /**
     * Gets the value of the ownership property.
     * 
     * @return
     *     possible object is
     *     {@link Ownership }
     *     
     */
    public Ownership getOwnership() {
        return ownership;
    }

    /**
     * Sets the value of the ownership property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ownership }
     *     
     */
    public void setOwnership(Ownership value) {
        this.ownership = value;
    }

    /**
     * Gets the value of the usage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Sets the value of the usage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsage(String value) {
        this.usage = value;
    }

}
