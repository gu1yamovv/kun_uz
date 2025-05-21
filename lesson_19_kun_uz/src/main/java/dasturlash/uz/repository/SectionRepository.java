package dasturlash.uz.repository;

import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.entity.SectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SectionRepository extends CrudRepository<SectionEntity,Integer> , PagingAndSortingRepository<SectionEntity,Integer> {
    List<SectionEntity> findAllByVisibleTrue();
}
