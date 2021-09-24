package com.stabbers.semenov.web.specials;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Column {
    List<UserCell> userCells;
    int allHours;

    public Column(){
        userCells = new LinkedList<>();
        allHours = 0;
    }

    public void addUserCell(UserCell userCell){
        userCells.add(userCell);
        allHours += userCell.hours;
    }
}
