package main.app.controller;

import main.app.entity.Product;
import main.app.model.ProductInformation;
import main.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/getAllProducts")
    public List<ProductInformation> getAllProductsWithNameAndPrice() {
        List<Product> productsInDB = productRepository.findAll();
        List<ProductInformation> productsWithNameAndPrice = new ArrayList<>();
        for (Product product : productsInDB) {
            ProductInformation productInformation = new ProductInformation();
            productInformation.setProductName(product.getProductName());
            productInformation.setPrice(product.getPrice());
            productsWithNameAndPrice.add(productInformation);
        }
        return productsWithNameAndPrice;
    }

    @GetMapping("/getAllProductsDetailedInfo")
    public List<Product> getAllProductsWithDetailedInformation() {
        List<Product> productsInDB = productRepository.findAll();
        for(int i = 0; i < productsInDB.size(); i++){
            System.out.println(productsInDB.get(i).getProductId()+", " + productsInDB.get(i).getProductName());
        }
        return productsInDB;
    }
    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id).get();
    return product;
    }
    @GetMapping("/getProductByName/{productName}")
    public List<Product> getProductByName(@PathVariable("productName") String productName) {
        List<Product> productList = productRepository.getProductDetailsByName(productName);
        return productList;
    }
    @GetMapping("/getProductsContains/{productName}")
    public List<Product> getProductsContains(@PathVariable("productName") String productName) {
    List<Product> productList = productRepository.getAllProductsContainsName(productName);
    return productList;
    }
//Promotons Team
    @PostMapping("/createDetailedProduct")
    public Product createDetailedProduct(@RequestBody Product product){
        Product newProduct = productRepository.save(product);
        return newProduct;
    }
//Vendor
    @PostMapping("/createProduct")
    public ProductInformation createDetailedProduct(@RequestBody ProductInformation product){
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setPrice(product.getPrice());
        productRepository.save(newProduct);
        return product;
    }
    //Promo team update
    @PutMapping("/updateDetailedProduct")
    public Product updateProductById(@RequestBody Product product){
        return productRepository.save(product);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public String deleteProductById(@PathVariable("id") Long productId){
        productRepository.deleteById(productId);
        return "Product Id "+productId+"Deleted Successfully from DB.";
    }
}
