package pe.gob.senamhi.movil.ubigeo.appmovil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.senamhi.movil.ubigeo.appmovil.entity.LimiteNacional;

import java.util.Optional;

@Repository("limiteNacionalRepository")
public interface LimiteNacionalRepository extends JpaRepository<LimiteNacional, Integer> {

    Optional<LimiteNacional> findById(int id);
}