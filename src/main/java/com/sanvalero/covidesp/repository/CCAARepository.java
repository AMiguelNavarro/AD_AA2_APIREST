package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.ComunidadAutonoma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Con CRUD repository genera de forma automática los método básicos del CRUD
*  Hay que indicarle la clase a la que hace referencia y el tipo de dato de la primery key (long en este caso)
* */
@Repository
public interface CCAARepository extends CrudRepository<ComunidadAutonoma, Long> {

    // Se pueden sobreescribir los que se quiera, en este caso sobreescribo el findAll porque el de serie
    // de CRUDrepository devuelve un iterable y así devuelve una lista
    List<ComunidadAutonoma> findAll();

}
