package br.com.desafiojavaspringboottest.repository;

import br.com.desafiojavaspringboottest.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Long> {

//    @Query("SELECT p FROM Products p WHERE p.price >= :min_price AND p.price <= :max_price AND p.name LIKE CONCAT ('%',:q,'%') AND p.description LIKE CONCAT('%',:q,'%')")
    @Query("SELECT p FROM Products p WHERE p.price >= :min_price AND p.price <= :max_price AND (:q = 'superget' OR (p.name LIKE CONCAT ('%',:q,'%') OR p.description LIKE CONCAT('%',:q,'%')))")
    List<Products> findByPrice(@Param("min_price") Double min_price, @Param("max_price") Double max_price, @Param("q") String q);
}
