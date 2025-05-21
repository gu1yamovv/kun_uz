package dasturlash.uz.repository;

import dasturlash.uz.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {
    List<RegionEntity> findAllByVisibleTrue();
}
