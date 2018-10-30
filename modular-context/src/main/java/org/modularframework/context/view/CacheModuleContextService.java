package org.modularframework.context.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 30/10/2018
 */
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "module-context")
public class CacheModuleContextService {
  private ModuleInitializationService moduleInitializationService;

  @Autowired
  public void setModuleInitializationService(ModuleInitializationService moduleInitializationService) {
    this.moduleInitializationService = moduleInitializationService;
  }

  @Cacheable
  public ModuleStore getModuleStore() {
    return new ModuleStore(moduleInitializationService.getModules());
  }

  @CacheEvict(allEntries = true)
  public void allClear() {

  }
}