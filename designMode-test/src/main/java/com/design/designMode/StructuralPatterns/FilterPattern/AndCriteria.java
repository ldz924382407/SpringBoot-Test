package com.design.designMode.StructuralPatterns.FilterPattern;

import java.util.List;

/**
 * @Author 211145187
 * @Date 2022/7/4 13:37
 **/
public class AndCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    //以第一标准criteria去匹配第二标准otherCriteria中的集合
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}
