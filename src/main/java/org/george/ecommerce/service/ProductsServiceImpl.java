package org.george.ecommerce.service;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.repository.ProductCategoriesRepository;
import org.george.ecommerce.repository.ProductsRepository;
import org.george.ecommerce.repository.UsersRepository;
import org.george.ecommerce.repository.specification.ProductsSpecification;
import org.george.ecommerce.service.interfaces.IProductsService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ProductsServiceImpl implements IProductsService {

    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;
    private final ProductCategoriesRepository productCategoriesRepository;
    private final ProductsSpecification productsSpecification;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<ProductsModel> getAllProducts(Pageable pageable) {
        return productsRepository.findAll(pageable);
    }

    @Override
    public ProductsModel getProductById(Long productId) {
        if (productsRepository.findById(productId).isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        return productsRepository.findById(productId).get();
    }

    @Override
    public ProductsModel createProduct(@RequestBody ProductsModel productsModel) {
        if (usersRepository.findByUserLogin(productsModel.getProductCreator().getUserLogin()).isEmpty()) {
            throw new NotFoundException("Owner not found");
        }
        UsersModel creator = usersRepository.findByUserLogin(productsModel.getProductCreator().getUserLogin()).get();
        productsModel.setProductCreator(creator);
        return productsRepository.save(productsModel);
    }

    @Override
    public ProductsModel findProductByProductName(String productName) {
        if (productsRepository.findProductByProductName(productName).isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return productsRepository.findProductByProductName(productName).get();
    }

    @Override
    public ProductsModel updateProduct(Long productId, ProductsModel productsModel) {
        if(productsRepository.findById(productId).isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        ProductsModel storedProduct = productsRepository.findById(productId).get();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(productsModel, storedProduct);
        return productsRepository.save(storedProduct);

    }

    @Override
    public void deleteProductById(Long productId) {
        if (productsRepository.findById(productId).isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        productsRepository.deleteById(productId);
    }

    @Override
    public Page<ProductsModel> getAllProductsByFilter(ProductsModel productsModel, Pageable pageable) {
        return productsRepository.findAll(productsSpecification.productSpecification(productsModel), pageable);
    }

    @Override
    public Page<Set<ProductsModel>> findAllByProductCategoriesIn(String categoryName, Pageable pageable) {
        if (productCategoriesRepository.
                findProductCategoryByProductCategoryName(categoryName).isEmpty()
                && !categoryName.equalsIgnoreCase("all")) {
            throw new NotFoundException("Product category not found");
        }

        Set<ProductCategoriesModel> productCategories = Sets.newHashSet();

        if (categoryName.equalsIgnoreCase("all")) {
            List<ProductCategoriesModel> allCategories = productCategoriesRepository.findAll();
            productCategories.addAll(allCategories);
        } else {
            ProductCategoriesModel productCategory =
                    productCategoriesRepository.findProductCategoryByProductCategoryName(categoryName).get();
            productCategories.add(productCategory);
        }

        return productsRepository.findAllByProductCategoriesIn(productCategories, pageable);

    }

    @Override
    public Page<Set<ProductsModel>> findAllByProductCategoriesInAndProductNameContaining(String categoryName, String productName, Pageable pageable) {
        if (productCategoriesRepository.
                findProductCategoryByProductCategoryName(categoryName).isEmpty()
                && !categoryName.equalsIgnoreCase("all") ) {
            throw new NotFoundException("Product category not found");
        }

        if (productName.isBlank() || productName.equalsIgnoreCase("all")) {
            return findAllByProductCategoriesIn(categoryName, pageable);
        }

        Set<ProductCategoriesModel> productCategories = Sets.newHashSet();

        if (categoryName.equalsIgnoreCase("all")) {
            List<ProductCategoriesModel> allCategories = productCategoriesRepository.findAll();
            productCategories.addAll(allCategories);
        } else {
            ProductCategoriesModel productCategory =
                    productCategoriesRepository.findProductCategoryByProductCategoryName(categoryName).get();
            productCategories.add(productCategory);
        }

        return productsRepository.findAllByProductCategoriesInAndProductNameContaining(productCategories, productName, pageable);

    }
}
