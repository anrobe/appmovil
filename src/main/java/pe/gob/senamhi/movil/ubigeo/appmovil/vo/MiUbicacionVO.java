package pe.gob.senamhi.movil.ubigeo.appmovil.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MiUbicacionVO {

    @NotEmpty
    private String departamento;

    @NotEmpty
    private String provincia;

    @NotEmpty
    private String distrito;

}
