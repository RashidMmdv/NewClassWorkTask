package az.writhline.product.service;

import az.writhline.product.Dto.RequestDto;
import az.writhline.product.Dto.ResponseDto;
import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.model.Category;
import az.writhline.product.model.ProductDetails;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.model.ShoppingCarts;
import az.writhline.product.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImp.class);
    private final ProductRepository productRepository;
    private final ProductDetailRepository detailRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;






    @Override
    public ResponseDto create(RequestDto product) {

        Category category = Category.builder()
                .name(product.getCategory().getName())
                .build();
        ProductDetails details = ProductDetails.builder()
                .color(product.getProductDetails().getColor())
                .image_url(product.getProductDetails().getImageUrl())
                .build();


        ProductsEntity products = ProductsEntity.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .productDetails(details)
                .category(category)
                .build();
        categoryRepository.save(category);
        detailRepository.save(details);


        return modelMapper.map(productRepository.save(products),ResponseDto.class);
    }



    @Override
    public List<ResponseDto> list(Integer from, Integer to) {
        if (from == null && to == null) {
            return productRepository.findAll()
                    .stream()
                    .map(productsEntity -> modelMapper.map(productsEntity,ResponseDto.class))
                    .toList();
        }

        return productRepository.getProductsEntityByIdBetween(from, to)
                .stream()
                .map(productsEntity -> modelMapper.map(productsEntity,ResponseDto.class))
                .toList();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<CategoryCount> countProductsByCategory() {
        return productRepository.countProductsByCategory();
    }

    @Override
    public ProductsEntity getProduct(Long id) {
        return  productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));

}


}
