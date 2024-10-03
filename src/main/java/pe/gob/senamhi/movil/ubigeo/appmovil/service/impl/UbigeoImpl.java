package pe.gob.senamhi.movil.ubigeo.appmovil.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.senamhi.movil.ubigeo.appmovil.entity.*;
import pe.gob.senamhi.movil.ubigeo.appmovil.exception.NoRecordsException;
import pe.gob.senamhi.movil.ubigeo.appmovil.repository.*;
import pe.gob.senamhi.movil.ubigeo.appmovil.service.Ubigeo;
import pe.gob.senamhi.movil.ubigeo.appmovil.vo.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service("ubigeo")
@Transactional(readOnly = true)
public class UbigeoImpl implements Ubigeo {

    private static final Logger log = LoggerFactory.getLogger(UbigeoImpl.class);

    @PersistenceContext
    private EntityManager em;

    private final LimiteNacionalRepository limiteNacionalRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    public UbigeoImpl(LimiteNacionalRepository limiteNacionalRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository) {
        this.limiteNacionalRepository = limiteNacionalRepository;
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
    }

    @Override
    public List<LimiteNacionalVO> listLimiteNacional() {
        List<LimiteNacional> list = limiteNacionalRepository.findAll();
        if(list.isEmpty()){
            throw new NoRecordsException("No listLimiteNacional found");
        }
        List<LimiteNacionalVO> vos = new LinkedList<>();
        list.forEach(entity->{
            LimiteNacionalVO vo = new LimiteNacionalVO();
            BeanUtils.copyProperties(entity, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public LimiteNacionalVO getLimiteNacional(int id) {
        Optional<LimiteNacional> entity = limiteNacionalRepository.findById(id);
        if(!entity.isPresent()){
            throw new NoRecordsException("No Records for National Limit for ID: " + id);
        }
        LimiteNacionalVO vo = new LimiteNacionalVO();
        BeanUtils.copyProperties(entity.get(), vo);
        return vo;
    }

    @Override
    public List<DepartamentoVO> getDepartamentoId(int limiteNacionalId) {
        List<Departamento> list = departamentoRepository.findByLimiteNacionalId(limiteNacionalId);
        if(list.isEmpty()){
            throw new NoRecordsException("No Records for Department for ID: " + limiteNacionalId);
        }
        List<DepartamentoVO> vos = new LinkedList<>();
        list.forEach(entity->{
            DepartamentoVO vo = new DepartamentoVO();
            BeanUtils.copyProperties(entity, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public List<ProvinciaVO> getProvinciaId(int provinciaId) {
        List<Provincia> list = provinciaRepository.findByProvinciaId(provinciaId);
        if(list.isEmpty()){
            throw new NoRecordsException("No Records for Province for ID: " + provinciaId);
        }
        List<ProvinciaVO> vos = new LinkedList<>();
        list.forEach(entity->{
            ProvinciaVO vo = new ProvinciaVO();
            BeanUtils.copyProperties(entity, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public List<DistritoVO> getDistritoId(int distritoId) {
        List<Distrito> list = distritoRepository.findByDistritoId(distritoId);
        if(list.isEmpty()){
            throw new NoRecordsException("No Records for District for ID: " + distritoId);
        }
        List<DistritoVO> vos = new LinkedList<>();
        list.forEach(entity->{
            DistritoVO vo = new DistritoVO();
            BeanUtils.copyProperties(entity, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public PtoCentralDistritoVO getPtoCentralDistrito(Integer distritoId) {
        PtoCentralDistritoVO pto = new PtoCentralDistritoVO();

        String sql = "" +
                "SELECT gid, ST_X(ST_Centroid(geom)) AS longitud, ST_Y(ST_Centroid(geom)) AS latitud " +
                "FROM dbcarto_fundamento.distritos " +
                "WHERE gid = ?" +
                "";

        Object[] result = (Object[]) em.createNativeQuery(sql)
                .setParameter(1,distritoId)
                .getSingleResult();

        if (result != null) {
            pto.setId((Integer) result[0]);
            pto.setLongitud((Double) result[1]);
            pto.setLatitud((Double) result[2]);
        }
        return pto;
    }

    @Override
    public MiUbicacionVO getMiUbicacion(Double longitud, Double latitud) {
        MiUbicacionVO miUbicacion = new MiUbicacionVO();

        int idLimiteNacional = 1;

        String sql = "" +
                "SELECT de.departamento, pr.provincia, di.distrito " +
                "FROM (" +
                "dbcarto_fundamento.limite_nacional ln JOIN dbcarto_fundamento.departamentos de ON (ln.gid=de.gidlimnac) " +
                "JOIN dbcarto_fundamento.provincias pr ON(de.gid=pr.giddpto) " +
                "JOIN dbcarto_fundamento.distritos di ON(pr.gid=di.gidprov)" +
                ")," +
                "(SELECT ST_SetSRID(ST_MakePoint(?,?), 4326) AS geom) pt " +
                "WHERE de.gidlimnac=? AND ST_Contains(di.geom, pt.geom)" +
                "";

        Object[] result = (Object[]) em.createNativeQuery(sql)
                .setParameter(1,longitud)
                .setParameter(2,latitud)
                .setParameter(3,idLimiteNacional)
                .getSingleResult();

        if (result != null) {
            miUbicacion.setDepartamento((String) result[0]);
            miUbicacion.setProvincia((String) result[1]);
            miUbicacion.setDistrito((String) result[2]);
        }

        return miUbicacion;
    }

}
