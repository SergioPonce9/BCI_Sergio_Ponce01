package cl.bci.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "phone")
@XmlRootElement
@Getter
@Setter
public class Phones implements Serializable{
    @Id
    @Basic(optional = false)
    @Column(name = "number")
    private String number;
    @Basic(optional = false)
    @Column(name = "citycode")
    private String cityCode;
    @Basic(optional = false)
    @Column(name = "contrycode")
    private String contryCode;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

}
