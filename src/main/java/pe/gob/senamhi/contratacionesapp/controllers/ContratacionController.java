package pe.gob.senamhi.contratacionesapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.gob.senamhi.contratacionesapp.entities.Contratacion;
import pe.gob.senamhi.contratacionesapp.entities.Formato;
import pe.gob.senamhi.contratacionesapp.services.ContratacionService;
import pe.gob.senamhi.contratacionesapp.services.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contrataciones")
@CrossOrigin(origins = "*")
public class ContratacionController {
    @Autowired
    private ContratacionService contratacionService;

    @Autowired
    private FileService fileService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<Contratacion>> findAll() {
        return ResponseEntity.ok(contratacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contratacion> findById(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(contratacionService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id")  Long id) {
        contratacionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Contratacion> save(@RequestBody Contratacion contratacion) {
        return ResponseEntity.ok(contratacionService.save(contratacion));
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String path = fileService.store(file);
        String host = request.getRequestURL().toString()
                .replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder.fromHttpUrl(host).
                path("/contrataciones/files/").
                path(path).
                toUriString();
        return ResponseEntity.ok(Map.of("url", url));
    }

    @GetMapping("/files/{filename:.*}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = fileService.loadFile(filename);
        String contentType;
        try {
            contentType = Files.probeContentType(file.getFile().toPath());
        } catch (IOException e) {
            // Si no se puede determinar el tipo de contenido, establecer un tipo por defecto
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }

}
