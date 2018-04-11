package org.tbogdanov.epamhw.module2.officesupplies.noviceset;

import org.tbogdanov.epamhw.module2.officesupplies.properties.Color;
import org.tbogdanov.epamhw.module2.officesupplies.properties.Manufacturer;
import org.tbogdanov.epamhw.module2.officesupplies.supplies.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Timur Bogdanov on 10.04.18.
 */
public class NoviceSet {

    private static final Random random = new Random();

    private Pen[] pens;
    private Pencil[] pencils;
    private Paper paper;
    private Scissors scissors;
    private Eraser eraser;

    public NoviceSet(Manufacturer manufacturer) {
        pens = new Pen[5];
        pencils = new Pencil[5];
        Color[] colors = Color.values();
        for (int i = 0; i < colors.length; i++) {
            pens[i] = new Pen(manufacturer, colors[i].toString()+"Pen", colors[i]);
            pencils[i] = new Pencil(manufacturer, colors[i].toString()+"Pencil", colors[i]);
        }
        paper = new Paper(manufacturer, 10);
        scissors = new Scissors(manufacturer, "SharpScissors");
        eraser = new Eraser(manufacturer, "Eraser");
    }

    public List<OfficeSupply> getAllSuppliesAsList() {
        List<OfficeSupply> retList = new LinkedList<>();
        retList.addAll(Arrays.asList(pens));
        retList.addAll(Arrays.asList(pencils));
        retList.add(paper);
        retList.add(scissors);
        retList.add(eraser);
        return retList;
    }

    public Pen[] getPens() {
        return pens;
    }

    public Pencil[] getPencils() {
        return pencils;
    }

    public Paper getPaper() {
        return paper;
    }

    public Scissors getScissors() {
        return scissors;
    }

    public Eraser getEraser() {
        return eraser;
    }

    public void setPens(Pen[] pen) {
        this.pens = pen;
    }

    public void setPencils(Pencil[] pencil) {
        this.pencils = pencil;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public void setScissors(Scissors scissors) {
        this.scissors = scissors;
    }

    public void setEraser(Eraser eraser) {
        this.eraser = eraser;
    }
}
