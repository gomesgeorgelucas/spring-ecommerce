package org.george.ecommerce.repository.specification;

import org.george.ecommerce.domain.model.ProductsModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsSpecification {

    public Specification<ProductsModel> productSpecification(ProductsModel productsFilter) {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if (!ObjectUtils.isEmpty(productsFilter.getProductName())) {
                predicateList.add(
                        criteriaBuilder.like(
                                criteriaBuilder.upper(root.get("productName")), "%" + productsFilter.getProductName().
                                        toUpperCase().concat("%")
                        )
                );
            }

            if (!ObjectUtils.isEmpty(productsFilter.getProductDescription())) {
                predicateList.add(
                        criteriaBuilder.like(
                                criteriaBuilder.upper(root.get("productDescription")), "%" + productsFilter.getProductName().
                                        toUpperCase().concat("%")
                        )
                );
            }

            if (!ObjectUtils.isEmpty(productsFilter.getProductUnitPrice())) {
                predicateList.add(
                        criteriaBuilder.equal(
                                root.get("productUnitPrice"), productsFilter.getProductUnitPrice())
                );
            }

            return criteriaBuilder.or(predicateList.toArray(Predicate[]::new));
        });

    }
}
