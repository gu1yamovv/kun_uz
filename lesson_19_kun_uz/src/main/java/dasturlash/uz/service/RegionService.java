package dasturlash.uz.service;

import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.RegionLangDTO;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.Region;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        regionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public RegionDTO update(RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            return null;
        }
        RegionEntity entity = optional.get();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        regionRepository.save(entity);
        return dto;
    }


    public boolean delete(Integer id) {
        regionRepository.deleteById(id);
        return true;
    }


    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        return dto;
    }

//    public List<RegionDTO> getAll() {
//
//        Iterable<RegionEntity> entityIterable = regionRepository.findAll();
//        List<RegionDTO> dtoList = new ArrayList<>();
//
//        for (RegionEntity region : entityIterable) {
//            RegionDTO dto = new RegionDTO();
//            dto.setId(region.getId());
//            dto.setOrderNumber(region.getOrderNumber());
//            dto.setKey(region.getKey());
//            dto.setNameUz(region.getNameUz());
//            dto.setNameRu(region.getNameRu());
//            dto.setNameEn(region.getNameEn());
//            dto.setVisible(region.getVisible());
//            dto.setCreatedDate(region.getCreatedDate());
//            dtoList.add(dto);
//        }
//        return dtoList;
//    }


    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> sections = regionRepository.findAll();
        List<RegionDTO> dtos = new ArrayList<>();
        sections.forEach(regionEntity -> dtos.add(toDTO(regionEntity)));
        return dtos;
    }


    public List<RegionLangDTO> getByLang(String lang) {
        return regionRepository.findAllByVisibleTrue().stream().map(regionEntity -> {
            String name = switch (lang.toLowerCase()) {
                case "uz" -> regionEntity.getNameUz();
                case "ru" -> regionEntity.getNameRu();
                case "en" -> regionEntity.getNameEn();
                default -> regionEntity.getNameEn();
            };
            return new RegionLangDTO(regionEntity.getId(), regionEntity.getKey(), name);
        }).collect(Collectors.toList());
    }


}
