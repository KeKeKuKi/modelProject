package per.zzz.test.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import per.zzz.model.tencentOss.common.TencentOssCommonResult;
import per.zzz.model.tencentOss.config.TencentCosConfig;
import per.zzz.model.tencentOss.utils.CosFileUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

/**
 * date 2021/10/20 13:38
 *
 * @author 阿忠 2669918628@qq.com
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private CosFileUtils cosFileUtils;

    @PostMapping("/upload")
    public String upload(@RequestBody MultipartFile multipartFile) {
        try {
            TencentOssCommonResult tencentOssCommonResult = cosFileUtils.fileUpload(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getSize());
            return tencentOssCommonResult.getResultUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
