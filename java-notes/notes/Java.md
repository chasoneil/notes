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
* long keepAliveTime 非核心工作线程在阻塞队列等待的时间
* TimeUnit unit 非核心工作线程在阻塞队列位置等待的时间单位
* BlockingQueue  阻塞队列，当没有核心线程处理任务， 任务就会被扔到阻塞队列中
* ThreadFactory  可以构建线程的工厂
* RejectExecutionHandler  当线程池无法处理任务时的拒绝策略
