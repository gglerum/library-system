package frl.hacklab.librarysystem.catalog;

import frl.hacklab.librarysystem.catalog.core.items.MagazineRepository;
import frl.hacklab.librarysystem.catalog.core.items.RepositoryFactory;
import frl.hacklab.librarysystem.catalog.details.items.MagazineJpaRepository;
import frl.hacklab.librarysystem.catalog.details.items.RepositoryFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaRepositories("frl.hacklab.librarysystem.catalog.details.items")
//@EntityScan("frl.hacklab.librarysystem.catalog.details.items")
//@ComponentScan(basePackages = "frl.hacklab.librarysystem.catalog")
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
