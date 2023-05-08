package com.design.designMode.CreationalPatterns.BuilderPattern;

public class BuilderPatternDemo {
    /**
     * 建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象。
     * 何时使用：一些基本部件不会变，而其组合经常变化的时候。
     * 应用实例： 1、去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"。 2、JAVA 中的 StringBuilder。
     * 使用场景： 1、需要生成的对象具有复杂的内部结构。 2、需要生成的对象内部属性本身相互依赖。
     * 优点： 1、建造者独立，易扩展。 2、便于控制细节风险。
     * 缺点： 1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。
     * 注意事项：与工厂模式的区别是：建造者模式更加关注与零件装配的顺序。
     * 疑惑点：为啥抽象类Burger中要对接口中未重写的price方法，用abstract修饰符再次修饰成抽象方法让子类去实现？不写这样结果也不影响，哪为啥要abstract再次声明？
     * 答案：为了让继承类能更加直观的清楚被继承的类需要重写哪些方法，如果不用abstract修饰符再次修饰成抽象方法让子类去实现，那么继承类根本不知道需要重写的方法是哪里来的（可能是上个类，可能是上上上个类，可能链路太复杂，不便于查看）
     */
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());
    }
}
