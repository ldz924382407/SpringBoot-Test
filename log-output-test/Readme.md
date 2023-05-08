市面上常见的日志框架有很多，比如：JCL、SLF4J、Jboss-logging、jUL、log4j、log4j2、logback等等，我们该如何选择呢？

市面上的日志框架；
JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j…
# 一.Java常用的日志框架介绍
## 日志的抽象层：JCL（Jakarta Commons Logging） SLF4j（Simple Logging Facade for Java） jboss-logging
- JCL：(Jakarta Commons Logging)：就是Apach的commons-logging.jar
- SLF4J：(Simple Logging Facade for Java)有调侃的人取了个好听的别名（酸辣粉4斤）,超级强大的一款日志框架
## 日志实现：  Log4j JUL（java.util.logging） Log4j2 Logback
- JUL：(Java util logging )是位于java.util.logging包下面的一款日志框架，属于JDK自带的日志实现
- Log4j：现在是Apache基金会下面的一个开源项目
- logback同样是由log4j的作者设计完成的，拥有更好的特性，用来取代log4j的一个日志框架，是slf4j的原生实现（springboot默认使用logback作为日志记录框架）
- Log4j2：Log4j是log4j 1.x和logback的改进版


左边选一个抽象层、右边来选一个实现；类似与我们经常使用的JDBC一样，选择不同的数据库驱动。
- 下面我们先看看日志的抽象层：JCL大家应该很熟悉，Commons Logging，spring中常用的框架最后一次更新2014年~~~；jboss-logging使用的场景太少了；就剩下SLF4j了也是我们springboot中使用的日志抽象层。
- 日志实现：大家应该看着都很熟悉把Log4j大家应该用的挺多的，Logback是Log4j的升级版本出至于同一个人开发的，考虑到以后的升级使用等问题，又写出了SLF4j的日志抽象层使用起来更加灵活。JUL（java.util.logging）一看就知道是java util包下的；Log4j2 咋一看像是Log4j的升级版本，其实并不是，它是apache下生产的日志框架。
SpringBoot选用 SLF4j和logback
