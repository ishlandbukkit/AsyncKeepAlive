package com.ishland.bukkit.AsyncKeepAlive.thread;

import java.util.TimerTask;

import org.bukkit.Bukkit;

import com.ishland.bukkit.AsyncKeepAlive.packet.KeepAlivePacket;
import com.ishland.bukkit.AsyncKeepAlive.packet.KeepAlivePacketFor1_12toLatest;
import com.ishland.bukkit.AsyncKeepAlive.packet.KeepAlivePacketGarbargeClean;

public class AsyncPacketThreadFor1_12toLatest extends AsyncPacketThread implements Runnable {

    public AsyncPacketThreadFor1_12toLatest() {
	setMainloop(new TimerTask() {
	    @Override
	    public void run() {
		if (!getPlugin().getServer().getOnlinePlayers().isEmpty())
		    try {
			KeepAlivePacket packet = new KeepAlivePacketFor1_12toLatest(getPlugin());
			count += Bukkit.getOnlinePlayers().size();
			packet.expectedCount = Bukkit.getOnlinePlayers().size();
			packet.receivedCount = 0;
			sentPackets.add(packet.getBody());
			packet.boardcast();
			getPing().put(packet.getBody(), packet);
			KeepAlivePacketGarbargeClean gc = new KeepAlivePacketGarbargeClean(getPing(), packet.getBody(),
				getGarbargeCleanList(), index, sentPackets);
			getTimer().schedule(gc, 60 * 1000);
			getGarbargeCleanList().put(index, gc);
			index++;
			if (isDebug())
			    getPlugin().getLogger()
				    .info("[Debug] Boardcasted plugin-sent keepalive " + packet.getBody());
		    } catch (Throwable t) {
			t.printStackTrace();
		    }
	    }
	});
    }

}
