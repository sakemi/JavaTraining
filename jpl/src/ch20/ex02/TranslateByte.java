package ch20.ex02;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TranslateByte extends FilterInputStream {
	private byte from;
	private byte to;

	protected TranslateByte(InputStream in, byte from, byte to) {
		super(in);
		this.from = from;
		this.to = to;
	}

	public static void main(String[] args) {
		FilterInputStream f = new TranslateByte(System.in, (byte) 'b', (byte) 'B');
		int c;
		try {
			while ((c = f.read()) != -1) {
				System.out.write((byte) c);
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : translate((byte) c));
	}

	@Override
	public int read(byte[] buf, int offset, int count) throws IOException {
		int nread = super.read(buf, offset, count);
		int last = nread + offset;
		for (int i = offset; i < last; i++) {
			buf[i] = translate((byte) buf[i]);
		}
		return nread;
	}

	private byte translate(byte in) {
		if (from == in) {
			return to;
		} else {
			return in;
		}
	}

	public byte getFrom() {
		return from;
	}

	public void setFrom(byte from) {
		this.from = from;
	}

	public byte getTo() {
		return to;
	}

	public void setTo(byte to) {
		this.to = to;
	}
}
