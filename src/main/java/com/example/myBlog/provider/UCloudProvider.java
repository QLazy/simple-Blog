package com.example.myBlog.provider;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;

@Service
public class UCloudProvider {
	@Value("${ucloud.ufile.private-key}")
	private String privateKey;
	@Value("${ucloud.ufile.public-key}")
	private String publicKey;

	

	public String upload(InputStream fileStream, String fileType, String fileName) {
		
		String generateFile = "";
		String[] fileSplit = fileName.split("\\.");
		if (fileSplit.length > 1) {
			generateFile = UUID.randomUUID().toString() + "." + fileSplit[fileSplit.length - 1];
		}

		try {
			ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey, privateKey);
			ObjectConfig config = new ObjectConfig("cn-bj", "ufileos.com");
			PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
					.putObject(fileStream, fileType).nameAs(generateFile).toBucket("qlazy")
					/**
					 * 是否上传校验MD5, Default = true
					 */
					// .withVerifyMd5(false)
					/**
					 * 指定progress callback的间隔, Default = 每秒回调
					 */
					// .withProgressConfig(ProgressConfig.callbackWithPercent(10))
					/**
					 * 配置进度监听
					 */
					.setOnProgressListener(new OnProgressListener() {
						@Override
						public void onProgress(long bytesWritten, long contentLength) {

						}
					}).execute();
		} catch (UfileClientException e) {
			e.printStackTrace();
		} catch (UfileServerException e) {
			e.printStackTrace();
		}
		return generateFile;
	}
}
