package pe.gob.senamhi.movil.ubigeo.appmovil.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PtoCentralDistritoVO {

    @NotNull
    private Integer id;

    @NotEmpty
    private Double longitud;

    @NotEmpty
    private Double latitud;

}
