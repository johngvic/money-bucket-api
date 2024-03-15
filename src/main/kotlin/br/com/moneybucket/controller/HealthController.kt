package br.com.moneybucket.controller

import br.com.moneybucket.dto.HealthResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("health")
class HealthController {
    @GetMapping
    fun getHealth(): ResponseEntity<HealthResponse> {
        return ResponseEntity.ok(HealthResponse())
    }
}