
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

@XmlRootElement(name = "retreiveByTeamResponse", namespace = "http://service.project.prova/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retreiveByTeamResponse", namespace = "http://service.project.prova/")

public class RetreiveByTeamResponse {

    @XmlElement(name = "return")
    private java.lang.String _return;

    public java.lang.String getReturn() {
        return this._return;
    }

    public void setReturn(java.lang.String new_return)  {
        this._return = new_return;
    }

}

