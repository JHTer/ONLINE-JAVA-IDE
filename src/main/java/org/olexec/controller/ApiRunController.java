package org.olexec.controller;

import org.olexec.service.ExecuteStringSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiRunController {

    @Autowired
    private ExecuteStringSourceService executeStringSourceService;

    public static class RunRequest {
        public String code;
        public String stdin;
        // getters/setters omitted for brevity
    }

    @PostMapping("/run")
    public Map<String, Object> run(@RequestBody RunRequest req) {
        String out = executeStringSourceService.execute(req.code, req.stdin == null ? "" : req.stdin);
        Map<String, Object> resp = new HashMap<>();
        resp.put("stdout", out);
        resp.put("stderr", "");
        resp.put("status", "SUCCESS");
        resp.put("execTimeMs", 0);
        return resp;
    }
} 