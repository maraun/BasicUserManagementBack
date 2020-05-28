package kz.rbasicb.RBasicB.services.search;

import kz.rbasicb.RBasicB.models.entities.profile.Profile;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class HibernateProfileSearchService {

    @Autowired
    private final EntityManager centityManager;

    public HibernateProfileSearchService(EntityManager entityManager) {
        this.centityManager = entityManager;
    }

    public void initializeHibernateSearch() {

        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private FullTextQuery getJpaQuery(org.apache.lucene.search.Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);

        return fullTextEntityManager.createFullTextQuery(luceneQuery, Profile.class);
    }

    private QueryBuilder getQueryBuilder() {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Profile.class)
                .get();
    }

    public List<Profile> searchMultipleFieldsByKeywordQuery(String text) {
        Query keywordQuery = getQueryBuilder()
                .keyword()
                .onFields("firstname", "lastname", "iin")
                .matching(text)
                .createQuery();
        return getJpaQuery(keywordQuery).getResultList();
    }


}
