package com.quotecollector.controller;

import com.quotecollector.model.Quote;
import com.quotecollector.repository.QuoteRepository;
import com.quotecollector.service.ScraperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final QuoteRepository quoteRepository;
    private final ScraperService scraperService;

    public WebController(QuoteRepository quoteRepository, ScraperService scraperService) {
        this.quoteRepository = quoteRepository;
        this.scraperService = scraperService;
    }

  
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("quotes", quoteRepository.findAll());
        return "index";
    }


    @GetMapping("/collect")
    public String collect() {
        scraperService.fetchAndSaveQuotes();
        return "redirect:/";
    }


    @PostMapping("/add")
    public String addQuote(@RequestParam("customText") String text) {
        if (text != null && !text.trim().isEmpty()) {
            quoteRepository.save(new Quote(text));
        }
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteQuote(@PathVariable("id") Long id) {
        quoteRepository.deleteById(id);
        return "redirect:/";
    }
}