package pe.gob.senamhi.movil.ubigeo.appmovil.vo;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import pe.gob.senamhi.movil.ubigeo.appmovil.entity.Provincia;

@Getter
@Setter
public class DistritoVO {

    @NotNull
    private Integer id;

    @NotEmpty
    private String iddist;

    @NotEmpty
    private String distrito;

    @JsonIgnore
    private Provincia gidprov;
}
