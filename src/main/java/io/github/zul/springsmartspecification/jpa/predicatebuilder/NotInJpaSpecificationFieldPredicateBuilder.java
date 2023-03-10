package io.github.zul.springsmartspecification.jpa.predicatebuilder;

import java.util.Set;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationArgs;
import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationFieldPredicateBuilder;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

public class NotInJpaSpecificationFieldPredicateBuilder implements JpaSpecificationFieldPredicateBuilder {

    @Override
    public Set<String> getPatterns() {
        return Set.of("NotIn");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate build(JpaSpecificationArgs args) {
        if (args.getExpressions().size() != 2) {
            throw new IllegalArgumentException("Invalid number of expressions: " + args.getExpressions().size());
        }
        Expression<?> path = (Expression<?>) args.getExpressions().get(0);
        In<Object> in = args.getCriteriaBuilder().in(path);
        Object value = args.getExpressions().get(1);
        if (value instanceof Iterable) {
            ((Iterable<Object>) value).forEach(in::value);
        } else {
            Object[] array = (Object[]) value;
            for (Object o : array) {
                in.value(o);
            }
        }
        return in.not();
    }

}
