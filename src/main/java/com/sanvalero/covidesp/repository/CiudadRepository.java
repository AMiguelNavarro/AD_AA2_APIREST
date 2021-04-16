package com.sanvalero.covidesp.repository;

import com.sanvalero.covidesp.domain.Ciudad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

    List<Ciudad> findAll();
    Optional<Ciudad> findByNombre(String nombreCiudad);
    @Query(value = "SELECT * FROM ciudades c INNER JOIN hospitales h ON h.ciudad_id = c.id WHERE c.numero_habitantes > :numeroHabitantes AND h.dosis_vacuna_administradas > :dosisVacunaAdministradas AND c.plan_de_vacunacion = :planDeVacunacion", nativeQuery = true)
    List<Ciudad> findFiltradas(int numeroHabitantes, int dosisVacunaAdministradas, boolean planDeVacunacion);
}
