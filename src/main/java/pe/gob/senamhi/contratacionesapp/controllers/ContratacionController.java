package pe.gob.senamhi.contratacionesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.gob.senamhi.contratacionesapp.entities.Contratacion;
import pe.gob.senamhi.contratacionesapp.services.ContratacionService;
import pe.gob.senamhi.contratacionesapp.services.FileService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;

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
        System.out.println(contratacion);
        return ResponseEntity.ok(contratacionService.save(contratacion));
    }
    @PutMapping
    public ResponseEntity<Contratacion> update(@RequestBody Contratacion contratacion) {
        return ResponseEntity.ok(contratacionService.update(contratacion));
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
        String contentType = determineContentType(filename);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));

        // Configurar el encabezado Content-Disposition para establecer el nombre del archivo
        ContentDisposition contentDisposition = ContentDisposition
                .builder("attachment")
                .filename(filename)
                .build();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok().headers(headers).body(file);
    }

    private String determineContentType(String filename) {
        // Obtiene la extensión del archivo
        String extension = FilenameUtils.getExtension(filename);

        // Determina el tipo de contenido basado en la extensión
        switch (extension.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "doc":
            case "docx":
                return "application/msword";
            case "xls":
            case "xlsx":
                return "application/vnd.ms-excel";
            // Agrega más casos según los tipos de archivo que necesites manejar
            default:
                return "application/octet-stream"; // Tipo de contenido por defecto
        }
    }



}
