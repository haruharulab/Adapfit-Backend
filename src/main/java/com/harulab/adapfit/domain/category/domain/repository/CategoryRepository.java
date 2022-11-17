package com.harulab.adapfit.domain.category.domain.repository;

import com.harulab.adapfit.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.name = :name")
    Optional<Category> findByCategoryName(@Param("name") String name);
}
