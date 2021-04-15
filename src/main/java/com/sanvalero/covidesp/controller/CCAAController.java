package com.sanvalero.covidesp.controller;

import com.sanvalero.covidesp.controller.errors.ccaa.CCAANotFoundException;
import com.sanvalero.covidesp.controller.errors.Response;
import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import com.sanvalero.covidesp.service.ccaa.CCAAServiceApiInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sanvalero.covidesp.controller.errors.Response.METHOD_NOT_ALLOWED;
import static com.sanvalero.covidesp.controller.errors.Response.NOT_FOUND;


@RestController
@Tag(name = "CCAA - Comunidades Autónomas", description = "Listado de CCCA - Comunidades Autónomas")
public class CCAAController {

    @Autowired
    private CCAAServiceApiInterface ccaaServiceApiInterface;


    @Operation(summary = "Obtiene un listado con todas las Comunidades Autónomas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Listado de Comunidades Autónomas", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComunidadAutonoma.class))))
    })
    @GetMapping(value = "/ccaa", produces = "application/json")
    public ResponseEntity<List<ComunidadAutonoma>> getAll() {
        List<ComunidadAutonoma> listadoCCAA = ccaaServiceApiInterface.findAllCCAA();
        return new ResponseEntity<>(listadoCCAA, HttpStatus.OK);
    }



    @Operation(summary = "Busca una Comunidad Autónoma por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Se encuentra la comunidad con ese ID correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComunidadAutonoma.class)))),
            @ApiResponse(responseCode = "404" , description = "La comunidad no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @GetMapping(value = "/ccaa/{id}", produces = "application/json")
    public ResponseEntity<ComunidadAutonoma> getById(@PathVariable long id) {
        ComunidadAutonoma ccaa = ccaaServiceApiInterface.findById(id)
                .orElseThrow(() -> new CCAANotFoundException(id));
        return new ResponseEntity<>(ccaa, HttpStatus.OK);
    }



    @Operation(summary = "Añade una Comunidad Autónoma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se añade correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComunidadAutonoma.class)))),
            @ApiResponse(responseCode = "409" , description = "La comunidad ya existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PostMapping(value = "/ccaa", produces = "application/json")
    public ResponseEntity<ComunidadAutonoma> addNew(@RequestBody ComunidadAutonoma comunidadAutonoma) {
        ccaaServiceApiInterface.addNew(comunidadAutonoma);
        return new ResponseEntity<>(comunidadAutonoma, HttpStatus.CREATED);
    }


    @Operation(summary = "Modifica una Comunidad Autónoma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Se modifica correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ComunidadAutonoma.class)))),
            @ApiResponse(responseCode = "404" , description = "La comunidad a modificar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @PutMapping(value = "/ccaa/{id}", produces = "application/json")
    public ResponseEntity<ComunidadAutonoma> modifyAllFromCCAA(@PathVariable long id, @RequestBody ComunidadAutonoma nuevaCCCAA) {
        ComunidadAutonoma ccaa = ccaaServiceApiInterface.modififyCCAA(id, nuevaCCCAA);
        return new ResponseEntity<>(ccaa, HttpStatus.CREATED);
    }


    @Operation(summary = "Elimina una Comunidad Autónoma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Se elimina correctamente", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class)))),
            @ApiResponse(responseCode = "404" , description = "La comunidad a eliminar no existe", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Response.class))))
    })
    @DeleteMapping(value = "/ccaa/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteCCAA(@PathVariable long id) {
        ccaaServiceApiInterface.deleteCCAA(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }


    /**
     * Para controlar la excepción NOT_FOUND ERROR CODE 404
     * @param ccaanfe
     * @return
     */
    @ExceptionHandler(CCAANotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleNotFoundException(CCAANotFoundException ccaanfe) {
        Response response = Response.errorResponse(NOT_FOUND, ccaanfe.getMessage());
//        logger.error(ccaanfe.getMessage(), ccaanfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



}
