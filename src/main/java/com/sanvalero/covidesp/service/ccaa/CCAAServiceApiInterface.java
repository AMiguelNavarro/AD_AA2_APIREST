package com.sanvalero.covidesp.service.ccaa;

import com.sanvalero.covidesp.domain.ComunidadAutonoma;

import java.util.List;

// Aqui podemos poner los nombres que nosotros queramos.
// En el service es donde irá toda la lógica de negocio, por ello lo mejor es dividirlo en 2 clases, una interfaz y
// una clase que la implementa para conseguir escalabilidad en un futuro
public interface CCAAServiceApiInterface {

    List<ComunidadAutonoma> findAllCCAA();
    List<ComunidadAutonoma> findByNombre();

}
