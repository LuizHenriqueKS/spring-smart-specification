package io.github.zul.springsmartspecification.jpa.query;

import java.util.List;
import java.util.Set;

public interface JpaSpecificationFieldExpressionHandler {

    Set<String> getPatterns();

    List<Object> handle(JpaSpecificationArgs args);

}
