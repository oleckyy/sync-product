package com.kumquat.syncProductApi.infrastructure.rest.internal.specification.predicate

import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductFiltersDto
import com.kumquat.syncProductApi.infrastructure.rest.internal.specification.ProductPredicateBuilder
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Component
class ProductVersionDatePredicateBuilder : ProductPredicateBuilder {
    override fun build(
        productFiltersDto: ProductFiltersDto,
        root: Root<ProductEntity>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        val versionDateFromPredicate =
            productFiltersDto.versionDateFrom
                ?.let { value ->
                    criteriaBuilder.greaterThanOrEqualTo(
                        criteriaBuilder.function("DATE", LocalDate::class.java, root.get<LocalDateTime>("versionDate")),
                        value.toLocalDate(),
                    )
                }
        val versionDateToPredicate =
            productFiltersDto.versionDateTo
                ?.let { value ->
                    criteriaBuilder.lessThanOrEqualTo(
                        criteriaBuilder.function("DATE", LocalDate::class.java, root.get<LocalDate>("versionDate")),
                        value.toLocalDate(),
                    )
                }
        return listOfNotNull(versionDateFromPredicate, versionDateToPredicate)
            .reduceOrNull { result, predicate -> criteriaBuilder.and(result, predicate) }
    }
}