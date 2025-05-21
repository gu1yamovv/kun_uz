package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.CategoryLangDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.RegionLangDTO;
import dasturlash.uz.service.CategoryService;
import dasturlash.uz.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin")
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        return categoryService.create(dto);
    }

//    @PutMapping("/admin/{id}")
//    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id, @RequestBody CategoryDTO dto) {
//        return new ResponseEntity<>(categoryService.update(id, dto), HttpStatus.OK);
//    }

    @PutMapping("/admin")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO dto) {
        return new ResponseEntity<>(categoryService.update(dto), HttpStatus.OK);
    }


    @DeleteMapping("/admin/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }

    @GetMapping("/admin")
    public List<CategoryDTO> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/lang")
    public List<CategoryLangDTO> getByLang(@RequestParam String lang) {
        return categoryService.getByLang(lang);
    }

}
