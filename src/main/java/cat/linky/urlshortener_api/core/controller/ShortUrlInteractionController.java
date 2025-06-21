package cat.linky.urlshortener_api.core.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.urlshortener_api.core.model.dto.ChartViewDayDTO;
import cat.linky.urlshortener_api.core.model.dto.ShortUrlInteractionDTO;
import cat.linky.urlshortener_api.core.service.ShortUrlInteractionService;


@RestController
@RequestMapping("/api/interaction")
public class ShortUrlInteractionController {
    
    private ShortUrlInteractionService service;

    public ShortUrlInteractionController(ShortUrlInteractionService service) {
        this.service = service;
    }

    @GetMapping("/{req}")
    public ResponseEntity<List<ShortUrlInteractionDTO>> get(@PathVariable Long req) {
        List<ShortUrlInteractionDTO> res = service.findAllByIdShortUrl(req);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/chart/day/{req}")
    public ResponseEntity<ChartViewDayDTO> getChartDayView(@PathVariable Long req, @RequestParam LocalDate day) {
        ChartViewDayDTO res = service.chartViewDay(req, day);
        return ResponseEntity.ok().body(res);
    }
    
}
