package com.bicky.demopayment.productservice.product.infrastructure.repository.postgres;

import com.bicky.demopayment.productservice.product.annotations.CreateElasticProduct;
import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import com.bicky.demopayment.productservice.product.infrastructure.repository.jpa.JpaProductRepository;
import com.bicky.demopayment.productservice.product.infrastructure.repository.model.ProductModel;
import com.bicky.demopayment.productservice.valueobject.ProductFilterPayload;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostgreSqlProductRepository implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;
    private final EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    @CreateElasticProduct
    @Override
    public Product save(Product product) {
        ProductModel productModel =  jpaProductRepository.save(ProductModel.fromEntity(product));
        return ProductModel.toEntity(productModel);
    }

    @Override
    public List<Product> findAll(Pageable pageable) {
        return jpaProductRepository.findAll(pageable).stream().map(ProductModel::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Product> filter(ProductFilterPayload filterPayload) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductModel> criteriaQuery = criteriaBuilder.createQuery(ProductModel.class);
        Root<ProductModel> root = criteriaQuery.from(ProductModel.class);

        Predicate predicate = getFilterPredicate(filterPayload, root);
        setOrder(filterPayload, criteriaQuery, root);
        criteriaQuery.where(predicate);
        TypedQuery<ProductModel> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult((filterPayload.getPage() - 1) * filterPayload.getPageSize());
        query.setMaxResults(filterPayload.getPageSize());
        long count = getProductCount(filterPayload);
        Pageable pageable = PageRequest.of(
                filterPayload.getPage(),
                filterPayload.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name")
        );
        List<Product> productList = query.getResultList()
                .stream()
                .map(ProductModel::toEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(productList, pageable, count);
    }

    private void setOrder(ProductFilterPayload filterPayload, CriteriaQuery<ProductModel> criteriaQuery, Root<ProductModel> root) {
        if (filterPayload.getSortBy() != null) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(filterPayload.getSortBy())));
        }
    }

    private long getProductCount(ProductFilterPayload filterPayload) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<ProductModel> root = countQuery.from(ProductModel.class);
        Predicate predicate = getFilterPredicate(filterPayload, root);
        countQuery.select(criteriaBuilder.count(root)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Predicate getFilterPredicate(ProductFilterPayload filterPayload, Root<ProductModel> root) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        if (filterPayload.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + filterPayload.getName() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
