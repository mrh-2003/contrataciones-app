package pe.gob.senamhi.contratacionesapp.controllers;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.gob.senamhi.contratacionesapp.entities.Convocado;
import pe.gob.senamhi.contratacionesapp.services.ConvocadoService;
import pe.gob.senamhi.contratacionesapp.services.FileService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/convocados")
@CrossOrigin(origins = "*")
public class ConvocadoController {
    @Autowired
    private ConvocadoService convocadoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private HttpServletRequest request;
    @PostMapping
    public ResponseEntity<Convocado> save(@RequestBody Convocado convocado) {
        return ResponseEntity.ok(convocadoService.save(convocado));
    }
    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(@RequestParam String dniRuc, @RequestParam Long codigoContratacion) {
        return ResponseEntity.ok(convocadoService.existsAllByDniRucAndCodigoContratacion(dniRuc, codigoContratacion));
    }
    @GetMapping("/{codigoContratacion}")
    public ResponseEntity<List<Convocado>> findAll(@PathVariable Long codigoContratacion) {
        return ResponseEntity.ok(convocadoService.findAllByCodigoContratacion(codigoContratacion));
    }
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String path = fileService.store(file);
        String host = request.getRequestURL().toString()
                .replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder.fromHttpUrl(host).
                path("/convocados/files/").
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
        ContentDisposition contentDisposition = ContentDisposition
                .builder("attachment")
                .filename(filename)
                .build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(file);
    }

    private String determineContentType(String filename) {
        String extension = FilenameUtils.getExtension(filename);
        switch (extension.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "doc":
            case "docx":
                return "application/msword";
            case "xls":
            case "xlsx":
                return "application/vnd.ms-excel";
            default:
                return "application/octet-stream";
        }
    }
}
