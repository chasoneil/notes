## Java





### 多线程



#### 1. Java中线程的状态

* 新建 （new）
* 运行 （runnable）
* 阻塞 （blocked） 线程阻塞于锁
* 等待 （waiting） 
* 超时等待 （timed_waiting） 和waiting不同的是，他可以在一定时间后自动唤醒运行
* 终止 （terminated） run执行完毕



#### 2. Java中怎么停止线程

* stop()  该方法已被弃用，不推荐使用的原因是执行该方法之后，不管线程处于什么状态都会直接被停止
* interrupt()  这种是比较常用的方式，调用该方法之后，线程只是更改了一个状态，当前的任务不会马上结束



#### 3. Sleep 和 Wait 区别

* sleep 是Thread类中的静态方法 wait 是Object类中的方法

* sleep 是属于 Timed_waiting状态  wait 是 waiting状态

* sleep 不会释放锁， wait会释放锁资源

* sleep 可以在持有或者不持有锁的时候执行， wait必须在持有锁时才能执行

  为啥这么说呢， wait 动作其实是 去操作 ObjectMonitor的信息， 将owner清除，同时将线程放到 waitSet中， 而这些都是持有锁的对象头中的信息，所以说wait必须在有锁的时候才能原型



#### 4 . 并发编程的三大特性

* 原子性

  * synchronized

  * CAS

  * Lock  基于CAS 以及 AQS

  * ThreadLocal

    ThreadLocal 其实不能保证原子性，ThreadLocal只是解决多线程共享变量的问题

* 可见性

  * volatile
  * synchronized  加锁的时候会将数据同步到内存
  * lock  本质是通过CAS操作一个volatile的变量实现加锁同步数据

* 有序性

  * volatile  使用内存屏障防止指令重排

  * happens-before

    这个原则是根据Java内存模型定义的一系列规则，有多个规则

  

  

#### 5. @Contented注解

这个注解主要解决的是缓存行重新同步的问题。

在CPU执行的过程中，会把主内存的数据从内存中同步到缓存中，一般CPU的缓存的最小单位是缓存行，一般是64byte， 一般刷数据也是以缓存行为单位的，不然一刷就刷整个缓存代价太大了。

如果一个缓存行中 存储了多个变量  N M K ...  当其中一个变量发生变化的时候，对整个缓存行中的数据影响是这个缓存行中的其他数据都要去主内存中刷一遍，因为你不知道是哪个数据发生变化乐。

这个注解的作用就是，我们注解之后，一个缓存行会只存储一个数据，如果占不满空间会自动用无效位置补满空间，这样就防止这个缓存行中其他数据变化会导致原本没有发生变化的数据也要去内存中重新刷一遍。提高效率



#### 6. Java中的四种引用类型

* 强引用  最常见的引用，只要有强引用，就不会被垃圾回收
* 软引用  SoftReference<T> 当JVM内存不够的时候，就算是有软引用，也会被垃圾回收
* 弱引用  WeakReference<T> 当JVM触发GC,  不管什么情况， 弱引用都会被回收
* 虚引用 不能单独使用，必须和引用队列联合使用





#### 7. ThreadLocal内存泄露问题

> ThreadLocal实现原理

* 每个Thread 中都有一个ThreadLocalMap
* ThreadLocal本身不存储数据，他是去操作ThreadLocalMap
* ThreadLocalMap 基于 Entry[] 实现，用于存储多个数据
* 每个线程都有自己独立的ThreadLocalMap， 基于ThreadLocal对象本身为key，对value进行操作，如果想存储多个数据，就需要多个ThreadLocal对象
* ThreadLocalMap的key是一个弱引用， 弱引用的特点，只要是触发了GC，就一定会被垃圾回收，

![image-20230330154312980](C:\Users\86183\AppData\Roaming\Typora\typora-user-images\image-20230330154312980.png)



​	在上面这张图中， threadlocal对象其实是有两个引用的， Thread中会有一个强引用指向这个对象，就是图中的tl1 和 tl2 ，同时，他又是Map中的key, 是个弱引用， 如果key那条线是个强引用，那么如果线程引用释放了， tl1 、2对象也不会释放，如果是弱引用，就可以保证tl对象会被GC回收，所以这个弱引用解决了key  的内存泄漏

​	从上面的图我们可以看到，线程对象只要存在，就有一条引用连接到ThreadLocalMap, 线程对象没了，自然不存在所谓的内存泄漏，但是很多时候我们使用的是线程池，线程池中核心线程会一直存在，所以线程对象会被复用一直存在，所以，当key，被回收之后，value就成为了无法获取的内容，而造成内存泄漏。

​	解决的办法也非常简单，当我们决定断开ThreadLocal对象的时候，执行remove,移除 key 对应的value 即可



#### 8. Java中锁的分类

* 可重入 和 不可重入
  * 可重入： 当线程获取到锁A (持有锁A) 的时候，还可以再次获取该锁  synchronized / ReentrantLock / ReentrantReadWriteLock
  * 不可重入： 与之相反   ThreadPool 中的 Worker
* 乐观锁 和 悲观锁
  * 乐观锁 CAS
  * 悲观锁 synchronized
* 公平锁 和 非公平锁
  * synchronized 非公平锁  不是先到先得
  * ReentrantLock ReentrantReadWriteLock 都可以实现公平与非公平  使用排队机制，先到先得
* 互斥锁 和 共享锁
  * 互斥锁  在同一个时间节点内只会有一个线程持有该锁  synchronized ReentrantLock
  * 共享锁 与之相反 ReentrantReadWriteLock 可以互斥也能共享





#### 9 . JDK 1.6 sychronized优化

* 锁消除 ：就是当我们使用这个关键字的时候，如果没有访问临界资源，会触发锁消除，写了也不会出发
* 锁膨胀 ：频繁加解锁是非常消费性能的，出现这个情况的时候，锁范围会升级，减少加解锁的次数

* 锁升级 ：从最开始的无锁到最后的重量级锁的升级过程 





#### 10. AQS



##### 10.1  什么是AQS

AbstractQueuedSynchronizer 抽象类

JUC下 很多内容都是基于AQS实现了部分功能   

* ReentrantLock
* ThreadPoolExecutor
* 阻塞队列
* CountDownLatch 
* Semaphore
* CyclicBarrier

AQS 中提供了一个 用 volatile修饰的 并且采用 CAS修改的 state

* ReentrantLock 基于 state 标记加锁状态， 未加锁是0 , 加了几次就是几次重入
* ThreadPoolExecutor基于state维护核心线程数
* CountDownLatch 基于 state 做原子加减

AQS 中维护了一个双向链表，有head 也有 tail 

* 链表的节点是Node， 并且中间存储了很多信息， 比如线程信息，状态信息等等
* 通过 addWaiter方法进行排队，当获取锁失败就要去排队

AQS中提供一个ConditionObject  , 这个object中也有个链表

* 当执行了await 方法的时候， 会把原来在上面Node排队的线程移动到conditionObject中的链表中
* 唤醒之后，才会从这个链表中移动到AQS的双向链表中

说白了就是有俩链表，一个是可以随时竞争锁的，一个是没资格竞争锁，需要等待唤醒的



##### 10.2 AQS唤醒，为什么从后往前找

* addWaiter 是往链表中放节点的， 当我竞争失败，我就放到这个队列中参与下次竞争

  但是加入这个链表的逻辑是 将该节点的 prev指针指向 原链表的 尾结点， 然后把tail指向这个新节点，最后才把原来的tail的 next指向下一个节点（也就是我们新的tail）

  这么操作会导致一个问题，在高并发的过程中，某个时间原来尾节点的next其实还是Null，（还没来得及调整指针） 如果从前往后找，可能会损失一个节点

* 具有同样逻辑的还有 cancelAquire 方法， 取消节点也是一样的操作



#### 11. synchronized 和 ReentrantLock的区别

* s 是对象锁，使用非常的方便，也不用释放锁
* 使用相对比较麻烦，需要手动解锁
* 性能上来说， 也是r 比较高
* 从功能上来说 r 除了提供 lock 和 unlock 之外，还提供 trylock 和 带时间的 trylock
* r 提供公平锁和非公平锁的创建 ，s只能是非公平



#### 12. reentrantReadWriteLock 的原理

> 为什么要出现读写锁

synchronized 和 ReentrantLock 都是互斥锁

如果有个操作是读多写少，还要保证线程安全，采用上面的锁效率会非常低

当我们使用ReentrantReadWriteLock就可以解决这个问题，读读之间是并发的不互斥，读写之间互斥，可以极大的增加性能



> 读写锁实现原理



读写锁还是基于 AQS 实现的， 互斥是通过 state实现的，当state 为0 表示没有锁，否则就是重入了几次。

state是一个 Int 类型的值  32位 ，在读写锁中把它拆成两部分  高16位 和低16位

高16位 是读锁

低16位 是写锁

因为读写锁也是重入的，那么就存在一个问题，如果是写锁，互斥，那么重入几次 state就 几次 + 1

但是读锁是不互斥的，所以当我们发现高16位 有4个锁，但是只有3个线程的时候，我们没法判断到底是哪个线程重入了一次

读写锁基于ThreadLocal 解决上面的问题 如果出现读锁重入，就在当前线程的ThreadLocal中记录



#### 13. 线程池

> JDK 提供的线程池

* fixedThreadPool 
* singleThreadExecutor
* cachedThreadPool
* scheduleThreadPool
* workStealingPool



> 线程池的核心参数

一共七个核心参数

*  corePoolsize  核心工作线程 （任务结束之后，线程不会被销毁）

* maxPoolSize   最大工作线程

* long keepAliveTime 非核心工作线程在阻塞队列等待的时间  超过时间非核心线程将会被回收

* TimeUnit unit 非核心工作线程在阻塞队列位置等待的时间单位

* BlockingQueue  阻塞队列，当没有核心线程处理任务， 任务就会被扔到阻塞队列中

* ThreadFactory  可以构建线程的工厂

* RejectExecutionHandler  当线程池无法处理任务时的拒绝策略

  *  AbortPolicy  直接抛出异常
  *  CallerRunsPolicy  将任务返回给调用者处理
  *  DiscardPolicy 直接丢弃任务
  *  DiscardOldestPolicy 将工作队列中最早的任务丢掉

  

> 线程池的状态

线程池中有个核心参数  AtomicInteger ctl  （32位）

这个参数维护了线程池的工作线程数 和 状态

高3位 是线程池状态   低29位是线程池线程数

* running 			正常状态的工作状态

* shutdown         执行了shutdown() 之后进入这个状态

  进入这个状态，线程池不再接受任务，但是正在执行的和阻塞队列中的任务会继续执行

* stop                   执行了shutdownNow() 之后变成这个状态

  线程池不接受新任务，正在处理的任务会被中断，阻塞队列的任务一个不留

  当然这个任务的中断是通过interrupt方法，不保证一定可以中断成功

* tidying               是一个过渡状态

* terminated       线程池结束了 关闭了 一般执行了terminated()之后变成这个状态



> 线程池的执行流程

执行的主要方法是execute()



> 线程池添加工作线程的流程

addWorker()  就是核心方法

1.  判断线程池状态  只有running状态才能添加任务
2.  判断工作线程个数   根据工作线程个数和核心线程数比较，决定添加核心线程还是非核心线程
3.  通过判断，将ctl 低29位通过CAS + 1
4.  创建一个worker对象 ，worker对象内部有 Thread ,
5.  将worker 对象放到 内部一个Hashset 中
6.  添加成功之后会调用 内部 Thread中的start方法启动



> 线程池为何要构建空任务的非核心线程

构建空任务非核心线程的时机：

* execute() 中 当线程池状态是running， 并且任务被放到任务队列中成功，且工作线程是0的时候
* 工作线程启动之后执行 runWorker() , 这个方法最后执行 processWorkExit() 方法
  * 这个方法内部也执行了



为什么这么做：防止出现阻塞队列中有任务但是没有工作线程处理任务

出现上面这个情况的时机：

*  创建线程池的时候， 核心线程数可以传入0， 传入0
*  线程池中有 allowCoreThreadTimeout参数，默认false, 当设置为true，意味着核心线程也可能会回收





> 线程池使用完毕为什么必须 shutdown

*  只要线程池不结束，核心线程会一直保留，当我们不shutdown ，那么这些核心线程就是内存泄漏了

*  创建工作线程的时候，是基于Worker对象内部的Thread实现的，runWorker中，传入了this （Worker）对象本身

   这是个强引用，只要有线程， 线程池本身也不会被回收，占用资源





> 线程池核心参数怎么设置

线程池的核心参数配置主要分两种，线程数和阻塞队列

hippo4j 可以对线程池进行监控，而且和 springboot整合



#### 14. ConcurrentHashMap()



> JDK 1.8 中做了啥优化

* 结构变化  

  引入了红黑树  

* 存储方式

  1.7 中使用的segment分段锁的方式实现线程安全，但是颗粒度不够细

  1.8 中是基于 CAS + synchronized 锁 头结点的方式实现线程安全

* 扩容

  treeifyBin 方法触发扩容，同时帮助扩容

* 计数器优化





### MyBatis

#### 1. 工作原理



> MyBatis 使用

* 需要添加依赖
* 需要全局配置文件
* 需要映射文件 



> 初始化流程

mybatis-demo

* 系统启动，加载解析全局配置文件和对应的映射文件， 构建Configuration
* 通过配置，构建 SqlSessionFactory
* 通过factory ，构建sqlSession
  * 通过 sqlSession中提供的API进行操作数据库
  * 通过 sqlSession 创建一个代理对象调用我们自己的接口去操作数据库
* session 具体是通过executor执行器进行SQL的处理，底层调用的JDBC
  * ParameterHandler进行参数的相关处理
  * StatementHandler进行SQL语句的处理
  * ResultHandler处理返回的结果
* 使用完毕之后，关闭session





#### 2. Mybatis 缓存

> MyBatis 中的缓存架构设计

* 源码是 org.apache.ibatis.cache 中
* 提供了一个接口 Cache ，接口中提供了实现 getObject putObject
* 当然也提供了一个默认实现 PerpetualCache 是基于内存的实现 HashMap
* mybatis通过装饰器模式提供了各种各样的cache

> MyBatis的一级缓存和二级缓存

* 一级缓存默认开启  session 级别
  * 默认开启，可以手动关闭
* 二级缓存是 sqlsessionFactory 级别 （进程级别）
  * 二级缓存需要手动开启
    * 需要配置文件中开启开关
    * mapper中需要使用cache标签
* 都开启的情况下，mybatis先走二级缓存再走一级缓存
  * 一级和二级缓存的作用域不同，二级缓存命中的概率远大于一级缓存
  * 如果是先一级缓存再去二级缓存会一定程度上降低性能



#### 3. 扩展Mybatis缓存

* MyBatis 默认提供 了一个Cache接口， 接口中提供了 对缓存数据的操作方法

* 我们可以通过扩展实现这个接口  实现getObject  实现putObject 

* 我们正常的过程中，如果不想使用系统默认的缓存，那么我们可以引入对应的包

  比如myabtis-redis，通过他们提供的具体实现接口去做对应的操作

* 我们自己实现了getObject 和 putObjectg 之后 需要在配置文件中的cache标签中的type配置自己的配置类完整路径





#### 4. MyBatis 设计模式

> 基础模块

* 缓存模块
  * 装饰器模式
* 日志模块
  * 适配器模式 【策略模式】
  * 代理模式  进行日志功能的增强
* 反射模块
  * 单例模式
  * 工厂模式
* SqlSessionFactory 
  * 建造者模式



#### 5. SqlSessionFactory

* MyBatis 核心 API,
* 本身是单例，使用工厂模式和建造者模式构建 SqlSession



#### 6. SqlSession

sqlSession 是 MyBatis中的接口，内部提供了关于CRUD的各种方法

* 本身是一个会话级别的参数
* 可以通过 内部提供的接口 通过JDBC处理数据
* 也可以通过 getMapper 获取代理对象来处理





#### 7. Mybatis 分页原理

Mybatis 中的分页有两种实现

* 逻辑分页： RowBounds
  * 本质上并不是分页查询，而是将所有的数据全部查出来，再对数据进行分页
  * 消耗的内存很大，可能会引发OOM，对性能的影响也很大，一般不推荐使用
* 物理分页： 拦截器实现
  * 通过配置文件中使用拦截器，最常用的就是pageHelper
  * pageHelper会对 Executor 中的 query 操作进行拦截，各种参数类型都会被拦截
  * 底层还是通过mysql limit 的一个sql改写去完成的分页



####  8. SqlSession 的安全问题



SqlSession 的默认实现类是 DefaultSqlSession ， 而这个类是线程非安全的， Spring 中解决了这个问题

最好的方式是每个线程都要有一个sqlsession实例，因为他不是线程安全的，所以不能被共享，也是这个原因，不能将SqlSession放到一个

类的静态域中。虽然他是线程不安全的，但是我们把它作为一个方法级别的，或者一个请求级别的参数，每次使用创建，使用完成销毁，是

完全可以的

> Spring 的解决方案

mybatis-spring中提供了一个SqlSessionTemplate

这个类是线程安全的，实现了SqlSession, 对象内部对于SqlSession的各种操作都是通过代理完成的，这个动态代理的对象中使用的session是方法级别的对象，因为源码中是在一个方法中产生的session



#### 9. 延迟加载

所谓的延迟加载，其实本质就是等一会儿加载，延迟加载一般出现在多表关联操作的时候，单表是没有延迟加载的概念的

比如： 查询用户和部门的信息，但是此时我们只需要使用用户信息，暂时不用部门信息，这个时候，就可以使用延时加载机制来处理

> 怎么做

* 开启延迟加载   
  * lazyLoadingEnable  
  *  aggresiveLazyLoading
* 配置多表关联查询
  * association 一对一
  * collection  一对多



> 延迟加载原理

延迟加载通过使用动态代理，通过拦截器的时候，如果是延迟加载，那么就单独发送要查询的部分SQL, 只加载其中我们需要的部分，当我们需要关联中的另外部分的时候，再通过我们第一步查询的对象，使用get set 方法加载其他的数据







#### 10. Mybatis Mapper

