package com.design.designMode.StructuralPatterns.CompositePattern;

/**
 * 组合模式（Composite Pattern），又叫部分整体模式
 * 意图：将对象组合成树形结构以表示"部分-整体"的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性。
 * 主要解决：它在我们树型结构的问题中，模糊了简单元素和复杂元素的概念，客户程序可以像处理简单元素一样来处理复杂元素，从而使得客户程序与复杂元素的内部结构解耦。
 * 何时使用： 1、您想表示对象的部分-整体层次结构（树形结构）。 2、您希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
 * 优点： 1、高层模块调用简单。 2、节点自由增加。
 * 缺点：在使用组合模式时，其叶子和树枝的声明都是实现类，而不是接口，违反了依赖倒置原则。
 * 使用场景：部分、整体场景，如树形菜单，文件、文件夹的管理。
 **/
public class CompositePatternDemo {
    /**
     * 结果展示：
     * Employee :[ Name : John, dept : CEO, salary :30000 ]
     * Employee :[ Name : Robert, dept : Head Sales, salary :20000 ]
     * Employee :[ Name : Richard, dept : Sales, salary :10000 ]
     * Employee :[ Name : Rob, dept : Sales, salary :10000 ]
     * Employee :[ Name : Michel, dept : Head Marketing, salary :20000 ]
     * Employee :[ Name : Laura, dept : Marketing, salary :10000 ]
     * Employee :[ Name : Bob, dept : Marketing, salary :10000 ]
     */
    public static void main(String[] args) {
        Employee CEO = new Employee("John","CEO", 30000);

        Employee headSales = new Employee("Robert","Head Sales", 20000);

        Employee headMarketing = new Employee("Michel","Head Marketing", 20000);

        //clerk：职员
        Employee clerk1 = new Employee("Laura","Marketing", 10000);
        Employee clerk2 = new Employee("Bob","Marketing", 10000);
        //salesExecutive：销售
        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //打印该组织的所有员工
        System.out.println(CEO);
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}
