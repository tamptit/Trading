package app.trading.stock.service.data;

import app.trading.stock.entity.Industry;
import app.trading.stock.entity.Symbols;
import app.trading.stock.repositories.IndustryRepository;
import app.trading.stock.repositories.SymbolsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SymbolCrawlerService {

    private final SymbolsRepository symbolRepository;
    private final IndustryRepository industryRepository;

    public static Map<String, String> getIndustries() {
        Map<String, String> industries = new LinkedHashMap<>();
        industries.put("0500", "Dầu khí");
        industries.put("1300", "Hóa chất");
        industries.put("1700", "Tài nguyên Cơ bản");
        industries.put("2300", "Xây dựng và Vật liệu");
        industries.put("2700", "Hàng & Dịch vụ Công nghiệp");
        industries.put("3300", "Ô tô và phụ tùng");
        industries.put("3500", "Thực phẩm và đồ uống");
        industries.put("3700", "Hàng cá nhân & Gia dụng");
        industries.put("4500", "Y tế");
        industries.put("5300", "Bán lẻ");
        industries.put("5500", "Truyền thông");
        industries.put("5700", "Du lịch và Giải trí");
        industries.put("6500", "Viễn thông");
        industries.put("7500", "Điện, nước & xăng dầu khí đốt");
        industries.put("8300", "Ngân hàng");
        industries.put("8500", "Bảo hiểm");
        industries.put("8600", "Bất động sản");
        industries.put("8700", "Dịch vụ tài chính");
        industries.put("9500", "Công nghệ Thông tin");
        return industries;
    }

    enum Floor {
        HOSE("1"), HNX("2"), UPCOM("3"); // Constants with associated values
        private final String floor_id;

        Floor(String floor_id) {
            this.floor_id = floor_id;
        }
        public String getFloor_id() {
            return floor_id;
        }

        // Lấy enum từ tên sàn
        public static Floor fromName(String name) {
            if (name == null) return null;
            return switch (name.trim().toUpperCase()) {
                case "HOSE" -> HOSE;
                case "HNX" -> HNX;
                case "UPCOM" -> UPCOM;
                default -> throw new IllegalArgumentException("Unknown floor: " + name);
            };
        }
    }


    public SymbolCrawlerService(SymbolsRepository symbolRepository, IndustryRepository industryRepository) {
        this.symbolRepository = symbolRepository;
        this.industryRepository = industryRepository;
    }

    @Transactional
    public void crawlAndSave() throws Exception {

        // crawl vài trang đầu tiên, có thể tăng đến 93
        Map<String, String> mapIndustries = SymbolCrawlerService.getIndustries();
        String baseUrl = "https://24hmoney.vn/companies?industry_code=%s&floor_code=all&com_type=all&letter=all&page=%d";
        int totalPage = 1;
        for (Map.Entry<String, String> indusId : mapIndustries.entrySet()) {
            List<Symbols> symbols = new ArrayList<>();
            String industryName = indusId.getValue();
            String industryId = indusId.getKey();

            String urlByIndus = String.format(baseUrl, indusId.getKey(), 1);

            Document docTotalPage = Jsoup.connect(urlByIndus).get();
            Element pageInfo = docTotalPage.selectFirst(".force-change-page .change-page");

            if (pageInfo != null) {
                String text = pageInfo.text();
                // Tách theo dấu "/" và lấy phần sau
                if (text.contains("/")) {
                    String[] parts = text.split("/");
                    try {
                        totalPage = Integer.parseInt(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.err.println("⚠️ Không parse được tổng số trang từ: " + text);
                    }
                }
                for (int page = 1; page <= totalPage; page++) {
                    Document doc = Jsoup.connect(String.format(baseUrl, indusId.getKey(), page)).get();
                    Elements rows = doc.select("table tr");
                    for (Element row : rows.subList(1, rows.size())) {
                        Elements cols = row.select("td");
                        if (cols.size() >= 4) {
                            String code = cols.get(0).text();
                            String company = cols.get(1).text();
                            String industry2Name = cols.get(2).text();
                            String floorName = cols.get(3).text();
                            String floorId = Floor.fromName(floorName).getFloor_id();
                            symbols.add(Symbols.builder()
                                    .code(code)
                                    .companyName(company)
                                    .industryId(industryId)
                                    .floorId(floorId)
                                    .industryName(industryName)
                                    .floorName(floorName)
                                    .build());
                        }
                    }
                }
                symbolRepository.saveAll(symbols);
                System.out.println("✅ Đã lưu " + symbols.size() + " mã chứng khoán vào MySQL");
            }
        }
    }

    @Transactional
    public void saveIndustriesFromHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements options = doc.select("select.select-filter option");

        List<Industry> industries = new ArrayList<>();

        for (Element option : options) {
            String value = option.attr("value").trim();
            String name = option.text().trim();

            if (!"all".equalsIgnoreCase(value) && !value.isEmpty()) {
                industries.add(new Industry(value, name));
            }
        }

        industryRepository.saveAll(industries);
        System.out.println("✅ Đã lưu " + industries.size() + " ngành vào bảng industry");
    }
}