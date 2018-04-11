package org.tbogdanov.epamhw.module3.polyglot;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Created by Timur Bogdanov on 11.04.18.
 */
public class Polyglot {

    class Question {
        private String question;
        private String answer;

        public Question(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }
    }

    enum LocaleEnum {
        DEFAULT(""), EN("en_US"), RU("ru_RU");

        String postfix;

        LocaleEnum(String postfix) {
            this.postfix = postfix;
        }

        String getPostfix() {
            return postfix;
        }
    }

    private static final int QUESTION_COUNT = 3;
    private ResourceBundle bundle;
    private Question[] questions;
    private String chooseLanguageMessage;
    private String startMessage;
    private String languageErrorMessage;
    private String noSuchQuestionMessage;
    private LocaleEnum currentLocale;

    public Polyglot() {
        setLocale(LocaleEnum.DEFAULT);
    }

    public void setLocale(LocaleEnum localeEnum) {
        setLocale(localeEnum.postfix);
    }

    public void setLocale(String postfix) {
        Locale locale;
        if (!postfix.isEmpty()) {
            locale = new Locale(postfix);
        }
        else {
            locale = Locale.getDefault();
        }

        bundle = ResourceBundle.getBundle("org.tbogdanov.epamhw.module3.polyglot.polyglot", locale);

        for (int i = 0; i < QUESTION_COUNT; i++) {
            questions[i] = new Question(bundle.getString(String.format("question%d", i+1)),
                bundle.getString(String.format("answer%d", i+1)));
        }
        chooseLanguageMessage = bundle.getString("chooseLanguageMessage");
        startMessage = bundle.getString("startMessage");
        languageErrorMessage = bundle.getString("languageErrorMessage");
        noSuchQuestionMessage = bundle.getString("noSuchQuestionMessage");

        currentLocale = LocaleEnum.valueOf(postfix);
    }

    public LocaleEnum getLocaleEnum() {
        return currentLocale;
    }

    public Question getQuestion(int i) throws IllegalArgumentException {
        if (i < 0 || i >= QUESTION_COUNT) {
            throw new IllegalArgumentException("No such question");
        }
        return questions[i];
    }

    public Question[] getAllQuestions() {
        return questions;
    }

    public String getQuestionStr(int i) throws IllegalArgumentException {
        return getQuestion(i).getQuestion();
    }

    public String[] getAllQuestionsStr() {
        String[] questionStr = new String[QUESTION_COUNT];
        for (int i = 0; i < QUESTION_COUNT; i++) {
            questionStr[i] = getQuestion(i).getQuestion();
        }
        return questionStr;
    }

    public String getAnswerStr(int i) throws IllegalArgumentException {
        return getQuestion(i).getAnswer();
    }

    public String[] getAllAnswersStr() {
        String[] answerStr = new String[QUESTION_COUNT];
        for (int i = 0; i < QUESTION_COUNT; i++) {
            answerStr[i] = getQuestion(i).getAnswer();
        }
        return answerStr;
    }

    public String getChooseLanguageMessage() {
        return chooseLanguageMessage;
    }

    public String getStartMessage() {
        return startMessage;
    }

    public String getLanguageErrorMessage() {
        return languageErrorMessage;
    }

    public String getNoSuchQuestionMessage() {
        return noSuchQuestionMessage;
    }

}
