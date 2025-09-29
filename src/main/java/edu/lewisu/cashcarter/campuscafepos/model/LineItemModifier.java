package edu.lewisu.cashcarter.campuscafepos.model;

public class LineItemModifier {

    private String option;
    private Modifier modifier;

    public String getOption() {return option;}
    public Modifier getModifier() {return modifier;}

    public LineItemModifier(String option, Modifier modifier) {
        this.option = option;
        this.modifier = modifier;
    }
}