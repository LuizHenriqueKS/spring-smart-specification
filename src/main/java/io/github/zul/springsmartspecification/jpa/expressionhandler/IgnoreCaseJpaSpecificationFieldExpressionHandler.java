package io.github.zul.springsmartspecification.jpa.expressionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationArgs;
import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationFieldExpressionHandler;
import jakarta.persistence.criteria.Expression;

public class IgnoreCaseJpaSpecificationFieldExpressionHandler implements JpaSpecificationFieldExpressionHandler {

    @Override
    public Set<String> getPatterns() {
        return Set.of("IgnoreCase");
    }

    @Override
    public List<Object> handle(JpaSpecificationArgs args) {
        List<Object> result = new ArrayList<>();
        for (Object value : args.getExpressions()) {
            result.add(lower(args, value));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private Object lower(JpaSpecificationArgs args, Object value) {
        if (value instanceof Expression) {
            return args.getCriteriaBuilder().lower((Expression<String>) value);
        } else if (value instanceof String) {
            return ((String) value).toLowerCase();
        } else {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }

}
