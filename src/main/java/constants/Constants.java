package constants;

public final class Constants {
    public static final String INITIAL_HOME_PAGE = "https://www.bookdepository.com";
    public static final String SIGN_IN_PAGE_URL = "https://www.bookdepository.com/?status=welcome&";
    public static final String SEARCH_RESULT_PAGE_URL = "https://www.bookdepository.com/search?searchTerm";
    public static final String BASKET_PAGE_URL = "https://www.bookdepository.com/basket";
    public static final String CHECKOUT_PAGE_URL = "https://www.bookdepository.com/payment/guest";
//    public static final String TEXT_PATTERN = "%s[contains(text(), '%s')]";
//    public static final String ADD_TO_CART_BUTTON = "//a[contains(@class, 'add-to-basket')]";
    public static final String BOOK_ITEM_TITLE = "//div[@class='book-item']//h3/a";

    private Constants(){
    }
}