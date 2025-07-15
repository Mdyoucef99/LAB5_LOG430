package com.lab5.gateway;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class SwaggerController {

    @GetMapping(value = "/swagger.yaml", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> getSwaggerYaml() throws IOException {
        ClassPathResource resource = new ClassPathResource("swagger.yaml");
        String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header("Content-Type", "application/x-yaml")
                .body(content);
    }

    @GetMapping("/swagger-ui.html")
    public ResponseEntity<String> getSwaggerUi() {
        String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>LAB5 API Gateway - Swagger UI</title>
                    <link rel="stylesheet" type="text/css" href="https://unpkg.com/swagger-ui-dist@5.9.0/swagger-ui.css" />
                </head>
                <body>
                    <div id="swagger-ui"></div>
                    <script src="https://unpkg.com/swagger-ui-dist@5.9.0/swagger-ui-bundle.js"></script>
                    <script>
                        window.onload = function() {
                            SwaggerUIBundle({
                                url: '/swagger.yaml',
                                dom_id: '#swagger-ui',
                                presets: [
                                    SwaggerUIBundle.presets.apis,
                                    SwaggerUIStandalonePreset
                                ],
                                layout: "StandaloneLayout"
                            });
                        }
                    </script>
                </body>
                </html>
                """;
        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(html);
    }
} 