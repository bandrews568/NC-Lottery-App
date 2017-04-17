package us.brandonandrews.nclottery;


public final class Urls {

    private Urls() {
    }

    private static final String BASE = "http://bandrews568.pythonanywhere.com/games/";

    public static final String ALL_GAMES = BASE + "all";
    public static final String PICK3 = BASE + "pick3";
    public static final String PICK4 = BASE + "pick4";
    public static final String CASH5 = BASE + "cash5";
    public static final String LUCKY_FOR_LIFE = BASE + "luckyforlife";
    public static final String POWERBALL = BASE + "powerball";
    public static final String MEGA_MILLIONS = BASE + "megamillions";
}
