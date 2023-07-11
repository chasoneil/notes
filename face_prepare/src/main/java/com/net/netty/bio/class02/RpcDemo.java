package com.net.netty.bio.class02;

import com.net.netty.bio.class02.pre.User;
import com.net.netty.bio.class02.pre.UserService;
import com.net.netty.bio.class02.pre.UserServiceImpl;
import com.net.netty.bio.class02.v1.client.V1Client;
import com.net.netty.bio.class02.v2.client.V2Client;
import com.net.netty.bio.class02.v3.client.V3Client;

public class RpcDemo {

    public static void main(String[] args) throws Exception {

        // local();

        // v1();

        // v2();

        v3();

    }

    public static void local() {

        UserService userService = new UserServiceImpl();

        System.out.println(userService.findUserByID(13).getName());
        System.out.println(userService.findUserByID2(13).getName());
    }

    public static void v1() throws Exception {

        User user = V1Client.findUserByIDRemote(13);
        System.out.println(user.getName());

    }

    public static void v2() throws Exception {

        UserService userService = V2Client.getStub();
        System.out.println(userService.findUserByID(13).getName());

    }

    public static void v3() throws Exception {

        UserService userService = (UserService) V3Client.getStub(UserService.class);
        System.out.println(userService.findUserByID(13).getName());
        System.out.println(userService.findUserByID2(13).getName());

    }

}
