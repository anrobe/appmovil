package pe.gob.senamhi.movil.ubigeo.appmovil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.senamhi.movil.ubigeo.appmovil.entity.Departamento;

import java.util.List;

@Repository("departamentoRepository")
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    @Query("SELECT d FROM Departamento d WHERE d.gidlimnac.id = :limiteNacionalId")
    List<Departamento> findByLimiteNacionalId(@Param("limiteNacionalId") Integer limiteNacionalId);

}