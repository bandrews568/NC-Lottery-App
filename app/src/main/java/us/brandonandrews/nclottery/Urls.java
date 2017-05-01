package us.brandonandrews.nclottery;


public final class Urls {

    private Urls() {
    }

    private static final String BASE = "http://bandrews568.pythonanywhere.com/games/";
    private static final String BASE_SMART_PICK = "https://www.lotteryinformation.us/apps/smart-pick.php?";

    public static final String ALL_GAMES = BASE + "all/";
    public static final String PICK3 = BASE + "pick3/";
    public static final String PICK4 = BASE + "pick4/";
    public static final String CASH5 = BASE + "cash5/";
    public static final String LUCKY_FOR_LIFE = BASE + "luckyforlife/";
    public static final String POWERBALL = BASE + "powerball/";
    public static final String MEGA_MILLIONS = BASE + "megamillions/";

    public static final String PICK3_SMART_PICK = BASE_SMART_PICK +
            "state=NC&game=NCMID3&tb_state=&tb_links=&tb_country=US&tb_lang=0&adsurl=&tbsite=&d=google.com";
    public static final String PICK4_SMART_PICK = BASE_SMART_PICK +
            "state=NC&game=NCMID4&tb_state=&tb_links=&tb_country=US&tb_lang=0&adsurl=&tbsite=&d=google.com";
    public static final String CASH5_SMART_PICK = BASE_SMART_PICK +
            "state=NC&game=NCCASH5&tb_state=&tb_links=&tb_country=US&tb_lang=0&adsurl=&tbsite=&d=google.com";
    public static final String LUCKY_FOR_LIFE_SMART_PICK = BASE_SMART_PICK +
            "state=NC&game=MULFL&tb_state=&tb_links=&tb_country=US&tb_lang=0&adsurl=&tbsite=&d=google.com";
    public static final String MEGA_MILLIONS_SMART_PICK = BASE_SMART_PICK +
            "state=NC&game=MUMM&tb_state=&tb_links=&tb_country=US&tb_lang=0&adsurl=&tbsite=&d=google.com";
    public static final String POWERBALL_SMART_PICK = BASE_SMART_PICK +
            "state=NC&game=MUPB&tb_state=&tb_links=&tb_country=US&tb_lang=0&adsurl=&tbsite=&d=google.com";

}
