package cl.bci.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "user")
@XmlRootElement
@Getter
@Setter
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    @Type(type = "uuid-char")
    private UUID userId = UUID.randomUUID();
	@Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Basic(optional = false)
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Basic(optional = false)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @Column(name = "isactive")
    private boolean isactive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Phones> phoneList;

    
}
