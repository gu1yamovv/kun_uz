package dasturlash.uz.controller;

import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.RegionLangDTO;
import dasturlash.uz.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping("/admin")
    public RegionDTO create(@RequestBody RegionDTO dto) {
        return regionService.create(dto);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<RegionDTO> update(@PathVariable("id") Integer id, @RequestBody RegionDTO dto) {
        return new ResponseEntity<>(regionService.update(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable Integer id) {
        regionService.delete(id);
    }

    @GetMapping("/admin")
    public List<RegionDTO> getAll() {
        return regionService.getAll();
    }

    @GetMapping("/lang")
    public List<RegionLangDTO> getByLang(@RequestParam String lang) {
        return regionService.getByLang(lang);
    }

}
