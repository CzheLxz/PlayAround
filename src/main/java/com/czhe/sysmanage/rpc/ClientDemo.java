package com.czhe.sysmanage.rpc;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/2 11:53
 * @description
 **/
public class ClientDemo {
    public static void main(String[] args) {
        Client client = new RpcClient();
        TestService service = (TestService) client.getService(TestService.class);
        String msg = service.getMsg("200", "成功");
        System.out.println(msg);
    }
}