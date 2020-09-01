package com.pet.springdata.web.controller.rest.cache;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
@Slf4j
public class CacheStatisticsRestController {

    @NonNull
    private final EntityManager entityManager;

    @GetMapping("/firstLevel")
    public List<Map<String, String>> getFirstLeveCacheStatistics() {
        return getEntityStatistics(
                List.of(
                        "com.pet.springdata.repository.user.model.User", "com.pet.springdata.repository.trivia.model.Trivia", "com.pet.springdata.repository.answer.model.Answer"
                )
        );
    }

    private List<Map<String, String>> getEntityStatistics(List<String> entityNames) {
        return entityNames.stream().map(this::createMapFromEntity).collect(Collectors.toList());
    }

    private Map<String, String> createMapFromEntity(String entityName) {
        Session session = entityManager.unwrap(Session.class);
        SessionFactory sessionFactory = session.getSessionFactory();
        Statistics statistics = sessionFactory.getStatistics();
        EntityStatistics entityStatistics = statistics.getEntityStatistics(entityName);

        return Map.of(
                "entityName", entityName,
                "hitCount", String.valueOf(entityStatistics.getCacheHitCount()),
                "missCount", String.valueOf(entityStatistics.getCacheMissCount()),
                "putCount", String.valueOf(entityStatistics.getCachePutCount()),
                "regionName", String.valueOf(entityStatistics.getCacheRegionName()),
                "deleteCount", String.valueOf(entityStatistics.getDeleteCount()),
                "insertCount", String.valueOf(entityStatistics.getInsertCount()),
                "updateCount", String.valueOf(entityStatistics.getUpdateCount()),
                "loadCount", String.valueOf(entityStatistics.getLoadCount()),
                "fetchCount", String.valueOf(entityStatistics.getFetchCount())
        );
    }

    @GetMapping("/secondLevel")
    public Map<String, Long> getSecondLevelCacheStatistics() {
        Session session = entityManager.unwrap(Session.class);
        SessionFactory sessionFactory = session.getSessionFactory();
        Statistics statistics = sessionFactory.getStatistics();

        return Map.of(
                "secondLevelCacheHitCount", statistics.getSecondLevelCacheHitCount(),
                "secondLevelCacheMissCount", statistics.getSecondLevelCacheMissCount(),
                "secondLevelCachePutCount", statistics.getSecondLevelCachePutCount()
        );
    }
}
