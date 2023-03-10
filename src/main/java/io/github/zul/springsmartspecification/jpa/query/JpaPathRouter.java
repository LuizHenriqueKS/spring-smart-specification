package io.github.zul.springsmartspecification.jpa.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import io.github.zul.springsmartspecification.path.PathUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaPathRouter {

    private final Root<?> root;
    private final Map<String, Path<?>> leftJoins = new HashMap<>();

    public Root<?> getRoot() {
        return root;
    }

    public Path<?> getPath(String... path) {
        return getPath(List.of(path));
    }

    public Path<?> getPath(List<String> path) {
        if (path.size() == 1) {
            return root.get(path.get(0));
        } else if (path.size() > 1) {
            Path<?> leftJoin = getLeftJoin(getParent(path));
            return leftJoin.get(path.get(path.size() - 1));
        }
        throw new IllegalArgumentException("Invalid path: " + path);
    }

    private List<String> getParent(List<String> path) {
        return path.subList(0, path.size() - 1);
    }

    private Path<?> getLeftJoin(List<String> subList) {
        String path = PathUtils.join(subList, ".");
        if (leftJoins.containsKey(path)) {
            return leftJoins.get(path);
        } else if (subList.size() > 1) {
            return getLeftJoin(getParent(subList)).get(subList.get(subList.size() - 1));
        } else {
            Path<?> leftJoin = getRoot().join(subList.get(0), JoinType.LEFT);
            leftJoins.put(path, leftJoin);
            return leftJoin;
        }
    }

}
