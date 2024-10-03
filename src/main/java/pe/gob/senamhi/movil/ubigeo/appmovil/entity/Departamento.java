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
@Table(name = "departamentos", schema = "dbcarto_fundamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid", nullable = false)
    private Integer id;

    @Size(max = 2)
    @Column(name = "iddpto", length = 2)
    private String iddpto;

    @Size(max = 25)
    @Column(name = "departamento", length = 25)
    private String departamento;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Size(max = 35)
    @Column(name = "capital", length = 35)
    private String capital;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Size(max = 25)
    @NotNull
    @Column(name = "fuente", nullable = false, length = 25)
    private String fuente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gidlimnac")
    private LimiteNacional gidlimnac;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Type(type="jts_geometry")
    @Column(name = "geom", columnDefinition = "geometry(MultiPolygon,4326)")
    private MultiPolygon geom;

}