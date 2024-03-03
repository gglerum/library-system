package frl.hacklab.librarysystem.catalog;

import frl.hacklab.librarysystem.catalog.application.usecases.GetLibraryItemUseCase;
import frl.hacklab.librarysystem.catalog.domain.repository.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CatalogConfig {
  @Bean
  @Autowired
  public GetLibraryItemUseCase getLibraryItemUseCase(RepositoryFactory repositoryFactory) {
    return new GetLibraryItemUseCase(repositoryFactory);
  }
}
