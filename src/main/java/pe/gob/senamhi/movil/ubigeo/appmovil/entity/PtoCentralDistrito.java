package pe.gob.senamhi.movil.ubigeo.appmovil.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;

import com.vividsolutions.jts.geom.MultiPolygon;

import javax.persistence.*;

@Entity
@Table(name = "distritos", schema = "dbcarto_fundamento")
@Access(AccessType.FIELD)
public class PtoCentralDistrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid", nullable = false)
    private Integer id;

    @Type(type="jts_geometry", parameters = {
            @Parameter(name = "geometryType", value = "MULTIPOLYGON"),
            @Parameter(name = "srid", value = "4326")
    })
    @Column(name = "geom", columnDefinition = "geometry(MultiPolygon,4326)")
    private MultiPolygon geom;


}
