package pe.gob.senamhi.movil.ubigeo.appmovil.entity;

import com.vividsolutions.jts.geom.MultiPolygon;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "limite_nacional", schema = "dbcarto_fundamento")
public class LimiteNacional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid", nullable = false)
    private Integer id;

    @NotNull
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String pais;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    @Type(type="jts_geometry")
    @Column(name = "geom", columnDefinition = "geometry(MultiPolygon,4326)")
    private MultiPolygon geom;

}