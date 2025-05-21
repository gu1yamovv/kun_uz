package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.CategoryLangDTO;
import dasturlash.uz.dto.SectionDTO;
import dasturlash.uz.dto.SectionLangDTO;
import dasturlash.uz.service.CategoryService;
import dasturlash.uz.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping("/admin")
    public SectionDTO create(@RequestBody SectionDTO dto) {
        return sectionService.create(dto);
    }

//    @PutMapping("/admin/{id}")
//    public ResponseEntity<SectionDTO> update(@PathVariable("id") Integer id, @RequestBody SectionDTO dto) {
//        return new ResponseEntity<>(sectionService.update(id, dto), HttpStatus.OK);
//    }

    @PutMapping("/admin")
    public ResponseEntity<SectionDTO> update(@RequestBody SectionDTO dto) {
        return new ResponseEntity<>(sectionService.update(dto), HttpStatus.OK);
    }


    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable Integer id) {
        sectionService.delete(id);
    }

    @GetMapping("/admin")
    public List<SectionDTO> getAll() {
        return sectionService.getAll();
    }

    @GetMapping("/lang")
    public List<SectionLangDTO> getByLang(@RequestParam String lang) {
        return sectionService.getByLang(lang);
    }

}
