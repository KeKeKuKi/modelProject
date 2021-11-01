package per.zzz.cloud.qiniu.utils;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import per.zzz.cloud.qiniu.common.QiNiuCommonResult;

import java.io.IOException;

/**
 * 七牛云上传工具
 *
 * @author TripleZ
 */
@Data
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuUploadUtils {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String outLink;

    private String getUpToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }

    private static Configuration getConfig() {
        Configuration cfg = new Configuration();
        // 设置请求协议为http
        cfg.useHttpsDomains = false;
        return cfg;
    }

    public QiNiuCommonResult QiNiuFileUpload(byte[] data, String fileName) {
        // 获取上传Key
        String upToken = getUpToken();
        // 获取配置
        UploadManager uploadManager = new UploadManager(getConfig());

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            Response response = uploadManager.put(data, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return QiNiuCommonResult.ofSuccess(outLink + "/" + putRet.key);
        } catch (IOException ex) {
            ex.printStackTrace();
            return QiNiuCommonResult.ofFail("上传失败");
        }
    }


}
