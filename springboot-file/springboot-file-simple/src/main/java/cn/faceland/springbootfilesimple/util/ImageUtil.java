package cn.faceland.springbootfilesimple.util;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import cn.faceland.springbootfilesimple.manager.FileManager;
import cn.faceland.springbootfilesimple.manager.impl.FileManagerImpl;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ImageUtil {
	/**
	 * 生成合并的图片
	 *
	 * @param imagePics
	 * @return
	 */
	public static String generateMergeImage(List<String> imagePics) {
		List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
		// 压缩图片所有的图片生成尺寸同意的 为 50x50

		if(imagePics.size()>9) {
			imagePics = imagePics.subList(0, 9);
		}

		int imageWidth = 40, interval = 6;
		if(imagePics.size() >= 1 && imagePics.size()<=4) {
			imageWidth = 60;
			interval = 6;
		}else if(imagePics.size() >= 5 && imagePics.size() <= 9) {
			imageWidth = 40;
			interval = 3;
		}

		for (int i = 0; i < imagePics.size(); i++) {
			bufferedImages
					.add(resize2(imagePics.get(i), imageWidth, imageWidth, true));
		}

		int width = 132; // 这是画板的宽高
		int height = 132; // 这是画板的高度

		// 9 每个图片 40 中间间隔4
		BufferedImage outImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 生成画布
		Graphics2D g = (Graphics2D) outImage.getGraphics();

		// 设置背景色
		g.setBackground(new Color(231, 231, 231));

		// 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
		g.clearRect(0, 0, width, height);

		// 开始拼凑 根据图片的数量判断该生成那种样式的组合头像目前为4中
		int j = 1;

		switch (bufferedImages.size()) {
			case 1:
				g.drawImage(bufferedImages.get(0), 36, 36, null);
				break;
			case 2:
				g.drawImage(bufferedImages.get(0), 4, 36, null);
				g.drawImage(bufferedImages.get(1), imageWidth+4*2, 36, null);
				break;
			case 3:
				g.drawImage(bufferedImages.get(0), 36, 4, null);
				g.drawImage(bufferedImages.get(1), 4, imageWidth+4*2, null);
				g.drawImage(bufferedImages.get(2), imageWidth+4*2, imageWidth+4*2, null);
				break;
			case 4:
				g.drawImage(bufferedImages.get(0), 4, 4, null);
				g.drawImage(bufferedImages.get(1), imageWidth+4*2, 4, null);
				g.drawImage(bufferedImages.get(2), 4, imageWidth+4*2, null);
				g.drawImage(bufferedImages.get(3), imageWidth+4*2, imageWidth+4*2, null);
				break;
			case 5:
				g.drawImage(bufferedImages.get(0), 17, 17, null);
				g.drawImage(bufferedImages.get(1), 17*2+imageWidth, 17, null);
				g.drawImage(bufferedImages.get(2), 3, imageWidth+17*2, null);
				g.drawImage(bufferedImages.get(3), 3*2+imageWidth, imageWidth+17*2, null);
				g.drawImage(bufferedImages.get(4), 3*3+imageWidth*2, imageWidth+17*2, null);
				break;
			case 6:
				g.drawImage(bufferedImages.get(0), 3, 17, null);
				g.drawImage(bufferedImages.get(1), imageWidth+3*2, 17, null);
				g.drawImage(bufferedImages.get(2), 3*3+imageWidth*2, 17, null);
				g.drawImage(bufferedImages.get(3), 3, imageWidth+17*2, null);
				g.drawImage(bufferedImages.get(4), imageWidth+3*2, imageWidth+17*2, null);
				g.drawImage(bufferedImages.get(5), 3*3+imageWidth*2, imageWidth+17*2, null);
				break;
			case 7:
				g.drawImage(bufferedImages.get(0), 46, 3, null);
				g.drawImage(bufferedImages.get(1), 3, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(2), 2*3+imageWidth, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(3), 3*3+imageWidth*2, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(4), 3, 3*3+imageWidth*2, null);
				g.drawImage(bufferedImages.get(5), 2*3+imageWidth, 3*3+imageWidth*2, null);
				g.drawImage(bufferedImages.get(6), 3*3+imageWidth*2, 3*3+imageWidth*2, null);
				break;
			case 8:
				g.drawImage(bufferedImages.get(0), 17, 3, null);
				g.drawImage(bufferedImages.get(1), 17*2+imageWidth, 3, null);
				g.drawImage(bufferedImages.get(2), 3, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(3), 2*3+imageWidth, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(4), 3*3+imageWidth*2, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(5), 3, 3*3+imageWidth*2, null);
				g.drawImage(bufferedImages.get(6), 2*3+imageWidth, 3*3+imageWidth*2, null);
				g.drawImage(bufferedImages.get(7), 3*3+imageWidth*2, 3*3+imageWidth*2, null);
				break;
			case 9:
				g.drawImage(bufferedImages.get(0), 3, 3, null);
				g.drawImage(bufferedImages.get(1), 3*2+imageWidth, 3, null);
				g.drawImage(bufferedImages.get(2), 3*3+imageWidth*2, 3, null);
				g.drawImage(bufferedImages.get(3), 3, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(4), 2*3+imageWidth, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(5), 3*3+imageWidth*2, 2*3+imageWidth, null);
				g.drawImage(bufferedImages.get(6), 3, 3*3+imageWidth*2, null);
				g.drawImage(bufferedImages.get(7), 2*3+imageWidth, 3*3+imageWidth*2, null);
				g.drawImage(bufferedImages.get(8), 3*3+imageWidth*2, 3*3+imageWidth*2, null);
				break;
			default:
				break;
		}


		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		String templatePath = "";
		String format = "JPG";
		try {
			ImageIO.write(outImage, format, baos);

			InputStream is = new ByteArrayInputStream(baos.toByteArray());

			//1：上传到服务器并获取图片地址
//			templatePath = FileUploadUtil.uploadImage(is,"imgName");

			//2：保存在本地，并获取图片地址
			FileManager fileManager = new FileManagerImpl();
			ResultBean rb = fileManager.saveFile(is,"test","testName.jpg");
			if(rb.getSuccess()){
				templatePath = (String)rb.getData();
			}

			//ImageIO.write(outImage, format, new File(outPath));
		} catch (IOException e) {
			LogUtil.fatalStackTrace(log, e);
		}

		return templatePath;
	}



	public static BufferedImage resize2(String filePath, int height, int width,
                                        boolean bb) {
		try {
			double ratio = 0; // 缩放比例
			File f = loadLogo(filePath);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height,
					Image.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue()
							/ bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(
						AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null)){
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				}else{
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				}
				g.dispose();
				itemp = image;
			}
			return (BufferedImage) itemp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	private static File loadLogo(String imageUrl) {
		File imageFile = null;
		//int mid = imageUrl.lastIndexOf(".");

		try {
			imageFile = new File("temp.jpg");
			//System.out.println("-----------------------------------------------------");
			//System.out.println("---imageUrl: "+imageUrl);
			//System.out.println("---url: "+ServerConfig.getSharePicPath()+"/"+System.currentTimeMillis()+".jpg");
			URL url = new URL(imageUrl);
			// 打开链接
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			//System.out.println("---imageUrl: "+imageUrl);
			conn.setHostnameVerifier(new NullHostnameVerifier());

			//HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			// 创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			outStream.close();
		}catch (Exception e) {
			LogUtil.fatalStackTrace(log, e);
		}

		return imageFile;
	}

	private static class NullHostnameVerifier implements javax.net.ssl.HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}