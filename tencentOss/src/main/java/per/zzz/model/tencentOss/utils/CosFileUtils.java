package per.zzz.model.tencentOss.utils;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import lombok.AllArgsConstructor;
import per.zzz.model.tencentOss.common.TencentOssCommonResult;
import per.zzz.model.tencentOss.config.TencentCosConfig;

import java.io.InputStream;

/**
 * date 2021/10/28 11:46
 *
 * @author 阿忠 2669918628@qq.com
 */
@AllArgsConstructor
public class CosFileUtils {

    private final TencentCosConfig tencentCosConfig;

    private final COSClient cosClient;

    public TencentOssCommonResult fileUpload(InputStream inputStream, String fileName, Long size) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        fileName = System.currentTimeMillis() + fileName;
        PutObjectResult putObjectResult = cosClient.putObject(tencentCosConfig.getBucket(), fileName, inputStream, objectMetadata);
        String etag = putObjectResult.getETag();
        return TencentOssCommonResult.ofSuccess(etag);
    }
}
