//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.10 at 10:21:18 AM EEST 
//


package dk.frv.eavdam.io.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fatdmaSlotAllocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fatdmaSlotAllocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fatdmaAllocationID" type="{http://eavdam.frv.dk/schema}fatdmaAllocation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fatdmaSlotAllocation", propOrder = {
    "fatdmaAllocationID"
})
public class FatdmaSlotAllocation {

    protected List<String> fatdmaAllocationID;

    /**
     * Gets the value of the fatdmaAllocationID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fatdmaAllocationID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFatdmaAllocationID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFatdmaAllocationID() {
        if (fatdmaAllocationID == null) {
            fatdmaAllocationID = new ArrayList<String>();
        }
        return this.fatdmaAllocationID;
    }

}
