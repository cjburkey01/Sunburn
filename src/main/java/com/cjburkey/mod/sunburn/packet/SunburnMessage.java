package com.cjburkey.mod.sunburn.packet;

import com.cjburkey.mod.sunburn.client.OverlayEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class SunburnMessage implements IMessage {
	
	private String time;
	
	public SunburnMessage() {  }
	
	public SunburnMessage(String time) {
		this.time = time;
	}
	
	public void fromBytes(ByteBuf buf) {
		time = ByteBufUtils.readUTF8String(buf);
	}
	
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, time);
	}
	
	public static class Handler implements IMessageHandler<SunburnMessage, IMessage> {
		public IMessage onMessage(SunburnMessage msg, MessageContext cont) {
			OverlayEvent.time = Integer.parseInt(msg.time);
			return null;
		}
	}
	
}