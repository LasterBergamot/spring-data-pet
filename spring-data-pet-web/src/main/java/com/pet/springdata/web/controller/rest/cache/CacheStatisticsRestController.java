package com.pet.springdata.web.controller.rest.cache;

import com.pet.springdata.repository.answer.model.Answer;
import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.repository.user.model.User;
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

import static com.pet.springdata.web.util.Constants.KEY_DELETE_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_ENTITY_NAME;
import static com.pet.springdata.web.util.Constants.KEY_FETCH_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_HIT_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_INSERT_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_LOAD_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_MISS_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_PUT_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_REGION_NAME;
import static com.pet.springdata.web.util.Constants.KEY_SECOND_LEVEL_CACHE_HIT_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_SECOND_LEVEL_CACHE_MISS_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_SECOND_LEVEL_CACHE_PUT_COUNT;
import static com.pet.springdata.web.util.Constants.KEY_UPDATE_COUNT;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_CACHE;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_FIRST_LEVEL;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_SECOND_LEVEL;

@RestController
@RequestMapping(REQUEST_MAPPING_CACHE)
@RequiredArgsConstructor
@Slf4j
public class CacheStatisticsRestController {

    @NonNull
    private final EntityManager entityManager;

    @GetMapping(REQUEST_MAPPING_FIRST_LEVEL)
    public List<Map<String, String>> getFirstLeveCacheStatistics() {
        return getEntityStatistics(
                List.of(User.class.getName(), Trivia.class.getName(), Answer.class.getName())
        );
    }

    private List<Map<String, String>> getEntityStatistics(List<String> entityNames) {
        return entityNames.stream().map(this::createMapFromEntity).collect(Collectors.toList());
    }

    private Map<String, String> createMapFromEntity(String entityName) {
        Statistics statistics = getStatistics();
        EntityStatistics entityStatistics = statistics.getEntityStatistics(entityName);

        return Map.of(
                KEY_ENTITY_NAME, entityName,
                KEY_HIT_COUNT, String.valueOf(entityStatistics.getCacheHitCount()),
                KEY_MISS_COUNT, String.valueOf(entityStatistics.getCacheMissCount()),
                KEY_PUT_COUNT, String.valueOf(entityStatistics.getCachePutCount()),
                KEY_REGION_NAME, String.valueOf(entityStatistics.getCacheRegionName()),
                KEY_DELETE_COUNT, String.valueOf(entityStatistics.getDeleteCount()),
                KEY_INSERT_COUNT, String.valueOf(entityStatistics.getInsertCount()),
                KEY_UPDATE_COUNT, String.valueOf(entityStatistics.getUpdateCount()),
                KEY_LOAD_COUNT, String.valueOf(entityStatistics.getLoadCount()),
                KEY_FETCH_COUNT, String.valueOf(entityStatistics.getFetchCount())
        );
    }

    private Statistics getStatistics() {
        Session session = entityManager.unwrap(Session.class);
        SessionFactory sessionFactory = session.getSessionFactory();

        return sessionFactory.getStatistics();
    }

    @GetMapping(REQUEST_MAPPING_SECOND_LEVEL)
    public Map<String, Long> getSecondLevelCacheStatistics() {
        Statistics statistics = getStatistics();

        return Map.of(
                KEY_SECOND_LEVEL_CACHE_HIT_COUNT, statistics.getSecondLevelCacheHitCount(),
                KEY_SECOND_LEVEL_CACHE_MISS_COUNT, statistics.getSecondLevelCacheMissCount(),
                KEY_SECOND_LEVEL_CACHE_PUT_COUNT, statistics.getSecondLevelCachePutCount()
        );
    }
}
