package pe.gob.senamhi.movil.ubigeo.appmovil.service;

import pe.gob.senamhi.movil.ubigeo.appmovil.entity.Departamento;
import pe.gob.senamhi.movil.ubigeo.appmovil.vo.*;

import java.util.List;

public interface Ubigeo {

    //Limite Nacional
    List<LimiteNacionalVO> listLimiteNacional();

    LimiteNacionalVO getLimiteNacional(int id);

    //Departamento
    List<DepartamentoVO> getDepartamentoId(int limiteNacionalId);

    //Provincia
    List<ProvinciaVO> getProvinciaId(int provinciaId);

    //Distrito
    List<DistritoVO> getDistritoId(int distritoId);

    //Coordenadas del punto central del Distrito (Poligono)
    PtoCentralDistritoVO getPtoCentralDistrito(Integer distritoId);

    //Ubicacion del Usuario o Cliente, a partir de su Coordenada
    MiUbicacionVO getMiUbicacion(Double longitud, Double latitud);
}
