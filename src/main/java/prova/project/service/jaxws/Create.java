
package prova.project.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.9
 * Tue Jun 27 18:23:16 BRT 2017
 * Generated source version: 3.1.9
 */

@XmlRootElement(name = "create", namespace = "http://service.project.prova/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "create", namespace = "http://service.project.prova/")

public class Create {

    @XmlElement(name = "json")
    private java.lang.String json;

    public java.lang.String getJson() {
        return this.json;
    }

    public void setJson(java.lang.String newJson)  {
        this.json = newJson;
    }

}

