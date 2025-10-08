package com.unoplus.www.smartgeofinderapi.repository;

import com.unoplus.www.smartgeofinderapi.domain.entity.PontosInteresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PontosInteresseRepository extends JpaRepository<PontosInteresse, Long> {
    @Query(
            """
            SELECT p FROM PontosInteresse p
            WHERE p.x BETWEEN :xMin AND :xMax
            AND p.y BETWEEN :yMin AND :yMax
            ORDER BY p.x, p.y
            """
    )
    List<PontosInteresse> encontrarPontosInteresseProximo(
            @Param("xMin") Long xMin,
            @Param("xMax") Long xMax,
            @Param("yMin") Long yMin,
            @Param("yMax") Long yMax
    );
}
