
package com.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.dto.CodeTemplateResponse;
import com.main.service.CodeTemplates;

@RestController
@RequestMapping("/api/code")
public class CodeTemplateController {

    private CodeTemplates codeTemplates;

    public CodeTemplateController(CodeTemplates codeTemplates) {
        this.codeTemplates = codeTemplates;
    }

    @GetMapping("/template/{language}")
    public ResponseEntity<CodeTemplateResponse> getTemplate(
            @PathVariable String language) {

        String code = codeTemplates.getTemplate(language);

        return ResponseEntity.ok(
            new CodeTemplateResponse(
                language.toUpperCase(),
                code
            )
        );
    }
}
