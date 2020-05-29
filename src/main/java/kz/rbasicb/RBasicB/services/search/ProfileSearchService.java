package kz.rbasicb.RBasicB.services.search;

import kz.rbasicb.RBasicB.models.entities.profile.Profile;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProfileSearchService {
    @Autowired
    private EntityManager entityManager;

    public List<Profile> searchMultipleFieldsByKeywordQuery(String text) {
        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(entityManager);

        org.hibernate.search.query.dsl.QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Profile.class)
                .get();

        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .fuzzy()
                .onFields("firstname", "lastname", "iin").matching(text)
                .createQuery();
        org.hibernate.search.jpa.FullTextQuery fullTextQuery
                = fullTextEntityManager.createFullTextQuery(query, Profile.class);


        //returns JPA managed entities
        return fullTextQuery.getResultList();
    }
}
