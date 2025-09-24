package edu.lewisu.cashcarter.campuscafepos.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import edu.lewisu.cashcarter.campuscafepos.data.ModifierDeserializer;

@JsonDeserialize(using = ModifierDeserializer.class)
public abstract class Modifier {

    private String option;
    protected LinkedHashMap<String, BigDecimal> options = new LinkedHashMap<String, BigDecimal>();

    public Modifier(String option, LinkedHashMap<String, BigDecimal> options) {
        this.option = option;
        this.options = options;
    }

    public abstract BigDecimal apply(BigDecimal value, String option);

    protected static LinkedHashMap<String, BigDecimal> stringValuesToBigDecimal(LinkedHashMap<String, String> stringMap) {
        LinkedHashMap<String, BigDecimal> bigDecimalMap = new LinkedHashMap<String, BigDecimal>();
        stringMap.forEach(new BiConsumer<String,String>() {

            @Override
            public void accept(String key, String value) {
                bigDecimalMap.put(key, new BigDecimal(value));
            }
        });
        return bigDecimalMap;
    }
}