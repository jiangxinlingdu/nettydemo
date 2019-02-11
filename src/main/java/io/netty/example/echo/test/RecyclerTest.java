package io.netty.example.echo.test;


import io.netty.util.Recycler;

public class RecyclerTest {


    private static Recycler<User> recycler = new Recycler<User>() {

        @Override
        protected User newObject(Handle<User> handle) {
            return new User("零度", handle);
        }
    };


    public static void main(String[] args) {
        User user = recycler.get();
        System.out.println(user.getName());

        user.setName("匠心零度");

        user.recycle();


        new Thread(() -> {

            User user2 = recycler.get();
            System.out.println(user2.getName());

            user2.setName("匠心-零度");

            user2.recycle();

            user2 = recycler.get();
            System.out.println(Thread.currentThread().getName() + ":" + user2.getName());


        }).start();

        user = recycler.get();
        System.out.println(Thread.currentThread().getName() + ":" + user.getName());
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