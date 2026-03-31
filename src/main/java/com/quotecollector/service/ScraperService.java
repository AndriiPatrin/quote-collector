package com.quotecollector.service;

import com.quotecollector.model.Quote;
import com.quotecollector.repository.QuoteRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ScraperService {

    private final QuoteRepository quoteRepository;

    public ScraperService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void fetchAndSaveQuotes() {
        try {
            // Підключення до сайту з імітацією браузера
            Document doc = Jsoup.connect("http://quotes.toscrape.com")
                                .userAgent("Mozilla/5.0")
                                .get();

            // Пошук елементів за класом .text та збереження в БД
            for (Element el : doc.getElementsByClass("text")) {
                quoteRepository.save(new Quote(el.text()));
            }
        } catch (Exception e) {
            System.err.println("Помилка парсингу: " + e.getMessage());
        }
    }
}