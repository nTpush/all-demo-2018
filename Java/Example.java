/* 
1、-------------------------------------------------------------------------------------- java:[]
两个线程并发执行以下代码，假设a是全局变量，初始为1，有几种输出的可能？
void foo () {
   a=a+1;
   System.out.println("%d ",a);
}
首先这里a = a+1不是原子操作，原子操作是不可分割的，在Java里面，++i或者--i不是线程安全的，这里面有三个独立的操作：
获得变量当前值，为该值+1/-1，然后写回新的值。在没有额外资源可以利用的情况下，只能使用加锁才能保证读-改-写这三个操作是“原子性”的。
a=a+1这一条语句，看起来是一条语句，实际上这条语句会被编译成大概3条指令的
读a
+1
写a
再读a （打印）
两个线程在并行执行过程这个几个操作交替执行就会出现各种不同结果
事务四大特征：原子性，一致性，隔离性和持久性
并发操作会带来的数据不一致性问题：
脏读：就是指当一个事务对数据进行了修改但还没有提交到数据库时，另一个事务访问并使用了这个数据。
不可重复读：在一个事务内两次读数据之间，第二个事务访问该数据并进行了修改。
丢失修改：两个事务读入同一数据并修改，T2提交的结果破坏了T1提交的结果。例如T1读A修改为A-1，T2也读A修改为A-1（实际上应该是读A-1，修改 为A-2）
幻读：同一事务在两个不同的时间段执行相同的查询条件得到的结果不一致。

数据库定义这四个隔离级别就是为了解决数据在高并发下所产生的问题

我们熟知的MySQL 数据库的默认事务隔离级别就是 READ_COMMITTED

2、-------------------------------------------------------------------------------------- java:[]
public class Example {  
  String str = new String("good");  
  char[] ch = {'a','b','c'};  
  public static void main(String[] args) {  
     Example ex = new Example();  
     ex.change(ex.str, ex.ch);  
     System.out.print(ex.str +"and");  
     System.out.print(ex.ch);   
  }  
  public void change(String str, char ch[]){  
     str= "test ok";  
     ch[0]= 'g';  
  }  
} 
3、-------------------------------------------------------------------------------------- js:[]
var a = 1;
function foo() {
  if (!a) {
    var a = 2;
  }
  alert(a);
};
foo();
4、-------------------------------------------------------------------------------------- java:[]
Integer f1 = 100;
Integer f2 = 100;
Integer f3 = 150;
Integer f4 = 150;
System.out.println(f1 == f2);
System.out.println(f3 == f4);



在sql查询中为了提高查询效率，我们常常会采取一些措施对查询语句进行sql优化，下面总结的一些方法，有需要的可以参考参考。

1.对查询进行优化，应尽量避免全表扫描，首先应考虑在 where 及 order by 涉及的列上建立索引。

2.应尽量避免在 where 子句中对字段进行 null 值判断，否则将导致引擎放弃使用索引而进行全表扫描，如：    
select id from t where num is null    
可以在num上设置默认值0，确保表中num列没有null值，然后这样查询：    
select id from t where num=0

3.应尽量避免在 where 子句中使用!=或<>操作符，否则将引擎放弃使用索引而进行全表扫描。

4.应尽量避免在 where 子句中使用 or 来连接条件，否则将导致引擎放弃使用索引而进行全表扫描，如：    
select id from t where num=10 or num=20    
可以这样查询：    
select id from t where num=10    
union all    
select id from t where num=20
5.in 和 not in 也要慎用，否则会导致全表扫描，如：    
select id from t where num in(1,2,3)    
对于连续的数值，能用 between 就不要用 in 了：    
select id from t where num between 1 and 3

6.下面的查询也将导致全表扫描：    
select id from t where name like '%abc%'

7.应尽量避免在 where 子句中对字段进行表达式操作，这将导致引擎放弃使用索引而进行全表扫描。如：    
select id from t where num/2=100    
应改为:    
select id from t where num=100*2

8.应尽量避免在where子句中对字段进行函数操作，这将导致引擎放弃使用索引而进行全表扫描。如：    
select id from t where substring(name,1,3)='abc'--name以abc开头的id   
应改为:    
select id from t where name like 'abc%'


快速失败(fail-fast)和安全失败(fail-safe)的区别
一：快速失败（fail—fast）
      在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的结构进行了修改（增加、删除），则会抛出Concurrent Modification Exception。
      原理：迭代器在遍历时直接访问集合中的内容，并且在遍历过程中使用一个 modCount 变量。集合在被遍历期间如果结构发生变化，就会改变modCount的值。每当迭代器使用hashNext()/next()遍历下一个元素之前，都会检测modCount变量是否为expectedmodCount值，是的话就返回遍历；否则抛出异常，终止遍历。
      注意：这里异常的抛出条件是检测到 modCount！=expectedmodCount 这个条件。如果集合发生变化时修改modCount值刚好又设置为了expectedmodCount值，则异常不会抛出。因此，不能依赖于这个异常是否抛出而进行并发操作的编程，这个异常只建议用于检测并发修改的bug。
      场景：java.util包下的集合类都是快速失败的，不能在多线程下发生并发修改（迭代过程中被修改）。
二：安全失败（fail—safe）
      采用安全失败机制的集合容器，在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历。
      原理：由于迭代时是对原集合的拷贝进行遍历，所以在遍历过程中对原集合所作的修改并不能被迭代器检测到，所以不会触发Concurrent Modification Exception。
      缺点：基于拷贝内容的优点是避免了Concurrent Modification Exception，但同样地，迭代器并不能访问到修改后的内容，即：迭代器遍历的是开始遍历那一刻拿到的集合拷贝，在遍历期间原集合发生的修改迭代器是不知道的。
      场景：java.util.concurrent包下的容器都是安全失败，可以在多线程下并发使用，并发修改。

说一下 java8是java一个重要版本，类似一个转折点，lambda和流要好好学下


docker run -it --name mdhtomcat -d -p 80:8080  tomcat:wave1.0  catalina.sh 1  ：通过传参1，告诉catalina.sh 修改server.xml，该容器不开启访问日志。
docker run -it --name mdhtomcat -d -p 80:8080  tomcat:wave1.0  :默认走catalina.sh命令；默认访问日志开启；
需要注意的是 在dockerfile中一定要用 ENTRYPOINT  

JDBC中，接口API是由oracle公司提供的，这些接口API一般是由Boot类加载器进行加载的
但是具体的实现是由App进行加载的。这样的话，就会使接口找不到实现。就会出现错误。所以我们要
使用线程上下文切换来进行，使加载的类加载器都是相同的

用单线程处理一个Selector，然后通过Selector.select()方法来获取到达事件，在获取了到达事件之后，就可以逐个地对这些事件进行响应处理。与Selector有关的一个关键类是SelectionKey，一个SelectionKey表示一个到达的事件，这2个类构成了服务端处理业务的关键逻辑。
当调用 register(Selector sel, int ops) 将通道注册选择器时，选择器对通道的监听事件，需要通过第二个参数 ops 指定。可以监听的事件类型（ 可使用 SelectionKey 的四个常量表示）：
读 : SelectionKey.OP_READ （1） 
写 : SelectionKey.OP_WRITE （4） 
连接 : SelectionKey.OP_CONNECT （8） 
接收 : SelectionKey.OP_ACCEPT （16）


String和StringBuilder、StringBuffer的区别：
Java平台提供了两种类型的字符串：String和StringBuffer/StringBuilder，它们可以储存和操作字符串。其中String是只读字符串，也就意味着String引用的字符串内容是不能被改变的。而StringBuffer/StringBuilder类表示的字符串对象可以直接进行修改。StringBuilder是Java 5中引入的，它和StringBuffer的方法完全相同，区别在于它是在单线程环境下使用的，因为它的所有方面都没有被synchronized修饰，因此它的效率也比StringBuffer要高。

所以，StringBuffer是线程安全的，在多线程情况下使用。
