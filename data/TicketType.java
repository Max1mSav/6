package data;

/**
 * Enum class for ticket types.
 */

public enum TicketType {
    USUAL,
    BUDGETARY,
    CHEAP;

    public static String nameList() {
        String nameList = "";
        for (TicketType ticketType : values()) {
            nameList += ticketType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
