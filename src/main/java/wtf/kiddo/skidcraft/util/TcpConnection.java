package wtf.kiddo.skidcraft.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.crypto.SecretKey;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.io.CipherInputStream;

import net.minecraft.src.CryptManager;
import net.minecraft.src.ILogAgent;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet252SharedKey;


//Author: mckuhei
public class TcpConnection implements INetworkManager {

	private NetHandler netHandler;
	private SocketAddress address;
	private SocketChannel channel;
	private String disconnectReason;
	private Object[] disconnectReasonArrar;
	private List<Packet> packets = Collections.synchronizedList(new ArrayList<Packet>());
	private final ByteBuffer buffer = ByteBuffer.allocate(2 << 20); // 2 MiB buffer
	private final ILogAgent logger;
	
	private BufferedBlockCipher inputCipher, outputCipher;
	private SecretKey sharedKey;
	
	public TcpConnection(InetAddress address, int port, String name, NetHandler netHandler, ILogAgent logger) throws IOException {
		this.netHandler = netHandler;
		channel = SocketChannel.open();
		channel.connect(this.address = new InetSocketAddress(address, port));
		channel.configureBlocking(false);
		this.logger = logger;
	}

	@Override
	public void setNetHandler(NetHandler var1) {
		netHandler = var1;
	}

	@Override
	public void addToSendQueue(Packet var1) {
		packets.add(var1);
	}

	@Override
	public void wakeThreads() {}
	
	private static void writeAll(ByteBuffer buffer, SocketChannel channel) throws IOException {
		int writes;
		do {
			writes = channel.write(buffer);
		} while(buffer.hasRemaining() && writes > 0);
	}
	
	private void writeData(byte[] buf) throws IOException {
		buffer.clear();
		if(buf.length <= buffer.capacity()) {
			writeAll((ByteBuffer) buffer.put(buf).flip(), channel);
		} else {
			byte[] arr = new byte[buffer.capacity()];
			int offset = 0;
			do {
				int reaming = Math.min(buf.length - offset, arr.length);
				byte[] dst = reaming == arr.length ? arr : new byte[reaming];
				System.arraycopy(buf, offset, dst, 0, reaming);
				buffer.clear();
				buffer.put(dst).flip();
				writeAll(buffer, channel);
			} while(offset < buf.length);
		}
	}
	
	private void flush() throws IOException {
		buffer.clear();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(bout);
		while(!packets.isEmpty() && bout.size() < buffer.capacity()) {
			Packet packet = packets.remove(0);
			Packet.writePacket(packet, out);
			if(packet instanceof Packet252SharedKey && outputCipher == null) {
				//System.out.println("输出端开始加密！");
				writeData(encryptData(bout.toByteArray()));
				bout = new ByteArrayOutputStream();
				out = new DataOutputStream(bout);
				outputCipher = CryptManager.createBufferedBlockCipher(true, sharedKey = ((Packet252SharedKey) packet).getSharedKey());
			}
		}
		writeData(encryptData(bout.toByteArray()));
	}
	
	private byte[] encryptData(byte[] data) {
		if(outputCipher == null)
			return data;
		int newSize = outputCipher.getOutputSize(data.length);
		byte[] buf = new byte[newSize];
		outputCipher.processByte(data, 0, data.length, buf, 0);
		return buf;
	}

	@Override
	public void processReadPackets() {
		List<Packet> incoming = new ArrayList<Packet>();
		try {
			buffer.clear();
			int reads;
			do {
				reads = channel.read(buffer);
			} while(reads>0&&buffer.hasRemaining());
			if (reads == -1 && incoming.isEmpty())
				throw new EOFException("End of Stream");
			buffer.flip();
			if(buffer.hasRemaining()) {
				byte[] arr = new byte[buffer.limit()];
				System.arraycopy(buffer.array(), 0, arr, 0, arr.length);
				if(inputCipher != null) {
					byte[] buf = new byte[inputCipher.getOutputSize(arr.length)];
					inputCipher.processByte(arr, 0, arr.length, buf, 0);
					arr = buf;
				}
				DataInputStream input = new DataInputStream(new ByteArrayInputStream(arr)) ;
				while(input.available() > 0) {
					Packet packet = Packet.readPacket(logger, input, netHandler.isServerHandler(), null);
					incoming.add(packet);
					if(packet instanceof Packet252SharedKey && inputCipher == null) {
						//System.out.println("输入端开始解密！");
						inputCipher = CryptManager.createBufferedBlockCipher(false, sharedKey);
						input = new DataInputStream(new CipherInputStream(input, inputCipher));
					}
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
			this.networkShutdown("disconnect.genericReason", new Object[] {"Internal exception: " + e.toString()});
		}
		Iterator<Packet> iterator = incoming.iterator();
		while(iterator.hasNext()) {
			Packet packet = iterator.next();
			if(packet == null) {
				iterator.remove();
				continue;
			}
			packet.processPacket(netHandler);
			iterator.remove();
		}
		try {
			flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.networkShutdown("disconnect.genericReason", new Object[] {"Internal exception: " + e.toString()});
		}
		if(disconnectReason != null) {
			netHandler.handleErrorMessage(disconnectReason, disconnectReasonArrar);
		}
	}

	@Override
	public SocketAddress getSocketAddress() {
		return address;
	}

	@Override
	public void serverShutdown() {
		
	}

	@Override
	public int packetSize() {
		return 0;
	}

	@Override
	public void networkShutdown(String var1, Object... var2) {
		this.disconnectReason = var1;
		this.disconnectReasonArrar = var2;
	}

	@Override
	public void closeConnections() {
		serverShutdown();
	}
	
}
