package com.wmy.java.juc.sync;

/**
 * @project_name: javaDemo
 * @package_name: com.wmy.java.juc.sync
 * @Author: wmy
 * @Date: 2021/8/29
 * @Major: 数据科学与大数据技术
 * @Post：大数据实时开发
 * @Email：wmy_2000@163.com
 * @Desription: 买票案例
 * @Version: wmy-version-01
 */
public class SaleTicket {
    // 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        // 创建Ticket对象
        Ticket ticket = new Ticket();

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

    // 第一步 创建资源类，定义属性和操作方法
    public static class Ticket {
        // 票数
        private int number = 30;

        // 操作方法：卖票
        public synchronized void sale() {
            // 判断是否有票
            if (number > 0) {
                number--;
                System.out.println(Thread.currentThread().getName() + " 卖出一张票之后还剩下：" + number);
            }
        }
    }
}
