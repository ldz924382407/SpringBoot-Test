package com.design.designMode.StructuralPatterns.FilterPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 211145187
 * @Date 2022/7/4 13:35
 **/
public class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
