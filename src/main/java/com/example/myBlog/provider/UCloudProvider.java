package com.example.myBlog.provider;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;

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

	@Value("${ucloud.ufile.bucket-name}")
	private String bucketName;

	@Value("${ucloud.ufile.region}")
	private String region;

	@Value("${ucloud.ufile.proxy-suffix}")
	private String proxySuffix;

	@Value("${ucloud.ufile.expires-duration}")
	private Integer expiresDurcation;

	public String upload(InputStream fileStream, String fileType, String fileName) {

		String generateFile = "";
		String[] fileSplit = fileName.split("\\.");
		if (fileSplit.length > 1) {
			generateFile = UUID.randomUUID().toString() + "." + fileSplit[fileSplit.length - 1];
		} else {
			throw new CustomizeExcuption(CustomizeErrorCode.FILE_UPLOAD_FAIL);
		}

		try {
			ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey, privateKey);
			ObjectConfig config = new ObjectConfig(region, proxySuffix);
			PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
					.putObject(fileStream, fileType).nameAs(generateFile).toBucket(bucketName)
					.setOnProgressListener(new OnProgressListener() {
						@Override
						public void onProgress(long bytesWritten, long contentLength) {

						}
					}).execute();
			if (response != null && response.getRetCode() == 0) {
				// 设置过期时间为10年
				String url = UfileClient.object(objectAuthorization, config)
						.getDownloadUrlFromPrivateBucket(generateFile, bucketName, expiresDurcation).createUrl();
				return url;
			} else {
				throw new CustomizeExcuption(CustomizeErrorCode.FILE_UPLOAD_FAIL);
			}
		} catch (UfileClientException e) {
			e.printStackTrace();
			throw new CustomizeExcuption(CustomizeErrorCode.FILE_UPLOAD_FAIL);
		} catch (UfileServerException e) {
			e.printStackTrace();
			throw new CustomizeExcuption(CustomizeErrorCode.FILE_UPLOAD_FAIL);
		}
	}
}
