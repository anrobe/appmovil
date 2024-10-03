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
@Table(name = "provincias", schema = "dbcarto_fundamento")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid", nullable = false)
    private Integer id;

    @Size(max = 4)
    @NotNull
    @Column(name = "idprov", nullable = false, length = 4)
    private String idprov;

    @Size(max = 40)
    @NotNull
    @Column(name = "provincia", nullable = false, length = 40)
    private String provincia;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Size(max = 30)
    @Column(name = "capital", length = 30)
    private String capital;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Size(max = 25)
    @Column(name = "fuente", length = 25)
    private String fuente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giddpto")
    private Departamento giddpto;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Type(type="jts_geometry")
    @Column(name = "geom", columnDefinition = "geometry(MultiPolygon,4326)")
    private MultiPolygon geom;

}