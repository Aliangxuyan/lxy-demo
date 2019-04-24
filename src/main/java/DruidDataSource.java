//import com.alibaba.druid.filter.FilterChainImpl;
//import sun.rmi.transport.Connection;
//
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class DruidDataSource {
//
//    // 默认使用公平锁
//    private ReentrantLock lock = new ReentrantLock(false);
//
//    private Condition notEmpty = lock.newCondition();
//
//    private Condition empty = lock.newCondition();
//
//    public Connection getConnection(long maxWaitMillis) {
//        init();
//
//        if (filters.size() > 0) {
//            FilterChainImpl filterChain = new FilterChainImpl(this);
//            return filterChain.dataSource_connect(this, maxWaitMillis);
//        } else {
//            return getConnectionDirect(maxWaitMillis);
//        }
//    }
//
//    public void init(){
//        new com.alibaba.druid.pool.DruidDataSource.CreateConnectionThread().start();
//        new com.alibaba.druid.pool.DruidDataSource.DestroyConnectionThread()start();
//    }
//
//    // 调用Connection.close()，会调用DruidDataSource的recycle方法
//    public void recycle( Connection conn ) {
//        // 对连接进行检测
//        lock.lock;
//        putLast();
//        lock.unlock();
//    }
//
//    public com.alibaba.druid.pool.DruidDataSource.CreateConnectionThread extends Thread {
//        public void run() {
//            for (;;) {
//                lock.lockInterruptibly();
//                if ( "池中有可用连接" ) {
//                    empty.await();      // 进入waiting，等等获取连接的线程唤醒
//                } else {
//                    //TODO 创建连接
//                    notEmpty.signal();  // 唤醒等等连接的线程
//                }
//                lock.unlock();
//            }
//        }
//    }
//
//    public com.alibaba.druid.pool.DruidDataSource.DestroyConnectionThread extends Thread {
//        public void run() {
//            for (;;) {
//                lock.lockInterruptibly();
//                // 清理连接池内的过期连接
//                // (可选)清理从连接池拿走，正在使用的连接
//                destroyTask.run();
//                lock.unlock();
//                Thread.sleep( times );
//            }
//        }
//    }
//
//    public class DestroyTask implements Runnable {
//
//        @Override
//        public void run() {
//            shrink(true, keepAlive);
//
//            if (isRemoveAbandoned()) {
//                removeAbandoned();
//            }
//        }
//
//    }
//
//}