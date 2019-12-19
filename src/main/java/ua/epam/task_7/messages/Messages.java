package ua.epam.task_7.messages;

public enum Messages {
    ACCOUNT_ALREADY_EXISTS("Account already exists."),
    ACCOUNT_ADDED("Added new Account."),
    ACCOUNT_NOT_FOUND("Requested account hasn't been found."),
    SUCCESS("Success.\n"),
    FAIL("Something went wrong."),
    ACCOUNT_ALREADY_DISABLED("Account is already disabled."),
    DEVELOPER_NOT_FOUND("Requested developer hasn't been found."),
    DEVELOPER_ALREADY_EXISTS("Developer already exists."),
    DEVELOPER_ADDED("Added new Developer"),
    SKILL_NOT_FOUND("Requested skill hasn't been found."),
    SKILL_ALREADY_EXISTS("Skill already exists."),
    SKILL_ADDED("Added new Skill"),
    WRONG_INPUT("Wrong input"),
    ACCOUNT_MENU("Please make a choice:\n" +
            "1. Print all accounts.\n" +
            "2. Print account by ID.\n" +
            "3. Add new account.\n" +
            "4. Update account.\n" +
            "5. Disable account.\n" +
            "6. Exit.\n"),
    ACCOUNT_ID_REQUEST("Please enter account ID"),
    ACCOUNT_INFO_REQUEST("Please enter account info"),
    DEVELOPER_MENU("Please make a choice\n" +
            "1. Print all developers.\n" +
            "2. Print developer by ID.\n" +
            "3. Add new developer.\n" +
            "4. Update developer.\n" +
            "5. Exit.\n"),
    DEVELOPER_ID_REQUEST("Please enter developer's ID"),
    DEVELOPER_NAME_REQUEST("Please enter developer's name"),
    DEVELOPER_INFO_REQUEST("Please enter developer's information"),
    DEVELOPER_ACCOUNT_STATUS_UPDATE("1. Change account status to ACTIVE\n" +
            "2. Change account status to NOT ACTIVE\n"),
    DEVELOPER_SKILL_ID_REQUEST("Please choose ID of a SKILL"),
    SKILL_MENU("Please make a choice\n" +
            "1. Print all skills.\n" +
            "2. Print skill by ID.\n" +
            "3. Add new skill.\n" +
            "4. Update skill.\n" +
            "5. Exit.\n"),
    SKILL_REQUEST("Please enter skill"),
    SKILL_ID_REQUEST("Please enter skill ID");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
