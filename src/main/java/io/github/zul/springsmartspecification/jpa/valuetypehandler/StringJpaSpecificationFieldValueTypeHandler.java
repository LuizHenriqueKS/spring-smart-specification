package io.github.zul.springsmartspecification.jpa.valuetypehandler;

import java.util.List;

import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationArgs;
import io.github.zul.springsmartspecification.jpa.query.JpaSpecificationFieldValueTypeHandler;

public class StringJpaSpecificationFieldValueTypeHandler implements JpaSpecificationFieldValueTypeHandler {

    @Override
    public boolean supportsType(Class<?> type) {
        return String.class.isAssignableFrom(type);
    }

    @Override
    public boolean isValidValue(Object value) {
        return value != null;
    }

    @Override
    public List<Object> getValueExpressions(JpaSpecificationArgs args) {
        return List.of(args.getValue());
    }

}
