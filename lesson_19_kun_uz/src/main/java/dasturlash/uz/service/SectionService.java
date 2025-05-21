package dasturlash.uz.service;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.CategoryLangDTO;
import dasturlash.uz.dto.SectionDTO;
import dasturlash.uz.dto.SectionLangDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.SectionEntity;
import dasturlash.uz.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public SectionDTO create(SectionDTO dto) {
        SectionEntity entity = new SectionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        sectionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public SectionDTO update(SectionDTO dto) {
        Optional<SectionEntity> optional = sectionRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            return null;
        }
        SectionEntity entity = optional.get();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        sectionRepository.save(entity);
        return dto;
    }

    public boolean delete(Integer id) {
        sectionRepository.deleteById(id);
        return true;
    }

    public SectionDTO toDTO(SectionEntity entity) {
        SectionDTO dto = new SectionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        return dto;
    }

    public List<SectionDTO> getAll() {
        Iterable<SectionEntity> sections = sectionRepository.findAll();
        List<SectionDTO> dtos = new ArrayList<>();
        sections.forEach(sectionEntity -> dtos.add(toDTO(sectionEntity)));
        return dtos;
    }

    public List<SectionLangDTO> getByLang(String lang) {
        return sectionRepository.findAllByVisibleTrue().stream().map(sectionEntity -> {
            String name = switch (lang.toLowerCase()) {
                case "uz" -> sectionEntity.getNameUz();
                case "ru" -> sectionEntity.getNameRu();
                case "en" -> sectionEntity.getNameEn();
                default -> sectionEntity.getNameEn();
            };
            return new SectionLangDTO(sectionEntity.getId(), sectionEntity.getKey(), name);
        }).collect(Collectors.toList());
    }

}
