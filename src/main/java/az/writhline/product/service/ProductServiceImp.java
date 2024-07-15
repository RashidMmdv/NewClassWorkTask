package az.writhline.product.service;

import az.writhline.product.Dto.RequestDto;
import az.writhline.product.Dto.ResponseDto;
import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.model.Category;
import az.writhline.product.model.ProductDetails;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.model.ShoppingCarts;
import az.writhline.product.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ProductDetailRepository detailRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;














    @Override
    public ResponseDto create(RequestDto product) {

        //        if (product == null) {
//            throw new IllegalArgumentException("Product cannot be null");
//        }
//
//        ProductsEntity products = modelMapper.map(product, ProductsEntity.class);
//
//
////        if (product.getProductDetails() != null) {
////            ProductDetails productDetail = modelMapper.map(product.getProductDetails(), ProductDetails.class);
////            productDetail.setProduct(products);
////            products.setProductDetails(productDetail);
////        }
//        if (product.getProductDetails() != null) {
//            ProductDetails productDetail = modelMapper.map(product.getProductDetails(), ProductDetails.class);
////            detailRepository.save(productDetail);  // Explicitly save product details
//            products.setProductDetails(productDetail);
////            productDetail.setProduct(products);
////            products.setProductDetails(productDetail);
//        }
//
//        // Handle Category
//        if (product.getCategory() != null) {
//            Category category = categoryRepository.findByName(product.getCategory().getName());
//            if (category == null) {
//                category = new Category();
//                category.setName(product.getCategory().getName());
//                categoryRepository.save(category);
//            }
//            products.setCategory(category);
//        }



        //--------------------

//        if (product.getProductDetails() == null) {
//            new ProductDetails();
//        }
//
//
//        ProductDetails productDetails = ProductDetails.builder()
//                .color(product.getProductDetails().getColor())
//                .image_url(product.getProductDetails().getImageUrl())
//                .build();
//
//        ProductsEntity products = ProductsEntity.builder()
//                .name(product.getName())
//                .price(product.getPrice())
//                .description(product.getDescription())
//                .productDetails(productDetails)
//                .build();

//        ShoppingCarts cart = new ShoppingCarts();
//        cart.setName(name.getName());
//        ShoppingCarts savedCart = shoppingCartRepository.save(cart);
//        return modelMapper.map(savedCart, ShoppingCartDto.class);

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

        return productRepository.getProductsEntityByPriceBetween(from, to)
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


}
