package ru.hehmdalolkek.productaggregator.repository;

import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;
import ru.hehmdalolkek.productaggregator.model.AbstractEntity;

import java.io.Serializable;

/**
 * Custom repository for QueryDSL.
 *
 * @author Inna Badekha
 */
@NoRepositoryBean
public interface QueryDSLRepository<T extends AbstractEntity, P extends EntityPathBase<T>, ID extends Serializable>
        extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T>, QuerydslBinderCustomizer<P> {

    @Override
    default void customize(QuerydslBindings bindings, P root) {
    }
}
