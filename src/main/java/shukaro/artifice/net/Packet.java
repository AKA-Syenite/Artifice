package shukaro.artifice.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Packet implements IMessage {
	private final ByteBuf write;
	private ByteBuf read;
	private final Gson gson = new Gson();
	
	public Packet() {
		this.write = Unpooled.buffer();
	}
	
	// Custom read functions
	public byte[] readByteArray() throws IOException {
		return readByteArrayData(read.readUnsignedShort());
	}
	
	public byte[] readByteArrayData(int size) throws IOException {
		byte[] data = new byte[size];
		read.readBytes(data, 0, size);
		return data;
	}
	
	public Object readJSON(Type t) throws IOException {
		return gson.fromJson(ByteBufUtils.readUTF8String(read), t);
	}
	
	public Object readJSON(Class t) throws IOException {
		return gson.fromJson(ByteBufUtils.readUTF8String(read), t);
	}
	
	// Forwarding existing read functions
	
	public byte readByte() throws IOException {
		return read.readByte();
	}
	
	public short readShort() throws IOException {
		return read.readShort();
	}
	
	public byte readSignedByte() throws IOException {
		return read.readByte();
	}
	
	public short readSignedShort() throws IOException {
		return read.readShort();
	}
	
	public int readUnsignedByte() throws IOException {
		return read.readUnsignedByte();
	}
	
	public int readUnsignedShort() throws IOException {
		return read.readUnsignedShort();
	}
	
	public int readInt() throws IOException {
		return read.readInt();
	}
	
	public long readLong() throws IOException {
		return read.readLong();
	}
	
	public double readDouble() throws IOException {
		return read.readDouble();
	}
	
	public float readFloat() throws IOException {
		return read.readFloat();
	}
	
	public String readString() throws IOException {
		return ByteBufUtils.readUTF8String(read);
	}
	
	// Custom write instructions
	
	public Packet writeTileLocation(TileEntity te) throws IOException, RuntimeException {
		if(te.getWorldObj() == null) throw new RuntimeException("World does not exist!");
		if(te.isInvalid()) throw new RuntimeException("TileEntity is invalid!");
		write.writeInt(te.getWorldObj().provider.dimensionId);
		write.writeInt(te.xCoord);
		write.writeInt(te.yCoord);
		write.writeInt(te.zCoord);
		return this;
	}
	
	public Packet writeByteArray(byte[] array) throws IOException, RuntimeException {
		if(array.length > 65535) throw new RuntimeException("Invalid array size!");
		write.writeShort(array.length);
		write.writeBytes(array);
		return this;
	}
	
	public Packet writeByteArrayData(byte[] array) throws IOException {
		write.writeBytes(array);
		return this;
	}
	
	// Forwarding all write instructions I care about

	public Packet writeByte(byte v) throws IOException {
		write.writeByte(v);
		return this;
	}
	
	public Packet writeBoolean(boolean v) throws IOException {
		write.writeBoolean(v);
		return this;
	}
	
	public Packet writeString(String s) throws IOException {
		ByteBufUtils.writeUTF8String(this.write, s);
		return this;
	}
	
	public Packet writeShort(short v) throws IOException {
		write.writeShort(v);
		return this;
	}
	
	public Packet writeInt(int v) throws IOException {
		write.writeInt(v);
		return this;
	}
	
	public Packet writeDouble(double v) throws IOException {
		write.writeDouble(v);
		return this;
	}
	
	public Packet writeFloat(float v) throws IOException {
		write.writeFloat(v);
		return this;
	}
	
	public Packet writeLong(long v) throws IOException {
		write.writeLong(v);
		return this;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.read = buf;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBytes(this.write);
	}
}
