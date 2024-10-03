package pe.gob.senamhi.movil.ubigeo.appmovil.vo;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import pe.gob.senamhi.movil.ubigeo.appmovil.entity.Departamento;

@Getter
@Setter
public class ProvinciaVO {

    @NotNull
    private Integer id;

    @NotEmpty
    private String idprov;

    @NotEmpty
    private String provincia;

    @JsonIgnore
    private Departamento giddpto;
}
