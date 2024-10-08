package az.writhline.product.service;

import az.writhline.product.Dto.RequestDto;
import az.writhline.product.Dto.ResponseDto;
import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.model.ShoppingCarts;
import az.writhline.product.repository.CategoryCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public ResponseDto create(RequestDto product);

    List<ResponseDto> list(Integer from, Integer to);

    void delete(Long id);

    public List<CategoryCount> countProductsByCategory();


    ProductsEntity getProduct(Long id);
}
