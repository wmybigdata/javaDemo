package com.wmy.java.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @project_name: javaDemo
 * @package_name: com.wmy.java.juc.lock
 * @Author: wmy
 * @Date: 2021/8/29
 * @Major: 数据科学与大数据技术
 * @Post：大数据实时开发
 * @Email：wmy_2000@163.com
 * @Desription: 使用lock来进行演示多线程的方法
 * @Version: wmy-version-01
 */
public class LSaleTicket {

    // 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        // 创建Ticket对象
        LTicket ticket = new LTicket();

        // 创建三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"龙昌辉").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"吴明洋").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        },"林虎").start();
    }

    // 第一步 创建资源里，定义属性和操作方法
    public static class LTicket {
        // 票的数量
        private int number = 30;

        // 创建可重入锁
        private ReentrantLock lock = new ReentrantLock(true);

        // 卖票的方法
        public void sale() {
            // 上锁
            lock.lock();
            try {
                if (number > 0) {
                    number--;
                    System.out.println(Thread.currentThread().getName() + " 卖出一张票之后还剩下：" + number);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
