package pe.gob.senamhi.movil.ubigeo.appmovil.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;

import pe.gob.senamhi.movil.ubigeo.appmovil.exception.ErrorMessage;
import pe.gob.senamhi.movil.ubigeo.appmovil.service.Ubigeo;

@Validated
@RestController
@RequestMapping("/spatial")
public class UbigeoController {

    private final Ubigeo ubigeo;

    public UbigeoController(@Qualifier("ubigeo") Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    @GetMapping(name="Limite Nacional",value = "/getLimiteNacionalAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLimiteNacionalAll() {
        try {
            return new ResponseEntity<>(ubigeo.listLimiteNacional(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name="Limite Nacional por ID",value = "/getLimiteNacionalById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLimiteNacionalById(@PathVariable @Positive(message="Invalid ID") int id) {
        try {
            return new ResponseEntity<>(ubigeo.getLimiteNacional(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name="Departamento por ID",value = "/getDepartamentoById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDepartamentoById(@PathVariable @Positive(message="Invalid ID") int id) {
        try {
            return new ResponseEntity<>(ubigeo.getDepartamentoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name="Provincia por ID",value = "/getProvinciaById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProvinciaById(@PathVariable @Positive(message="Invalid ID") int id) {
        try {
            return new ResponseEntity<>(ubigeo.getProvinciaId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name="Distrito por ID",value = "/getDistritoById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDistritoById(@PathVariable @Positive(message="Invalid ID") int id) {
        try {
            return new ResponseEntity<>(ubigeo.getDistritoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(name="Coordenada del Distrito por ID",value = "/getDistritoCoordenadaById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDistritoCoordenadaById(@PathVariable @Positive(message="Invalid ID") Integer id) {
        try {
            return new ResponseEntity<>(ubigeo.getPtoCentralDistrito(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("No Record for District for ID: " + id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(
            name = "Ubica el Departamento, Provincia y Distrito a partir de la coordenada",
            value = "/getUbica/{longitud}/{latitud}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getUbica(
            @PathVariable @DecimalMin("-180.0") @DecimalMax("180.0") Double longitud,
            @PathVariable @DecimalMin("-90.0") @DecimalMax("90.0") Double latitud
    ) {
        try {
            return new ResponseEntity<>(ubigeo.getMiUbicacion(longitud, latitud), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("It is outside the national territory"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
