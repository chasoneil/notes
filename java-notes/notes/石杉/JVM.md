### JVM

#### 1. 认识JVM

##### 1.1 Java程序是怎么运行起来的

* 编译  ->   将.java 文件编译成字节码文件 .class
* 执行 
    * 通过java 执行单个.class文件
    * 通过 java -jar 命令运行已经编译好 并 打包完成的 .jar 包或者 .war 包
* 类加载    执行的过程中需要进行类加载，找到执行的入口，然后从入口开始执行。

![](C:\Users\86183\AppData\Roaming\Typora\typora-user-images\image-20210713094632913.png)



**案例一 手动编译并执行java**

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

我们把上述文件放到一个文件夹下，确保配置好Java 环境变量

![image-20210813144140053](C:\Users\86183\AppData\Roaming\Typora\typora-user-images\image-20210813144140053.png)

* 先使用Javac 命令编译 .java 文件，生成了.class文件
* 然后使用java 执行对应的文件名即可



*问题：既然可以从java  -> class 那么必然可以从 class -> java 那是不是意味着你们公司的代码只要运行起来，就已经泄露了?*



##### 1.2 类的加载和使用

* 类的加载使用经历的过程:
    * 加载 -> 验证 -> 准备 -> 解析 -> 初始化 -> 使用 -> 卸载

* 类加载的时机
     * 什么时候使用什么时候加载
* 分析类加载中比较关键的几个步骤

     * 验证：这个过程其实比较复杂，简单的概述 验证就是验证类是否异常，是有有安全问题等等
     * 准备：给加载类分配内存空间，给类中static类型的变量分配空间  给初始化值 也是一个核心阶段
     * 解析：这个过程就是把符号引用替换成直接引用
     * 初始化： 这个阶段比较核心，这个阶段主要是执行初始化代码
         * 第二阶段准备阶段给变量的是初始化的值，如果一个变量在代码中有值，那么这个值在初始化阶段完成赋值
         * 执行static静态代码块中的内容

```java
 package com.chason.base.jvm;
 
 public class JvmDemo1 {
 
     /**
      * 准备阶段会给这个变量分配内存空间并初始化值为0
      * 初始化阶段会给这个变量赋值为 5 
      */
     private static int NUMBER = 5;
 
     /**
      * static静态代码块中的内容也是初始化阶段执行的
      */
     static {
         
     }
 
 
     /*
      * 这里是函数的入口
      */
     public static void main(String[] args) {
 
     }
 
     /**
      * 初始化阶段会执行构造方法
      */
     public JvmDemo1 (){
         
     }
 
 }
```

* 什么时候会初始化一个类？
    * new Object() 的时候  
    * main （入口类）在程序加载的时候就要执行初始化
* 初始化有什么要注意的?
    * 当JVM初始化一个类的时候，如果发现他的父类还没有进行初始化，会先初始化他的父类



##### 1.3 双亲委派



1. 类加载器的种类

    * BootStrapClassLoader  启动类加载器 最顶层加载器
    * ExtClassLoader 扩展类加载器 加载lib\ext文件夹下的类
    * ApplicationClassLoader 应用类加载器
    * 自定义加载器
2. 双亲委派
   * 所谓的双亲委派 就是把活交给父加载器去干，如果父加载器没法加载才会交给子加载器去做



##### 1.4 JVM 中的内存

1.  前面所说的类加载到内存中，那么存放这些被加载的类的实例的内存空间 是方法区

      * 在 JDK 1.8 之后，更名为了元数据区 Metaspace 
      * 元数据区存放的是加载进来的类的基本信息，比如类的全名，全局的static类型的变量 之类的，是类的元数据

2.  我们在执行Java的时候，本质上是执行类中的方法，执行方法产生的会有局部变量产生，那么存放这些局部变量的内存空间是Java虚拟机栈

      * 在栈空间中，会针对每个执行的方法创建一个栈帧

      * 栈帧中存放着这个方法的局部变量列表，方法出口，动态链接，操作数栈等信息

      * 栈帧是伴随着方法执行产生的，方法执行结束，则栈帧删除 也就是我们常说的入栈出栈

        ==栈是伴随着线程的，一个线程就有其对应的虚拟机栈==

        

3.  当我们的程序被编译成字节码文件以后，需要有个东西，来记录并存放程序执行的位置，这个就是程序计数器
      * 因为Java是多线程执行的，所以程序计数器，也是伴随线程而生，一个线程就拥有一个程序计数器

4.  JVM实际运行的时候，我们会创建大量的对象，那么这些对象就会被存放在堆内存的空间中

5.  JVM中很多方法是调用的本地方法native,那么 这些方法的执行也要有自己的内存空间，我们称为本地方法区

6.  Java中有时候使用的不一定是JVM空间，比如NIO中可能会用到堆外空间



##### 1.5 JVM中的垃圾回收

 1. JVM 分代模型

    * 分代模型是因为 Java在运行过程中，对象生命周期不同，部分对象生命周期很短，部分很长
    * 之所以要分代，是因为上面所说的这些对象，因为生命周期的不同，需要的垃圾回收算法也是不同的

    * 年轻代		所谓的年轻代 就是刚被创建的对象所在的内存空间，也是因为刚被创建，称为年轻代
    * 老年代        如果一个对象经过很多次垃圾回收之后还是存在的，那么他就被放到这个内存空间
        * 默认情况下，当躲过15次垃圾回收，这个对象就会进入老年代
        * 动态年龄判断规则，比如年龄1+ 2+...N 的对象总大小超过survivor区的一半，那么年龄大于N的对象就都要移动到老年代中。
        * 大对象直接进入老年代，所谓的大对象是指对象超过一定的大小，而这个大小可以通过JVM参数来设置
        * 如果一次GC Eden 中存活的对象总大小大于survivor ,这些对象会直接进入老年代
    * 永久代        就是上文中提到的元数据空间（方法区）

    2.  为什么要对堆内存进行划分?

     是因为 Java 堆内存中存放的对象存活的周期不同，有的对象存货的周期很长，有的却很短，为了方便分类回收，做了内存划分机制。

    3. 垃圾回收的触发时机   垃圾回收并不是堆内存中产生了垃圾就进行回收的，有自己的时间
* 当年轻代的内存空间已经快满了  新对象分配的内存空间不够了 会进行垃圾回收 这种我们称为Young GC 或者 Minor GC



##### 1.6 JVM 参数

* -Xms  堆内存大小
* -Xmx  最大堆内存大小
* -Xmn  新生代大小
* -XX:PermSize   永久代大小   JDK 1.8以后改成  -XX:MetaSpaceSize
* -XX:MaxPermSize   永久代最大大小   JDK  1.8后改成  -XX:MaxMetaSpaceSize
* -Xss  栈内存大小
* -XX:MaxTenuringThreshold   一个对象经过多少次GC才会进入老生代，默认15
* -XX:PretenureSizeThreshold  大对象的大小
* -XX:HandlePromotionFailure  是否开启老年代担保机制 默认开启  1.6以后废弃该参数
* -XX:SurvivorRatio=8      Eden 所占年轻代比例，默认8 为80%
* -XX:+UseParNewGC     指定使用ParNew 垃圾回收器
* -XX:ParallelGCThreads    当使用ParNew垃圾回收器，可以用该参数调节垃圾回收器使用的线程数
* -XX:CMSInitiatingOccupancyFaction  老年代中已使用空间最大占比触发CMS(FULL GC) jdk1.6 默认是92%
* -XX:UseCMSCompactAtFullCollection 每次full GC之后，stop the world 进行老年代的对象整理
  * 因为CMS 使用标记 清除算法进行回收，回收之后必然存在内存碎片，所以需要整理
  * 这个参数默认是开启的




##### 1.7 什么时候会触发垃圾回收

* 新对象都是在新生代中创建，那么如果出现新生代中无法创建对象的时候就需要进行垃圾回收

* 强软弱虚四种引用

  * 区别是什么，分别有什么特点

  

#### 2. 垃圾回收(GC)

##### 2.1 垃圾回收算法

* 标记清除算法 
    * 最开始的算法 会造成大量的内存碎片
* 复制算法   ==新生代常用的垃圾回收算法==
    * 复制算法就是把堆内存分为两个模块，使用标记整理，将需要保留的对象挪到其中一个区域，剩下的另一个区域全部删除
    * 缺点非常明显，那就是内存被分成两个部分非常浪费内存
    * 优化： 针对上面的缺点，进行优化
        * 我们发现 其实每次GC保留的对象其实占比非常少，意味着其实90%以上的对象是直接被垃圾清理的
        * 根据上面的情况，把内存划分成三个区，分别是Eden 和两个 survivor
        * Eden 占用80%的空间 ，两个survivor各占10%
    * 复制算法是这样的， 刚开始两个sur是空的， 创建的对象都在Eden中，第一波GC之后，把存活的对象放到其中一个sur中，然后清空Eden, 第二次是GC Eden和其中那个sur 把存活的放到另一个sur中，清空sur1和Eden ,以此类推。

##### 2.2 什么情况下对象进入老年代

* 对象经过一定次数的回收之后，仍然存在，则这个对象会进入老年代。
    * 默认值是15次 通过 -XX:MaxTenuringThreshold 设置这个值
* 动态对象年龄判断 
    * 1+2+...+n 的对象总大小大于sur区大小的一半，则大于n岁的对象会进入老年代
    * 主要目的是让那些本身就要去老年代中的对象早点去老年代
* 大对象直接进入老年代
    * 通过-XX:PretenureSizeThreshold 设置大对象的大小，超过该值的对象直接进入老年代
    * 主要是因为大对象的拷贝非常消耗性能和时间
* 特殊情况：当某次GC之后，Eden 中存活的对象大小和大于了sur的大小，那么这波对象会直接进入老年代
    * 基于上面的特殊情况，如果老年代剩余空间也不够了，不足以放下Eden 中存活的对象咋办? 引入老年代内存分配担保原则
        * 首先执行从年轻代对象往老年代转移的时候，会判断老年代剩余空间大小是不是大于年轻代所有对象大小总和，考虑极端情况，经过一次GC，年轻代中所有的对象都存活了下来，那么如果这些对象会全部移动到老年代中，老年代要接的下
        * 如果老年代剩余空间大小已经接不住年轻代，那么这时候需要有个担保了，事实上虽然我们做了上面这个判断，但是我们考虑的是极端情况，其实绝大多数时候，只有极少数对象会在GC之后保留下来，那么这时会看 -XX:HandlePromotionFailure  默认开启，如果这个参数开启，那么则会判断Minor GC剩余对象的平均大小和老年代剩余空间大小，如果老年代装得下，那么还是会尝试移动这部分对象到老年代中。  

##### 2.3 老年代垃圾回收算法

* 概念： 老年代的垃圾回收，又称为Full GC
* 触发老年代垃圾回收的时机
    * Minor GC前的检查 发现老年代剩余空间小于 年轻代对象大小总和 在没有开启担保的情况下，会进行FULL GC
    * Minor GC之后，发现需要移动到老年代对象大小的和大于老年代剩余空间，引发FULL GC
* 老年代的垃圾回收算法：一般使用标记清除算法 （CMS）



> JVM 优化的本质

JVM 优化的本质， 是让对象尽量在新生代里分配和回收，尽量减少对象频繁进入老年代，避免频繁对老年代进行垃圾回收，同时设置合理的内存大小，减少年轻代的垃圾回收



##### 2.4 垃圾回收器

​	在JVM 中，不同区域可以使用不同的垃圾回收器，常见的垃圾回收器分为以下三种

* ParNew   一般用在新生代回收器 多线程并发
* CMS         一般用在老年代回收器 多线程并发
* G1            新生代和老年代均可使用，使用了更加优秀的算法，提高了效率



##### 2.5 GC中的Stop the world

​	事实上， GC的过程中，是不能再继续创建新的对象的，因为新对象会扰乱GC, 所以GC的痛点就是Stop the world， 在进行GC的时候要停止所有的工作线程，专心致志的进行GC。

​	JVM 垃圾回收器的优化，其实就是降低Stop the world的时间，降低GC对工作线程的影响。



##### 2.6 最常用的年轻代回收器 ParNew

* 通过参数指定使用 ParNew  -XX:+UseParNewGC

* 使用ParNew 垃圾回收线程数一般等于CPU核心数



##### 2.7 CMS的工作原理

* 初始标记
    * 这个阶段是 Stop the world
    * 这个阶段只标记 GC-roots 出发的对象  也正是因为如此，所以这个阶段的速度是非常快的
* 并发标记
    * 这个阶段是并发执行，工作线程正常执行
    * 这个阶段会根据上个阶段标记出的对象追踪所有被引用中的对象，因此这个阶段时间比较长
    * 因为这个阶段是并发执行的，所以这个阶段会有一些新对象被创建
* 重新标记
    * 这个阶段是 Stop the world 
    * 这个阶段是去标记哪些在第二阶段执行过程中的那些时间里产生的对象
    * 因为并发表计时间并不长，所以这个阶段其实阻塞的时间也不久
* 并发清理
    * 这个阶段是并发执行
    * 清理前面标记的垃圾对象和挪动还在使用中的对象

==问题: 重新标记的阶段是只标记GC-ROOT还是全都标记？==



##### 2.8 CMS并发工作中CPU资源的分配

​	CMS 的垃圾回收一共分为四个步骤，其中两个并发执行的步骤其实都比较耗时，同时比较消耗资源，所以使用并发执行。

但是并发执行需要消耗CPU资源，如果并发过多，则影响工作线程。

* CMS 启用的默认垃圾回收线程数是 （CPU核数 + 3）/ 4



##### 2.9 Cocurrent Mode Failure问题

​	这个问题的本质是因为 “浮动垃圾”

* 浮动垃圾 ： CMS 在垃圾回收的第四个阶段并发清理，并发意味着这个阶段其实工作线程可以工作，工作线程可以工作意味着会产生对象，如果这个阶段产生的对象因为Minor GC 而进入了老年代，但是这个对象又没有被引用，那么这种对象就被称为“浮动垃圾”。
* 浮动垃圾的产生在CMS中是不可避免的，为了保证这些浮动垃圾顺利进入老年代，然后被下次的GC清理掉，所以老年代必须保留一定的空间，JDK1.6 中默认是 92%，这意味着，只要老年代使用的大小达到老年代大小的92% ，则会触发CMS垃圾回收。
* 如果在CMS期间，真的出现了浮动垃圾的大小超过了剩余空间的大小，那么此时 Serial 会强制接手CMS ,Stop the world 执行单线程的垃圾回收，这个效率是非常低的。

==本章中CMS是老年代的垃圾回收，也就是说是full gc, 事实上，CMS 工作是不影响ParNew工作的，所以要分开来看==



##### 2.10 CMS怎么避免内存碎片的问题

​	CMS 使用的标记清除算法执行垃圾回收，这个算法会产生大量的内存碎片

* CMS 通过每次Full GC之后都stop the world的方式进行碎片整理
* 上述整理的开关是 -XX:+UseCMSCompactAtFullCollection   默认开启，开启则意味着Full GC后进行碎片整理，关闭则不整理
* 你也可以通过 -XX:CMSFullGCsBeforeCompaction  参数调整整理的频率，改参数默认是0 意味着每次FullGC后都进行整理



#### 3. G1垃圾回收器

​	ParNew  + CMS的组合 有一个痛点，就是Stop the world. 针对减少Stop the world的时间，出现了G1

* G1 是不需要区分年轻代和老年代，他可以用于两个区域的垃圾回收，这点不同于ParNew +  CMS的组合
* G1 最大的特点，就是把堆内存分为了很多个Region
* G1 也有年轻代和老年代的概念，但是是逻辑上的，意思是某些region 组成了年轻代， 某些region组成了老年代
* G1 的核心就是他可以提供一个==垃圾回收的预期时间设置==
    * 上述这个预期时间设置你可以这么理解：就是比如你可以规定他1个小时之内，垃圾回收stop the world的时间不能超过1分钟
    * 你只需要设置，至于怎么完成目标由G1决定



###### 3.1 G1怎么实现预期时间规划

​	重点就是追踪每个Region的动态回收价值。

什么叫动态回收价值呢？比如一个region有10M垃圾回收需要 10s ,另一个region 有200M垃圾回收只要8秒，那么2号region的价值就比较高。

​	而通过定位不同region的回收量和回收时间，能让G1的回收更加灵活



###### 3.2  G1的内存设定

​	G1 是分了很多个region ,其实这些region的大小是不固定的，那么到底有多少个region，每个region的大小是多少这个问题就非常关键了。

* 当我们通过 -XX: UseG1GC 使用G1进行垃圾回收，同时指定了堆内存的大小，那么G1会把堆内存 /  2048 (JVM 最多可以有2048个 region)  一般情况下我们都是保持这个默认的方案。
* 可以手动指定region大小：-XX:G1HeapRegionSize  
* G1 在开始时默认新生代 占内存大小的 5% 可以通过 -XX: G1NewSizePercent 来设置 一般维持默认设置
* 运行过程中 新生代占比会逐渐增大（region增多）默认最大不超过堆内存的60% 使用-XX:G1MaxNewSizePercent来控制
* 事实上在整个JVM运行过程中， 每个区域的大小是动态变化的
* 同时G1也有逻辑上的 Eden 和两个 survivor 的概念
* G1默认使用复制算法进行垃圾回收。
* 事实上，G1使用复制算法来垃圾回收也是需要Stop the world的，但是他可以通过 -XX:MaxGCPauseMills 来设置Stop the world的时间，默认是200ms , 通过我们的设置，加上每个region的价值，G1可以做到最优回收。



###### 3.3 G1对象进入老年代

​	G1 从年轻代到老年代和之前的回收器其实没有区别

* 到达了一定的年龄 进入老年代

* 动态年龄规划方案中 到了年龄n 大小超过survivor的50% 则年龄n以上的都进入老年代

    ==但是， G1和前面不一样的是，大对象不会直接进入老年代==

* 大对象如果超过了一个region的大小，那么G1会分配多个region来存放这个大对象



###### 3.4 G1触发新老混合垃圾回收

* 我们知道 默认情况下 新生代的占比为60%  老年代的占比为 40%
* 默认情况下，当老年代的大小超过45%的堆内存总大小的时候，就会触发新老垃圾混合回收，可以用-  XX:InitiatingHeapOccupancyPercent  参数来控制 默认为45%



###### 3.5 G1 垃圾回收的步骤

* 初始标记  Stop the world 这个阶段标记 GC-roots对象，因为这部分对象很少，所以速度很快
* 并发标记  这个阶段是并发执行，这个阶段会从GC-Roots出发，标记所有正在使用中的对象，这个阶段会比较慢，但是因为并发不影响工作线程
* 最终标记  Stop the world 这个阶段会根据第二步的结果再次标记一次哪些对象是使用中的
* 混合回收  这个阶段会计算每个region的存活对象，占比，回收的大小，时间，效率等，然后stop the world 根据我们设定的时间，尽快完成回收。这个阶段会回收老年代，新生代和大对象

因为混合回收的存在，混合回收会选择一部分region回收，所以第四步混合回收其实可以执行多次，意味着可以先把回收效率高的region回收，然后恢复系统运行，运行一段时间，再次停止执行回收

* -XX:G1MixedGCCountTarget  这个参数可以控制混合回收的次数 默认值是8次
* -XX:G1HeapWastePercent  默认值是5%  混合回收使用的是复制算法，意味着随着回收的进行，空闲的region会越来越多，如果空闲的region数量超过5% ，将会立即停止回收。意味着本次回收结束了
* -XX:G1MixedGCLiveThresholdPercent  默认值是85% 意味着你回收一个region的时候，如果这个region中存活的对象大于85%,那么这个region就不回收。因为存活的对象越多，使用复制算法回收的成本越高，毕竟你需要拷贝对象啊。
* G1使用复制算法回收，那么总归是有个情况，当你发现region用完了，没有空间了，那么这时会立即stop the world， 然后切换回Serial 进行非常缓慢的回收。



###### 3.6 G1 的 YGC 和 Mixed GC

我们在了解了上面的内容之后，很明显会问， G1 有 Young GC吗？ 

当然有， 不仅有而且我们和 ParNew + CMS 一样，也是尽量让G1 进行 Young GC

那么 G1 什么时候触发 Young GC呢？

G1 的YGC是根据你XX:MaxGCPauseMills 这个参数决定的，根据回收时间参数， G1会评估触发YGC回收的时机，并不会一定等到 年轻代撑到了 堆空间的 60%才会触发GC







