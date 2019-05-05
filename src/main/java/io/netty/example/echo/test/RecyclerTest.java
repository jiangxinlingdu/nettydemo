package io.netty.example.echo.test;


import io.netty.util.Recycler;
import io.netty.util.concurrent.FastThreadLocalThread;

public class RecyclerTest {


    private static Recycler<User> recycler = new Recycler<User>() {

        @Override
        protected User newObject(Handle<User> handle) {
            return new User("零度", handle);
        }
    };


    public static void main(String[] args) {

        try {
            differentThreadRecycler();
        } catch (InterruptedException e) {
        }

        sameThreadRecycler();
    }

    private static void sameThreadRecycler() {
        User user = recycler.get();
        System.out.println(user.getName());

        user.setName("匠心零度");

        //当前回收的线程和Stack所属的线程一致
        user.recycle();


        new FastThreadLocalThread(() -> {

            User user2 = recycler.get();
            System.out.println(user2.getName());

            user2.setName("匠心-零度");

            //当前回收的线程和Stack所属的线程一致
            user2.recycle();

            user2 = recycler.get();
            System.out.println(Thread.currentThread().getName() + ":" + user2.getName());


        }).start();

        user = recycler.get();
        System.out.println(Thread.currentThread().getName() + ":" + user.getName());
    }


    public static void differentThreadRecycler() throws InterruptedException {
        //Stack属于main线程
        User user1 = recycler.get();
        System.out.println(user1.getName());
        user1.setName("hello,java");

        Thread thread = new Thread(() -> {
            System.out.println(user1.getName());
            user1.setName("hello,netty");
            //当前回收的线程和Stack所属的线程不一致 需要添加到WeakOrderQueue中
            user1.recycle();
        });

        thread.start();
        thread.join();

        // 将其他线程回收的对象重新加入到Stack中进行获取
        User user2 = recycler.get();
        System.out.println(user2.getName());
        //等于true 表示是同一对象
        System.out.println(user1 == user2);
    }

}

class User {
    private String name;
    private Recycler.Handle handle;

    public User() {
    }

    public User(String name, Recycler.Handle handle) {
        this.name = name;
        this.handle = handle;
    }

    public User(Recycler.Handle handle) {
        this.handle = handle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void recycle() {
        this.handle.recycle(this);
    }
}