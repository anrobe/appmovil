package pe.gob.senamhi.movil.ubigeo.appmovil.entity;

import com.vividsolutions.jts.geom.MultiPolygon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "distritos", schema = "dbcarto_fundamento")
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid", nullable = false)
    private Integer id;

    @Size(max = 6)
    @NotNull
    @Column(name = "iddist", nullable = false, length = 6)
    private String iddist;

    @Size(max = 50)
    @NotNull
    @Column(name = "distrito", nullable = false, length = 50)
    private String distrito;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Size(max = 60)
    @Column(name = "capital", length = 60)
    private String capital;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Size(max = 25)
    @Column(name = "fuente", length = 25)
    private String fuente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gidprov")
    private Provincia gidprov;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Type(type="jts_geometry")
    @Column(name = "geom", columnDefinition = "geometry(MultiPolygon,4326)")
    private MultiPolygon geom;

}