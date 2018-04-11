package org.tbogdanov.epamhw.module3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.tbogdanov.epamhw.module3.polyglot.Polyglot;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Timur Bogdanov on 11.04.18.
 */
public class PolyglotTest {

    private Polyglot polyglot;
    private ResourceBundle bundle;

    @BeforeEach
    public void init() {
        polyglot = new Polyglot();
    }

    @ParameterizedTest
    @ValueSource(strings = {"en_US, ru_RU"})
    public void languageTest(String localeString) {
        Locale locale = new Locale(localeString);
        bundle = ResourceBundle.getBundle("org.tbogdanov.epamhw.module3.polyglot.polyglot", locale);
        polyglot.setLocale(localeString);
        assertEquals(bundle.getString("question1"), polyglot.getQuestionStr(0));
        assertEquals(bundle.getString("question2"), polyglot.getQuestionStr(1));
        assertEquals(bundle.getString("question3"), polyglot.getQuestionStr(2));
        assertEquals(bundle.getString("chooseLanguageMessage"), polyglot.getChooseLanguageMessage());
    }

}
