package org.george.ecommerce.service;

import lombok.AllArgsConstructor;
import org.george.ecommerce.domain.model.ProductCategoriesModel;
import org.george.ecommerce.repository.ProductCategoriesRepository;
import org.george.ecommerce.service.interfaces.IProductCategoriesService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@AllArgsConstructor
@Service
public class ProductCategoriesServiceImpl implements IProductCategoriesService {
    final ProductCategoriesRepository productCategoriesRepository;
    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<ProductCategoriesModel> getAllProductCategories(Pageable pageable) {
        return productCategoriesRepository.findAll(pageable);
    }

    @Override
    public ProductCategoriesModel getProductCategory(Long productCategoryId) {
        if (productCategoriesRepository.findById(productCategoryId).isEmpty()) {
            throw new NotFoundException("Product Category not found");
        }
        return productCategoriesRepository.findById(productCategoryId).get();
    }

    @Override
    public ProductCategoriesModel createProductCategory(ProductCategoriesModel productCategoriesModel) {
        return productCategoriesRepository.save(productCategoriesModel);
    }

    @Override
    public ProductCategoriesModel updateProductCategory(String productCategoryName, ProductCategoriesModel productCategoriesModel) {
        if (productCategoriesRepository.findProductCategoryByProductCategoryName(productCategoryName).isEmpty()) {
            throw new NotFoundException("Product Category not found");
        }
        ProductCategoriesModel storeProductCategory = productCategoriesRepository.findProductCategoryByProductCategoryName(productCategoryName).get();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(productCategoriesModel, storeProductCategory);
        return productCategoriesRepository.save(storeProductCategory);
    }

    @Override
    public void deleteProductCategoryById(Long productCategoryId) {
        if (productCategoriesRepository.findById(productCategoryId).isEmpty()) {
            throw new NotFoundException("Product Category not found");
        }
        productCategoriesRepository.deleteById(productCategoryId);
    }
}
