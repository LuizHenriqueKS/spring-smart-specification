package io.github.zul.springsmartspecification.mongo.query;

import java.util.List;
import java.util.Set;

public interface MongoSpecificationFieldExpressionHandler {

    Set<String> getPatterns();

    List<Object> handle(MongoSpecificationArgs args);

}
