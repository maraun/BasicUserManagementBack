package kz.rbasicb.RBasicB.shared.utils.beans;

import kz.rbasicb.RBasicB.services.search.HibernateProfileSearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManager;
@EnableAutoConfiguration
@Configuration
public class CustomConfiguration {
    @Autowired
    private EntityManager bentityManager;
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    HibernateProfileSearchService hibernateSearchService() {
        HibernateProfileSearchService hibernateSearchService = new HibernateProfileSearchService(bentityManager);
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }
}
