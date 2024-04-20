package cinira.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/procedures")
class Procedures {

    @GetMapping(value = ["/"])
    fun get(): ResponseEntity<String> {
        return ResponseEntity.ok("[]");
    }
}
