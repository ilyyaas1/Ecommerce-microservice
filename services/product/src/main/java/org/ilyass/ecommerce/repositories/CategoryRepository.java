package org.ilyass.ecommerce.repositories;

import org.ilyass.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
