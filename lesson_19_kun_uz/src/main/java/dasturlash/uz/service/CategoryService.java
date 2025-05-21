package dasturlash.uz.service;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.CategoryLangDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.RegionLangDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }


    public CategoryDTO update(CategoryDTO dto) {
        Optional<CategoryEntity> optional = categoryRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            return null;
        }
        CategoryEntity entity = optional.get();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        categoryRepository.save(entity);
        return dto;
    }

    public boolean delete(Integer id) {
        categoryRepository.deleteById(id);
        return true;
    }


    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        return dto;
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> sections = categoryRepository.findAll();
        List<CategoryDTO> dtos = new ArrayList<>();
        sections.forEach(categoryEntity -> dtos.add(toDTO(categoryEntity)));
        return dtos;
    }


    public List<CategoryLangDTO> getByLang(String lang) {
        return categoryRepository.findAllByVisibleTrue().stream().map(categoryEntity -> {
            String name = switch (lang.toLowerCase()) {
                case "uz" -> categoryEntity.getNameUz();
                case "ru" -> categoryEntity.getNameRu();
                case "en" -> categoryEntity.getNameEn();
                default -> categoryEntity.getNameEn();
            };
            return new CategoryLangDTO(categoryEntity.getId(), categoryEntity.getKey(), name);
        }).collect(Collectors.toList());
    }


}
