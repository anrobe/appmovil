package pe.gob.senamhi.movil.ubigeo.appmovil.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.movil.ubigeo.appmovil.entity.PtoCentralDistrito;

@Repository("ptoCentralDistritoRepository")
public interface PtoCentralDistritoRepository extends JpaRepository<PtoCentralDistrito, Integer> {
}