package pe.gob.senamhi.movil.ubigeo.appmovil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.senamhi.movil.ubigeo.appmovil.entity.Provincia;

import java.util.List;

@Repository("provinciaRepository")
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {

    @Query("SELECT d FROM Provincia d WHERE d.giddpto.id = :provinciaId")
    List<Provincia> findByProvinciaId(@Param("provinciaId") Integer provinciaId);
}