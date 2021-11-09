package br.com.desafiojavaspringboottest.controller;

import br.com.desafiojavaspringboottest.exception.ResourceNotFoundException;
import br.com.desafiojavaspringboottest.model.Products;
import br.com.desafiojavaspringboottest.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/products"})
public class ProductsController {

    @Autowired
    private IProductsRepository productsRepository;

    ProductsController(IProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    // Criação de um produto
    @PostMapping()
    public Products createProduct(@Valid @RequestBody Products products){
        return productsRepository.save(products);
    }

    // Atualização de um produto
    @PutMapping(value = "/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable(value = "id") long id,
                                                  @Valid @RequestBody Products errorDetails) throws ResourceNotFoundException {
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para este id :: " + id));
        products.setName(errorDetails.getName());
        products.setDescription(errorDetails.getDescription());
        products.setPrice(errorDetails.getPrice());
        return ResponseEntity.ok().body(products);
    }

    // Busca de um produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductsId(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
            Products products = productsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para este id :: " + id));
            return ResponseEntity.ok().body(products);

    }

    // Lista de produtos
    @GetMapping()
    public List findAll(){
        return productsRepository.findAll();
    }

    // Lista de produtos filtrados
    @GetMapping("/search")
    public ResponseEntity<List<Products>> findByFiltros(@RequestParam("min_price") Double min_price, @RequestParam("max_price") Double max_price, @RequestParam("q") String q) {
        List<Products> products = productsRepository.findByPrice(min_price, max_price, q);
        return ResponseEntity.ok().body(products);
    }


    // Deleção de um produto
    @DeleteMapping(path ={"/{id}"})
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException{
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para este id :: " + id));

        productsRepository.delete(products);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

}
