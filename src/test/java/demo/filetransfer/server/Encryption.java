package demo.filetransfer.server;

import net.role4j.IRole;

/**
 * Created by nguonly on 10/14/16.
 */
public class Encryption implements IChannel, IRole{
    public String prepareChannelForReceiving(String data){
        int idx = data.indexOf("@:");
        return data.substring(idx+2);
    }

    public String prepareChannelForSending(String data){
//        String fMsg = getPlayer(IChannel.class).prepareChannelForSending(data);
        return "<E>" + data + "<E>";
    }
}
