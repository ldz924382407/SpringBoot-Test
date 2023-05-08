package com.design.designMode.StructuralPatterns.FilterPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式（Filter Pattern）或标准模式（Criteria Pattern）是一种设计模式
 * 意图：允许开发人员使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式把它们连接起来。
 **/
public class CriteriaPatternDemo {
    /**
     * 结果展示：
     * Males:
     * Person : [ Name : Robert, Gender : Male, Marital Status : Single ]
     * Person : [ Name : John, Gender : Male, Marital Status : Married ]
     * Person : [ Name : Mike, Gender : Male, Marital Status : Single ]
     * Person : [ Name : Bobby, Gender : Male, Marital Status : Single ]
     *
     * Females:
     * Person : [ Name : Laura, Gender : Female, Marital Status : Married ]
     * Person : [ Name : Diana, Gender : Female, Marital Status : Single ]
     *
     * Single Males:
     * Person : [ Name : Robert, Gender : Male, Marital Status : Single ]
     * Person : [ Name : Mike, Gender : Male, Marital Status : Single ]
     * Person : [ Name : Bobby, Gender : Male, Marital Status : Single ]
     *
     * Single Or Females:
     * Person : [ Name : Robert, Gender : Male, Marital Status : Single ]
     * Person : [ Name : Diana, Gender : Female, Marital Status : Single ]
     * Person : [ Name : Mike, Gender : Male, Marital Status : Single ]
     * Person : [ Name : Bobby, Gender : Male, Marital Status : Single ]
     * Person : [ Name : Laura, Gender : Female, Marital Status : Married ]
     */
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert","Male", "Single"));
        persons.add(new Person("John","Male", "Married"));
        persons.add(new Person("Laura","Female", "Married"));
        persons.add(new Person("Diana","Female", "Single"));
        persons.add(new Person("Mike","Male", "Single"));
        persons.add(new Person("Bobby","Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +", Marital Status : " + person.getMaritalStatus()
                    +" ]");
        }
    }
}
