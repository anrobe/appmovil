package pe.gob.senamhi.movil.ubigeo.appmovil.vo;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import pe.gob.senamhi.movil.ubigeo.appmovil.entity.LimiteNacional;

@Getter
@Setter
public class DepartamentoVO {

    @NotNull
    private Integer id;

    @NotEmpty
    private String iddpto;

    @NotEmpty
    private String departamento;

    //@JsonBackReference
    @JsonIgnore
    private LimiteNacional gidlimnac;
}
