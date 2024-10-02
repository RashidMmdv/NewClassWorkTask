package az.writhline.product.controller;

import az.writhline.product.Dto.RequestDto;
import az.writhline.product.Dto.ResponseDto;
import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.repository.CategoryCount;
import az.writhline.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;


    @PostMapping
    public ResponseDto create(@RequestBody RequestDto product){
        return productService.create(product);
    }
    @GetMapping("/list")
    public List<ResponseDto> list(@RequestParam(value = "from",required = false)Integer priceFrom,
                                  @RequestParam(value = "to",required = false)Integer priceTo){
        return productService.list(priceFrom,priceTo);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
    @GetMapping("/count-by-category")
    public List<CategoryCount> countProductsByCategory() {
        return productService.countProductsByCategory();
    }


//    @GetMapping("/{id}")
//    public ProductsEntity getProduct(@PathVariable Long id){
//        return productService.getProduct(id);
//    }
}
