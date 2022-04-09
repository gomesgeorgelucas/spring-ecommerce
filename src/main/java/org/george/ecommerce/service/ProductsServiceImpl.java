package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.ProductsModel;
import org.george.ecommerce.domain.model.UsersModel;
import org.george.ecommerce.domain.views.ProductCategoriesModelView;
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

@AllArgsConstructor
@Service
public class ProductsServiceImpl implements IProductsService {

    final ProductsRepository productsRepository;
    final UsersRepository usersRepository;
    final ProductsSpecification productsSpecification;
    final ModelMapper modelMapper = new ModelMapper();


    @Override
    public Page<ProductCategoriesModelView> findByProductAndCategory(Pageable pageable) {
        return productsRepository.findByProductAndCategory(pageable);
    }

    @Override
    public Page<ProductsModel> getAllProducts(Pageable pageable) {
        Page<ProductsModel> page = productsRepository.findAll(pageable);
        return page;
    }

    @Override
    public ProductsModel getProductById(Long productId) {
        if (productsRepository.findById(productId).isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        ProductsModel productsModel = productsRepository.findById(productId).get();
        return productsModel;
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
}
