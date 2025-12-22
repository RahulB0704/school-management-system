package com.school.school_management.Base.query;


import com.school.school_management.Base.query.filters.BigDecimalFilter;
import com.school.school_management.Base.query.filters.Filter;
import com.school.school_management.Base.query.filters.InstantFilter;
import com.school.school_management.Base.query.filters.StringFilter;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

/**
 * Base service for constructing and executing complex queries.
 *
 * @param <ENTITY> the type of the entity which is queried.
 */
@Transactional(readOnly = true)
public abstract class QueryService<ENTITY> {

    /**
     * Helper function to return a specification for filtering on a single field, where equality, and null/non-null
     * conditions are supported.
     *
     * @param filter the individual attribute filter coming from the frontend.
     * @param field  the JPA static metamodel representing the field.
     * @param <X>    The type of the attribute which is filtered.
     * @return a Specification
     */
    protected <X> Specification<ENTITY> buildSpecification(Filter<X> filter, SingularAttribute<? super ENTITY, X>
            field) {
        return buildSpecification(filter, root -> root.get(field));
    }

    /**
     * Helper function to return a specification for filtering on a single field, where equality, and null/non-null
     * conditions are supported.
     *
     * @param filter            the individual attribute filter coming from the frontend.
     * @param metaclassFunction the function, which navigates from the current entity to a column, for which the filter applies.
     * @param <X>               The type of the attribute which is filtered.
     * @return a Specification
     */
    protected <X> Specification<ENTITY> buildSpecification(Filter<X> filter, Function<Root<ENTITY>, Expression<X>> metaclassFunction) {
        if (filter.getEq() != null) {
            return equalsSpecification(metaclassFunction, filter.getEq());
        } else if (filter.getIn() != null) {
            return valueIn(metaclassFunction, filter.getIn());
        } else if (filter.getNotIn() != null) {
            return valueNotIn(metaclassFunction, filter.getNotIn());
        } else if (filter.getNot() != null) {
            return notEqualsSpecification(metaclassFunction, filter.getNot());
        } else if (filter.getIs() != null) {
            return byFieldSpecified(metaclassFunction, filter.getIs());
        }
        return null;
    }

    protected Specification<ENTITY> buildInstantSpecification(InstantFilter filter, SingularAttribute<? super ENTITY, Instant>
            field) {
        return buildInstantSpecification(filter, root -> root.get(field));
    }
    protected <X> Specification<ENTITY> buildInstantSpecification(InstantFilter filter, Function<Root<ENTITY>, Expression<Instant>> metaclassFunction) {
        if(filter.getGt()!=null){
            return greaterThan(metaclassFunction, filter.getGt().toInstant());
        } else if(filter.getGte()!=null){
            return greaterThanOrEqualTo(metaclassFunction, filter.getGte().toInstant());
        } else if(filter.getLt()!=null){
            return lessThan(metaclassFunction, filter.getLt().toInstant());
        } else if(filter.getLte()!=null){
            return lessThanOrEqualTo(metaclassFunction, filter.getLte().toInstant());
        } else if(filter.getEq()!=null){
            return equalsSpecification(metaclassFunction, filter.getEq().toInstant());
        } else if(filter.getNot()!=null){
            return notEqualsSpecification(metaclassFunction, filter.getNot().toInstant());
        }
        return null;
    }

    protected Specification<ENTITY> buildBigDecimalSpecification(BigDecimalFilter filter, SingularAttribute<? super ENTITY, BigDecimal>
            field) {
        return buildBigDecimalSpecification(filter, root -> root.get(field));
    }
    protected <X> Specification<ENTITY> buildBigDecimalSpecification(BigDecimalFilter filter, Function<Root<ENTITY>, Expression<BigDecimal>> metaclassFunction) {
        if(filter.getGt()!=null){
            return greaterThan(metaclassFunction, filter.getGt());
        } else if(filter.getGte()!=null){
            return greaterThanOrEqualTo(metaclassFunction, filter.getGte());
        } else if(filter.getLt()!=null){
            return lessThan(metaclassFunction, filter.getLt());
        } else if(filter.getLte()!=null){
            return lessThanOrEqualTo(metaclassFunction, filter.getLte());
        } else if(filter.getEq()!=null){
            return equalsSpecification(metaclassFunction, filter.getLte());
        } else if(filter.getNot()!=null){
            return notEqualsSpecification(metaclassFunction, filter.getLte());
        }
        return null;
    }
    /**
     * Helper function to return a specification for filtering on a {@link String} field, where equality, containment,
     * and null/non-null conditions are supported.
     *
     * @param filter the individual attribute filter coming from the frontend.
     * @param field  the JPA static metamodel representing the field.
     * @return a Specification
     */
    protected Specification<ENTITY> buildStringSpecification(StringFilter filter, SingularAttribute<? super ENTITY,
            String> field) {
        return buildSpecification(filter, root -> root.get(field));
    }

    /**
     * Helper function to return a specification for filtering on a {@link String} field, where equality, containment,
     * and null/non-null conditions are supported.
     *
     * @param filter            the individual attribute filter coming from the frontend.
     * @param metaclassFunction lambda, which based on a Root&lt;ENTITY&gt; returns Expression - basicaly picks a column
     * @return a Specification
     */
    protected Specification<ENTITY> buildSpecification(StringFilter filter, Function<Root<ENTITY>, Expression<String>> metaclassFunction) {
        if (filter.getEq() != null) {
            return equalsSpecification(metaclassFunction, filter.getEq());
        } else if (filter.getIn() != null) {
            return valueIn(metaclassFunction, filter.getIn());
        } else if (filter.getNotIn() != null) {
            return valueNotIn(metaclassFunction, filter.getNotIn());
        } else if (filter.getContains() != null) {
            return likeUpperSpecification(metaclassFunction, filter.getContains());
        } else if (filter.getDoesNotContain() != null) {
            return doesNotContainSpecification(metaclassFunction, filter.getDoesNotContain());
        } else if (filter.getNot() != null) {
            return notEqualsSpecification(metaclassFunction, filter.getNot());
        } else if (filter.getIs() != null) {
            return byFieldSpecified(metaclassFunction, filter.getIs());
        }
        return null;
    }


    /**
     * Helper function to return a specification for filtering on one-to-one or many-to-one reference. Usage:
     * <pre>
     *   Specification&lt;Employee&gt; specByProjectId = buildReferringEntitySpecification(criteria.getProjectId(),
     * Employee_.project, Project_.id);
     *   Specification&lt;Employee&gt; specByProjectName = buildReferringEntitySpecification(criteria.getProjectName(),
     * Employee_.project, Project_.name);
     * </pre>
     *
     * @param filter     the filter object which contains a value, which needs to match or a flag if nullness is
     *                   checked.
     * @param reference  the attribute of the static metamodel for the referring entity.
     * @param valueField the attribute of the static metamodel of the referred entity, where the equality should be
     *                   checked.
     * @param <OTHER>    The type of the referenced entity.
     * @param <X>        The type of the attribute which is filtered.
     * @return a Specification
     */
    protected <OTHER, X> Specification<ENTITY> buildReferringEntitySpecification(Filter<X> filter,
                                                                                 SingularAttribute<? super ENTITY, OTHER> reference,
                                                                                 SingularAttribute<? super OTHER, X> valueField) {
        return buildSpecification(filter, root -> root.get(reference).get(valueField));
    }

    protected <OTHER, X> Specification<ENTITY> buildReferringEntitySpecification(StringFilter filter,
                                                                                 SingularAttribute<? super ENTITY, OTHER> reference,
                                                                                 SingularAttribute<? super OTHER, String> valueField) {
        return buildSpecification(filter, root -> root.join(reference, JoinType.LEFT).get(valueField));
    }

    protected <OTHER, OTHER2, X> Specification<ENTITY> buildReferringEntitySpecification(StringFilter filter,
                                                                                         SingularAttribute<? super ENTITY, OTHER> reference,
                                                                                         SingularAttribute<? super OTHER, OTHER2> reference2,
                                                                                         SingularAttribute<? super OTHER2, String> valueField) {
        return buildSpecification(filter, root -> root.join(reference, JoinType.LEFT).get(reference2).get(valueField));
    }

    /**
     * Helper function to return a specification for filtering on one-to-many or many-to-many reference. Usage:
     * <pre>
     *   Specification&lt;Employee&gt; specByEmployeeId = buildReferringEntitySpecification(criteria.getEmployeId(),
     * Project_.employees, Employee_.id);
     *   Specification&lt;Employee&gt; specByEmployeeName = buildReferringEntitySpecification(criteria.getEmployeName(),
     * Project_.project, Project_.name);
     * </pre>
     *
     * @param filter     the filter object which contains a value, which needs to match or a flag if emptiness is
     *                   checked.
     * @param reference  the attribute of the static metamodel for the referring entity.
     * @param valueField the attribute of the static metamodel of the referred entity, where the equality should be
     *                   checked.
     * @param <OTHER>    The type of the referenced entity.
     * @param <X>        The type of the attribute which is filtered.
     * @return a Specification
     */
    protected <OTHER, X> Specification<ENTITY> buildReferringEntitySpecification(Filter<X> filter,
                                                                                 SetAttribute<ENTITY, OTHER> reference,
                                                                                 SingularAttribute<OTHER, X> valueField) {
        return buildReferringEntitySpecification(filter, root -> root.join(reference), entity -> entity.get(valueField));
    }

    protected <OTHER, X> Specification<ENTITY> buildReferringEntitySpecification(StringFilter filter,
                                                                                 SetAttribute<ENTITY, OTHER> reference,
                                                                                 SingularAttribute<OTHER, String> valueField) {
        return buildReferringEntitySpecification(filter, root -> root.join(reference), entity -> entity.get(valueField));
    }

    /**
     * Helper function to return a specification for filtering on one-to-many or many-to-many reference.Usage:<pre>
     *   Specification&lt;Employee&gt; specByEmployeeId = buildReferringEntitySpecification(
     *          criteria.getEmployeId(),
     *          root -&gt; root.get(Project_.company).join(Company_.employees),
     *          entity -&gt; entity.get(Employee_.id));
     *   Specification&lt;Employee&gt; specByProjectName = buildReferringEntitySpecification(
     *          criteria.getProjectName(),
     *          root -&gt; root.get(Project_.project)
     *          entity -&gt; entity.get(Project_.name));
     * </pre>
     *
     * @param filter           the filter object which contains a value, which needs to match or a flag if emptiness is
     *                         checked.
     * @param functionToEntity the function, which joins he current entity to the entity set, on which the filtering is applied.
     * @param entityToColumn   the function, which of the static metamodel of the referred entity, where the equality should be
     *                         checked.
     * @param <OTHER>          The type of the referenced entity.
     * @param <MISC>           The type of the entity which is the last before the OTHER in the chain.
     * @param <X>              The type of the attribute which is filtered.
     * @return a Specification
     */
    protected <OTHER, MISC, X> Specification<ENTITY> buildReferringEntitySpecification(Filter<X> filter,
                                                                                       Function<Root<ENTITY>, SetJoin<MISC, OTHER>> functionToEntity,
                                                                                       Function<SetJoin<MISC, OTHER>, Expression<X>> entityToColumn) {
        if (filter.getEq() != null) {
            return equalsSpecification(functionToEntity.andThen(entityToColumn), filter.getEq());
        } else if (filter.getIs() != null) {
            // Interestingly, 'functionToEntity' doesn't work, we need the longer lambda formula
            return byFieldSpecified(root -> functionToEntity.apply(root), filter.getIs());
        }
        return null;
    }


    /**
     * Generic method, which based on a Root&lt;ENTITY&gt; returns an Expression which type is the same as the given 'value' type.
     *
     * @param metaclassFunction function which returns the column which is used for filtering.
     * @param value             the actual value to filter for.
     * @param <X>              The type of the attribute which is filtered.
     * @return a Specification.
     */
    protected <X> Specification<ENTITY> equalsSpecification(Function<Root<ENTITY>, Expression<X>> metaclassFunction, final X value) {
        return (root, query, builder) -> builder.equal(metaclassFunction.apply(root), value);
    }

    /**
     * Generic method, which based on a Root&lt;ENTITY&gt; returns an Expression which type is the same as the given 'value' type.
     *
     * @param metaclassFunction function which returns the column which is used for filtering.
     * @param value             the actual value to exclude for.
     * @param <X>              The type of the attribute which is filtered.
     * @return a Specification.
     */
    protected <X> Specification<ENTITY> notEqualsSpecification(Function<Root<ENTITY>, Expression<X>> metaclassFunction, final X value) {
        return (root, query, builder) -> builder.not(builder.equal(metaclassFunction.apply(root), value));
    }

    /**
     * <p>likeUpperSpecification.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param value a {@link String} object.
     * @return a {@link Specification} object.
     */
    protected Specification<ENTITY> likeUpperSpecification(Function<Root<ENTITY>, Expression<String>> metaclassFunction,
                                                           final String value) {
        return (root, query, builder) -> builder.like(builder.upper(metaclassFunction.apply(root)), wrapLikeQuery(value));
    }

    /**
     * <p>doesNotContainSpecification.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param value a {@link String} object.
     * @return a {@link Specification} object.
     */
    protected Specification<ENTITY> doesNotContainSpecification(Function<Root<ENTITY>, Expression<String>> metaclassFunction,
                                                                final String value) {
        return (root, query, builder) -> builder.not(builder.like(builder.upper(metaclassFunction.apply(root)), wrapLikeQuery(value)));
    }

    /**
     * <p>byFieldSpecified.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param specified a boolean.
     * @param <X> a X object.
     * @return a {@link Specification} object.
     */
    protected <X> Specification<ENTITY> byFieldSpecified(Function<Root<ENTITY>, Expression<X>> metaclassFunction,
                                                         final boolean specified) {
        return specified ?
                (root, query, builder) -> builder.isNotNull(metaclassFunction.apply(root)) :
                (root, query, builder) -> builder.isNull(metaclassFunction.apply(root));
    }

    /**
     * <p>byFieldEmptiness.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param specified a boolean.
     * @param <X> a X object.
     * @return a {@link Specification} object.
     */
    protected <X> Specification<ENTITY> byFieldEmptiness(Function<Root<ENTITY>, Expression<Set<X>>> metaclassFunction,
                                                         final boolean specified) {
        return specified ?
                (root, query, builder) -> builder.isNotEmpty(metaclassFunction.apply(root)) :
                (root, query, builder) -> builder.isEmpty(metaclassFunction.apply(root));
    }

    /**
     * <p>valueIn.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param values a {@link Collection} object.
     * @param <X> a X object.
     * @return a {@link Specification} object.
     */
    protected <X> Specification<ENTITY> valueIn(Function<Root<ENTITY>, Expression<X>> metaclassFunction,
                                                final Collection<X> values) {
        return (root, query, builder) -> {
            CriteriaBuilder.In<X> in = builder.in(metaclassFunction.apply(root));
            for (X value : values) {
                in = in.value(value);
            }
            return in;
        };
    }

    /**
     * <p>valueNotIn.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param values a {@link Collection} object.
     * @param <X> a X object.
     * @return a {@link Specification} object.
     */
    protected <X> Specification<ENTITY> valueNotIn(Function<Root<ENTITY>, Expression<X>> metaclassFunction,
                                                   final Collection<X> values) {
        return (root, query, builder) -> {
            CriteriaBuilder.In<X> in = builder.in(metaclassFunction.apply(root));
            for (X value : values) {
                in = in.value(value);
            }
            return builder.not(in);
        };
    }

    /**
     * <p>greaterThanOrEqualTo.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param value a X object.
     * @param <X> a X object.
     * @return a {@link Specification} object.
     */
    protected <X extends Comparable<? super X>> Specification<ENTITY> greaterThanOrEqualTo(Function<Root<ENTITY>, Expression<X>> metaclassFunction,
                                                                                           final X value) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(metaclassFunction.apply(root), value);
    }

    /**
     * <p>greaterThan.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param value a X object.
     * @param <X> a X object.
     * @return a {@link Specification} object.
     */
    protected <X extends Comparable<? super X>> Specification<ENTITY> greaterThan(Function<Root<ENTITY>, Expression<X>> metaclassFunction,
                                                                                  final X value) {
        return (root, query, builder) -> builder.greaterThan(metaclassFunction.apply(root), value);
    }

    /**
     * <p>lessThanOrEqualTo.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param value a X object.
     * @param <X> a X object.
     * @return a {@link Specification} object.
     */
    protected <X extends Comparable<? super X>> Specification<ENTITY> lessThanOrEqualTo(Function<Root<ENTITY>, Expression<X>> metaclassFunction,
                                                                                        final X value) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(metaclassFunction.apply(root), value);
    }

    /**
     * <p>lessThan.</p>
     *
     * @param metaclassFunction a {@link Function} object.
     * @param value a X object.
     * @param <X> a X object.
     * @return a {@link Specification} object.
     */
    protected <X extends Comparable<? super X>> Specification<ENTITY> lessThan(Function<Root<ENTITY>, Expression<X>> metaclassFunction,
                                                                               final X value) {
        return (root, query, builder) -> builder.lessThan(metaclassFunction.apply(root), value);
    }

    /**
     * <p>wrapLikeQuery.</p>
     *
     * @param txt a {@link String} object.
     * @return a {@link String} object.
     */
    protected String wrapLikeQuery(String txt) {
        return "%" + txt.toUpperCase() + '%';
    }

}