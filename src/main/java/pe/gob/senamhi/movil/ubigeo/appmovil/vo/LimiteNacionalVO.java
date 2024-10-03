package pe.gob.senamhi.movil.ubigeo.appmovil.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LimiteNacionalVO {

    @NotNull
    private Integer id;

    @NotEmpty
    private String pais;

}
