package frl.hacklab.librarysystem.catalog;

import frl.hacklab.librarysystem.catalog.core.items.MagazineRepository;
import frl.hacklab.librarysystem.catalog.core.items.RepositoryFactory;
import frl.hacklab.librarysystem.catalog.details.items.MagazineJpaRepository;
import frl.hacklab.librarysystem.catalog.details.items.RepositoryFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CatalogConfig {
    @Autowired
    @Bean
    public MagazineRepository magazineRepository(MagazineJpaRepository magazineJpaRepository) {
        return magazineJpaRepository;
    }

    @Autowired
    @Bean
    RepositoryFactory repositoryFactory(RepositoryFactoryImpl repositoryFactory){
        return repositoryFactory;
    }
}
