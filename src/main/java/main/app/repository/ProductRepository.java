package main.app.repository;

import main.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
@Query(value = "SELECT * from product where product_name=:productName", nativeQuery = true)
    public List<Product> getProductDetailsByName(String productName);
@Query(value = "SELECT * from product where product_name like %:productNameContains%", nativeQuery = true)
    public List<Product> getAllProductsContainsName(String productNameContains);
}
