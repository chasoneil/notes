## Java



### 多线程



#### 1. Java中线程的状态

* 新建 （new）
* 运行 （runnable）
* 阻塞 （blocked） 线程阻塞于锁
* 等待 （waiting）  需要手动唤醒
* 超时等待 （timed_waiting） 和waiting不同的是，他可以在一定时间后自动唤醒运行
* 终止 （terminated） run执行完毕



#### 2. Java中怎么停止线程

* stop()  该方法已被弃用，不推荐使用的原因是执行该方法之后，不管线程处于什么状态都会直接被停止
* 使用共享变量的方式  这是一种自己编程的方式实现线程结束的方式 比较灵活
* interrupt()  这种是比较常用的方式，调用该方法之后，线程只是更改了一个状态，当前的任务不会马上结束



#### 3. Sleep 和 Wait 区别

* sleep 是Thread类中的静态方法 wait 是Object类中的方法

* sleep 是属于 Timed_waiting状态  wait 是 waiting状态

* sleep 不会释放锁， wait会释放锁资源

* sleep 可以在持有或者不持有锁的时候执行， wait必须在持有锁时才能执行

  为啥这么说呢， wait 动作其实是 去操作 ObjectMonitor的信息， 将owner清除，同时将线程放到 waitSet中，如果没有持有synchronized锁的话，是无法操作ObjectMonitor对象的

> synchronized 底层

```c++
ObjectMonitor() {
    
    _header   = null;
    _owner    = null; // 持有锁的线程
    
    _WaitSet  = null;  // 保存wait线程的信息  （执行了wait方法）
    _EntryList = null; // 被唤醒的线程放到这个list中参与下次竞争
    
}
```



#### 4 . 并发编程的三大特性

* 原子性  

  * synchronized

  * CAS    CAS虽然不会造成内核态和用户态的切换，但是频繁的自旋会消耗CPU

  * Lock  基于CAS 以及 AQS

  * ThreadLocal

    ThreadLocal 其实不能保证原子性，ThreadLocal只是解决多线程共享变量的问题

* 可见性

  发生可见性问题的原因是因为  CPU 有缓存，当CPU缓存中有某个数据，那么就不会去内存中重新读。

  多核CPU中，如果C1读取到缓存中的数据被 C2修改了， C1是感知不到的，就是看不见。

  * volatile    JMM内存屏障实现的，每次写入和读取数据的时候，都要从主内存中重新同步一份
  * synchronized  加锁的时候会将数据同步到内存
  * lock  本质是通过CAS操作一个volatile的变量实现加锁同步数据

* 有序性

  JIT 在编译的过程中，可能会进行指令重排，另外就是CPU在执行层面的时候，也可能进行指令重排

  * volatile  使用内存屏障防止指令重排

  * happens-before

    这个原则是根据Java内存模型定义的一系列规则，有多个规则

  

> CAS的问题

* ABA 问题
* 自旋时间过长问题
  * LongAdder 的设计思路



#### 5. @Contented注解

LongAdder add() 中用到的 Cell 类就被注解了

这个注解主要解决的是缓存行重新同步的问题。

在CPU执行的过程中，会把主内存的数据从内存中同步到缓存中，一般CPU L1 的缓存的最小单位是缓存行，一般是64byte， 一般刷数据也是以缓存行为单位的，不然一刷就刷整个缓存代价太大了。

如果一个缓存行中 存储了多个变量  N M K ...  当其中一个变量发生变化的时候，对整个缓存行中的数据影响是这个缓存行中的其他数据都要去主内存中刷一遍，因为你不知道是哪个数据发生变化了。

这个注解的作用就是，我们注解之后，一个缓存行会只存储一个数据，如果占不满空间会自动用无效位置补满空间，这样就防止这个缓存行中其他数据变化会导致原本没有发生变化的数据也要去内存中重新刷一遍。提高效率



#### 6. Java中的四种引用类型

* 强引用  最常见的引用，只要有强引用，就不会被垃圾回收
* 软引用  SoftReference<T> 当JVM内存不够的时候，就算是有软引用，也会被垃圾回收 内存够的时候则不会回收
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

  > 非公平锁

  线程A 持有锁 ，线程B 来了发现锁被持有，开始等待， 线程C来了，他不管前面有没有人排队，尝试竞争一波

  竞争成功，就插队成功了，线程C在线程B之前执行了

  竞争失败，安心排队，不过还是排在B后面

  * synchronized 非公平锁  不是先到先得
  * ReentrantLock ReentrantReadWriteLock 都可以实现公平与非公平  使用排队机制，先到先得

* 互斥锁 和 共享锁

  * 互斥锁  在同一个时间节点内只会有一个线程持有该锁  synchronized ReentrantLock
  * 共享锁 与之相反 ReentrantReadWriteLock 可以互斥也能共享



#### 9 . JDK 1.6 sychronized优化

* 锁消除 ：就是当我们使用这个关键字的时候，如果没有访问临界资源，会触发锁消除，写了也不会触发
  * JIT 帮我们优化，如果没有获取的话，直接编译就没有锁
* 锁膨胀 ：频繁加解锁是非常消费性能的，出现这个情况的时候，锁范围会升级，减少加解锁的次数
  * 也是JIT的优化，比如循环中的锁会被优化到循环之外

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

AQS 中维护了一个双向链表 Node对象，有head 也有 tail 

* 链表的节点是Node， 并且中间存储了很多信息， 比如线程信息，状态信息等等
* 通过 addWaiter方法进行排队，当获取锁失败就要去排队
* Node 节点可以是共享的，也可以是互斥的

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
* r 使用相对比较麻烦，需要手动解锁
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

* fixedThreadPool            推荐使用
* singleThreadExecutor
* cachedThreadPool
* scheduleThreadPool
* workStealingPool         底层是ForkJoinPool



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

* 添加任务到线程池  判断任务是不是为空， 不为空的时候
* 查看核心工作线程是不是满了，没有满创建核心线程， 但是创建核心线程不一定能成功
  * 创建成功则使用核心工作线程执行任务
  * 创建失败则判断线程池状态是不是正常
    * 正常 则将任务添加到阻塞队列中
    * 否则 将任务从队列移除 执行拒绝策略
* 如果核心工作线程满了，那么去判断阻塞队列满没满
  * 阻塞队列没有满，把任务放到阻塞队列中
  * 阻塞队列满了，判断最大线程数到没到
    * 没到就创建非核心线程执行任务
    * 到了执行拒绝策略

* 当任务添加到阻塞队列中，查看有没有工作线程在干活，如果没有工作线程就创建非核心线程，如果有，扔进去就行了



> 线程池添加工作线程的流程

addWorker()  就是核心方法

1.  判断线程池状态  只有running状态才能添加任务
2.  判断工作线程个数   根据工作线程个数和核心线程数比较，决定添加核心线程还是非核心线程
3.  通过判断，将ctl 低29位通过CAS + 1
4.  创建一个worker对象 ，worker对象内部有 Thread ,
5.  将worker 对象放到 内部一个Hashset （workers）中
6.  添加成功之后会调用 内部 Thread中的start方法启动



> 线程池为何要构建空任务的非核心线程

构建空任务非核心线程的时机：

* execute() 中 当线程池状态是running， 并且任务被放到任务队列中成功，且工作线程是0的时候
* 工作线程启动之后执行 runWorker() , 这个方法最后执行 processWorkExit() 方法
  * 这个方法内部也执行了



为什么这么做：防止出现阻塞队列中有任务但是没有工作线程处理任务， 当出现这个状况之后，只有下次新任务添加，

才会创建工作线程解决任务，这期间一直是阻塞队列中有任务，但是没线程处理的状态

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

  引入了红黑树 ， 红黑树的查询时间复杂度是 O(logN)

* 存储方式

  1.7 中使用的segment分段锁的方式实现线程安全，但是颗粒度不够细

  1.8 中是基于 CAS + synchronized 锁 头结点的方式实现线程安全

  * 当数据放在数组上的时候，我们使用CAS, 不加锁
  * 当数据放在链表中的时候，我们使用synchronized锁 只锁了数组中的一个头节点

* 扩容

  1.7中的时候，当触发扩容的时候，是直接等待，到扩容结束，才能使用

  1.8 treeifyBin 方法触发扩容，同时帮助扩容

* 计数器优化

  1.8 中计数器选择的不是 AtomicLong  而是类似 LongAdder的一个功能。

  ConcurrentHashMap 在计数时，需要保证线程安全，还要保证效率，原来采用CAS的方式保证了线程安全

  但是当出现高并发的时候， 大量CAS会消耗很多CPU性能

  LongAdder就是为了解决这个问题 实现一个Cell数组，将原本对一个值的自增变成对这个cell数组的增加

  最终汇总成为一个总的自增值，提高效率



> ConcurrentHashMap的散列算法

chm在原来HashMap中的散列算法基础上做了改进

入口是 putVal 

* 根据key计算hash  

  当数组长度不长的时候，我们通过数组长度和 hash值做与运算来确定放在数组的哪个位置，

  但是数组长度不长，比如16 转化成2进制就是10000，这个时候其实只有低位参与了这个与运算

  高位因为根本长度不够用不到，所以都是0 根本不参与与运算，这个时候就容易发生hash冲突

* 新的hash算法，先把原来的hash值向右移动16位，再和原来的hash 做个异或， 让原来的低位与

  高位都参与到了hash算法中，数据被打的更散了

* 数组长度 -1 与hash 进行与运算 如果数组长度是 2的N次方，当我 -1 之后，将会出现大量的1 ，这个1 

  其实就是有效的二进制位，参与到hash运算中来。  比如你数组长度17 100001 -1 = 10000  仅仅一个1

  当时你数组长度16 10000 -1 = 01111 4个1 



> ConcurrentHashMap 初始化数组的流程

chm的数组是懒加载的。

入口也是 putVal  initTable() 进行初始化，其中有个非常重要的变量  sizeCtl:

* -1 表示正在初始化
* < -1 低16位代表当前数组正在扩容的线程个数
* 0 表示数据还没初始化
* 大于0 代表当前数组扩容的阈值
* 内部使用双重检查 DCL 来确定当前的情况 去构建数组完成初始化



> ConcurrentHashMap 扩容流程

treeifyBin方法触发扩容  tryPresize()

* 确认什么时候触发扩容
* 计算扩容标识戳
* 计算每个线程需要扩容的步长是多少，最小值是16
* 初始化一个全新的数组，线程开始领取任务，确认当前线程从多少位置到多少位置来扩容数据
* 最后一个扩容的线程还要从头到尾检查一次有没有遗漏的数据

```java
private final void tryPresize(int size) {
    int c = (size >= (MAXIMUM_CAPACITY >>> 1)) ? MAXIMUM_CAPACITY :
        tableSizeFor(size + (size >>> 1) + 1);
    int sc;
    
    // 准备扩容
    while ((sc = sizeCtl) >= 0) {
        
        else if (c <= sc || n >= MAXIMUM_CAPACITY)
            break;
        else if (tab == table) {
            
            // 计算扩容标识 主要是用于协助扩容用
            // 基于老数组的长度计算这个扩容标识戳
            int rs = resizeStamp(n);
            if (sc < 0) {
                
            }
            
            // 我是第一个扩容的线程 左移16位
            // + 2表示当前正在有一个线程正在扩容
            else if (U.compareAndSwapInt(this, SIZECTL, sc,
                                         (rs << RESIZE_STAMP_SHIFT) + 2))
                
                // 迁移数据，是一块一块来
                transfer(tab, null);
        }
    }
}
```



> ConcurrentHashMap 读取数据的流程

虽然是线程安全，但是读取的过程中不会阻塞 get() 是入口

* 传入一个key ，基于key 计算hash值 计算的方法和put的时候一致
* 查询数组的Entry key数据，如果数组的数据和我们key相等 ，那么直接返回val
* 查询数组下面挂的链表或者红黑树
* 剩下的情况都是走 find 方法，find有很多种实现
  * 可能会去新数组上  迁移中
  * 去红黑数中查找
    * 如果有写操作，那么红黑树会存在翻转的可能，那么这个时候我就会去查询实现红黑树的双向链表

总而言之，查询不会是个阻塞操作



> ConcurrentHashMap中的计数器

主要的方法是addCount()

LongAdder思想 其实本质是一个优化 





### 分布式



#### 1. 分布式幂等性如何设计



高并发的场景下幂等性是常见的， 比如说支付的场景，用户点了两次，不能支付两次。商品库存的更改，如果多点了一次库存就减少了

两个那肯定也是不行的。

对数据的操作主要是 增删改查  

* 查询和删除不存在幂等性
