import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		byte[] buffer;
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("pictures/bitmap.bmp");
			long size = fis.getChannel().size();
			buffer = new byte[(int) size];
			int j = fis.read(buffer);
			
			for(int i = 138; i < j; i++) {
				System.out.print(buffer[i] + " : ");
				if(buffer[i] <= 0) {
					buffer[i] = (byte) (-buffer[i] - 127);
				}else {
					buffer[i] = (byte) (buffer[i] + 127);
				}
				System.out.println(buffer[i]);
			}
			
			exportPicture(buffer);
			
		}catch(FileNotFoundException fnfe) {
			System.out.println("File not found");
		}catch(IOException ioe) {
			System.out.println("Unable to read the file");
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	public static void exportPicture(byte[] buffer) {
		FileOutputStream fos = null;
		
		try{
			fos = new FileOutputStream("pictures/modified.bmp");
			fos.write(buffer);
			
			System.out.println("Export done");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
