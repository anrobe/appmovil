package pe.gob.senamhi.movil.ubigeo.appmovil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.senamhi.movil.ubigeo.appmovil.entity.Distrito;

import java.util.List;

@Repository("distritoRepository")
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {

    /*
    @Query("SELECT d FROM Distrito d WHERE d.gidprov.id = :distritoId")
    List<Distrito> findByDistritoId(@Param("distritoId") Integer distritoId);
    */

    @Query(value = "SELECT * FROM dbcarto_fundamento.distritos d WHERE d.gidprov = :distritoId", nativeQuery = true)
    List<Distrito> findByDistritoId(@Param("distritoId") Integer distritoId);
}