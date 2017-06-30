package com.tlyong1992.net;

import com.tlyong1992.constant.Constant;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * USER：tangly
 * DATE：2017/6/15
 * TIME：13:54
 */
@Component
public class NetManager {

    Logger logger = Logger.getLogger(this.getClass());

    private Socket s = null;

    public void connect() {
        try {
            s = new Socket(Constant.SERVER_ADDRESS, Constant.SERVER_TCP_PORT);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(1111);
            dos.flush();
            dos.close();
            logger.info("Socket 已连接: "+s);
        }catch(Exception e){
            logger.error(e);
        }
    }
}
