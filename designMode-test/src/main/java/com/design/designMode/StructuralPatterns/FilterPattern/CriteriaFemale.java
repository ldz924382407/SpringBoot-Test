package com.design.designMode.StructuralPatterns.FilterPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 211145187
 * @Date 2022/7/4 13:35
 **/
public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
